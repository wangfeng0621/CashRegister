package Presenter;

import Model.PrintBillDetails.PrivilegeThreeForTwo;
import Model.PrintBillDetails.ShoppingListAll;
import Model.PrintBillDetails.SumOfBill;
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

    public CollectShoppingList collectShoppingList = new CollectShoppingList();
    public CalculatePrice calculatePrice = new CalculatePrice();

    @Before
    //准备商品信息库中的商品信息
    public void read_data_of_test() {
        InputCommodityInfo inputCommodityInfo = new InputCommodityInfo();
        String goods1 = "ITEM000021 羽毛球 个 器材 2.00 ThreeForTwo";
        String goods2 = "ITEM000022 可口可乐 瓶 饮料 3.00 null";
        String goods3 = "ITEM000023 苹果 斤 水果 10.00 0.95";
        String goods4 = "ITEM000024 纸巾 盒 日用品 3.50 ThreeForTwo_0.95";
        inputCommodityInfo.insertNewCommInfo(goods1);
        inputCommodityInfo.insertNewCommInfo(goods2);
        inputCommodityInfo.insertNewCommInfo(goods3);
        inputCommodityInfo.insertNewCommInfo(goods4);
    }

    @After
    //打印账单和清除账单数据
    public void reset_bill_data() {
        OutPutBill putBill = new OutPutBill();
        putBill.printFullBill();
        ResetBillEmpty reset = new ResetBillEmpty();
        reset.resetAll();
    }

    @Test
    //测试计算没有优惠商品的价格，输入为2瓶可口可乐，返回的总价为6元，优惠为0
    public void should_return_6rmb_and_printShoppingAll_include_goods_info_when_calculated() {
        //Given
        collectShoppingList.addNewGoods("ITEM000022-2");
        collectShoppingList.addNewGoods("ITEM000022-2");

        //When
        calculatePrice.calculatePrice();

        //Then
        assertThat(SumOfBill.total, is(12.00));
        assertThat(SumOfBill.privilege, is(0.00));

    }

    @Test
    //测试购物清单中含有买二赠一的商品，计算他们各自价格和总价，返回总价总价为20，优惠为4元
    public void should_return_20rmb_and_privilege_4_when_ShoppingList_include_ThreeforTwo_goods() {
        //Given
        collectShoppingList.addNewGoods("ITEM000021-3");
        collectShoppingList.addNewGoods("ITEM000021-3");
        collectShoppingList.addNewGoods("ITEM000022-2");
        collectShoppingList.addNewGoods("ITEM000022-2");

        //When
        calculatePrice.calculatePrice();

        //Then
        assertThat(SumOfBill.total, is(20.00));
        assertThat(SumOfBill.privilege, is(4.00));

    }

    @Test
    //测试购物清单中含有买二赠一同时满足九五折的商品，计算他们各自价格和总价，返回总价总价为27，优惠为7.5元
    public void should_return_27rmb_and_privilege_more_7_when_ShoppingList_include_ThreeforTwo_and_sale95_goods() {
        //Given
        collectShoppingList.addNewGoods("ITEM000021-3");
        collectShoppingList.addNewGoods("ITEM000021-3");
        collectShoppingList.addNewGoods("ITEM000022-2");
        collectShoppingList.addNewGoods("ITEM000022-2");
        collectShoppingList.addNewGoods("ITEM000024");
        collectShoppingList.addNewGoods("ITEM000024");
        collectShoppingList.addNewGoods("ITEM000024");

        //When
        calculatePrice.calculatePrice();

        //Then
        assertThat(SumOfBill.total, is(27.00));
        assertThat(SumOfBill.privilege, is(7.50));

    }

    @Test
    //测试购物清单中含有满足买二赠一和九五折的商品，计算他们各自价格和总价，返回总价总价为19.5，优惠为2.5元
    public void should_return_more_19rmb_and_privilege_more_2_when_ShoppingList_include_ThreeforTwo_or_sale95_goods() {
        //Given
        collectShoppingList.addNewGoods("ITEM000021-3");
        collectShoppingList.addNewGoods("ITEM000023");
        collectShoppingList.addNewGoods("ITEM000022-2");

        //When
        calculatePrice.calculatePrice();

        //Then
        assertThat(SumOfBill.total, is(19.50));
        assertThat(SumOfBill.privilege, is(2.50));

    }

    @Test
    //测试在计算价格的同时是否将这些数据添加到准备打印输出的数据结构中
    public void test_the_result_of_print_when_purchase_three_different_privilege_goods() {
        //Given
        collectShoppingList.addNewGoods("ITEM000021-3");
        collectShoppingList.addNewGoods("ITEM000023");
        collectShoppingList.addNewGoods("ITEM000022-2");

        //When
        calculatePrice.calculatePrice();

        //Then
        assertThat(ShoppingListAll.shoppingArr.size(), is(3));
        assertThat(PrivilegeThreeForTwo.threeForTwoArr.size(), is(1));
    }

}