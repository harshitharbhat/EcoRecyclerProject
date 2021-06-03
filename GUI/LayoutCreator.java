package GUI;

import Cache.RecycleItemCache;
import DTO.RecycleItem;
import DTO.RecycleMachine;
import RMOS.StatsCalculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
//@TODO fix layout and updating UI
public class LayoutCreator implements ActionListener{
    JPanel panel = new JPanel(new BorderLayout());
    float returnableAmount = 0;
    float totalWeight = 0;
    RecycleMachine rcm;
    LayoutCreator(RecycleMachine rcm){
        this.rcm = rcm;
    }

    public JPanel createLayout(){
        panel.add(addMachineDetails(),BorderLayout.NORTH);
        panel.add(addItemsData(),BorderLayout.CENTER);
        panel.add(createButton(),BorderLayout.WEST);
        panel.add(updatedValues("0",""),BorderLayout.SOUTH);
        return panel;
    }

    public JPanel addMachineDetails() {
        JPanel subPanel = new JPanel(new BorderLayout());
        subPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        String data = "Machine ID : " + rcm.getMachineId()
                        + "\nLocation : " + rcm.getLocation()
                        +"\nLast Emptied : " + rcm.getTimeEmptied();
        JTextArea textArea = new JTextArea(data);
        subPanel.add(textArea,BorderLayout.CENTER);
        subPanel.add(addCapacityDetails(),BorderLayout.SOUTH);
        subPanel.add(addOpStatus(),BorderLayout.EAST);
        return subPanel;
    }
    JPanel subPanelWeight = new JPanel(new BorderLayout());
    public JPanel addCapacityDetails(){
        subPanelWeight.removeAll();
        String data = "Remaining capacity : " + (rcm.getAllowedWeight() - totalWeight) + " lbs";

        JTextArea textArea = new JTextArea(data);
        subPanelWeight.add(textArea);
        return subPanelWeight;
    }

    JPanel opStatus = new JPanel(new BorderLayout());
    public JPanel addOpStatus(){
        opStatus.removeAll();
        String data = (rcm.isOperational() ? "Available" : "Down") ;
        JTextArea textArea = new JTextArea(data);
        opStatus.add(textArea);
        textArea.setForeground(rcm.isOperational() ? Color.GREEN : Color.RED);
        return opStatus;
    }
    DefaultTableModel model = new DefaultTableModel();
    public JPanel addItemsData() {
        JPanel subPanel = new JPanel();
        subPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        String[] columns = {"ID","Item Type","Price"};
        List<RecycleItem> itemList = rcm.getRecycleItemList();

        JTable table = new JTable();

        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model.setColumnIdentifiers(columns);
        for (RecycleItem ri : itemList) {
            Object[] o = new Object[3];
            o[0] = ri.getItemId();
            o[1] = ri.getType();
            o[2] = ri.getAmount();
            model.addRow(o);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        subPanel.add(scrollPane,BorderLayout.CENTER);
        return subPanel;
    }

    //@TODO Use this code. Figure out how to get the frame
    public void addNewItem(Integer id, String itemType, float amount){
        RecycleItem item = new RecycleItem();
        item.setType(itemType);
        item.setId(id);
        item.setAmount(amount);
        RecycleItemCache.addNewItem(item);
        Object[] o = new Object[3];
        o[0] = id;
        o[1] = itemType;
        o[2] = amount;
        model.addRow(o);
    }

    private JPanel createButton() {
        JPanel subPanel = new JPanel(new BorderLayout());
        subPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        JLabel label = new JLabel("Please insert items to be recycled");
        JButton button = new JButton("Insert Item");
        button.setPreferredSize(new Dimension(200, 20));
        button.addActionListener(this);
        subPanel.add(label,BorderLayout.NORTH);
        subPanel.add(button,BorderLayout.SOUTH);
        return subPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(rcm.isOperational()) {
            String[] options = new String[RecycleItemCache.getAllItems().size()];
            JTextField weight = new JTextField();
            int i = 0;
            for (RecycleItem ri : RecycleItemCache.getAllItems()) {
                options[i] = ri.getType() + " - " + ri.getAmount();
                i++;
            }

            JComboBox comboBox = new JComboBox(options);
            final JComponent[] inputs = new JComponent[]{
                    new JLabel("Item Type"),
                    comboBox,
                    new JLabel("Please Enter Weight of Item"),
                    weight,
            };
            int result = JOptionPane.showConfirmDialog(null, inputs,
                    "Recycle Item", JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                System.out.println("New Item entered :  " +
                        weight.getText() + ", " +
                        comboBox.getSelectedItem());
                String[] selected = String.valueOf(comboBox.getSelectedItem()).split(" - ");
                returnableAmount += Float.parseFloat(weight.getText()) * Float.parseFloat(selected[1]);
                totalWeight += Float.parseFloat(weight.getText());
                if (totalWeight > rcm.getAllowedWeight()) {
                    JOptionPane.showMessageDialog(null, "Weight exceeds capacity of machine. Please reduce the weight of the input item");
                    totalWeight -= Float.parseFloat(weight.getText());
                } else {
                    rcm.setWeightAdded(totalWeight);
                    rcm.setReturnableAmount(returnableAmount);
                    panel.add(updatedValues(weight.getText(), selected[0]), BorderLayout.SOUTH);
                    panel.repaint();
                    panel.revalidate();
                }

            } else {
                System.out.println("Add new Item  : User canceled / closed the dialog, result = " + result);
            }
        } else {
            JOptionPane.showMessageDialog(null,"Recycling machine outlet is currently inoperational. " +
                    "Please try again later");
        }
    }
    JPanel subPanel1 = new JPanel();
    public JPanel updatedValues(String weight,String item){
        subPanel1.removeAll();
        subPanel1.setBorder(new EmptyBorder(10, 5, 10, 5));
        JLabel label1 = new JLabel("TOTAL AMOUNT RETURNABLE :    $"  + returnableAmount);
        JLabel label2 = new JLabel("How would you like to be paid?");
        JLabel label = new JLabel("<html> <p> Added " + weight + "lbs of " + item + "</p></html>");
        addCapacityDetails();
        subPanel1.add(label,BorderLayout.NORTH);
        subPanel1.add(label1,BorderLayout.CENTER);
        subPanel1.add(label2,BorderLayout.SOUTH);
        subPanel1.add(returnAmount());
        subPanel1.revalidate();
        subPanel1.repaint();
        return subPanel1;
    }

    private JPanel returnAmount() {
        JPanel subPanel2 = new JPanel();
        String[] options = new String[]{"Select option","Cash","Coupon"};
        JComboBox comboBox = new JComboBox(options);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Return "+comboBox.getSelectedItem() + "?");
                if(result == JOptionPane.OK_OPTION){
                    rcm.setAllowedWeight(rcm.getAllowedWeight() - totalWeight);
                    rcm.setMoneyAvailable(rcm.getMoneyAvailable() - returnableAmount);
                    updateValuesAvailable();
                    returnableAmount = 0;
                    updatedValues("0","");
                    addCapacityDetails();
                }
            }
        });

        subPanel2.add(comboBox);
        return subPanel2;
    }

    private void updateValuesAvailable() {
        StatsCalculator statsCalculator = new StatsCalculator(rcm);
        statsCalculator.getCashDistributed(returnableAmount);
        statsCalculator.getUpdatedWeight();
    }

}
