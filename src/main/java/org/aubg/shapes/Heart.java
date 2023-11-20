package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;

public class Heart extends Shape {

    public Heart(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Adjusting size and position to fit the drawing panel
        int x = 100; // X-coordinate for the top left corner of the bounding rectangle
        int y = 50;  // Y-coordinate for the top left corner of the bounding rectangle
        int width = 80; // Adjusted width of the bounding rectangle
        int height = 120; // Adjusted height of the bounding rectangle

        // Adjusted proportions for the heart
        int arcWidth = width / 2;
        int arcHeight = height / 2;

        // Create a path for the heart
        Path2D.Double path = new Path2D.Double();

        // Left arc
        Arc2D.Double leftArc = new Arc2D.Double(x, y, arcWidth, arcHeight, 0, 180, Arc2D.OPEN);
        path.append(leftArc, false);

        // Right arc
        Arc2D.Double rightArc = new Arc2D.Double(x + arcWidth, y, arcWidth, arcHeight, 0, 180, Arc2D.OPEN);
        path.append(rightArc, false);

        // Triangle part
        path.moveTo(x, y + arcHeight / 2);
        path.lineTo(x + width / 2, y + height);
        path.lineTo(x + width, y + arcHeight / 2);
        path.closePath();

        // Draw the heart
        g2d.setPaint(color);
        g2d.fill(path);
    }
}
