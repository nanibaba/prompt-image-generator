package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Pyramid extends Shape {

    public Pyramid(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded coordinates for the pyramid
        int baseCenterX = 150; // X-coordinate of the base's center
        int baseCenterY = 150; // Y-coordinate of the base's center
        int baseHalfWidth = 50; // Half width of the base
        int height = 50;       // Height of the pyramid

        // Base of the pyramid (a triangle)
        Path2D.Double base = new Path2D.Double();
        base.moveTo(baseCenterX - baseHalfWidth, baseCenterY);
        base.lineTo(baseCenterX + baseHalfWidth, baseCenterY);
        base.lineTo(baseCenterX, baseCenterY - height);
        base.closePath();

        // Draw the base
        g2d.draw(base);

        // Side faces of the pyramid
        Path2D.Double side1 = new Path2D.Double();
        side1.moveTo(baseCenterX - baseHalfWidth, baseCenterY);
        side1.lineTo(baseCenterX, baseCenterY + height);
        side1.lineTo(baseCenterX, baseCenterY - height);
        side1.closePath();

        Path2D.Double side2 = new Path2D.Double();
        side2.moveTo(baseCenterX + baseHalfWidth, baseCenterY);
        side2.lineTo(baseCenterX, baseCenterY + height);
        side2.lineTo(baseCenterX, baseCenterY - height);
        side2.closePath();

        // Draw the side faces
        g2d.draw(side1);
        g2d.draw(side2);
    }
}
