package org.aubg.shapes;

import java.awt.Color;

public class Octagon extends Polygon {

    public Octagon(Color color) {
        super(color);
        setRadius(40);
        setSides(8);
    }
}
