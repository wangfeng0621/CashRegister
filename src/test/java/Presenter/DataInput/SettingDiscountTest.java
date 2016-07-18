package Presenter.DataInput;

import Model.CommodityRepertory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by feng on 2016/7/18.
 */
public class SettingDiscountTest {

    public CommodityRepertory commRep;
    public InputCommodityInfo inputCommodityInfo;
    public SettingDiscount discount;


    @Before
    //建立商品信息库
    //读入打折信息
    public void Initialize_commodityInfo() {
        inputCommodityInfo = new InputCommodityInfo();
        discount = new SettingDiscount();
    }

    @Test
    //更新商品的打折信息，如果存在这个商品并且更新成功，返回true
    public void should_return_true_when_update_privilege_of_a_commodity() {
        //Given
        String updateInfo = "ITEM000008 0.95";

        //when
        boolean status = discount.update(updateInfo);

        //then
        assertThat(status, is(true));
        assertThat(commRep.commodityInfomap.get("ITEM000008").privilege, is("0.95"));
    }

    @Test
    //更新商品的打折信息，如果不存在这个商品，返回true
    public void should_return_false_when_update_privilege_of_an_nonexistence_commodity() {
        //Given
        String updateInfo = "ITEM000028 0.95";

        //when
        boolean status = discount.update(updateInfo);

        //then
        assertThat(status, is(false));
    }

}