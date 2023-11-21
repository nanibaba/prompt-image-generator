package org.aubg.shapes;

import java.awt.Color;

/**
 * Pentagon class extending the Polygon class.
 * Represents a specific type of polygon with five sides.
 */
public class Pentagon extends Polygon {

    /**
     * Constructor to initialize a Pentagon with a color.
     * @param color The color of the pentagon.
     */
    public Pentagon(Color color) {
        super(color);  // Call to the superclass (Polygon) constructor
        setRadius(50); // Set a default radius for the pentagon
        setSides(5);   // Set the number of sides to 5, as it's a pentagon
    }
}
