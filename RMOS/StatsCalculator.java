package RMOS;

import Cache.RecycleMachineCache;
import DTO.RecycleMachine;
import GUI.RecyclableMachineContent;
import GUI.RmosGui;

public class StatsCalculator {

    RecycleMachine rcm;

    public StatsCalculator(RecycleMachine rcm){
        this.rcm = rcm;
    }
    public float getCashDistributed(float amount){
        rcm.setDisbursedAmount(rcm.getDisbursedAmount() + amount);
        RecycleMachineCache.addNewItem(rcm);
        updateBarChart();
        updateRmos();
        return amount;
    }

    public void getUpdatedWeight(){
        updateWeight();
    }

    private void updateRmos() {
        int index = 9;
        if(rcm.getMachineId() == "1001"){
            index = 10;
        }
        RecyclableMachineContent.getInstance().updateAmount(index,rcm.getMoneyAvailable());
    }

    private void updateBarChart() {
        RmosGui.getInstance().addGraph();
    }

    private void updateWeight() {
        int index = 9;
        if(rcm.getMachineId() == "1001"){
            index = 10;
        }
        RecyclableMachineContent.getInstance().updateWeight(index,rcm.getAllowedWeight());
    }
}
