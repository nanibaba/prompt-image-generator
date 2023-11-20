package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.GradientPaint;

public class Prism extends Shape {

    public Prism(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Base color and darker variant for shading
        Color baseColor = color;
        Color darkerColor = baseColor.darker();

        // Hard-coded coordinates for the parallelogram prism
        int x = 100; // X-coordinate of the front base's left corner
        int y = 50;  // Y-coordinate of the front base's left corner
        int width = 100; // Width of the parallelogram base
        int height = 150; // Height of the prism
        int slant = 20;   // Slant distance for the parallelogram base

        // Front base of the prism (a parallelogram)
        Path2D.Double frontBase = new Path2D.Double();
        frontBase.moveTo(x, y);
        frontBase.lineTo(x + width, y);
        frontBase.lineTo(x + width + slant, y + height);
        frontBase.lineTo(x + slant, y + height);
        frontBase.closePath();
        g2d.setPaint(baseColor);
        g2d.fill(frontBase);

        // Back base of the prism (a parallelogram)
        Path2D.Double backBase = new Path2D.Double();
        backBase.moveTo(x + slant, y - slant);
        backBase.lineTo(x + width + slant, y - slant);
        backBase.lineTo(x + width + 2 * slant, y + height - slant);
        backBase.lineTo(x + 2 * slant, y + height - slant);
        backBase.closePath();
        g2d.setPaint(darkerColor);
        g2d.fill(backBase);

        // Side faces of the prism
        GradientPaint gp = new GradientPaint(x, y, baseColor, x, y + height, darkerColor);

        Rectangle2D.Double leftSide = new Rectangle2D.Double(x, y, slant, height);
        g2d.setPaint(gp);
        g2d.fill(leftSide);

        Rectangle2D.Double rightSide = new Rectangle2D.Double(x + width, y, slant, height);
        g2d.setPaint(gp);
        g2d.fill(rightSide);
    }
}
