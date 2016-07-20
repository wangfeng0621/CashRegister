package Presenter;

import Model.PrintBillDetails.PrivilegeThreeForTwo;
import Model.PrintBillDetails.ShoppingListAll;
import Model.PrintBillDetails.SumOfBill;

/**
 * Created by feng on 2016/7/17.
 */
public class OutPutBill {

    ShoppingListAll shoppingList = new ShoppingListAll();
    PrivilegeThreeForTwo threeforTwo = new PrivilegeThreeForTwo();
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

    public boolean printThreeForTwo() {
        int len = threeforTwo.threeForTwoArr.size();
        if( len > 0 ) {
            printThreeForTwo(len);
            return true;
        }
        return false;
    }

    private void printThreeForTwo(int len) {
        System.out.println("买二赠一商品：");
        for(int i = 0; i < len; i++) {
            PrivilegeThreeForTwo.ThreeForTwo oneThreeForTwoRecord = threeforTwo.threeForTwoArr.get(i);
            printOneThreeforTwoRecord(oneThreeForTwoRecord);
        }
        System.out.println("----------------------");
    }

    private void printOneThreeforTwoRecord(PrivilegeThreeForTwo.ThreeForTwo oneThreeForTwoRecord) {
        System.out.print("名称："+ oneThreeForTwoRecord.name+"，");
        System.out.println("数量："+ oneThreeForTwoRecord.count+ oneThreeForTwoRecord.unit);
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
        printThreeForTwo();
        printSumOfBill();
        return true;
    }

}
