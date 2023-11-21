package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Ellipse class extending the Shape abstract class.
 * Represents an ellipse shape with specific drawing behavior.
 */
public class Ellipse extends Shape {

    /**
     * Constructor to initialize an Ellipse with a color.
     * @param color The color of the ellipse.
     */
    public Ellipse(Color color) {
        super(color); // Call to the superclass (Shape) constructor
    }

    /**
     * Overrides the draw method from Shape to provide specific drawing logic for an ellipse.
     * @param g Graphics context used for drawing.
     */
    @Override
    public void draw(Graphics g) {
        // Cast Graphics object to Graphics2D for more advanced drawing capabilities
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color); // Set the color for the ellipse

        // Hard-coded coordinates and dimensions for the ellipse
        int x = 100; // X-coordinate of the top-left corner of the bounding rectangle
        int y = 50;  // Y-coordinate of the top-left corner of the bounding rectangle
        int width = 120; // Width of the ellipse (bounding rectangle)
        int height = 80; // Height of the ellipse (bounding rectangle)

        // Create and draw the ellipse
        Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, width, height);
        g2d.fill(ellipse); // Fill the ellipse with the specified color
    }
} 