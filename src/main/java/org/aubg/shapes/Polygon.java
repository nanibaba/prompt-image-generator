package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Polygon extends Shape {

    private int radius;
    private int sides;

    public Polygon(Color color) {
        super(color);
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public void setSides(int sides){
        this.sides = sides;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded center coordinates and radius
        int centerX = 150; // X-coordinate of the center
        int centerY = 100; // Y-coordinate of the center

        // Create a path for the polygon
        Path2D.Double polygon = new Path2D.Double();
        polygon.moveTo(
            centerX + radius * Math.cos(0), 
            centerY - radius * Math.sin(0));

        // Add points for each vertex of the polygon
        for (int i = 1; i < sides; i++) {
            polygon.lineTo(
                centerX + radius * Math.cos(i * 2 * Math.PI / sides),
                centerY - radius * Math.sin(i * 2 * Math.PI / sides));
        }
        
        polygon.closePath();
        g2d.fill(polygon);
    }
}
