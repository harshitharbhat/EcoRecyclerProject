package RMOS;

import DTO.RecycleItem;
import DTO.RecycleMachine;

import java.util.List;

public abstract class RmosObject {

    public abstract List<RecycleMachine> getMachines();

    public abstract List<RecycleItem> getItems();
}
