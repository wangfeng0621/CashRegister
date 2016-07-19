package Presenter;

import Model.CommodityRepertory;
import Model.ShoppingList;

/**
 * Created by feng on 2016/7/17.
 */
public class CollectShoppingList {

    public String STATUS;

    public void inputBarcode(String barcode) {

        if(CommodityRepertory.commodityInfomap.get(barcode) == null)
        {
            STATUS =  "purchase failed";
            return;
        }
        ShoppingList.shoppinglist.put(barcode, ShoppingList.shoppinglist.get(barcode) != null ? ShoppingList.shoppinglist.get(barcode) + 1 : 1);
        STATUS = "purchase successful";
    }


    public void delete(String barcode) {

        if(ShoppingList.shoppinglist.get(barcode) == null)
        {
            STATUS = "not exist barcode in shoppinglist";
            return;
        }

        ShoppingList.shoppinglist.put(barcode,ShoppingList.shoppinglist.get(barcode) -1);

        if(ShoppingList.shoppinglist.get(barcode) ==0)
            ShoppingList.shoppinglist.remove(barcode);

        STATUS = "delete successful";
    }

    public void modifyCount(String barcode , int value) {

        if(ShoppingList.shoppinglist.get(barcode) == null)
        {
            STATUS = "not exist barcode in shoppinglist";
            return;
        }
        ShoppingList.shoppinglist.put(barcode,value );

        STATUS = "modified successful";
    }
}
