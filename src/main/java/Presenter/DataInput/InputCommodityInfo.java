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

    public boolean alterCommInfo(String alterInfo) {
        String alterInf[] = alterInfo.split(" ");
        if(commodityInfo.commodityInfomap.get(alterInf[0]) != null) {
            for(int i = 1; i < alterInf.length; i++){
                modityInfo(alterInf[0],alterInf[i],alterInf[i+1]);
                i++;
            }
            return true;
        }
        return false;
    }

    private void modityInfo(String barCode, String flag, String value) {
        if(flag.equals("name")) {
            commodityInfo.commodityInfomap.get(barCode).name = value;
        }
        else if(flag.equals("price")) {
            commodityInfo.commodityInfomap.get(barCode).price = Double.valueOf(value);
        }
        else if(flag.equals("unit")) {
            commodityInfo.commodityInfomap.get(barCode).unit = value;
        }
        else if(flag.equals("privilege")) {
            commodityInfo.commodityInfomap.get(barCode).privilege = value;
        }
        else if(flag.equals("category")) {
            commodityInfo.commodityInfomap.get(barCode).category = value;
        }
    }

    public boolean deleteInfo(String barcode) {
        if(commodityInfo.commodityInfomap.get(barcode) != null) {
            commodityInfo.commodityInfomap.remove(barcode);
            return true;
        }
        return false;
    }
}
