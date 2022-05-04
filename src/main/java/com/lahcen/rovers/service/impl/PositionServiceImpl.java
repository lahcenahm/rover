package com.lahcen.rovers.service.impl;


import com.lahcen.rovers.exception.UnknownEntryException;
import com.lahcen.rovers.model.OrientationEnum;
import com.lahcen.rovers.model.PositionAndOrientation;
import com.lahcen.rovers.service.IPositionService;

public class PositionServiceImpl implements IPositionService {
    @Override
    public PositionAndOrientation move(PositionAndOrientation positionAndOrientation) {
        if(positionAndOrientation!=null) {
            switch (positionAndOrientation.getOrientation()){
                case E:
                    return new PositionAndOrientation(positionAndOrientation.getxPosition()+1,positionAndOrientation.getyPosition(),positionAndOrientation.getOrientation() );
                case W:
                     return new PositionAndOrientation(positionAndOrientation.getxPosition()-1,positionAndOrientation.getyPosition(),positionAndOrientation.getOrientation() );
                case N:
                    return  new PositionAndOrientation(positionAndOrientation.getxPosition(), positionAndOrientation.getyPosition()+1,positionAndOrientation.getOrientation());
                case S:
                    return  new PositionAndOrientation(positionAndOrientation.getxPosition(), positionAndOrientation.getyPosition()-1,positionAndOrientation.getOrientation());
                default:
                    return  null;
            }
        }
        return null;
        }

    @Override
    public PositionAndOrientation spinToLeft(PositionAndOrientation positionAndOrientation) {
        if(positionAndOrientation!=null) {
            switch (positionAndOrientation.getOrientation()){
                case E:
                    return new PositionAndOrientation(positionAndOrientation.getxPosition(),positionAndOrientation.getyPosition(), OrientationEnum.N );
                case W:
                    return new PositionAndOrientation(positionAndOrientation.getxPosition(),positionAndOrientation.getyPosition(),OrientationEnum.S );
                case N:
                    return  new PositionAndOrientation(positionAndOrientation.getxPosition(), positionAndOrientation.getyPosition(),OrientationEnum.W);
                case S:
                    return  new PositionAndOrientation(positionAndOrientation.getxPosition(), positionAndOrientation.getyPosition(),OrientationEnum.E);
                default:
                    return  null;
            }
        }
        return null;
    }

    @Override
    public PositionAndOrientation spinToRight(PositionAndOrientation positionAndOrientation) {
        if(positionAndOrientation!=null) {
            switch (positionAndOrientation.getOrientation()){
                case E:
                    return new PositionAndOrientation(positionAndOrientation.getxPosition(),positionAndOrientation.getyPosition(),OrientationEnum.S);
                case W:
                    return new PositionAndOrientation(positionAndOrientation.getxPosition(),positionAndOrientation.getyPosition(),OrientationEnum.N );
                case N:
                    return  new PositionAndOrientation(positionAndOrientation.getxPosition(), positionAndOrientation.getyPosition(),OrientationEnum.E);
                case S:
                    return  new PositionAndOrientation(positionAndOrientation.getxPosition(), positionAndOrientation.getyPosition(),OrientationEnum.W);
                default:
                    return  null;
            }
        }
        return null;
    }

    @Override
    public PositionAndOrientation getNextPosition(PositionAndOrientation positionAndOrientation, char nextPosition) throws UnknownEntryException {
        switch (nextPosition){
            case 'L':
                return spinToLeft(positionAndOrientation);
            case 'R':
                return spinToRight(positionAndOrientation);
            case 'M':
                return move(positionAndOrientation);
            default:
                throw new UnknownEntryException("Unknown Entry");
        }

    }


}
