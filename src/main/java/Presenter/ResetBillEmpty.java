package Presenter;

import Model.PrintBillDetails.PrivilegeThreeForTwo;
import Model.PrintBillDetails.ShoppingListAll;
import Model.PrintBillDetails.SumOfBill;
import Model.ShoppingList;

/**
 * Created by feng on 2016/7/17.
 */
public class ResetBillEmpty {

    private void resetShoppingList() {
        ShoppingList.shoppinglist.clear();
    }

    private void resetthreeforList() {
        PrivilegeThreeForTwo.threeForTwoArr.clear();
    }

    public void resetAll() {
        resetShoppingListAll();
        resetthreeforList();
        resetSumOfBill();
        resetShoppingList();
    }

    private void resetShoppingListAll() {
        ShoppingListAll.shoppingArr.clear();
    }

    private void resetSumOfBill() {
        SumOfBill.total = 0.0;
        SumOfBill.privilege = 0.0;
    }
}
