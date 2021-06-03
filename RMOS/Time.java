package RMOS;

import DTO.RecycleMachine;
import Interfaces.ValueDecorator;

import java.sql.Timestamp;
import java.util.Date;

public class Time implements ValueDecorator {

    RecycleMachine rcm;

    public Time(RecycleMachine rcm){
        this.rcm = rcm;
    }

    @Override
    public String updateValue(String updatedTime) {
        if(updatedTime.isEmpty()){
            int startYear=2021;
            int endYear=2021;
            long start = Timestamp.valueOf(startYear+1+"-1-1 0:0:0").getTime();
            long end = Timestamp.valueOf(endYear+"-1-1 0:0:0").getTime();
            long ms=(long) ((end-start)*Math.random()+start);
            Date newDate =new Date(ms);
            updatedTime = newDate.toString();
        }
        return updatedTime;
    }
}
