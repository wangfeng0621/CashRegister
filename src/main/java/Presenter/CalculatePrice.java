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
            if(ci.privilege.contains("买二赠一"))
                threeForTwoCalcu(ci,barcode);
            if(ci.privilege.equals("0.95"))
                sale95Calcu(ci,barcode);

        }
    }

    private void sale95Calcu(CommodityRepertory.CommodityInfo ci, String barcode) {

        int count=0; Double subtotal =0.0;Double privilege = 0.0;
        count = barcode.contains("-") ? barcodeParse(barcode) : ShoppingList.shoppinglist.get(barcode);

        subtotal = ci.price * count*0.95;
        privilege = ci.price * count*0.05;

        printShoppingListAll(ci,count,subtotal,privilege);

        printSumOfBill(subtotal,privilege);

    }


    public int  barcodeParse(String code) {
        String[] s = code.split("-");
        String barcode = s[0];
        int count = Integer.valueOf(s[1])* ShoppingList.shoppinglist.get(code);
        return count;
    }

    public void notPrivilCalcu(CommodityRepertory.CommodityInfo ci, String barcode) {

        int count=0; Double subtotal =0.0;Double privilege = 0.0;

        count = barcode.contains("-") ? barcodeParse(barcode) : ShoppingList.shoppinglist.get(barcode);

        subtotal = ci.price * count;

        printShoppingListAll(ci,count,subtotal,privilege);

        printSumOfBill(subtotal,privilege);
    }

    private void threeForTwoCalcu(CommodityRepertory.CommodityInfo ci, String barcode) {

        int count=0;  int number=0; Double privilege=0.0;  Double subtotal=0.0;

        count = barcode.contains("-") ? barcodeParse(barcode) : ShoppingList.shoppinglist.get(barcode);

        number = count-count/3;
        subtotal = ci.price * number;
        privilege = subtotal - ci.price * (count/3);

        printShoppingListAll(ci,count,subtotal,privilege);

        printThreeforTwo(ci,count);

        printSumOfBill(subtotal,privilege);

    }

    private void printSumOfBill(Double subtotal, Double privil) {

        SumOfBill.total += subtotal;
        SumOfBill.privilege += privil;
    }

    private void printThreeforTwo(CommodityRepertory.CommodityInfo ci, int count) {

        PrivilegeThreeforTwo.ThreeforTwo pt = new PrivilegeThreeforTwo.ThreeforTwo();
        pt.name = ci.name;
        pt.count = count;
        pt.unit = ci.unit;
        PrivilegeThreeforTwo.threeforTwoArr.add(pt);
    }

    public void printShoppingListAll(CommodityRepertory.CommodityInfo ci,int count ,Double subtotal ,Double privil) {

        ShoppingListAll.Shopping ss = new ShoppingListAll.Shopping();
        ss.subtotal = subtotal;
        ss.name = ci.name;
        ss.unit = ci.unit;
        ss.count = count;
        ss.price = ci.price;
        ss.sale95 = privil;
        ShoppingListAll.shoppingArr.add(ss);

    }
}
