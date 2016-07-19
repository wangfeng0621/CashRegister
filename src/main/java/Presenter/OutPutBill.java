package Presenter;

import Model.PrintBillDetails.PrivilegeThreeforTwo;
import Model.PrintBillDetails.ShoppingListAll;
import Model.PrintBillDetails.SumOfBill;

/**
 * Created by feng on 2016/7/17.
 */
public class OutPutBill {

    ShoppingListAll shoppingList = new ShoppingListAll();
    PrivilegeThreeforTwo threeforTwo = new PrivilegeThreeforTwo();
    SumOfBill bill = new SumOfBill();
    public boolean printShoppingList() {
        int len = shoppingList.shoppingArr.size();
        if(len > 0) {
            printShoppingList(len);
            return true;
        }
        return false;
    }

    private void printShoppingList(int len) {
        System.out.println("```");
        System.out.println("***<没钱赚商店>购物清单***");
        for(int i = 0; i < len; i++) {
            ShoppingListAll.Shopping oneShoppingRecord = shoppingList.shoppingArr.get(i);
            printOneShoppingRecord(oneShoppingRecord);
        }
        System.out.println("----------------------");
    }

    private void printOneShoppingRecord(ShoppingListAll.Shopping oneShoppingRecord) {
        System.out.print("名称："+oneShoppingRecord.name+"，");
        System.out.print("数量："+oneShoppingRecord.count+oneShoppingRecord.unit+"，");
        System.out.print("单价："+String.format("%.2f", oneShoppingRecord.price)+"(元)，");
        System.out.print("小计："+String.format("%.2f", oneShoppingRecord.subtotal)+"(元)");
        if( oneShoppingRecord.sale95 != 0.0 ) {
            System.out.println(",节省："+String.format("%.2f", oneShoppingRecord.sale95)+"(元)");
        }
        else  {
            System.out.println();
        }
    }

    public boolean printThreeforTwo() {
        int len = threeforTwo.threeforTwoArr.size();
        if( len > 0 ) {
            printThreeforTwo(len);
            return true;
        }
        return false;
    }

    private void printThreeforTwo(int len) {
        System.out.println("买二赠一商品：");
        for(int i = 0; i < len; i++) {
            PrivilegeThreeforTwo.ThreeforTwo oneThreeforTwoRecord = threeforTwo.threeforTwoArr.get(i);
            printOneThreeforTwoRecord(oneThreeforTwoRecord);
        }
        System.out.println("----------------------");
    }

    private void printOneThreeforTwoRecord(PrivilegeThreeforTwo.ThreeforTwo oneThreeforTwoRecord) {
        System.out.print("名称："+oneThreeforTwoRecord.name+"，");
        System.out.println("数量："+oneThreeforTwoRecord.count+oneThreeforTwoRecord.unit);
    }

    public boolean printSumOfBill() {
        System.out.println("总计："+String.format("%.2f",bill.total)+"(元)");
        System.out.println( bill.privilege > 0.0 ? "节省："+String.format("%.2f",bill.privilege)+"(元)": "");
        System.out.println("**********************");
        System.out.println("```");
        return true;
    }

    public boolean printFullBill() {
        printShoppingList();
        printThreeforTwo();
        printSumOfBill();
        return true;
    }

}
