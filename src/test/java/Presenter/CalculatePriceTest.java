package Presenter;

import Model.CommodityRepertory;
import Model.PrintBillDetails.SumOfBill;
import Model.ShoppingList;
import Presenter.DataInput.InputCommodityInfo;
import org.junit.After;
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
        String goods3 = "ITEM000023 苹果 斤 水果 10.00 0.95";
        String goods4 = "ITEM000024 纸巾 盒 日用品 3.50 买二赠一_0.95";
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
        cs.inputBarcode("ITEM000022-2");

        //when
        cp.calculatePrice();

        //then
        assertThat(SumOfBill.total, is(12.00));
        assertThat(SumOfBill.privilege, is(0.00));

    }

    @Test
    //测试购物清单中含有买二赠一的商品，计算他们各自价格和总价，返回总价总价为20，优惠为4元
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

    }

    @Test
    //测试购物清单中含有买二赠一同时满足九五折的商品，计算他们各自价格和总价，返回总价总价为27，优惠为7.5元
    public void should_return_27rmb_and_privilege_more_7_when_ShoppingList_include_ThreeforTwo_and_sale95_goods() {
        //given
        CollectShoppingList cs = new CollectShoppingList();
        CalculatePrice cp = new CalculatePrice();
        cs.inputBarcode("ITEM000021-3");
        cs.inputBarcode("ITEM000021-3");
        cs.inputBarcode("ITEM000022-2");
        cs.inputBarcode("ITEM000022-2");
        cs.inputBarcode("ITEM000024");
        cs.inputBarcode("ITEM000024");
        cs.inputBarcode("ITEM000024");

        //when
        cp.calculatePrice();

        //then
        assertThat(SumOfBill.total, is(27.00));
        assertThat(SumOfBill.privilege, is(7.50));

    }

    @Test
    //测试购物清单中含有满足买二赠一和九五折的商品，计算他们各自价格和总价，返回总价总价为19.5，优惠为2.5元
    public void should_return_20rmb_and_privilege_4_when_ShoppingList_include_ThreeforTwo_or_sale95_goods() {
        //given
        CollectShoppingList cs = new CollectShoppingList();
        CalculatePrice cp = new CalculatePrice();
        cs.inputBarcode("ITEM000021-3");
        cs.inputBarcode("ITEM000023");
        cs.inputBarcode("ITEM000022-2");

        //when
        cp.calculatePrice();

        //then
        assertThat(SumOfBill.total, is(19.50));
        assertThat(SumOfBill.privilege, is(2.50));

    }

    @After
    //清除测试过程中产生的一些底层数据
    public void clear_data_after_test() {

        CommodityRepertory.commodityInfomap.clear();
        ShoppingList.shoppinglist.clear();
        SumOfBill.total = 0;
        SumOfBill.privilege = 0;
    }

}