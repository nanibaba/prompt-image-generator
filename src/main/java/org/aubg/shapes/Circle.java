package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int x = 100;
        int y = 25;
        
        g.fillOval(x, y, 100, 100);
    }
}
