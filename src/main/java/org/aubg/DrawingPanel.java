package org.aubg;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

    private boolean shouldDrawCircle = false;
    private Color color; 

    public DrawingPanel() {
        this.color = new Color(255, 255, 255);
    }

    public void setDrawingColor(Color color) {
        this.color = color;
    }

    public void drawCircle() {
        shouldDrawCircle = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (shouldDrawCircle) {
            g.setColor(color);

            // Calculate the circle's coordinates and size
            int x = (getWidth() - 100) / 2;
            int y = (getHeight() - 100) / 2;

            // Draw the circle
            g.fillOval(x, y, 100, 100);
        }
    }
}
