package ru.ifmo.se.asurkis.web.lab2.results;

import java.io.Serializable;

public class Result implements Serializable {
    public double x;
    public double y;
    public double radius;
    public boolean falls;

    public Result(double x, double y, double radius, boolean falls) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.falls = falls;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public boolean doesFall() {
        return falls;
    }
}
