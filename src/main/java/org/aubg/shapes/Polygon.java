package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/**
 * Polygon class extending the Shape abstract class.
 * Represents a polygon with a customizable number of sides and radius.
 */
public class Polygon extends Shape {

    private int radius; // Radius of the polygon
    private int sides;  // Number of sides of the polygon

    /**
     * Constructor to initialize a Polygon with a color.
     * @param color The color of the polygon.
     */
    public Polygon(Color color) {
        super(color); // Call to the superclass (Shape) constructor
    }

    /**
     * Sets the radius of the polygon.
     * @param radius The radius to set.
     */
    public void setRadius(int radius){
        this.radius = radius;
    }

    /**
     * Sets the number of sides of the polygon.
     * @param sides The number of sides to set.
     */
    public void setSides(int sides){
        this.sides = sides;
    }

    /**
     * Overrides the draw method from Shape to provide specific drawing logic for a polygon.
     * @param g Graphics context used for drawing.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color); // Set the color for the polygon

        // Hard-coded center coordinates for the polygon
        int centerX = 150; // X-coordinate of the center of the polygon
        int centerY = 100; // Y-coordinate of the center of the polygon

        // Create a path representing the polygon
        Path2D.Double polygon = new Path2D.Double();
        polygon.moveTo(
            centerX + radius * Math.cos(0), 
            centerY - radius * Math.sin(0));

        // Add points for each vertex of the polygon
        for (int i = 1; i < sides; i++) {
            polygon.lineTo(
                centerX + radius * Math.cos(i * 2 * Math.PI / sides),
                centerY - radius * Math.sin(i * 2 * Math.PI / sides));
        }
        
        polygon.closePath(); // Close the path of the polygon
        g2d.fill(polygon);   // Fill the polygon with the specified color
    }
}
