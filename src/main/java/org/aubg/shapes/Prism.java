package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Prism extends Shape {

    public Prism(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Adjusted dimensions and position for the prism
        int x = 10;  // X-coordinate of the front bottom-left corner
        int y = 120; // Y-coordinate of the front bottom-left corner
        int baseWidth = 100; // Adjusted width of the prism base
        int height = 50; // Adjusted height of the prism
        int topOffset = 20; // Adjusted horizontal offset for the top edge of the base

        // Adjusted projection factor for depth simulation
        int depth = 20;

        // Create a path for the prism
        Path2D.Double path = new Path2D.Double();
        
        // Front face (parallelogram)
        path.moveTo(x, y);
        path.lineTo(x + topOffset, y - height);
        path.lineTo(x + topOffset + baseWidth, y - height);
        path.lineTo(x + baseWidth, y);
        path.closePath();

        // Draw the front face
        g2d.draw(path);

        // Rear face (parallelogram)
        path.moveTo(x + depth, y + depth);
        path.lineTo(x + topOffset + depth, y - height + depth);
        path.lineTo(x + topOffset + baseWidth + depth, y - height + depth);
        path.lineTo(x + baseWidth + depth, y + depth);
        path.closePath();

        // Draw the rear face
        g2d.draw(path);

        // Connect front and rear faces
        path.moveTo(x, y);
        path.lineTo(x + depth, y + depth);

        path.moveTo(x + baseWidth, y);
        path.lineTo(x + baseWidth + depth, y + depth);

        path.moveTo(x + topOffset, y - height);
        path.lineTo(x + topOffset + depth, y - height + depth);

        path.moveTo(x + topOffset + baseWidth, y - height);
        path.lineTo(x + topOffset + baseWidth + depth, y - height + depth);

        // Draw the connecting lines
        g2d.draw(path);
    }
}
