package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class Cone extends Shape {

    public Cone(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Adjusted coordinates and dimensions for a smaller, more oval base
        int baseCenterX = 150; // X-coordinate of the base's center
        int baseCenterY = 100; // Y-coordinate of the base's center
        int baseWidth = 80;    // Width of the oval base
        int baseHeight = 40;   // Height of the oval base
        int apexY = 25;        // Y-coordinate of the apex

        // Base of the cone (an oval)
        Ellipse2D.Double base = new Ellipse2D.Double(
            baseCenterX - baseWidth / 2, baseCenterY - baseHeight / 2, baseWidth, baseHeight);
        g2d.fill(base); // Fill the base with color

        // Sides of the cone
        Path2D.Double side = new Path2D.Double();
        side.moveTo(baseCenterX - baseWidth / 2, baseCenterY); // Left edge of the base
        side.lineTo(150, apexY);                               // Apex of the cone
        side.lineTo(baseCenterX + baseWidth / 2, baseCenterY); // Right edge of the base
        side.closePath();
        g2d.fill(side); // Fill the sides with color
    }
}
