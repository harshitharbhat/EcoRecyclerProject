package DTO;

import java.util.Date;
import java.util.List;

public class RecycleMachine implements Cloneable{

    private String machineId;
    private String location;
    private float weightAdded;
    private float allowedWeight;
    private float returnableAmount;
    private float moneyAvailable;
    private boolean operational;
    private String timeEmptied;
    private float disbursedAmount;
    List<RecycleItem> recycleItemList;

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }

    public RecycleMachine setMachineId(String machineId) {
        this.machineId = machineId;
        return this;
    }

    public RecycleMachine setLocation(String location) {
        this.location = location;
        return this;
    }

    public RecycleMachine setWeightAdded(float weightAdded) {
        this.weightAdded = weightAdded;
        return this;
    }

    public RecycleMachine setAllowedWeight(float allowedWeight) {
        this.allowedWeight = allowedWeight;
        return this;
    }

    public RecycleMachine setReturnableAmount(float returnableAmount) {
        this.returnableAmount = returnableAmount;
        return this;
    }

    public RecycleMachine setMoneyAvailable(float moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
        return this;
    }


    public RecycleMachine setOperational(boolean operational) {
        this.operational = operational;
        return this;
    }

    public RecycleMachine setDisbursedAmount(float disbursedAmount) {
        this.disbursedAmount = disbursedAmount;
        return this;
    }

    public RecycleMachine setTimeEmptied(String timeEmptied) {
        this.timeEmptied = timeEmptied;
        return this;
    }

    public RecycleMachine setRecycleItemList(List<RecycleItem> recycleItemList) {
        this.recycleItemList = recycleItemList;
        return this;
    }

    public String getMachineId() {
        return machineId;
    }

    public String getLocation() {
        return location;
    }

    public float getWeightAdded() {
        return weightAdded;
    }

    public float getAllowedWeight() {
        return allowedWeight;
    }

    public float getReturnableAmount() {
        return returnableAmount;
    }

    public float getMoneyAvailable() {
        return moneyAvailable;
    }

    public boolean isOperational() {
        return operational;
    }

    public String getTimeEmptied() {
        return timeEmptied;
    }

    public float getDisbursedAmount() {return disbursedAmount;}

    public List<RecycleItem> getRecycleItemList() {
        return recycleItemList;
    }

}
