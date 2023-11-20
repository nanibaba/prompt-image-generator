package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Cross extends Shape {

    public Cross(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded dimensions and position for the cross
        int centerX = 150; // X-coordinate of the center
        int centerY = 100; // Y-coordinate of the center
        int armWidth = 20; // Width of each arm of the cross
        int armLength = 60; // Length of each arm of the cross

        // Draw the vertical rectangle
        Rectangle2D.Double verticalRect = new Rectangle2D.Double(
            centerX - armWidth / 2, centerY - armLength / 2, armWidth, armLength);
        g2d.fill(verticalRect);

        // Draw the horizontal rectangle
        Rectangle2D.Double horizontalRect = new Rectangle2D.Double(
            centerX - armLength / 2, centerY - armWidth / 2, armLength, armWidth);
        g2d.fill(horizontalRect);
    }
}
