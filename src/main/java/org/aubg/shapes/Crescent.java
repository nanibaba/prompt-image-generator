package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Crescent extends Shape {

    public Crescent(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded dimensions and position for the larger circle
        int x1 = 100; // X-coordinate of the top-left corner
        int y1 = 50;  // Y-coordinate of the top-left corner
        int diameter1 = 100; // Diameter of the larger circle

        // Hard-coded dimensions and position for the smaller circle
        int x2 = 120; // X-coordinate of the top-left corner
        int y2 = 50;  // Y-coordinate of the top-left corner
        int diameter2 = 100; // Diameter of the smaller circle

        // Create the larger circle
        Ellipse2D.Double largerCircle = new Ellipse2D.Double(x1, y1, diameter1, diameter1);

        // Create the smaller circle
        Ellipse2D.Double smallerCircle = new Ellipse2D.Double(x2, y2, diameter2, diameter2);

        // Subtract the smaller circle from the larger circle to create a crescent shape
        Area crescent = new Area(largerCircle);
        crescent.subtract(new Area(smallerCircle));

        // Draw the crescent
        g2d.fill(crescent);
    }
}
