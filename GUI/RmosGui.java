package GUI;

import javax.swing.*;

public class RmosGui {

    private static RmosGui single_instance = null;
    private RmosGui(){};
    public static RmosGui getInstance()
    {
        if (single_instance == null)
            single_instance = new RmosGui();

        return single_instance;
    }

    public void createRMOS(){
        JFrame frame = new JFrame("Recycle Machines Administrator");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(createTabs());
        frame.setVisible(true);

    }
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    private JTabbedPane createTabs() {
        machineContent();
        panel2.add(new RecyclableItemContent().displayItemsContent());
        addGraph();
        tabbedPane.addTab("Recycling Machines ", panel1);
        tabbedPane.addTab("Recyclable Items", panel2);
        tabbedPane.addTab("Stats and Analytics", panel3);
        return tabbedPane;
    }

    public void addGraph(){
        panel3.removeAll();
        panel3.add(new GraphCreator().displayGraph());
    }

    public void machineContent(){
        panel1.add(RecyclableMachineContent.getInstance().displayMachineContent());
    }

}
