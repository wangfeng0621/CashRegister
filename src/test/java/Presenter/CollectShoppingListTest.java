package Presenter;

import Model.ShoppingList;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by He on 2016/7/18.
 */
public class CollectShoppingListTest {


    @Test
    //当购买第一个商品时，向购物清单中加入该商品的条形码，返回商品计数的值为1。
    public void should_return_shopping_goods_counts_is_one_when_input_first_barcode() {
        //given
        String barcode = "ITEM000001";
        CollectShoppingList cs = new CollectShoppingList();
        ShoppingList.shoppinglist.clear();

        //when
        cs.inputBarcode(barcode);

        //then
        assertThat(ShoppingList.shoppinglist.get(barcode) , is(1));
    }

    @Test
    //当购买第二个商品时，向购物清单中加入该商品的条形码，返回商品计数的值为2。
    public void should_return_shopping_goods_counts_is_two_when_input_second_barcode() {
        //given
        String barcode = "ITEM000001";
        CollectShoppingList cs = new CollectShoppingList();
        ShoppingList.shoppinglist.clear();

        //when
        cs.inputBarcode(barcode);
        cs.inputBarcode(barcode);

        //then
        assertThat(ShoppingList.shoppinglist.get(barcode) , is(2));
    }
    @Test
    //当购买的商品输入重复或者错误需要删除时，向购物清单中删除或者减少该商品的数量，返回商品计数的值为1而且删除成功。
    public void should_return_shopping_goods_counts_is_one_and_delete_successful_when_cancel_buy_a_goods() {
        //given
        String barcode = "ITEM000001";
        CollectShoppingList cs = new CollectShoppingList();
        ShoppingList.shoppinglist.clear();

        //when
        cs.inputBarcode(barcode);
        cs.inputBarcode(barcode);
        cs.delete(barcode);

        //then
        assertThat(ShoppingList.shoppinglist.get(barcode) , is(1));
        assertThat(cs.delete(barcode), is("delete successful"));
    }
    @Test
    //当购买的商品输入删除的商品条形码不在购物清单上的，返回内容是该条形码不在清单中。
    public void should_return_not_exist_barcode_in_shoppinglist_delete_successful_when_delete_a_goods_not_bought () {
        //given
        String barcode = "ITEM000002";
        CollectShoppingList cs = new CollectShoppingList();
        ShoppingList.shoppinglist.clear();

        //when
        cs.delete(barcode);

        //then
        assertThat(cs.delete(barcode), is("not exist barcode in shoppinglist"));
    }
}