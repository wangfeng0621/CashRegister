package Presenter;

import Model.ShoppingList;

/**
 * Created by feng on 2016/7/17.
 */
public class CollectShoppingList {


    public void inputBarcode(String barcode) {

        ShoppingList.shoppinglist.put(barcode, ShoppingList.shoppinglist.get(barcode) != null ? ShoppingList.shoppinglist.get(barcode) + 1 : 1);
    }


    public String delete(String barcode) {

        if(ShoppingList.shoppinglist.get(barcode) == null)
        {
            return "not exist barcode in shoppinglist";
        }

        ShoppingList.shoppinglist.put(barcode,ShoppingList.shoppinglist.get(barcode) -1);

        if(ShoppingList.shoppinglist.get(barcode) ==0)
            ShoppingList.shoppinglist.remove(barcode);

        return "delete successful";
    }

    public String modifyCount(String barcode , int value) {

        if(ShoppingList.shoppinglist.get(barcode) == null)
            return "not exist barcode in shoppinglist";

        ShoppingList.shoppinglist.put(barcode,value );

        return "modified successful";
    }
}
