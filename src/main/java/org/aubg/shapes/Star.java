package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Star extends Shape {

    public Star(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded center coordinates and radius for the star
        int centerX = 150;
        int centerY = 100;
        int radius = 40;
        int innerRadius = 20;

        // Create a path for the star
        Path2D.Double star = new Path2D.Double();
        double angle = Math.PI / 5;

        for (int i = 0; i < 10; i++) {
            double r = (i % 2 == 0) ? radius : innerRadius;
            double x = centerX + r * Math.cos(i * angle);
            double y = centerY - r * Math.sin(i * angle);

            if (i == 0) {
                star.moveTo(x, y);
            } else {
                star.lineTo(x, y);
            }
        }
        
        star.closePath();
        g2d.fill(star);
    }
}

