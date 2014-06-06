package developerworks.ajax.store;

import java.math.BigDecimal;
import java.util.*;

/**
 * A very simple shopping Cart
 */
public class Cart {

    private HashMap<Item, Integer> contents;

    /**
     * Creates a new Cart instance
     */
    public Cart() {
        contents = new HashMap<Item, Integer>();
    }

    /**
     * Adds a named item to the cart
     *
     * @param itemName The name of the item to add to the cart
     */
    public void addItem(String itemCode) {

        Catalog catalog = new Catalog();

        if (catalog.containsItem(itemCode)) {
            Item item = catalog.getItem(itemCode);

            int newQuantity = 1;
            if (contents.containsKey(item)) {
                Integer currentQuantity = contents.get(item);
                newQuantity += currentQuantity.intValue();
            }

            contents.put(item, new Integer(newQuantity));
        }
    }

    /**
     * Removes the named item from the cart
     *
     * @param itemName Name of item to remove
     */
    public void removeItem(String itemCode) {

        Catalog catalog = new Catalog();

        if (catalog.containsItem(itemCode)) {
            Item item = catalog.getItem(itemCode);

            int newQuantity = 0;
            if (contents.containsKey(item)) {
                if (contents.get(item) > 1) {
                    Integer currentQuantity = contents.get(item);
                    newQuantity = (currentQuantity.intValue()) - 1;
                } else {
                    System.out.println("nonsense is here");
                    contents.remove(item);
                }
                contents.put(item, new Integer(newQuantity));
            }


        }
    }

    public void removeItems(String itemCode) {

        contents.remove(new Catalog().getItem(itemCode));
    }

    /**
     * @return XML representation of cart contents
     */
    public String toJSON() {
        StringBuffer json = new StringBuffer();
        //json.append("<?xml version=\"1.0\"?>\n");
        json.append("{ \n");
        json.append("\"cart\":{ \"-generated\":\"" + System.currentTimeMillis() + "\", \"-total\":\""+getCartTotal() + "\",\n" );
        json.append("\"item\":[ \n");
        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            int itemQuantity = contents.get(item).intValue();


            json.append("{ ");
            json.append("\"-code\":\"" + item.getCode() + "\",\n");
            json.append("\"name\":\"" + item.getName() + "\",\n");
            json.append("\"quantity\":\"" + itemQuantity + "\"\n");
            json.append("},");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]\n");
        json.append("}\n");
        json.append("}\n");
        System.out.println(json.toString());
        return json.toString();
    }

    private String getCartTotal() {
        int total = 0;

        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            int itemQuantity = contents.get(item).intValue();

            total += (item.getPrice() * itemQuantity);
        }

        return "$" + new BigDecimal(total).movePointLeft(2);
    }
}
