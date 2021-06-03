package RMOS;

import DTO.RecycleMachine;
import Interfaces.ValueDecorator;

public class Weight implements ValueDecorator {

    float updatedWeight;

    RecycleMachine rcm;

    public Weight(RecycleMachine rcm){
        this.rcm = rcm;
    }

    @Override
    public String updateValue(String updatedWeight) {
        this.updatedWeight = Float.parseFloat(updatedWeight);
        float allowedWeight = rcm.getAllowedWeight();
        rcm.setWeightAdded(rcm.getWeightAdded() + this.updatedWeight);
        return String.valueOf((allowedWeight - this.updatedWeight));
    }
}
