package Presenter;

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

    public CollectShoppingList collectShoppingList = new CollectShoppingList();
    public ShoppingList shoppingList = new ShoppingList();

    @Before
    //读入商品信息库中的商品信息
    public void read_data_of_test() {
        InputCommodityInfo inputCommodityInfo = new InputCommodityInfo();
    }

    @After
    //清除测试过程中产生的一些底层数据
    public void clear_data_after_test() {
        ResetBillEmpty reset = new ResetBillEmpty();
        reset.resetAll();
    }

    @Test
    public void should_create_a_shopping_list_when_give_a_shopping_list_use_of_Json(){
        //Given
        String shoppings = "['ITEM000001', 'ITEM000001', 'ITEM000001', 'ITEM000001','ITEM000001','ITEM000003-2', 'ITEM000005','ITEM000005', 'ITEM000005']";

        //When
        collectShoppingList.createShoppingList(shoppings);

        //Then
        assertThat(shoppingList.shoppinglist.size(), is(3));
        assertThat(shoppingList.shoppinglist.get("ITEM000001"), is(5));
        assertThat(shoppingList.shoppinglist.get("ITEM000003"), is(2));
        assertThat(shoppingList.shoppinglist.get("ITEM000005"), is(3));


    }

    @Test
    //在购物清单中新增一个商品，成功返回success
    public void should_return_success_when_add_new_commodity(){
        //Given
        String goods = "ITEM000008-8";

        //When
        String status = collectShoppingList.addNewGoods(goods);

        //Then
        assertThat(status, is("success"));
        assertThat(shoppingList.shoppinglist.get("ITEM000008"), is(8));
    }

    @Test
    //在购物清单中新增一个商品，但是这个商品的条形码不在商品库里面,成功返回failure
    public void should_return_failure_when_add_new_commodity(){
        //Given
        String goods = "ITEM000028-8";

        //When
        String status = collectShoppingList.addNewGoods(goods);

        //Then
        assertThat(status, is("failure"));
        assertThat((shoppingList.shoppinglist.get("ITEM000008") == null), is(true));
    }

    @Test
    //在购物清单中删除一个或者多个或者全部的某种商品
    public void should_return_success_when_delete_a_commodity(){
        //Given
        String shoppings = "['ITEM000001', 'ITEM000001', 'ITEM000001', 'ITEM000001','ITEM000001','ITEM000003-2', 'ITEM000005','ITEM000005', 'ITEM000005']";
        String delete1 = "ITEM000001-2";
        String delete2 = "ITEM000003";
        String delete3 = "ITEM000005-ALL";

        //When
        collectShoppingList.createShoppingList(shoppings);
        String status1 = collectShoppingList.deleteComm(delete1);
        String status2 = collectShoppingList.deleteComm(delete2);
        String status3 = collectShoppingList.deleteComm(delete3);

        //Then
        assertThat(status1, is("success"));
        assertThat(status2, is("success"));
        assertThat(status3, is("success"));
        assertThat(shoppingList.shoppinglist.size(), is(2));
        assertThat(shoppingList.shoppinglist.get("ITEM000001"), is(3));
        assertThat(shoppingList.shoppinglist.get("ITEM000003"), is(1));
        assertThat((shoppingList.shoppinglist.get("ITEM000005") == null), is(true));
    }

    @Test
    //在购物清单中删除某种商品,但是这个商品不在购物清单中，或者删除的数量超过了购买数量
    public void should_return_failure_when_delete_commodity(){
        //Given
        String shoppings = "['ITEM000001', 'ITEM000001', 'ITEM000001', 'ITEM000001','ITEM000001','ITEM000003-2', 'ITEM000005','ITEM000005', 'ITEM000005']";
        String delete1 = "ITEM000001-6";
        String delete2 = "ITEM000002";

        //When
        collectShoppingList.createShoppingList(shoppings);
        String status1 = collectShoppingList.deleteComm(delete1);
        String status2 = collectShoppingList.deleteComm(delete2);

        //Then
        assertThat(status1, is("failure"));
        assertThat(status2, is("failure"));
    }

}