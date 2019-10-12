package ru.ifmo.se.asurkis.web.lab2.parameters;

import java.io.Serializable;

public class RequestParameters implements Serializable {
    private MultipleParameter<Double> x;
    private MultipleParameter<Double> y;
    private MultipleParameter<Double> r;
    private MultipleParameter<Integer> toDelete;

    public MultipleParameter<Double> getX() {
        return x;
    }

    public MultipleParameter<Double> getY() {
        return y;
    }

    public MultipleParameter<Double> getR() {
        return r;
    }

    public void setX(MultipleParameter<Double> x) {
        this.x = x;
    }

    public void setY(MultipleParameter<Double> y) {
        this.y = y;
    }

    public void setR(MultipleParameter<Double> r) {
        this.r = r;
    }

    public MultipleParameter<Integer> getToDelete() {
        return toDelete;
    }

    public void setToDelete(MultipleParameter<Integer> toDelete) {
        this.toDelete = toDelete;
    }
}
