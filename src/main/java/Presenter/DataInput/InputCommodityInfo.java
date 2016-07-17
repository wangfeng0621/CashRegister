package Presenter.DataInput;

import Model.CommodityRepertory;

import java.io.*;


/**
 * Created by feng on 2016/7/17.
 */
public class InputCommodityInfo {
    public CommodityRepertory commodityInfo;

    public InputCommodityInfo() {
        commodityInfo = new CommodityRepertory();
        String path = "src/main/java/Presenter/DataInput/commodityInformation.txt";
        readCommodityInfo(path);
    }

    private void readCommodityInfo(String path) {
        BufferedReader fp;
        try {

            fp = new BufferedReader(new FileReader(path));
            String data = fp.readLine();
            while (data != null) {
                inputDataToMap(data);
                data = fp.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void inputDataToMap(String data) {
        String info[] = data.split(" ");
        commodityInfo.barCode = info[0];
        CommodityRepertory.CommodityInfo basicInfo = new CommodityRepertory.CommodityInfo();
        basicInfo.name = info[1];
        basicInfo.unit = info[2];
        basicInfo.category = info[3];
        basicInfo.price = Double.valueOf(info[4]);
        basicInfo.privilege = info[5];
        commodityInfo.commodityInfomap.put(commodityInfo.barCode,basicInfo);
    }

    public boolean insertNewCommInfo(String newCommInfo) {
        String basicInfo[] = newCommInfo.split(" ");
        if(commodityInfo.commodityInfomap.get(basicInfo[0]) == null) {
            inputDataToMap(newCommInfo);
            return true;
        }
        return false;
    }
}
