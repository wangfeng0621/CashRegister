package Model.PrintBillDetails;

import java.util.AbstractList;

/**
 * Created by feng on 2016/7/17.
 */
public class ShoppingListAll {

    public static AbstractList<Shopping> shoppingArr;

    public static class Shopping {
        public String name;
        public String unit;
        public double price;
        public double subtotal;
        public double sale95;
    }


}
