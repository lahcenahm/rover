package com.lahcen.rovers.service;


import com.lahcen.rovers.exception.UnknownEntryException;
import com.lahcen.rovers.model.PositionAndOrientation;

public interface IPositionService {
     PositionAndOrientation move(PositionAndOrientation positionAndOrientation);

    PositionAndOrientation spinToLeft(PositionAndOrientation positionAndOrientation);

    PositionAndOrientation spinToRight(PositionAndOrientation positionAndOrientation);

    PositionAndOrientation getNextPosition(PositionAndOrientation positionAndOrientation, char nextPosition) throws UnknownEntryException;
}
