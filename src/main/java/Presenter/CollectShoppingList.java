package Presenter;

import Model.ShoppingList;

/**
 * Created by feng on 2016/7/17.
 */
public class CollectShoppingList {
    public void inputBarcode(String barcode) {
        if(ShoppingList.shoppinglist.get(barcode) != null)
        {
            ShoppingList.shoppinglist.put(barcode,ShoppingList.shoppinglist.get(barcode) +1);
        }
        else{
            ShoppingList.shoppinglist.put(barcode,1);
        }
    }
}
