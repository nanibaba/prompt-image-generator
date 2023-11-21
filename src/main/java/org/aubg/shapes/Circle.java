package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Circle class extending the Shape abstract class.
 * Represents a circle shape with specific drawing behavior.
 */
public class Circle extends Shape {
    
    /**
     * Constructor to initialize a Circle with a color.
     * @param color The color of the circle.
     */
    public Circle(Color color) {
        super(color); // Call to the superclass (Shape) constructor
    }

    /**
     * Overrides the draw method from Shape to provide specific drawing logic for a circle.
     * @param g Graphics context used for drawing.
     */
    @Override
    public void draw(Graphics g) {
        // Set the color for the circle
        g.setColor(color);
        
        // Fixed position and size values for the circle
        int x = 100; // x-coordinate of the top-left corner of the circle
        int y = 25;  // y-coordinate of the top-left corner of the circle
        
        // Draw a filled oval representing the circle
        g.fillOval(x, y, 100, 100); // width and height are both 100, making it a perfect circle
    }
}
