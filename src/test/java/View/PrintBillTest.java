package View;

import Presenter.CalculatePrice;
import Presenter.CollectShoppingList;
import Presenter.DataInput.InputCommodityInfo;
import Presenter.DataInput.SettingDiscount;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by feng on 2016/7/20.
 */
public class PrintBillTest {

    public InputCommodityInfo inputCommInfo ;
    public SettingDiscount discount ;
    public CollectShoppingList shoppingList ;
    public CalculatePrice calculatePrice = new CalculatePrice();

    @Before
    public void read_data_for_test() {
        inputCommInfo = new InputCommodityInfo();
        discount = new SettingDiscount();
        shoppingList = new CollectShoppingList();
    }

    @Test
    //这是这个程序完整的流程
    public void this_is_a__complete_process(){
        //Given
        String shoppings = "['ITEM000001', 'ITEM000001', 'ITEM000001', 'ITEM000001','ITEM000001','ITEM000003-2', 'ITEM000005','ITEM000005', 'ITEM000005']";

        //When
        shoppingList.createShoppingList(shoppings);
        calculatePrice.calculatePrice();

        //Then
        PrintBill print = new PrintBill();
    }

}