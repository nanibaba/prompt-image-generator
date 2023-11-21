package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/**
 * Star class extending the Shape class.
 * Represents a star shape with specific drawing behavior.
 */
public class Star extends Shape {

    /**
     * Constructor to initialize a Star with a color.
     * @param color The color of the star.
     */
    public Star(Color color) {
        super(color); // Call to the superclass (Shape) constructor
    }

    /**
     * Overrides the draw method from Shape to provide specific drawing logic for a star.
     * @param g Graphics context used for drawing.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color); // Set the color for the star

        // Hard-coded center coordinates, outer radius, and inner radius for the star
        int centerX = 150; // X-coordinate of the center of the star
        int centerY = 100; // Y-coordinate of the center of the star
        int radius = 40;   // Outer radius of the star
        int innerRadius = 20; // Inner radius of the star

        // Create a path representing the star
        Path2D.Double star = new Path2D.Double();
        double angle = Math.PI / 5; // Angle increment for drawing star points

        // Loop to add points to the path, alternating between outer and inner radius
        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? radius : innerRadius; // Alternate radius
            double x = centerX + r * Math.cos(i * angle);  // X-coordinate of the point
            double y = centerY - r * Math.sin(i * angle);  // Y-coordinate of the point

            if (i == 0) {
                star.moveTo(x, y); // Move to the first point
            } else {
                star.lineTo(x, y); // Draw line to each subsequent point
            }
        }
        
        star.closePath(); // Close the path of the star
        g2d.fill(star);   // Fill the star with the specified color
    }
}