package Presenter;

import Model.ShoppingList;
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
        ShoppingList sl = new ShoppingList();

        //when
        cs.inputBarcode(barcode);

        //then
        assertThat(sl.shoppinglist.get("ITEM000001") , is(1));
    }

    @Test
    //当购买第二个商品时，向购物清单中加入该商品的条形码，返回商品计数的值为2。
    public void should_return_shopping_goods_counts_is_two_when_input_second_barcode() {
        //given
        String barcode = "ITEM000001";
        CollectShoppingList cs = new CollectShoppingList();
        ShoppingList sl = new ShoppingList();

        //when
        cs.inputBarcode(barcode);

        //then
        assertThat(sl.shoppinglist.get("ITEM000001") , is(2));
    }
}