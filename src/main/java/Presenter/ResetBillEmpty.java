package Presenter;

import Model.PrintBillDetails.PrivilegeThreeforTwo;
import Model.PrintBillDetails.ShoppingListAll;
import Model.PrintBillDetails.SumOfBill;

/**
 * Created by feng on 2016/7/17.
 */
public class ResetBillEmpty {

    ShoppingListAll shoppingList = new ShoppingListAll();
    PrivilegeThreeforTwo threeforTwo = new PrivilegeThreeforTwo();
    SumOfBill bill = new SumOfBill();

    private void resetShoppingList() {
        shoppingList.shoppingArr.clear();
    }

    private void resetthreeforList() {
        threeforTwo.threeforTwoArr.clear();
    }

    public void resetAll() {
        resetShoppingList();
        resetthreeforList();
        resetSumOfBill();
    }

    private void resetSumOfBill() {
        bill.total = 0.0;
        bill.privilege = 0.0;
    }
}
