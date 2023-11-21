package org.aubg.shapes;

import java.awt.Color;

/**
 * Hexagon class extending the Polygon class.
 * Represents a specific type of polygon with six sides.
 */
public class Hexagon extends Polygon {

    /**
     * Constructor to initialize a Hexagon with a color.
     * @param color The color of the hexagon.
     */
    public Hexagon(Color color) {
        super(color);  // Call to the superclass (Polygon) constructor
        setRadius(40); // Set a default radius for the hexagon
        setSides(6);   // Set the number of sides to 6, as it's a hexagon
    }
}
