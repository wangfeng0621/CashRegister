package Presenter.DataInput;

import Model.CommodityRepertory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by feng on 2016/7/17.
 */
public class InputCommodityInfoTest {

    public InputCommodityInfo inputCommodityInfo;
    public CommodityRepertory commRep;
    @Before
    //从commodityInformation.txt文件中读取所有商品的基本信息,初始化商品信息
    public void Initialize_commodityInfo() {
        inputCommodityInfo = new InputCommodityInfo();
    }

    @After
    public void clear_commodity_information(){
        commRep.commodityInfomap.clear();
    }

    @Test
    //在Before中读入了所有商品的基本信息，商品信息库的大小应该是20
    public void should_length_of_commodity_repertory_is_20_when_Initialize_commodityInfo(){
        //Then
        assertThat(commRep.commodityInfomap.size(), is(20));
    }

    @Test
    //对已经建立好商品信息库进行插入操作，插入一个全新的商品
    public void should_return_true_when_insert_a_new_commodity_information() {
        //Given
        String newCommInfo = "ITEM000021 大豆 斤 食品 8.00 无";

        //When
        boolean status = inputCommodityInfo.insertNewCommInfo(newCommInfo);

        //Then
        assertThat(status, is(true));
        assertThat(commRep.commodityInfomap.get("ITEM000021").price, is(8.00));
        assertThat(commRep.commodityInfomap.get("ITEM000021").name, is("大豆"));
        assertThat(commRep.commodityInfomap.get("ITEM000021").unit, is("斤"));
        assertThat(commRep.commodityInfomap.get("ITEM000021").category, is("食品"));
        assertThat(commRep.commodityInfomap.get("ITEM000021").privilege, is("无"));
    }

    @Test
    //插入一个已经存在的商品，返回false
    public void should_return_false_when_insert_a_existing_commodity_information() {
        //Given
        String newCommInfo = "ITEM000005 苹果 斤 水果 5.50 无";

        //When
        boolean status = inputCommodityInfo.insertNewCommInfo(newCommInfo);

        //Then
        assertThat(status, is(false));
    }

    @Test
    //修改一个商品的信息，修改字符串应该为 “条形码 标签 值 标签 值 标签 值 ......”
    public void should_return_true_when_alter_a_commodity_information() {
        //Given
        String alterInfo = "ITEM000005 price 6.00";

        //When
        boolean status = inputCommodityInfo.alterCommInfo(alterInfo);

        //Then
        assertThat(status, is(true));
        assertThat(commRep.commodityInfomap.get("ITEM000005").price, is(6.00));
    }

    @Test
    //修改一个商品的信息，但是这个商品不存在，返回false
    public void should_return_false_when_alter_a_commodity_information_but_the_commodity_nonexistence() {
        //Given
        String alterInfo = "ITEM000025 price 6.00";

        //When
        boolean status = inputCommodityInfo.alterCommInfo(alterInfo);

        //Then
        assertThat(status, is(false));
    }

    @Test
    //从商品库中删除一个商品，删除成功返回true，需要提供这个商品的条形码
    public void should_return_true_when_delete_a_commodity_information() {
        //Given
        String deleteInfo = "ITEM000005";

        //When
        boolean status = inputCommodityInfo.deleteInfo(deleteInfo);

        //Then
        assertThat(status, is(true));
    }

    @Test
    //从商品库中删除一个商品,如果这个商品不存在，返回false
    public void should_return_false_when_delete_an_nonexistence_commodity_information() {
        //Given
        String deleteInfo = "ITEM000025";

        //When
        boolean status = inputCommodityInfo.deleteInfo(deleteInfo);

        //Then
        assertThat(status, is(false));
    }

}