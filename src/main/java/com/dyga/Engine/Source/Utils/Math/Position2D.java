package com.dyga.Engine.Source.Utils.Math;

import java.awt.geom.Point2D;

public class Position2D extends Point2D {

    private double x;
    private double y;

    public Position2D() {
        this(0, 0);
    }

    public Position2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
