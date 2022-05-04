package com.lahcen.rovers.service;



import com.lahcen.rovers.exception.CollisionException;
import com.lahcen.rovers.exception.FileInputException;
import com.lahcen.rovers.exception.OutOfRangeException;
import com.lahcen.rovers.exception.UnknownEntryException;
import com.lahcen.rovers.model.PositionAndOrientation;

import java.util.List;

public interface IRoversCalculatePositionService {
    List<PositionAndOrientation> calculateRoversPosition(List<String> lines) throws FileInputException, OutOfRangeException, CollisionException, UnknownEntryException;

    PositionAndOrientation getFinalPositionAndOrientation(PositionAndOrientation positionAndOrientation,
                                                          String instruction,
                                                          List<PositionAndOrientation> initialRovPositionAndOrientations,
                                                          int xMaxvalue,
                                                          int yMaxValue) throws UnknownEntryException, CollisionException, OutOfRangeException;

    int getXMaxPlateau(List<String> lines) throws UnknownEntryException;

    int getYMaxPlateau(List<String> lines);

    List<PositionAndOrientation> getInitialRoversPositions(List<String> lines);

    List<String> getInstructionsRovers(List<String> lines);
}
