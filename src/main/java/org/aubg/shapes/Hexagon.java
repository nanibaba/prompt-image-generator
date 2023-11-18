package org.aubg.shapes;

import java.awt.Color;

public class Hexagon extends Polygon {

    public Hexagon(Color color) {
        super(color);
        setRadius(40);
        setSides(6);
    }
}
