package org.aubg.shapes;

import java.awt.Color;

/**
 * Oval class extending the Ellipse class.
 * Represents an oval shape, which is a specific type of ellipse.
 * Inherits drawing behavior and properties from the Ellipse class.
 */
public class Oval extends Ellipse {

    /**
     * Constructor to initialize an Oval with a color.
     * @param color The color of the oval.
     */
    public Oval(Color color) {
        super(color); // Call to the superclass (Ellipse) constructor
    }
    
    // Inherits the draw method from Ellipse without any modifications.
}
