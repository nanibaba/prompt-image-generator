package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Rhombus extends Shape {

    public Rhombus(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded coordinates for the rhombus
        int centerX = 150; // X-coordinate of the center
        int centerY = 100; // Y-coordinate of the center
        int width = 100;   // Width of the rhombus
        int height = 60;   // Height of the rhombus

        // Calculate corner points
        int x1 = centerX;                // X-coordinate of the top corner
        int y1 = centerY - height / 2;   // Y-coordinate of the top corner
        int x2 = centerX + width / 2;    // X-coordinate of the right corner
        int y2 = centerY;                // Y-coordinate of the right corner
        int x3 = centerX;                // X-coordinate of the bottom corner
        int y3 = centerY + height / 2;   // Y-coordinate of the bottom corner
        int x4 = centerX - width / 2;    // X-coordinate of the left corner
        int y4 = centerY;                // Y-coordinate of the left corner

        // Create a path for the rhombus
        Path2D.Double rhombus = new Path2D.Double();
        rhombus.moveTo(x1, y1);
        rhombus.lineTo(x2, y2);
        rhombus.lineTo(x3, y3);
        rhombus.lineTo(x4, y4);
        rhombus.closePath();

        // Draw the rhombus
        g2d.fill(rhombus);
    }
}
