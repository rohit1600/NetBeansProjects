package edu.cmu.heinz.ds.pit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import javax.naming.*;


/*
 * PITplayer 0, 1, and 2 share identical code, except for three places: - the
 * Queue that is being listened to (e.g. jms/PITplayer0) - the name of the class
 * (e.g. PITplayer0) - the value of myPlayerNumber
 *
 * Therefore as you change the code in one, make sure to reflect those changes
 * in all 3. In your final submission, all 3 PITplayers should have identical
 * code except for in these three places.
 */
@MessageDriven(mappedName = "jms/PITplayer2", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PITplayer2 implements MessageListener {

    // Each PITplayer has a unique myPlayerNumber.  It should be the same as the Queue listend to.
    private final int myPlayerNumber = 2;
    // The following are all static for they are shared between multiple instances of this class
    // (of which there could be multiple).
    // Cards is this player's set of cards.  
    private final static ArrayList cards = new ArrayList();
    // tradeCount counts trades
    private static int tradeCount = 0;
    private static int marker_sender1 = 3;
    private static int marker_sender2 = 3;
    private static int markers_sent = 0;
    private static boolean marker_recd = false;
    // maxTrades is the maximum number of trades, after which trading is stopped.
    private final static int maxTrades = 1000;
    // numPlayers are the number of Players trading.  This comes with a NewHand from the PITinit servlet
    private static int numPlayers = 0;
    // The snapshot servlet (PITsnapshot) is expecting to be passed an ObjectMessage
    // where the object is a HashMap. Therefore this definition of that HashMap is 
    // provided although it is not currently used (it is for you to use).
    // Also included below is a utility method that will turn a HashMap into a string
    // which is useful for printing diagnostic messages to the console.
    private static HashMap<String, Integer> state = new HashMap<String, Integer>();

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                Object o = ((ObjectMessage) message).getObject();

                // There are 4 types of messages:  Reset, NewHand, Trade, and Marker

                // Reset the Player.  The Reset message is generated by the PITinit servlet
                if (o instanceof Reset) {
                    System.out.println("PITplayer" + myPlayerNumber + " received reset");
                    // Drop all cards in hand
                    cards.clear();
                    tradeCount = 0;
                    numPlayers = 0;
                    // Reply to the PITinit servlet acknowledging the Reset
                    sendToQueue("jms/PITmonitor", (Reset) o);
                    return;

                    // NewHand received from PITinit
                } else if (o instanceof Marker) {
                    Marker marker = (Marker) o;
                    if (marker_sender1 == 3) {
                        Marker m = new Marker(2);
                        if (marker.source == -1) {
                            marker_recd = true;
                            for (int i = 0; i < cards.size(); i++) {
                                String name = cards.get(i).toString();
                                if (state.get(name) == null) {
                                    state.put(name, 1);
                                } else {
                                    state.put(name, (state.get(name) + 1));
                                }
                            }
                            sendToQueue("jms/PITplayer0", m);
                            sendToQueue("jms/PITplayer1", m);
                            markers_sent = 1;
                        } else {
                            marker_sender1 = marker.source;
                            marker_recd = true;
                            if (markers_sent != 1) {
                                for (int i = 0; i < cards.size(); i++) {
                                    String name = cards.get(i).toString();
                                    if (state.get(name) == null) {
                                        state.put(name, 1);
                                    } else {
                                        state.put(name, (state.get(name) + 1));
                                    }
                                }
                                sendToQueue("jms/PITplayer0", m);
                                sendToQueue("jms/PITplayer1", m);
                                markers_sent = 1;
                            }

                        }
                        
                    } else if (marker_sender1 != 3 && marker_sender2 == 3) {
                        marker_sender2 = marker.source;
                        System.out.println("In Player2, got second marker from " + marker.source);
                        sendSnapShot(state);
                        return;
                    }

                } else if (o instanceof NewHand) {
                    // Add new hand cards.  
                    // It is actually possible that a trade had added a card already.
                    cards.addAll(((NewHand) o).handCard);
                    numPlayers = ((NewHand) o).numPlayers;
                    System.out.println("PITplayer" + myPlayerNumber + " new hand: " + toString(cards));

                    // Receive a Trade from another Player
                } else if (o instanceof Trade) {
                    // Receiving a Trade card from another Player, add it to my hand of cards
                    Trade trade = (Trade) o;
                    cards.add(trade.tradeCard);
                    if (marker_recd == true && marker_sender1 != trade.sourcePlayer) {
                       // if (state.get(trade.tradeCard) == null) {
                         //   state.put(trade.tradeCard, 1);
                        //} else {
                            state.put(trade.tradeCard, (state.get(trade.tradeCard) + 1));
                        //}

                    }
                    System.out.println("PITplayer" + myPlayerNumber + " received: " + trade.tradeCard + " from player: " + trade.sourcePlayer);
                    System.out.println("PITplayer" + myPlayerNumber + " hand: " + toString(cards));
                } else {
                    System.out.println("PITplayer" + myPlayerNumber + " received unknown Message type");
                    // just ignore it
                    return;
                }
            }

            // if hit maxTrades limit, then stop sending trades
            if (maxTrades(maxTrades)) {
                return;
            }

            /*
             * If numPlayers == 0, while we have received a Trade, we have not
             * received our NewHand yet, so we don't know how many players there
             * are. Therefore, don't send out a Trade at this time.
             *
             */
            if (numPlayers == 0) {
                return;
            }

            // Create a new Trade from my set of cards, and send to another player
            Trade newTrade = new Trade();
            newTrade.sourcePlayer = myPlayerNumber;
            newTrade.tradeCard = (String) cards.remove(0);

            // Find a random player to trade to (not including myself)
            int sendTo = Math.round((float) Math.random() * (numPlayers - 1));
            while (sendTo == myPlayerNumber) {
                sendTo = Math.round((float) Math.random() * (numPlayers - 1));
            }

            //Send the card to the other player
            System.out.println("PITplayer" + myPlayerNumber + " sending: " + newTrade.tradeCard + " to player: " + sendTo);
            String sendToJNDI = "jms/PITplayer" + sendTo;
            sendToQueue(sendToJNDI, newTrade);

        } catch (JMSException e) {
            System.out.println("JMS Exception thrown" + e);
        } catch (Throwable e) {
            System.out.println("Throwable thrown in PITplayer" + myPlayerNumber + ": " + e);
        }
    }

    // Create a string of hand size and all cards for use in printing
    private String toString(ArrayList hand) {

        String cardsString = "size: " + hand.size() + " ";
        for (int i = 0; i < hand.size(); i++) {
            cardsString += hand.get(i) + " ";
        }
        return cardsString;
    }

    // Create a printable version of the "state".
    private String toString(HashMap<String, Integer> state) {
        String stateString = "";
        for (Iterator it = state.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            String commodity = (String) entry.getKey();
            int number = ((Integer) entry.getValue()).intValue();
            stateString += "{" + commodity + ":" + number + "} ";
        }
        return stateString;
    }

    // Send an object to a Queue, given its JNDI name
    private void sendToQueue(String queueJNDI, Serializable message) throws Exception {
        // Gather necessary JMS resources
        Context ctx = new InitialContext();
        Connection con = ((ConnectionFactory) ctx.lookup("jms/myConnectionFactory")).createConnection();
        Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue q = (Queue) ctx.lookup(queueJNDI);
        MessageProducer writer = session.createProducer(q);
        ObjectMessage msg = session.createObjectMessage(message);
        // Send the object to the Queue
        writer.send(msg);
        /*
         * if (queueJNDI.equals("jms/PITsnapshot")) { state.clear();
         * //marker_sender1 = 3; //markers_sent = 0; //marker_recd = false;
        }
         */
        session.close();
        con.close();
        ctx.close();
    }

    // stop trading when hit maxTrades
    private boolean maxTrades(int max) {
        // stop trading after some number of trades
        if ((tradeCount % 100) == 0) {
            System.out.println("PITplayer" + myPlayerNumber + " tradeCount: " + tradeCount);
        }
        return (tradeCount++ < max) ? false : true;
    }

    private void sendSnapShot(HashMap<String, Integer> state) throws Exception {
        System.out.println("SNAPSHOT IN PLAYER 2 IS " + state.toString());
        String sendToJNDI = "jms/PITsnapshot";
        sendToQueue(sendToJNDI, state);
        //state = null;
        state.put("rice", 0);
        state.put("gold", 0);
        state.put("oil", 0);
        marker_sender1 = 3;
        marker_sender2 = 3;
        marker_recd = false;
        markers_sent = 0;
    }
}