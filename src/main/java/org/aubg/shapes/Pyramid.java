package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Pyramid extends Shape {

    public Pyramid(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Adjusted dimensions and position to fit the drawing panel
        int x = 50; // Adjusted X-coordinate for better centering
        int y = 75; // Adjusted Y-coordinate for better vertical positioning
        int baseWidth = 80; // Adjusted width of the base of the pyramid
        int baseHeight = 40; // Adjusted height of the parallelogram base
        int slant = 20; // Adjusted slant distance for the parallelogram base
        int pyramidHeight = 60; // Adjusted height of the pyramid from base to tip

        // Tip of the pyramid
        int tipX = x + baseWidth / 2 + slant / 2; // Adjusted X-coordinate of the tip
        int tipY = y - pyramidHeight; // Adjusted Y-coordinate of the tip

        // Create a path for the pyramid
        Path2D.Double path = new Path2D.Double();

        // Base of the pyramid (a parallelogram)
        path.moveTo(x, y);
        path.lineTo(x + baseWidth, y);
        path.lineTo(x + baseWidth + slant, y + baseHeight);
        path.lineTo(x + slant, y + baseHeight);
        path.closePath();

        // Sides of the pyramid
        // Side 1
        path.moveTo(x, y);
        path.lineTo(tipX, tipY);
        path.lineTo(x + baseWidth, y);

        // Side 2
        path.moveTo(x + baseWidth, y);
        path.lineTo(tipX, tipY);
        path.lineTo(x + baseWidth + slant, y + baseHeight);

        // Side 3
        path.moveTo(x + baseWidth + slant, y + baseHeight);
        path.lineTo(tipX, tipY);
        path.lineTo(x + slant, y + baseHeight);

        // Side 4
        path.moveTo(x + slant, y + baseHeight);
        path.lineTo(tipX, tipY);
        path.lineTo(x, y);

        // Draw the pyramid
        g2d.draw(path);
    }
}
