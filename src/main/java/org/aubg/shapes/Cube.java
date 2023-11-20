package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Cube extends Shape {

    public Cube(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Hard-coded dimensions and position for the cube
        int x = 100; // X-coordinate of the front top-left corner
        int y = 50;  // Y-coordinate of the front top-left corner
        int size = 50; // Size of the cube (length of each edge)

        // Projection factor for depth simulation
        int depth = (int) (size * 0.6);

        // Create a path for the cube
        Path2D.Double path = new Path2D.Double();
        
        // Front face
        path.moveTo(x, y);
        path.lineTo(x + size, y);
        path.lineTo(x + size, y + size);
        path.lineTo(x, y + size);
        path.closePath();

        // Top face
        path.moveTo(x, y);
        path.lineTo(x + depth, y - depth);
        path.lineTo(x + size + depth, y - depth);
        path.lineTo(x + size, y);
        path.closePath();

        // Side face
        path.moveTo(x + size, y);
        path.lineTo(x + size + depth, y - depth);
        path.lineTo(x + size + depth, y + size - depth);
        path.lineTo(x + size, y + size);
        path.closePath();

        // Draw the cube
        g2d.draw(path);
    }
}
