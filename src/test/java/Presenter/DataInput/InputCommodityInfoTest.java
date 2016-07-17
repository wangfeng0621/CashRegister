package Presenter.DataInput;

import Model.CommodityRepertory;
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
    public void Initialize_commodityInfo(){
        inputCommodityInfo = new InputCommodityInfo();
    }

    @Test
    //对已经建立好商品信息库进行插入操作，插入一个全新的商品
    public void should_return_true_when_insert_a_new_commodity_information(){
        //Given
        String newCommInfo = "ITEM000021 大豆 斤 食品 8.00 无";

        //when
        boolean status = inputCommodityInfo.insertNewCommInfo(newCommInfo);

        //then
        assertThat(status, is(true));
        assertThat(commRep.commodityInfomap.get("ITEM000021").price, is(8.00));
        assertThat(commRep.commodityInfomap.get("ITEM000021").name, is("大豆"));
        assertThat(commRep.commodityInfomap.get("ITEM000021").unit, is("斤"));
        assertThat(commRep.commodityInfomap.get("ITEM000021").category, is("食品"));
        assertThat(commRep.commodityInfomap.get("ITEM000021").privilege, is("无"));
    }

    @Test
    public void should_return_false_when_insert_a_existing_commodity_information(){
        //Given
        String newCommInfo = "ITEM000005 苹果 斤 水果 5.50 无";

        //when
        boolean status = inputCommodityInfo.insertNewCommInfo(newCommInfo);

        //then
        assertThat(status, is(false));
        assertThat(commRep.commodityInfomap.get("ITEM000005").price, is(5.50));
        assertThat(commRep.commodityInfomap.get("ITEM000005").name, is("苹果"));
        assertThat(commRep.commodityInfomap.get("ITEM000005").unit, is("斤"));
        assertThat(commRep.commodityInfomap.get("ITEM000005").category, is("水果"));
        assertThat(commRep.commodityInfomap.get("ITEM000005").privilege, is("无"));
    }
}