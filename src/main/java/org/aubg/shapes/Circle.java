package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle implements Shape {
    private Color color; 

    public Circle(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        g.setColor(color);
        int x = (width - 100) / 2;
        int y = (height - 100) / 2;
        g.fillOval(x, y, 100, 100);
    }
}
