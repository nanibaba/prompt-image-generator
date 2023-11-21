package org.aubg.shapes;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Abstract class representing a generic shape.
 * Defines the basic structure and behavior that all specific shapes should have.
 */
public abstract class Shape {
    // Color of the shape
    protected Color color;

    /**
     * Constructor to initialize a Shape with a color.
     * @param color The color of the shape.
     */
    public Shape(Color color) {
        this.color = color;
    }

    /**
     * Getter method for the color of the shape.
     * @return The color of this shape.
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Abstract method to draw the shape.
     * This method should be implemented by subclasses to define how the shape is drawn.
     * @param g Graphics context used for drawing.
     */
    public abstract void draw(Graphics g);
}
