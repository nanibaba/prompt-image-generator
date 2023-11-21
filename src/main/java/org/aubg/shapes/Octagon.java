package org.aubg.shapes;

import java.awt.Color;

/**
 * Octagon class extending the Polygon class.
 * Represents a specific type of polygon with eight sides.
 */
public class Octagon extends Polygon {

    /**
     * Constructor to initialize an Octagon with a color.
     * @param color The color of the octagon.
     */
    public Octagon(Color color) {
        super(color);  // Call to the superclass (Polygon) constructor
        setRadius(40); // Set a default radius for the octagon
        setSides(8);   // Set the number of sides to 8, as it's an octagon
    }
}
