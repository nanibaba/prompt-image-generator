package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Diamond extends Shape {

    public Diamond(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded center coordinates and size for the diamond
        int centerX = 150; // X-coordinate of the center
        int centerY = 100; // Y-coordinate of the center
        int halfWidth = 40; // Half width of the diamond

        // Create a path for the diamond
        Path2D.Double diamond = new Path2D.Double();
        diamond.moveTo(centerX, centerY - halfWidth); // Top vertex
        diamond.lineTo(centerX + halfWidth, centerY); // Right vertex
        diamond.lineTo(centerX, centerY + halfWidth); // Bottom vertex
        diamond.lineTo(centerX - halfWidth, centerY); // Left vertex
        diamond.closePath();

        // Draw the diamond
        g2d.fill(diamond);
    }
}
