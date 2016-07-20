package Presenter;

import Model.PrintBillDetails.PrivilegeThreeforTwo;
import Model.PrintBillDetails.ShoppingListAll;
import Model.PrintBillDetails.SumOfBill;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by feng on 2016/7/19.
 */
public class ResetBillEmptyTest {

    ShoppingListAll shoppingList = new ShoppingListAll();
    PrivilegeThreeforTwo threeforTwo = new PrivilegeThreeforTwo();
    SumOfBill bill = new SumOfBill();
    ResetBillEmpty reset = new ResetBillEmpty();

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

    private PrivilegeThreeforTwo.ThreeForTwo addOneThreeforTwoRecord(String name, int count, String unit) {
        PrivilegeThreeforTwo.ThreeForTwo oneThreeForTwoRecord = new PrivilegeThreeforTwo.ThreeForTwo();
        oneThreeForTwoRecord.name = name;
        oneThreeForTwoRecord.count = count;
        oneThreeForTwoRecord.unit = unit;
        return oneThreeForTwoRecord;
    }

    @Test
    public void should_shoppingList_size_is_0_when_reset_shopping_list() {
        //Given
        shoppingList.shoppingArr.add(addOneBillingRecord("可口可乐",3,"瓶",3.00,6.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("羽毛球",5,"个",1.00,4.00,0.0));
        shoppingList.shoppingArr.add(addOneBillingRecord("苹果",2,"斤",5.50,11.00,0.0));
        threeforTwo.threeForTwoArr.add(addOneThreeforTwoRecord("可口可乐",1,"瓶"));
        threeforTwo.threeForTwoArr.add(addOneThreeforTwoRecord("羽毛球",1,"个"));
        bill.total = 21.00;
        bill.privilege = 4.00;
        //When
        reset.resetAll();

        //Then
        assertThat(shoppingList.shoppingArr.size(), is(0));
        assertThat(threeforTwo.threeForTwoArr.size(), is(0));
        assertThat(bill.total, is(0.0));
        assertThat(bill.privilege, is(0.0));
    }

}