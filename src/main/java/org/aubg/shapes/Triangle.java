package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Triangle class extending the Shape abstract class.
 * Represents a triangle shape with specific drawing behavior.
 */
public class Triangle extends Shape {

    /**
     * Constructor to initialize a Triangle with a color.
     * @param color The color of the triangle.
     */
    public Triangle(Color color) {
        super(color); // Call to the superclass (Shape) constructor
    }

    /**
     * Overrides the draw method from Shape to provide specific drawing logic for a triangle.
     * @param g Graphics context used for drawing.
     */
    @Override
    public void draw(Graphics g) {
        // Set the color for the triangle
        g.setColor(color);

        // Coordinates for the three vertices of the triangle
        int x1 = 150; // x-coordinate of the first vertex
        int y1 = 25;  // y-coordinate of the first vertex
        int x2 = x1 - 50; // x-coordinate of the second vertex
        int y2 = y1 + 100; // y-coordinate of the second vertex
        int x3 = x1 + 50; // x-coordinate of the third vertex
        int y3 = y2;      // y-coordinate of the third vertex

        // Arrays to hold the x and y coordinates of the triangle's vertices
        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};

        // Draw a filled triangle using the arrays of x and y coordinates
        g.fillPolygon(xPoints, yPoints, 3); // The '3' indicates that the polygon has 3 vertices
    }
}
