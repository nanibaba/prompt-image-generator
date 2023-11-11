package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape {
    public Square(Color color) {
        super(color);
    }    

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
         // Calculate the square's coordinates and size
         int x = 100;
         int y = 25;

         // Draw the square
         g.fillRect(x, y, 100, 100);
    }
}
