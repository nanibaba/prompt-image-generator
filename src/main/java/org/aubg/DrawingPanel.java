package org.aubg;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

    private Color color; 
    private String shape;

    public DrawingPanel() {
        this.color = Color.WHITE;
        this.shape = "";
    }

    public void setDrawingColor(Color color) {
        this.color = color;
    }

    public void setDrawingShape(String shape) {
        this.shape = shape;
    }

    public void drawShape() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Setting shape color
        g.setColor(color);

        if (shape.equals("circle")) {
            // Calculate the circle's coordinates and size
            int x = (getWidth() - 100) / 2;
            int y = (getHeight() - 100) / 2;

            // Draw the circle
            g.fillOval(x, y, 100, 100);

        } else if (shape.equals("triangle")) {
            // Calculate the triangle's coordinates
            int x1 = getWidth() / 2;
            int y1 = (getHeight() - 100) / 2;

            int x2 = x1 - 50;
            int y2 = y1 + 100;

            int x3 = x1 + 50;
            int y3 = y2;

            // Draw the triangle
            int[] xPoints = {x1, x2, x3};
            int[] yPoints = {y1, y2, y3};
            int nPoints = 3;
            g.fillPolygon(xPoints, yPoints, nPoints);

        } else if (shape.equals("square")) {
             // Calculate the square's coordinates and size
             int x = (getWidth() - 100) / 2;
             int y = (getHeight() - 100) / 2;
 
             // Draw the square
             g.fillRect(x, y, 100, 100);
        }
        
        // Reset color and shape
        color = Color.WHITE;
        shape = "";
    }
}
