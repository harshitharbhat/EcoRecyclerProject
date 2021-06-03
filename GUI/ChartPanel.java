package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ChartPanel extends JPanel {
    private double[] values;
    private String[] labels;
    private Color[] colors;
    private String title;

    public ChartPanel(double[] values, String[] labels, Color[] colors, String title) {
        this.labels = labels;
        this.values = values;
        this.colors = colors;
        this.title = title;
    }

    public void paintComponent(Graphics g) {

    }
}
