package Presenter;

import Model.CommodityRepertory;
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
public class CollectShoppingListTest {


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
    //当购买第一个商品时，向购物清单中加入该商品的条形码，返回商品计数的值为1。
    public void should_return_shopping_goods_counts_is_one_when_input_first_barcode() {
        //given
        String barcode = "ITEM000023";
        CollectShoppingList cs = new CollectShoppingList();

        //when
        cs.inputBarcode(barcode);

        //then
        assertThat(ShoppingList.shoppinglist.get(barcode) , is(1));
    }

    @Test
    //当购买第二个商品时，向购物清单中加入该商品的条形码，返回商品计数的值为2。
    public void should_return_shopping_goods_counts_is_two_when_input_second_barcode() {
        //given
        String barcode = "ITEM000023";
        CollectShoppingList cs = new CollectShoppingList();

        //when
        cs.inputBarcode(barcode);
        cs.inputBarcode(barcode);

        //then
        assertThat(ShoppingList.shoppinglist.get(barcode) , is(2));
    }

    @Test
    //向购物清单中加入了商品库中不存在的条形码，返回购买失败。
    public void should_return_purchase_failed_when_input_a_barcode_not_exist_in_CommodityRepertory() {
        //given
        String barcode = "ITEM00001";
        CollectShoppingList cs = new CollectShoppingList();

        //when
       cs.inputBarcode(barcode);

        //then
        assertThat(cs.STATUS , is("purchase failed"));
    }

    @Test
    //当购买的商品输入重复或者错误需要删除时，向购物清单中删除或者减少该商品的数量，返回商品计数的值为1而且删除成功。
    public void should_return_shopping_goods_counts_is_one_and_delete_successful_when_cancel_buy_a_goods() {
        //given
        String barcode = "ITEM000023";
        CollectShoppingList cs = new CollectShoppingList();

        //when
        cs.inputBarcode(barcode);
        cs.inputBarcode(barcode);
        cs.delete(barcode);

        //then
        assertThat(ShoppingList.shoppinglist.get(barcode) , is(1));
        assertThat(cs.STATUS , is("delete successful"));
    }

    @Test
    //当购买的商品输入删除的商品条形码不在购物清单上的，返回内容是该条形码不在清单中。
    public void should_return_not_exist_barcode_in_shoppinglist_delete_successful_when_delete_a_goods_not_bought () {
        //given
        String barcode = "ITEM000002";
        CollectShoppingList cs = new CollectShoppingList();

        //when
        cs.delete(barcode);

        //then
        assertThat(cs.STATUS , is("not exist barcode in shoppinglist"));
    }

    @Test
    //当在购物清单中某商品的数量是3，修改该商品的数量为4，返回修改成功且修改后的商品数为4
    public void should_return_modify_successful_and_the_goods_count_is_4when_modified_the_count_as_4() {
        //given
        String barcode = "ITEM000023";
        int value = 4;
        CollectShoppingList cs = new CollectShoppingList();

        //when
        cs.inputBarcode(barcode);
        cs.inputBarcode(barcode);
        cs.inputBarcode(barcode);
        cs.modifyCount("ITEM000023",value);

        //then
        assertThat(cs.STATUS , is("modified successful"));
        assertThat(ShoppingList.shoppinglist.get(barcode) , is(4));
    }

    @Test
    //修改一个在购物清单中不存在的商品数量，返回修改失败，购物清单不存在该商品
    public void should_return_modify_failed_when_the_modified_goods_not_exist_in_shopppinglist() {
        //given
        String barcode = "ITEM000023";
        int value = 4;
        CollectShoppingList cs = new CollectShoppingList();

        //when
        cs.inputBarcode(barcode);
        cs.inputBarcode(barcode);
        cs.inputBarcode(barcode);
        cs.modifyCount("ITEM000001",value);
        //then
        assertThat(cs.STATUS , is("not exist barcode in shoppinglist"));
    }

    @After
    //清除测试过程中产生的一些底层数据
    public void clear_data_after_test() {

        ShoppingList.shoppinglist.clear();
        CommodityRepertory.commodityInfomap.clear();
    }
}