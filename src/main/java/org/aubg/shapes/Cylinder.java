package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.GradientPaint;

public class Cylinder extends Shape {

    public Cylinder(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Base color and shading colors
        Color baseColor = color;
        Color darkerColor = baseColor.darker();
        Color lightestColor = baseColor.brighter();

        // Hard-coded dimensions and position for the cylinder
        int x = 100;      // X-coordinate of the top-left corner of the top base
        int y = 50;       // Y-coordinate of the top-left corner of the top base
        int width = 80;  // Width of the bases
        int height = 120; // Total height of the cylinder

        // Create gradient paint for the side to give a 3D effect
        GradientPaint gp = new GradientPaint(x, y, lightestColor, x, y + height, darkerColor);

        // Draw the top ellipse (top base of the cylinder)
        Ellipse2D.Double topEllipse = new Ellipse2D.Double(x, y, width, 40);
        g2d.setColor(lightestColor);
        g2d.fill(topEllipse);

        // Draw the bottom ellipse (bottom base of the cylinder)
        Ellipse2D.Double bottomEllipse = new Ellipse2D.Double(x, y + height - 40, width, 40);
        g2d.setColor(darkerColor);
        g2d.fill(bottomEllipse);

        // Draw the sides of the cylinder
        g2d.setPaint(gp);
        g2d.drawLine(x, y + 20, x, y + height - 20); // Left side
        g2d.drawLine(x + width, y + 20, x + width, y + height - 20); // Right side
    }
}
