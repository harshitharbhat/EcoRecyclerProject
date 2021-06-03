import Cache.RecycleItemCache;
import Cache.RecycleMachineCache;
import DTO.RecycleMachine;
import GUI.RcmGui;
import GUI.RmosLogin;
import RCM.RcmCreator;

import java.util.List;

public class EcoRecycler {
    public static void main(String[] args) {
        RecycleItemCache.loadCache();
        RecycleMachineCache.loadMachineCache();

        RcmCreator rcmCreator = new RcmCreator();
        List<RecycleMachine> recycleMachines = rcmCreator.rcmCreator();

        RcmGui rcmGui = new RcmGui();
        rcmGui.createMachineUI(recycleMachines);

//        RmosGui rmosGui = new RmosGui();
//        rmosGui.createRMOS();

        RmosLogin rmosLogin = new RmosLogin();
        rmosLogin.rmosLogin();

    }
}
