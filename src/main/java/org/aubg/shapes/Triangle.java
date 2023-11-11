package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle implements Shape {
    private Color color; 

    public Triangle(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        g.setColor(color);
        int x1 = width / 2;
        int y1 = (height - 100) / 2;
        int x2 = x1 - 50;
        int y2 = y1 + 100;
        int x3 = x1 + 50;
        int y3 = y2;
        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
