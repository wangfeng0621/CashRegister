package Presenter;

import Model.CommodityRepertory;
import Model.ShoppingList;

/**
 * Created by feng on 2016/7/17.
 */
public class CollectShoppingList {


    public String inputBarcode(String barcode) {

        if(CommodityRepertory.commodityInfomap.get(barcode) == null)
            return "purchase failed";
        ShoppingList.shoppinglist.put(barcode, ShoppingList.shoppinglist.get(barcode) != null ? ShoppingList.shoppinglist.get(barcode) + 1 : 1);
        return "purchase successful";
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
