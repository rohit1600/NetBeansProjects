
import edu.cmu.andrew.rohitraj.MyCoolClass;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class MyCool_Skeleton {

    MyCoolClass mcc;

    public MyCool_Skeleton(MyCoolClass p) {

        mcc = p;
    }

    public void serve() {
        try {
            ServerSocket s = new ServerSocket(9000);
            while (true) {
                Socket socket = s.accept();
                System.out.println("We have a visitor");
                ObjectInputStream i = new ObjectInputStream(socket.getInputStream());
                String method = (String) i.readObject();
                if (method.equals("get")) {
                    String name = (String) i.readObject();
                    System.out.println("They want information on" + name);
                    String result = mcc.getDevice(name);
                    System.out.println("Sending back" + result);
                    ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
                    o.writeObject(result);
                    o.flush();
                }
                else if(method.equals("set")){
                    String name = (String)i.readObject();
                    String maker = (String)i.readObject();
                    mcc.setDevice(name, maker);
                }

            }
        } catch (Throwable t) {
            System.out.println("Error " + t);
            System.exit(0);
        }
    }
}