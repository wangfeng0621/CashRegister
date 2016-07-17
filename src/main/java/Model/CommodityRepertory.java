package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 2016/7/17.
 */
public class CommodityRepertory {

    public String barCode;
    public static Map<String , CommodityInfo> commodityInfomap = new HashMap<String , CommodityInfo>();

    public static class CommodityInfo{
        public String name;
        public String unit;
        public double price;
        public String category;
        public String privilege;
    }

}
