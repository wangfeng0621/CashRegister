package Presenter;

import Model.CommodityRepertory;
import Model.PrintBillDetails.ShoppingListAll;
import Model.PrintBillDetails.SumOfBill;
import Presenter.DataInput.InputCommodityInfo;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by He on 2016/7/18.
 */
public class CalculatePriceTest {

    CollectShoppingList cs = new CollectShoppingList();
    CalculatePrice cp = new CalculatePrice();

    @Before
    public void read_data_of_test() {
        InputCommodityInfo inputCommodityInfo = new InputCommodityInfo();
        String goods1 = "ITEM000021 大豆 斤 食品 8.00 无";
        String goods2 = "ITEM000022 可口可乐 瓶 饮料 3.00 买二赠一";
        String goods3 = "ITEM000023 苹果 斤 水果 9.00 九五";
        String goods4 = "ITEM000024 纸巾 盒 日用品 3.50 买二赠一-九五";
        inputCommodityInfo.insertNewCommInfo(goods1);
        inputCommodityInfo.insertNewCommInfo(goods2);
        inputCommodityInfo.insertNewCommInfo(goods3);
        inputCommodityInfo.insertNewCommInfo(goods4);
    }

    @Test
    public void should_return_16rmb_and_printShoppingAll_include_goods_info_when_calculated() {
        //given
        cs.inputBarcode("ITEM000022");
        cs.inputBarcode("ITEM000022");

        //when
        cp.notPrivilegeCalcu();

        //then
        assertThat(SumOfBill.total, is(6.00));
        assertThat(SumOfBill.privilege, is(0.00));
    }


}