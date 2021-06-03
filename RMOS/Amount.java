package RMOS;

import DTO.RecycleMachine;
import Interfaces.ValueDecorator;

import java.util.Date;

public class Amount implements ValueDecorator {

    RecycleMachine rcm;

    float updatedAmount;

    String timeIssued;

    public Amount(RecycleMachine rcm){
        this.rcm = rcm;
    }


    @Override
    public String updateValue(String value) {
        updatedAmount = Float.parseFloat(value);
        rcm.setDisbursedAmount(updatedAmount);
        return value;
    }

}
