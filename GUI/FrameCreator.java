package GUI;

import DTO.RecycleMachine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrameCreator{


    public JFrame createFrame(RecycleMachine rcm){
        JFrame frame = new JFrame("Recycling Machine Outlet");
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.add(new LayoutCreator(rcm).createLayout());
        frame.setSize(700,600);
        frame.setVisible(true);
        return frame;
    }

}
