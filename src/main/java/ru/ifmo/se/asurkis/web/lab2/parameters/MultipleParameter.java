package ru.ifmo.se.asurkis.web.lab2.parameters;

import java.io.Serializable;

public class MultipleParameter<T> implements Serializable {
    public static enum State {
        UNSET,
        INVALID,
        VALID
    }
    private State state = State.UNSET;
    private T[] values;

    public T[] getValues() {
        if (state != State.VALID) {
            throw new RuntimeException();
        }
        return values;
    }

    public void setValues(T[] values) {
        this.values = values;
        state = State.VALID;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isUnset() {
        return state == State.UNSET;
    }

    public boolean isValid() {
        return state == State.VALID;
    }

    public boolean isInvalid() {
        return state == State.INVALID;
    }
}
