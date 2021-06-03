package GUI;

import Cache.RecycleMachineCache;
import DTO.RecycleMachine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GraphCreator{

    public JPanel displayGraph() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel subPanel1 = new JPanel(new BorderLayout());
        JRadioButton radioButton1 = new JRadioButton();
        JRadioButton radioButton2 = new JRadioButton();

        radioButton1.setText("Graph");
        radioButton1.setActionCommand("Graph");
        radioButton1.setSelected(true);
        radioButton2.setText("Text");
        radioButton2.setActionCommand("Text");


        JLabel label = new JLabel("Select type");
        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);

        subPanel1.add(label,BorderLayout.NORTH);
        subPanel1.add(radioButton1,BorderLayout.EAST);
        subPanel1.add(radioButton2,BorderLayout.WEST);


        JPanel subPanel2 = new JPanel();
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subPanel2.removeAll();
                    BarChart chart = new BarChart();
                    for(RecycleMachine rcm : RecycleMachineCache.getAllItems()){
                        chart.addBar(Integer.parseInt(rcm.getMachineId()), rcm.getDisbursedAmount());
                    }
                    chart.addTitle("Amount disbursed in the past 7 days");
                subPanel2.add(chart,BorderLayout.CENTER);
                subPanel2.repaint();
                subPanel2.revalidate();
            }
        });


        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subPanel2.removeAll();
                DefaultTableModel model = new DefaultTableModel();
                subPanel2.setBorder(new EmptyBorder(10, 10, 10, 10));
                JPanel panel = new JPanel();
                String[] columns = {"ID","Location","Amount Disbursed"};
                List<RecycleMachine> itemList = RecycleMachineCache.getAllItems();
                JTable table = new JTable();
                table.setModel(model);
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                model.setColumnIdentifiers(columns);

                for (RecycleMachine rcm : itemList) {
                    Object[] o = new Object[3];
                    o[0] = rcm.getMachineId();
                    o[1] = rcm.getLocation();
                    o[2] = rcm.getDisbursedAmount();
                    model.addRow(o);
                }
                JScrollPane scrollPane = new JScrollPane(table);
                panel.add(scrollPane);
                //JLabel label1 = new JLabel("Amount Disbursed in the past 7 days");
                //subPanel2.add(label1,BorderLayout.NORTH);
                subPanel2.add(table);
                subPanel2.revalidate();
                subPanel2.repaint();
            }
        });

        panel.add(subPanel1,BorderLayout.NORTH);
        panel.add(subPanel2,BorderLayout.SOUTH);
        return panel;
    }




}
