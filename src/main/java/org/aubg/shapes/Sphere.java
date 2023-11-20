package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Sphere extends Shape {

    public Sphere(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Hard-coded dimensions and position for the sphere
        int centerX = 150; // X-coordinate of the center
        int centerY = 100; // Y-coordinate of the center
        int radius = 50;   // Radius of the sphere

        // Gradient paint for a 3D effect
        Point2D center = new Point2D.Float(centerX, centerY);
        float[] dist = {0.0f, 1.0f};
        Color[] colors = {color.brighter(), color.darker()};
        RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
        
        g2d.setPaint(p);

        // Draw the sphere
        Ellipse2D.Double sphere = new Ellipse2D.Double(
            centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        g2d.fill(sphere);
    }
}
