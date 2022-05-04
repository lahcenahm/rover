package com.lahcen.rovers.service.impl;


import com.lahcen.rovers.exception.CollisionException;
import com.lahcen.rovers.exception.FileInputException;
import com.lahcen.rovers.exception.OutOfRangeException;
import com.lahcen.rovers.exception.UnknownEntryException;
import com.lahcen.rovers.model.OrientationEnum;
import com.lahcen.rovers.model.PositionAndOrientation;
import com.lahcen.rovers.service.IPositionService;
import com.lahcen.rovers.service.IRoversCalculatePositionService;

import java.util.ArrayList;
import java.util.List;

public class RoversCalculatePositionServiceImpl implements IRoversCalculatePositionService {

    private  final IPositionService positionService;

    public RoversCalculatePositionServiceImpl(IPositionService iPositionService){
        this.positionService = iPositionService;
    }
    @Override
    public List<PositionAndOrientation> calculateRoversPosition(List<String> lines) throws FileInputException, OutOfRangeException, CollisionException, UnknownEntryException {
        if(lines==null || lines.size()%2!=1){
            throw new FileInputException("Incorrect format input file");
        }
        else {
            int xMaxPlateau= getXMaxPlateau(lines);
            int yMaxPlateau= getYMaxPlateau(lines);
            List<PositionAndOrientation> roversInitialPositions = getInitialRoversPositions(lines);
            List<String> instructionsRovers = getInstructionsRovers(lines);
            List<PositionAndOrientation> finalPositionAndOrientations = new ArrayList<>();
            for(int i=0;i<roversInitialPositions.size();i++){
                PositionAndOrientation finalPositionAndOrientation = getFinalPositionAndOrientation(roversInitialPositions.get(i),instructionsRovers.get(i),roversInitialPositions,xMaxPlateau,yMaxPlateau);
                finalPositionAndOrientations.add(finalPositionAndOrientation);
            }
            return finalPositionAndOrientations;
        }
    }

    @Override
    public PositionAndOrientation getFinalPositionAndOrientation(PositionAndOrientation positionAndOrientation, String instruction, List<PositionAndOrientation> initialRovPositionAndOrientations,int xMaxvalue, int yMaxValue) throws UnknownEntryException, CollisionException, OutOfRangeException {
        PositionAndOrientation finalPositionAndOrientation = positionAndOrientation;
        for(int i=0;i<instruction.length();i++){
            finalPositionAndOrientation = positionService.getNextPosition(finalPositionAndOrientation,instruction.charAt(i));
            if(initialRovPositionAndOrientations.stream().filter(positionAndOrientation1 -> !positionAndOrientation.equals(positionAndOrientation1)).anyMatch(finalPositionAndOrientation::equals)){
                throw new CollisionException("Another rover exists already in this position for the rover ");
            }
            if(finalPositionAndOrientation.getxPosition()<0 || finalPositionAndOrientation.getxPosition()>xMaxvalue|| finalPositionAndOrientation.getyPosition()<0 || finalPositionAndOrientation.getyPosition()>yMaxValue){
                throw new OutOfRangeException("the co-ordinates are out of range fro the rover "+ positionAndOrientation.toString());
            }

        }
        return finalPositionAndOrientation;
    }

    @Override
    public int getXMaxPlateau(List<String> lines) throws UnknownEntryException {
        if(lines!=null && !lines.isEmpty() && lines.get(0)!=null ) {
            return Integer.parseInt(lines.get(0).substring(0, 1));
        }
        throw new UnknownEntryException("the x of the plateau is unknown");
    }

    @Override
    public int getYMaxPlateau(List<String> lines) {
        return Integer.parseInt(lines.get(0).substring(1));
    }

    @Override
    public List<PositionAndOrientation> getInitialRoversPositions(List<String> lines) {
        List<PositionAndOrientation> roversInitialPositions = new ArrayList<>();
        for(int i=1; i< lines.size()-1;i+=2){
            roversInitialPositions.add(new PositionAndOrientation(Integer.parseInt(lines.get(i).substring(0,1)),
                    Integer.parseInt(lines.get(i).substring(1,2)),
                    OrientationEnum.valueOf(lines.get(i).substring(2))));

        }
        return roversInitialPositions;
    }

    @Override
    public List<String> getInstructionsRovers(List<String> lines) {
        List<String> instructionsRovers = new ArrayList<>();
        for(int i=1; i< lines.size()-1;i+=2){
            instructionsRovers.add(lines.get(i+1));

        }
        return instructionsRovers;
    }
}
