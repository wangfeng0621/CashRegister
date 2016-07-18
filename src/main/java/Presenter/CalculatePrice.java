package Presenter;

import Model.CommodityRepertory;
import Model.CommodityRepertory.CommodityInfo;
import Model.PrintBillDetails.SumOfBill;
import Model.ShoppingList;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by feng on 2016/7/17.
 */
public class CalculatePrice {
    public void notPrivilegeCalcu() {

        Iterator entries = ShoppingList.shoppinglist.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            CommodityRepertory.CommodityInfo ci = CommodityRepertory.commodityInfomap.get((String) entry.getKey());
            SumOfBill.total += ci.price*ShoppingList.shoppinglist.get((String) entry.getKey());
        }
    }
}
