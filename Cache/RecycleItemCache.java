package Cache;

import DTO.RecycleItem;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RecycleItemCache {
    private static Hashtable<Integer, RecycleItem> itemsMap = new Hashtable<>();

    public static RecycleItem getItem(Integer itemId){
        return itemsMap.get(itemId);
    }

    public static List<RecycleItem> getAllItems(){
        return (new ArrayList<>(itemsMap.values()));
    }

    public static void addNewItem(RecycleItem ri){
        itemsMap.put(ri.getItemId(), ri);
    }

    public static void loadCache(){
        RecycleItem item1 = createItem(9001, (float) 2.5,"Aluminum");
        RecycleItem item2 = createItem(9011,2,"Glass");
        RecycleItem item3 = createItem(9102, (float) 1.5,"Plastic Bottles");
        RecycleItem item4 = createItem(9213, (float) 1.75,"Tin");
        RecycleItem item5 = createItem(9222, (float) 0.75,"Milk Cartons");
        RecycleItem item6 = createItem(9029, (float) 2.0,"Aerosol Cans");
        RecycleItem item7 = createItem(9571, (float) 2.30,"Metal pots");
        RecycleItem item8 = createItem(9811, (float) 1.25,"Old keys");
        RecycleItem item9 = createItem(9900, (float) 0.75,"Clothing");
        RecycleItem item10 = createItem(9235, (float) 1.75,"Keys");

        itemsMap.put(item1.getItemId(),item1);
        itemsMap.put(item2.getItemId(),item2);
        itemsMap.put(item3.getItemId(),item3);
        itemsMap.put(item4.getItemId(),item4);
        itemsMap.put(item5.getItemId(),item5);
        itemsMap.put(item6.getItemId(),item6);
        itemsMap.put(item7.getItemId(),item7);
        itemsMap.put(item8.getItemId(),item8);
        itemsMap.put(item9.getItemId(),item9);
        itemsMap.put(item10.getItemId(),item10);

    }

    private static RecycleItem createItem(int id, float amount, String type) {
        RecycleItem item = new RecycleItem();
        item.setAmount(amount);
        item.setId(id);
        item.setType(type);
        return item;
    }
}
