package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int x1 = 150;
        int y1 = 25;
        int x2 = x1 - 50;
        int y2 = y1 + 100;
        int x3 = x1 + 50;
        int y3 = y2;
        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
