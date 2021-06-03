package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class BarChart extends JPanel {

    private Map<Integer, Float> bars = new LinkedHashMap<Integer, Float>();

    String title = "";



    public void addBar(int key, float value) {
        bars.put(key, value);
        //repaint();
    }

    public void addTitle(String title) {
        this.title = title;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Color color = new Color((int)(Math.random() * 0x1000000));
        float max = Integer.MIN_VALUE;
        for (float value : bars.values()) {
            max = Math.max(max, value);
        }
        int width = (getWidth() / bars.size()) - 2;
        int x = 1;

        int panelWidth = getPreferredSize().width;
        Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);

        Font labelFont = new Font("Book Antiqua", Font.PLAIN, 14);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth(title);
        int stringHeight = titleFontMetrics.getAscent();
        int stringWidth = (panelWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, stringWidth, stringHeight);
        g.setFont(labelFont);
        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        int j = 0;
        for (int key : bars.keySet()) {

            float value = bars.get(key);
            int height = (int)((getHeight()-5) * ((double)value / max));
            g.setColor(color);
            g.fillRect(x, getHeight() - height, width, height);
            g.setColor(Color.black);
            g.drawRect(x, getHeight() - height, width, height);
            x += (width + 2);

            int labelWidth = labelFontMetrics.stringWidth(String.valueOf(key));
            stringWidth = j * width + (width - labelWidth) / 2;
            g.drawString(String.valueOf(key), stringWidth+15, stringHeight+300);
            j++;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(bars.size() * 50 + 2, 500);
    }

}
