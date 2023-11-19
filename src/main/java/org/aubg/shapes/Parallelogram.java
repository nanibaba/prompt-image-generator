package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Parallelogram extends Shape {

    public Parallelogram(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded coordinates for the parallelogram
        int x1 = 100; // X-coordinate of the first corner
        int y1 = 50;  // Y-coordinate of the first corner
        int x2 = 160; // X-coordinate of the second corner
        int y2 = 50;  // Y-coordinate of the second corner
        int x3 = 140; // X-coordinate of the third corner
        int y3 = 100; // Y-coordinate of the third corner
        int x4 = 80;  // X-coordinate of the fourth corner
        int y4 = 100; // Y-coordinate of the fourth corner

        // Create a path for the parallelogram
        Path2D.Double parallelogram = new Path2D.Double();
        parallelogram.moveTo(x1, y1);
        parallelogram.lineTo(x2, y2);
        parallelogram.lineTo(x3, y3);
        parallelogram.lineTo(x4, y4);
        parallelogram.closePath();

        // Draw the parallelogram
        g2d.fill(parallelogram);
    }
}
