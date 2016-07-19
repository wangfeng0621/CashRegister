package Presenter;

import Model.CommodityRepertory;
import Model.PrintBillDetails.PrivilegeThreeforTwo;
import Model.PrintBillDetails.ShoppingListAll;
import Model.PrintBillDetails.SumOfBill;
import Model.ShoppingList;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by feng on 2016/7/17.
 */
public class CalculatePrice {

    public void calculatePrice() {
        Iterator entries = ShoppingList.shoppinglist.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String barcode = (String) entry.getKey();

            CommodityRepertory.CommodityInfo ci = CommodityRepertory.commodityInfomap.get(barcode);
            if(ci.privilege.equals("无"))
                notPrivilCalcu(ci,barcode);
            if(ci.privilege.equals("买二赠一"))
                threeForTwoCalcu(ci,barcode);

        }
    }


    public int  barcodeParse(String code) {
        String[] s = code.split("-");
        String barcode = s[0];
        int count = Integer.valueOf(s[1])* ShoppingList.shoppinglist.get(code);
        return count;
    }

    public void notPrivilCalcu(CommodityRepertory.CommodityInfo ci, String barcode) {

            int count=0;
        count = barcode.contains("-") ? barcodeParse(barcode) : ShoppingList.shoppinglist.get(barcode);
            ShoppingListAll.Shopping ss = new ShoppingListAll.Shopping();
            ss.subtotal = ci.price * count;
            ss.name = ci.name;
            ss.unit = ci.unit;
            ss.count = count;
            ss.price = ci.price;
            ShoppingListAll.shoppingArr.add(ss);

            SumOfBill.total += ss.subtotal;
    }

    private void threeForTwoCalcu(CommodityRepertory.CommodityInfo ci, String barcode) {

        ShoppingListAll.Shopping ss = new ShoppingListAll.Shopping();
        PrivilegeThreeforTwo.ThreeforTwo pt = new PrivilegeThreeforTwo.ThreeforTwo();
        int count=0;
        count = barcode.contains("-") ? barcodeParse(barcode) : ShoppingList.shoppinglist.get(barcode);
        int number = count-count/3;
        ss.subtotal = ci.price * number;
        ss.name = ci.name;
        ss.unit = ci.unit;
        ss.count = count;
        ss.price = ci.price;
        ShoppingListAll.shoppingArr.add(ss);

        pt.name = ci.name;
        pt.count = count;
        pt.unit = ci.unit;

        SumOfBill.total += ss.subtotal;
        SumOfBill.privilege += ss.subtotal - ci.price * count/3;
    }

}
