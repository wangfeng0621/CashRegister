package Presenter;

import Model.CommodityRepertory;
import Model.ShoppingList;

/**
 * Created by He on 2016/7/17.
 */
public class CollectShoppingList {

    public ShoppingList shoppingList = new ShoppingList();
    public CommodityRepertory commInfo= new CommodityRepertory();

    public void createShoppingList(String shoppings) {

        String goods[] = analysisShoppingListUseOfJson(shoppings);
        for (String barcode : goods) {
            insertOneShoppingRecordToMap(barcode);
        }
    }

    private void insertOneShoppingRecordToMap(String oneShoppingRecord) {

        if(oneShoppingRecord.contains("-")) {
            insertShoppingRecordA(oneShoppingRecord);
        }
        else {
            insertShoppingRecordB(oneShoppingRecord);
        }

    }

    private void insertShoppingRecordB(String oneShoppingRecord) {

        if(shoppingList.shoppinglist.get(oneShoppingRecord) != null) {
            shoppingList.shoppinglist.put(oneShoppingRecord, shoppingList.shoppinglist.get(oneShoppingRecord) + 1);
        }
        else {
            shoppingList.shoppinglist.put(oneShoppingRecord, 1);
        }

    }

    private void insertShoppingRecordA(String oneShoppingRecord) {

        int count = 0;
        String goods[] = oneShoppingRecord.split("-");
        if(shoppingList.shoppinglist.get(goods[0]) != null) {
            count = shoppingList.shoppinglist.get(goods[0])+Integer.valueOf(goods[1]);
            shoppingList.shoppinglist.put(goods[0],count);
        }
        else {
            shoppingList.shoppinglist.put(goods[0],Integer.valueOf(goods[1]));
        }

    }

    private String[] analysisShoppingListUseOfJson(String shoppings) {
        shoppings = shoppings.replace("[", "");
        shoppings = shoppings.replace("]", "");
        shoppings = shoppings.replace("'", "");
        shoppings = shoppings.replace(" ", "");
        String goods[] = shoppings.split(",");
        return goods;
    }

    public String addNewGoods(String goods) {
        String barcode[] = goods.split("-");
        if(checkBarcode(barcode[0])){
            insertOneShoppingRecordToMap(goods);
            return "success";
        }
        return "failure";
    }

    private boolean checkBarcode(String barcode) {
        if(commInfo.commodityInfomap.get(barcode) != null) {
            return true;
        }
        return false;
    }


    public String deleteComm(String deleteInfo) {
        String barcode[] = deleteInfo.split("-");
        if(checkBarcodeInShoppingList(barcode[0])){
            if(deleteCommFromShopping(deleteInfo)){
                return "success";
            }
            System.out.println("For this kind of goods to delete more than the number of shopping list!!!!!!");
            return "failure";
        }
        System.out.println("This product is not in the shopping list!!!!!!");
        return "failure";
    }

    private boolean checkBarcodeInShoppingList(String barcode) {
        if(shoppingList.shoppinglist.get(barcode) != null) {
            return true;
        }
        return false;
    }

    private boolean deleteCommFromShopping(String deleteInfo) {
        String barcode[] = deleteInfo.split("-");
        if(barcode.length == 1 ) {
            int count = shoppingList.shoppinglist.get(barcode[0]) - 1;
            if(count == 0) {
                shoppingList.shoppinglist.remove(barcode[0]);
            }
            else {
                shoppingList.shoppinglist.put(barcode[0],count);
            }
        }
        else if(barcode[1].equals("ALL")) {
            shoppingList.shoppinglist.remove(barcode[0]);
        }
        else {
            int count = shoppingList.shoppinglist.get(barcode[0]);
            int delNum = Integer.valueOf(barcode[1]);
            if(delNum > count){
                return false;
            }
            else if(delNum == count){
                shoppingList.shoppinglist.remove(barcode[0]);
            }
            else {
                count -= delNum;
                shoppingList.shoppinglist.put(barcode[0],count);
            }
        }
        return true;
    }
}
