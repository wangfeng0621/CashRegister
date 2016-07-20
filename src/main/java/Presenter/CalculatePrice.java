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

            CommodityRepertory.CommodityInfo commodityInfo = CommodityRepertory.commodityInfomap.get(barcode);
            if(commodityInfo.privilege.equals("null")) {
                notPrivilegeCalculate(commodityInfo,barcode);
            }
            else if(commodityInfo.privilege.contains("ThreeForTwo")) {
                threeForTwoCalculate(commodityInfo,barcode);
            }
            else if(commodityInfo.privilege.equals("0.95")){
                sale95Calculate(commodityInfo,barcode);
            }


        }
    }

    private void sale95Calculate(CommodityRepertory.CommodityInfo ci, String barcode) {

        int count = ShoppingList.shoppinglist.get(barcode);
        Double subtotal = ci.price * count*0.95;
        Double privilege = ci.price * count*0.05;

        printShoppingListAll(ci,count,subtotal,privilege);

        printSumOfBill(subtotal,privilege);

    }


    public void notPrivilegeCalculate(CommodityRepertory.CommodityInfo ci, String barcode) {

        Double privilege = 0.0;
        int count = ShoppingList.shoppinglist.get(barcode);
        Double subtotal = ci.price * count;

        printShoppingListAll(ci,count,subtotal,privilege);

        printSumOfBill(subtotal,privilege);
    }

    private void threeForTwoCalculate(CommodityRepertory.CommodityInfo ci, String barcode) {

        int count = ShoppingList.shoppinglist.get(barcode);
        int number = count-count/3;
        Double subtotal = ci.price * number;
        Double privilege = subtotal - ci.price * (count/3);

        printShoppingListAll(ci,count,subtotal,privilege);

        printThreeForTwo(ci,count/3);

        printSumOfBill(subtotal,privilege);

    }

    private void printSumOfBill(Double subtotal, Double privilege) {

        SumOfBill.total += subtotal;
        SumOfBill.privilege += privilege;
    }

    private void printThreeForTwo(CommodityRepertory.CommodityInfo ci, int count) {

        PrivilegeThreeforTwo.ThreeForTwo pt = new PrivilegeThreeforTwo.ThreeForTwo();
        pt.name = ci.name;
        pt.count = count;
        pt.unit = ci.unit;
        if(pt.count > 0 ) {
            PrivilegeThreeforTwo.threeForTwoArr.add(pt);
        }
    }

    public void printShoppingListAll(CommodityRepertory.CommodityInfo ci,int count ,Double subtotal ,Double privil) {

        ShoppingListAll.Shopping shopping = new ShoppingListAll.Shopping();
        shopping.subtotal = subtotal;
        shopping.name = ci.name;
        shopping.unit = ci.unit;
        shopping.count = count;
        shopping.price = ci.price;
        shopping.sale95 = privil;
        ShoppingListAll.shoppingArr.add(shopping);

    }
}
