package org.aubg.shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

    private Color color;

    public Shape (Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract void draw(Graphics g, Color color);
}
