package Model.PrintBillDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by feng on 2016/7/17.
 */
public class ShoppingListAll {

    public static ArrayList<Shopping> shoppingArr =new  ArrayList<Shopping>();

    public static class Shopping {
        public String name;
        public String unit;
        public int count;
        public double price;
        public double subtotal;
        public double sale95;
    }


}
