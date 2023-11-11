package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Arrow extends Shape {
    public Arrow(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        // Coordinates for the arrow shaft
        int shaftLength = 100;
        int shaftWidth = 10;
        int startX = 25; // X-coordinate for the start of the shaft
        int startY = 100; // Y-coordinate for the start of the shaft

        // Draw the arrow shaft as a horizontal rectangle
        g.fillRect(startX, startY, shaftLength, shaftWidth);

        // Coordinates for the arrowhead
        // Making the arrowhead wider than the shaft
        int arrowheadWidth = shaftWidth * 2;
        int arrowheadLength = shaftWidth * 2; // Making the length equal to the width

        // The points for the triangle must be set such that the arrowhead is at the right end of the shaft
        int[] xPoints = {
            startX + shaftLength, // back of the arrowhead at the end of the shaft
            startX + shaftLength + arrowheadLength, // tip of the arrowhead
            startX + shaftLength // back of the arrowhead at the end of the shaft
        };

        int[] yPoints = {
            startY - (arrowheadWidth - shaftWidth) / 2, // top of the arrowhead
            startY + (shaftWidth / 2), // middle of the shaft
            startY + shaftWidth + (arrowheadWidth - shaftWidth) / 2 // bottom of the arrowhead
        };

        // Draw the arrowhead
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
