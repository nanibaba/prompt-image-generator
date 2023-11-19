package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Shape {

    public Ellipse(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded coordinates and dimensions for the ellipse
        int x = 100; // X-coordinate of the top-left corner
        int y = 50;  // Y-coordinate of the top-left corner
        int width = 120; // Width of the ellipse
        int height = 80; // Height of the ellipse

        // Draw the ellipse
        Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, width, height);
        g2d.fill(ellipse);
    }
}

