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

        // Adjusted position and size to fit the drawing panel
        int x = 50;       // Adjusted X-coordinate for better centering
        int y = 30;       // Adjusted Y-coordinate for better positioning within the panel
        int width = 80;   // Width of the cylinder
        int height = 100; // Reduced height of the cylinder to fit the panel

        // Gradient paint for the 3D effect
        GradientPaint gp = new GradientPaint(x, y, color.brighter(), x, y + height, color.darker());

        // Top ellipse (top base of the cylinder)
        Ellipse2D.Double topEllipse = new Ellipse2D.Double(x, y, width, 20); // Adjusted ellipse height
        g2d.setPaint(color.brighter());
        g2d.fill(topEllipse);

        // Bottom ellipse (bottom base of the cylinder)
        Ellipse2D.Double bottomEllipse = new Ellipse2D.Double(x, y + height - 20, width, 20); // Adjusted ellipse height
        g2d.setPaint(color.darker());
        g2d.fill(bottomEllipse);

        // Sides of the cylinder
        g2d.setPaint(gp);
        g2d.drawLine(x, y + 10, x, y + height - 10); // Left side
        g2d.drawLine(x + width, y + 10, x + width, y + height - 10); // Right side
    }
}
