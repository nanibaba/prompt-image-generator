package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {

    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded dimensions and position for the rectangle
        int x = 50;       // X-coordinate of the top-left corner
        int y = 50;       // Y-coordinate of the top-left corner
        int width = 100;  // Width of the rectangle
        int height = 50;  // Height of the rectangle

        // Draw the rectangle
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x, y, width, height);
        g2d.fill(rectangle);
    }
}
