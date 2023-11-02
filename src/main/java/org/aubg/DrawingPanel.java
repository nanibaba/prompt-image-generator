package org.aubg;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

    // Fields to store the color and shape that should be drawn
    private Color color; 
    private String shape;
    // Fields to store the current color and shape being drawn
    private Color currentColor;
    private String currentShape; 

    // Constructor initializes the panel with default color and shape
    public DrawingPanel() {
        this.color = Color.WHITE;
        this.shape = "";
        this.currentColor = color;
        this.currentShape = shape;
    }

    // Setter method to set the drawing color
    public void setDrawingColor(Color color) {
        this.color = color;
    }

    // Setter method to set the drawing shape
    public void setDrawingShape(String shape) {
        this.shape = shape;
    }

    // Method to update the current drawing color and shape, and then repaint the panel
    public void drawShape() {
        currentColor = color;
        currentShape = shape;

        // Reset color and shape to default values after drawing
        color = Color.WHITE;
        shape = "";
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Setting the color for the shape to be drawn
        g.setColor(currentColor);

        // Draw a circle if the current shape is "circle"
        if (currentShape.equals("circle")) {
            // Calculate the circle's coordinates and size
            int x = (getWidth() - 100) / 2;
            int y = (getHeight() - 100) / 2;

            // Draw the circle
            g.fillOval(x, y, 100, 100);

        // Draw a triangle if the current shape is "triangle"
        } else if (currentShape.equals("triangle")) {
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

        // Draw a square if the current shape is "square"
        } else if (currentShape.equals("square")) {
             // Calculate the square's coordinates and size
             int x = (getWidth() - 100) / 2;
             int y = (getHeight() - 100) / 2;
 
             // Draw the square
             g.fillRect(x, y, 100, 100);
        }
    }
}
