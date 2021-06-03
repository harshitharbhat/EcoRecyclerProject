package GUI;

import Cache.RecycleMachineCache;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class FrameCache {

    public static Hashtable<Integer, JFrame> itemsMap = new Hashtable<>();

    public static JFrame getItem(Integer itemId){
        return itemsMap.get(itemId);
    }

    public static List<JFrame> getAllItems(){
        return (new ArrayList<>(itemsMap.values()));
    }

    public void createFrameCache(){
        FrameCreator fc1 = new FrameCreator();
        FrameCreator fc2 = new FrameCreator();
        JFrame frame1 = fc1.createFrame(RecycleMachineCache.getItem("1001"));
        JFrame frame2 = fc2.createFrame(RecycleMachineCache.getItem("1012"));
        itemsMap.put(1001,frame1);
        itemsMap.put(1012,frame2);
    }
}
