package RMOS;

import Cache.RecycleItemCache;
import Cache.RecycleMachineCache;
import DTO.RecycleItem;
import DTO.RecycleMachine;

import java.util.ArrayList;
import java.util.List;

public class RmosBuilder extends RmosObject{

    public void createRmos(){
        List<RecycleItem> recycleItems = new ArrayList<>();
        List<RecycleMachine> recycleMachines = new ArrayList<>();
    }

    public List<RecycleMachine> getMachines(){
        List<RecycleMachine> rcms = RecycleMachineCache.getAllItems();
        return rcms;
    }

    public List<RecycleItem> getItems(){
        List<RecycleItem> recycleItems = RecycleItemCache.getAllItems();
        return recycleItems;
    }


}
