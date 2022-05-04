package com.lahcen.rovers.model;


public class PositionAndOrientation {

    private final int xPosition;
    private final int yPosition;
    private final OrientationEnum orientation;
    public PositionAndOrientation(int xPosition, int yPosition, OrientationEnum orientation) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.orientation = orientation;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public OrientationEnum getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object object){

        if(this == object)
            return true;

        if(object == null || object.getClass()!= this.getClass())
            return false;
        PositionAndOrientation positionAndOrientation = (PositionAndOrientation) object;
        return (positionAndOrientation.xPosition == this.xPosition && positionAndOrientation.yPosition == this.yPosition);
    }

    @Override
    public String toString() {
        return xPosition+ " " + yPosition + " "+ orientation;
    }
}
