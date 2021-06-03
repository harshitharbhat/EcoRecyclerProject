package GUI;


import Cache.RecycleItemCache;
import Cache.RecycleMachineCache;
import DTO.RecycleMachine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class RecyclableMachineContent implements ActionListener {

    private static RecyclableMachineContent single_instance = null;

    private RecyclableMachineContent(){};
    public static RecyclableMachineContent getInstance()
    {
        if (single_instance == null)
            single_instance = new RecyclableMachineContent();

        return single_instance;
    }

    DefaultTableModel model = new DefaultTableModel();
    public JPanel displayMachineContent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(1000,1000);

        String[] columns = {"ID","Location","Current Weight","Amount","Last Emptied","Status"};
        List<RecycleMachine> itemList = RecycleMachineCache.getAllItems();

        JTable table = new JTable();
        table.setModel(model);
        int i = 1;
        model.setColumnIdentifiers(columns);
        for (RecycleMachine rcm : itemList) {
            String[] options = {"true","false"};
            Object[] o = new Object[6];
            o[0] = rcm.getMachineId();
            o[1] = rcm.getLocation();
            o[2] = rcm.getAllowedWeight();
            o[3] = rcm.getMoneyAvailable();
            o[4] = rcm.getTimeEmptied();
            o[5] = rcm.isOperational() ? "Available" : "Down";
            model.addRow(o);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane,BorderLayout.CENTER);
        final JTable dialogTable =new JTable();
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow > -1) {
                    int columnCount = table.getModel().getColumnCount();
                    Object[] column = new Object[]{"Row "+(selectedRow+1)};
                    Object[][] data = new Object[columnCount][1];
                    for (int i = 0; i < columnCount; i++) {
                        Object obj = table.getModel().getValueAt(selectedRow, i);
                        data[i][0] = obj;
                    }
                    dialogTable.setModel(new DefaultTableModel(data, column));
                    JOptionPane.showMessageDialog(null, new JScrollPane(dialogTable));
                }
            }
        });


        panel.add(createButtons(),BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createButtons() {
        JPanel subPanel = new JPanel();
        JButton button1 = new JButton("Add Machine" );
        JButton button2 = new JButton("Change op status" );
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options1 = {"true","false"};
                String[] options2 = new String[RecycleMachineCache.getAllItems().size()];
                int i = 0;
                for(RecycleMachine rcm : RecycleMachineCache.getAllItems()){
                    options2[i] = rcm.getMachineId() + "-"+ rcm.getLocation();
                    i++;
                }
                JComboBox comboBox1 = new JComboBox(options1);
                JComboBox comboBox2 = new JComboBox(options2);
                final JComponent[] inputs = new JComponent[] {
                        new JLabel("Operational"),
                        comboBox2,
                        new JLabel("Activation Status"),
                        comboBox1
                };
                int result = JOptionPane.showConfirmDialog(null, inputs,
                        "Activate/Deactivate machine", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    System.out.println("New Item entered :  " +
                            comboBox2.getSelectedItem());
                    String id = comboBox2.getSelectedItem().toString().split("-")[0];
                    setOperationalStatus(id,comboBox1.getSelectedItem().toString(),comboBox2.getSelectedIndex());
                } else {
                    System.out.println("Activation status not changed");
                }
            }
        });
        button1.addActionListener(this);
        subPanel.add(button1);
        subPanel.add(button2);
        return subPanel;
    }

    private void setOperationalStatus(String id, String status,int index) {
        RecycleMachine rcm = RecycleMachineCache.getItem(id);
        boolean st = status.equals("true")?true : false;
        if(rcm.isOperational() != st){
            rcm.setOperational(st);
            JOptionPane.showMessageDialog(null,"Successfully updated operational status of Machine" + id);
            String o = rcm.isOperational() ? "Available" : "Down";;
            model.setValueAt(o,index,5);
            model.fireTableCellUpdated(index,5);
            updateRcm(id);
        }
    }

    private void updateRcm(String id) {
        if(id.equals("1001") || id.equals("1012")) {
            JFrame frame = FrameCache.getItem(Integer.parseInt(id));
            frame.dispose();

            FrameCreator fc1 = new FrameCreator();
            JFrame frame1 = fc1.createFrame(RecycleMachineCache.getItem(id));
            FrameCache.itemsMap.put(1001, frame1);
        }
    }

    public boolean addNewItem(String id, String location, float weight,String operational,float money){
        boolean isOperational = (operational.equals("true"))? true : false;
        RecycleMachine rcm = new RecycleMachine();
        rcm.setMachineId(id);
        rcm.setOperational(isOperational);
        rcm.setLocation(location);
        rcm.setAllowedWeight(weight);
        rcm.setMoneyAvailable(money);
        rcm.setRecycleItemList(RecycleItemCache.getAllItems());

        rcm.setTimeEmptied(new Date().toString());
        RecycleMachineCache.addNewItem(rcm);
        Object[] o = new Object[6];
        o[0] = rcm.getMachineId();
        o[1] = rcm.getLocation();
        o[2] = rcm.getAllowedWeight();
        o[3] = rcm.getMoneyAvailable();
        o[4] = rcm.getTimeEmptied();
        o[5] = rcm.isOperational() ? "Available" : "Down";
        model.addRow(o);

        FrameCreator fc = new FrameCreator();
        fc.createFrame(rcm);
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField id = new JTextField();
        JTextField location = new JTextField();
        JTextField amount = new JTextField();
        JTextField weight = new JTextField();
        String[] options = {"true","false"};
        JComboBox comboBox = new JComboBox(options);
        final JComponent[] inputs = new JComponent[] {
                new JLabel("ID"),
                id,
                new JLabel("Location"),
                location,
                new JLabel("Money Added"),
                amount,
                new JLabel("Allowed Weight"),
                weight,
                new JLabel("Operational"),
                comboBox
        };
        int result = JOptionPane.showConfirmDialog(null, inputs,
                "Adding New Machine", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("New Item entered :  " +
                    id.getText() + ", " +
                    location.getText() + ", " +
                    comboBox.getSelectedItem());
        } else {
            System.out.println("Add new Item  : User canceled / closed the dialog, result = " + result);
        }
        validateValues(id.getText(),location.getText(),weight.getText(),comboBox.getSelectedItem().toString(),amount.getText());
    }

    private void validateValues(String id, String location, String weight,String operational,String amount) {
        boolean err=true;
        try{
            Integer.parseInt(id);
            Float.parseFloat(weight);
            Float.parseFloat(amount);
            err=false;
        }catch(NumberFormatException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage() +
                    "Unable to add item");
        }
        if(!err){
            JOptionPane.showMessageDialog(null,"Successfully added a new RCM");
            addNewItem(id,
                    location,
                    Float.parseFloat(weight),
                    operational ,
                    Float.parseFloat(amount));
        }

    }

    public void updateAmount(int index,float updatedAmt){
        float amount = updatedAmt;
        model.setValueAt(amount,index,3);
        model.fireTableCellUpdated(index,3);

    }

    public void updateWeight(int index,float updatedWeight){
        float weight = updatedWeight;
        model.setValueAt(weight,index,2);
        model.fireTableCellUpdated(index,2);

    }
}
