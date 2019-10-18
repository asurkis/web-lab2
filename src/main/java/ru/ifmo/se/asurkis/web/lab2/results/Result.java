package ru.ifmo.se.asurkis.web.lab2.results;

import java.io.Serializable;

public class Result implements Serializable {
    public double x;
    public double y;
    public double radius;
    public boolean falls;

    public Result(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        falls = fallsInto(radius);
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

    public boolean fallsInto(double r) {
        if (x > 0) {
            if (y >= 0) {
                return x * x + y * y <= r * r;
            } else {
                return 2 * x - y <= r;
            }
        } else if (x < 0) {
            return -r <= x && -r <= y && y <= 0;
        } else {
            return -r <= y && y <= r;
        }
    }
}
