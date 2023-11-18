package org.aubg.shapes;

import java.awt.Color;

public class Pentagon extends Polygon {

    public Pentagon(Color color) {
        super(color);
        setRadius(50);
        setSides(5);
    }
}
