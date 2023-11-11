package org.aubg;

import org.aubg.shapes.Shape;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.lang.reflect.Constructor;

public class DrawingPanel extends JPanel {

    // Fields to store the shape that should be drawn
    private Shape shape;

    // Constructor initializes the panel with default color and shape
    public DrawingPanel() {}

    public void setShape(String shapeTypeName, Color color) {
        try {
            // Assume shapeTypeName is a fully qualified class name
            Class<?> shapeClass = Class.forName(shapeTypeName);
            Constructor<?> constructor = shapeClass.getConstructor(Color.class);
            this.shape = (Shape) constructor.newInstance(color);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error gracefully, possibly by logging or notifying the user
        }
    }

    // Setter method to set the drawing shape
    public void drawShape(Shape shape) {
        this.shape = shape;
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        shape.draw(g, getWidth(), getHeight());
    }
}
