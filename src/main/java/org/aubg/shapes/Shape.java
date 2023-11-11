package org.aubg.shapes;

import java.awt.Graphics;
import java.awt.Color;
public abstract class Shape {

    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public abstract void draw(Graphics g);
}