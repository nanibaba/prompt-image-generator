package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Square implements Shape {
    private Color color; 

    public Square(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        g.setColor(color);
         // Calculate the square's coordinates and size
         int x = (width - 100) / 2;
         int y = (height - 100) / 2;

         // Draw the square
         g.fillRect(x, y, 100, 100);
    }
}
