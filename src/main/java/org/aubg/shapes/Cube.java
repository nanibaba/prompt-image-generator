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

        // Base color and shade variations for the cube faces
        Color baseColor = color;
        Color darkerColor = baseColor.darker();
        Color lightestColor = baseColor.brighter();

        // Hard-coded dimensions and position for the cube
        int x = 100; // X-coordinate of the front top-left corner
        int y = 50;  // Y-coordinate of the front top-left corner
        int size = 50; // Size of the cube (length of each edge)

        // Projection factor for depth simulation
        int depth = (int) (size * 0.6);

        // Front face of the cube
        Path2D.Double frontFace = new Path2D.Double();
        frontFace.moveTo(x, y);
        frontFace.lineTo(x + size, y);
        frontFace.lineTo(x + size, y + size);
        frontFace.lineTo(x, y + size);
        frontFace.closePath();
        g2d.setPaint(baseColor);
        g2d.fill(frontFace);

        // Top face of the cube
        Path2D.Double topFace = new Path2D.Double();
        topFace.moveTo(x, y);
        topFace.lineTo(x + depth, y - depth);
        topFace.lineTo(x + size + depth, y - depth);
        topFace.lineTo(x + size, y);
        topFace.closePath();
        g2d.setPaint(lightestColor);
        g2d.fill(topFace);

        // Side face of the cube
        Path2D.Double sideFace = new Path2D.Double();
        sideFace.moveTo(x + size, y);
        sideFace.lineTo(x + size + depth, y - depth);
        sideFace.lineTo(x + size + depth, y + size - depth);
        sideFace.lineTo(x + size, y + size);
        sideFace.closePath();
        g2d.setPaint(darkerColor);
        g2d.fill(sideFace);
    }
}
