package RMOS;

import DTO.RecycleMachine;

public class UsageCalculator {

    public float calculateUsage(RecycleMachine rcm, float updated){
        float usageTillNow = rcm.getMoneyAvailable();
        return usageTillNow - updated;
    }

}
