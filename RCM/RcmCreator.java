package RCM;

import Cache.RecycleItemCache;
import Cache.RecycleMachineCache;
import DTO.RecycleItem;
import DTO.RecycleMachine;
import GUI.FrameCreator;
import RMOS.Time;
import RMOS.Weight;

import java.util.List;

public class RcmCreator extends FrameCreator {

    public List<RecycleMachine> rcmCreator(){
        List<RecycleMachine> recycleMachines = RecycleMachineCache.getAllItems();
        List<RecycleItem> recycleItems = RecycleItemCache.getAllItems();
        float exWeight = 0;
        for (RecycleMachine rcm : recycleMachines) {
            rcm.setRecycleItemList(recycleItems);
            Weight weight = new Weight(rcm);
            String newWeight = weight.updateValue(String.valueOf(exWeight));
            rcm.setAllowedWeight(Float.parseFloat(newWeight));
            Time time = new Time(rcm);
            rcm.setTimeEmptied(time.updateValue(""));
            exWeight+=1.75;
        }

        return recycleMachines;
    }
}
