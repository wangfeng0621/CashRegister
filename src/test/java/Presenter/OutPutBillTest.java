package Presenter;

import Model.PrintBillDetails.PrivilegeThreeforTwo;
import Model.PrintBillDetails.ShoppingListAll;
import Model.PrintBillDetails.SumOfBill;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by feng on 2016/7/19.
 */
public class OutPutBillTest {

    ShoppingListAll shoppingList = new ShoppingListAll();
    PrivilegeThreeforTwo threeForTwo = new PrivilegeThreeforTwo();
    SumOfBill bill = new SumOfBill();
    OutPutBill printBill = new OutPutBill();

    //为了测试输入的购物清单信息
    private ShoppingListAll.Shopping addOneBillingRecord(String name, int count, String unit, double price, double subtotal, double sale95) {

        ShoppingListAll.Shopping oneBillingRecord = new ShoppingListAll.Shopping();
        oneBillingRecord.name = name ;
        oneBillingRecord.count = count ;
        oneBillingRecord.price = price ;
        oneBillingRecord.unit = unit ;
        oneBillingRecord.subtotal = subtotal ;
        oneBillingRecord.sale95 = sale95 ;
        return oneBillingRecord ;

    }

    //为了测试输入的打折信息
    private PrivilegeThreeforTwo.ThreeForTwo addOneThreeForTwoRecord(String name, int count, String unit) {
        PrivilegeThreeforTwo.ThreeForTwo oneThreeForTwoRecord = new PrivilegeThreeforTwo.ThreeForTwo();
        oneThreeForTwoRecord.name = name;
        oneThreeForTwoRecord.count = count;
        oneThreeForTwoRecord.unit = unit;
        return oneThreeForTwoRecord;
    }

    @After
    //清除测试产生的底层数据
    public void reset_bill_data() {
        ResetBillEmpty reset = new ResetBillEmpty();
        reset.resetAll();
    }

    @Test
    //购物清单当中有买二赠一的商品
    public void should_print_shopping_list_when_Goods_have_commodity_of_three_for_two() {
        //Given
        shoppingList.shoppingArr.add(addOneBillingRecord("可口可乐",3,"瓶",3.00,6.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("羽毛球",5,"个",1.00,4.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("苹果",2,"斤",5.50,11.00,0.0));
        threeForTwo.threeForTwoArr.add(addOneThreeForTwoRecord("可口可乐",1,"瓶"));
        threeForTwo.threeForTwoArr.add(addOneThreeForTwoRecord("羽毛球",1,"个"));
        bill.total = 21.00;
        bill.privilege = 4.00;

        //When
        boolean shoppingListStatus = printBill.printShoppingList();
        boolean threeForTwoStatus = printBill.printThreeForTwo();
        boolean billSum = printBill.printSumOfBill();

        //Then
        assertThat(shoppingListStatus, is(true));
        assertThat(threeForTwoStatus, is(true));
        assertThat(billSum, is(true));
    }

    @Test
    //购物清单中既含有买二赠一的商品，也含有95折的商品
    public void should_print_shopping_list_when_Goods_have_commodity_of_three_for_two_and_sale95() {
        //Given
        shoppingList.shoppingArr.add(addOneBillingRecord("可口可乐",3,"瓶",3.00,6.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("羽毛球",5,"个",1.00,4.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("苹果",2,"斤",5.50,10.45,0.55));
        threeForTwo.threeForTwoArr.add(addOneThreeForTwoRecord("可口可乐",1,"瓶"));
        threeForTwo.threeForTwoArr.add(addOneThreeForTwoRecord("羽毛球",1,"个"));
        bill.total = 20.45;
        bill.privilege = 4.55;

        //When
        boolean shoppingListStatus = printBill.printShoppingList();
        boolean threeForTwoStatus = printBill.printThreeForTwo();
        boolean billSum = printBill.printSumOfBill();

        //Then
        assertThat(shoppingListStatus, is(true));
        assertThat(threeForTwoStatus, is(true));
        assertThat(billSum, is(true));
    }

    @Test
    //购物清单中有95折的商品，但是没有买二赠一的商品
    public void should_print_shopping_list_when_Goods_have_sale95_and_have_no_commodity_of_three_for_two() {
        //Given
        shoppingList.shoppingArr.add(addOneBillingRecord("可口可乐",3,"瓶",3.00,6.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("羽毛球",5,"个",1.00,4.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("苹果",2,"斤",5.50,10.45,0.55));
        bill.total = 24.45;
        bill.privilege = 0.55;

        //When
        boolean shoppingListStatus = printBill.printShoppingList();
        boolean threeForTwoStatus = printBill.printThreeForTwo();
        boolean billSum = printBill.printSumOfBill();

        //Then
        assertThat(shoppingListStatus, is(true));
        assertThat(threeForTwoStatus, is(false));
        assertThat(billSum, is(true));
    }

 @Test
    public void should_return_true_when_print_all() {
        //Given
        shoppingList.shoppingArr.add(addOneBillingRecord("可口可乐",3,"瓶",3.00,6.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("羽毛球",5,"个",1.00,4.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("苹果",2,"斤",5.50,10.45,0.55));
        threeForTwo.threeForTwoArr.add(addOneThreeForTwoRecord("可口可乐",1,"瓶"));
        threeForTwo.threeForTwoArr.add(addOneThreeForTwoRecord("羽毛球",1,"个"));
        bill.total = 20.45;
        bill.privilege = 4.55;

        //When
        boolean printBillStatus = printBill.printFullBill();

        //Then
        assertThat(printBillStatus, is(true));
    }

}