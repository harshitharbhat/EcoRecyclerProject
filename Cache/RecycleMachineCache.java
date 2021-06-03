package Cache;

import DTO.RecycleMachine;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RecycleMachineCache {

    private static Hashtable<String, RecycleMachine> itemsMap = new Hashtable<>();

    public static RecycleMachine getItem(String itemId){
//        @TODO try to use clone
//        RecycleMachine cachedItem = itemsMap.get(itemId);
//        return (RecycleMachine) cachedItem.clone();
        return itemsMap.get(itemId);
    }

    public static List<RecycleMachine> getAllItems(){
        //        @TODO try to use clone
//        RecyclableItem cachedItem = itemsMap.get(itemId);
//        return (RecyclableItem) cachedItem.clone();
        return (new ArrayList<>(itemsMap.values()));
    }

    public static void addNewItem(RecycleMachine rcm){
        itemsMap.put(rcm.getMachineId(),rcm);
    }

    public static void loadMachineCache(){
        RecycleMachine rcm1 = createRCM("1001", "Santa Clara", true,60,100,10);
        RecycleMachine rcm3 = createRCM("1012", "Milpitas", false,60,500,25);
        RecycleMachine rcm4 = createRCM("1013", "Alameda", true,60,450,35);
        RecycleMachine rcm5 = createRCM("1014", "Gilroy", true,60,100,50);
        RecycleMachine rcm6 = createRCM("1015", "Greenwich", false,60,350,25);
        RecycleMachine rcm7 = createRCM("1016", "Freemont", true,60,500,40);
        RecycleMachine rcm8 = createRCM("1017", "Fresno", true,60,390,0);
        RecycleMachine rcm11 = createRCM("1020", "Winchester St", true,60,500,25);
        RecycleMachine rcm13= createRCM("1022", "Bellomy", true,60,200,30);
        RecycleMachine rcm14= createRCM("1023", "San Thomas", true,60,350,22);
        RecycleMachine rcm15= createRCM("1027", "Saratoga", true,60,500,3);
        RecycleMachine rcm16 = createRCM("1050", "El Camino", true,60,200,7);



        itemsMap.put(rcm1.getMachineId(),rcm1);
        itemsMap.put(rcm3.getMachineId(),rcm3);
        itemsMap.put(rcm4.getMachineId(),rcm4);
        itemsMap.put(rcm5.getMachineId(),rcm5);
        itemsMap.put(rcm6.getMachineId(),rcm6);
        itemsMap.put(rcm7.getMachineId(),rcm7);
        itemsMap.put(rcm8.getMachineId(),rcm8);
        itemsMap.put(rcm11.getMachineId(),rcm11);
        itemsMap.put(rcm13.getMachineId(),rcm13);
        itemsMap.put(rcm14.getMachineId(),rcm14);
        itemsMap.put(rcm15.getMachineId(),rcm15);
        itemsMap.put(rcm16.getMachineId(),rcm16);


    }

    private static RecycleMachine createRCM(String id, String location, boolean op, float capacity, float amount,float disbursedAmt) {
        RecycleMachine rcm = new RecycleMachine();
        rcm.setMachineId(id);
        rcm.setAllowedWeight(capacity);
        rcm.setLocation(location);
        rcm.setOperational(op);
        rcm.setMoneyAvailable(amount);
        rcm.setDisbursedAmount(disbursedAmt);
        return rcm;
    }
}
