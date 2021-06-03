package RMOS;

import DTO.RecycleItem;
import DTO.RecycleMachine;

import java.util.List;

public class Rmos {

    public void rmos(){
        RmosBuilder rmosBuilder = new RmosBuilder();
        List<RecycleItem> recycleItems = rmosBuilder.getItems();
        List<RecycleMachine> recycleMachines = rmosBuilder.getMachines();
        rmosBuilder.createRmos();
    }
}
