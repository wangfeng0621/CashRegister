package Presenter.DataInput;

import Model.CommodityRepertory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by feng on 2016/7/17.
 */
public class SettingDiscount {

    public CommodityRepertory commodityInfo;

    public SettingDiscount() {
        commodityInfo = new CommodityRepertory();
        String path = "src/main/java/Presenter/DataInput/discount.txt";
        readDiscountInfo(path);
    }

    private void readDiscountInfo(String path) {
        BufferedReader fp;
        try {
            fp = new BufferedReader(new FileReader(path));
            String data = fp.readLine();
            while (data != null) {
                update(data);
                data = fp.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean update(String updateInfo) {
        String info[] = updateInfo.split(" ");
        if(commodityInfo.commodityInfomap.get(info[0]) != null) {
            commodityInfo.commodityInfomap.get(info[0]).privilege = info[1];
            return true;
        }
        return false;
    }
}
