package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Trapezoid extends Shape {

    public Trapezoid(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded coordinates for the trapezoid
        int x1 = 100; // X-coordinate of the first vertex (top left)
        int y1 = 50;  // Y-coordinate of the first vertex (top left)
        int x2 = 200; // X-coordinate of the second vertex (top right)
        int y2 = 50;  // Y-coordinate of the second vertex (top right)
        int x3 = 220; // X-coordinate of the third vertex (bottom right)
        int y3 = 100; // Y-coordinate of the third vertex (bottom right)
        int x4 = 80;  // X-coordinate of the fourth vertex (bottom left)
        int y4 = 100; // Y-coordinate of the fourth vertex (bottom left)

        // Create a path for the trapezoid
        Path2D.Double trapezoid = new Path2D.Double();
        trapezoid.moveTo(x1, y1);
        trapezoid.lineTo(x2, y2);
        trapezoid.lineTo(x3, y3);
        trapezoid.lineTo(x4, y4);
        trapezoid.closePath();

        // Draw the trapezoid
        g2d.fill(trapezoid);
    }
}

