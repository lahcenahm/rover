package com.lahcen.rovers.model;

public enum OrientationEnum {
    N("N"),
    S("S"),
    E("E"),
    W("W");

    private final String value;

     OrientationEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
