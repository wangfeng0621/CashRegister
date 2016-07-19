package Presenter;

import Model.CommodityRepertory;
import Model.PrintBillDetails.SumOfBill;
import Model.ShoppingList;
import Presenter.DataInput.InputCommodityInfo;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by He on 2016/7/18.
 */
public class CalculatePriceTest {


    @Before
    //准备商品信息库中的商品信息
    public void read_data_of_test() {
        CollectShoppingList cs = new CollectShoppingList();
        CalculatePrice cp = new CalculatePrice();
        InputCommodityInfo inputCommodityInfo = new InputCommodityInfo();
        String goods1 = "ITEM000021-3 羽毛球 个 器材 2.00 买二赠一";
        String goods2 = "ITEM000022-2 可口可乐 瓶 饮料 3.00 无";
        String goods3 = "ITEM000023 苹果 斤 水果 9.00 九五";
        String goods4 = "ITEM000024 纸巾 盒 日用品 3.50 买二赠一_九五";
        inputCommodityInfo.insertNewCommInfo(goods1);
        inputCommodityInfo.insertNewCommInfo(goods2);
        inputCommodityInfo.insertNewCommInfo(goods3);
        inputCommodityInfo.insertNewCommInfo(goods4);
    }

    @Test
    //测试计算没有优惠商品的价格，输入为2瓶可口可乐，返回的总价为6元，优惠为0
    public void should_return_6rmb_and_printShoppingAll_include_goods_info_when_calculated() {
        //given
        CollectShoppingList cs = new CollectShoppingList();
        CalculatePrice cp = new CalculatePrice();
        cs.inputBarcode("ITEM000022-2");
       // cs.inputBarcode("ITEM000022");

        //when
        cp.calculatePrice();

        //then
        assertThat(SumOfBill.total, is(6.00));
        assertThat(SumOfBill.privilege, is(0.00));

        ShoppingList.shoppinglist.clear();
        SumOfBill.total = 0;
        SumOfBill.privilege = 0;

    }

    @Test
    //测试购物清单中含有买二赠一的商品，计算他们各自价格和总价，返回总价总价为7，优惠为2元
    public void should_return_20rmb_and_privilege_4_when_ShoppingList_include_ThreeforTwo_goods() {
        //given
        CollectShoppingList cs = new CollectShoppingList();
        CalculatePrice cp = new CalculatePrice();
        cs.inputBarcode("ITEM000021-3");
        cs.inputBarcode("ITEM000021-3");
        cs.inputBarcode("ITEM000022-2");
        cs.inputBarcode("ITEM000022-2");

        //when
        cp.calculatePrice();

        //then
        assertThat(SumOfBill.total, is(20.00));
        assertThat(SumOfBill.privilege, is(4.00));

        CommodityRepertory.commodityInfomap.clear();
        ShoppingList.shoppinglist.clear();
        SumOfBill.total = 0;
        SumOfBill.privilege = 0;
    }


}