package org.aubg;

import org.aubg.shapes.Shape;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.lang.reflect.Constructor;

public class DrawingPanel extends JPanel {

    // Fields to store the shape and color that should be drawn
    private Color color;
    private String shapeTypeName; 
    private Shape shape;

    // Fields to store the current color
    private Color currentColor;

    // Constructor initializes the panel with default color and shape
    public DrawingPanel() {
        this.color = Color.WHITE;
        this.shapeTypeName = "";
        this.currentColor = color;
    }

    // Setter method to set the drawing color
    public void setDrawingColor(Color color) {
        this.color = color;
    }

    // Setter method to set the drawing shape
    public void setDrawingShape(String targetClass) {
        currentColor = color;
        shapeTypeName = targetClass;
        try {
            Class<?> shapeClass = Class.forName(shapeTypeName);
            Constructor<?> constructor = shapeClass.getConstructor(Color.class);
            this.shape = (Shape) constructor.newInstance(currentColor);
             // Reset color to default value after drawing
            color = Color.WHITE;
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (shape != null) {
            shape.draw(g);
        }
    }
}
