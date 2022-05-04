package com.lahcen.rovers.service;


import com.lahcen.rovers.exception.CollisionException;
import com.lahcen.rovers.exception.FileInputException;
import com.lahcen.rovers.exception.OutOfRangeException;
import com.lahcen.rovers.exception.UnknownEntryException;
import com.lahcen.rovers.model.OrientationEnum;
import com.lahcen.rovers.model.PositionAndOrientation;
import com.lahcen.rovers.service.impl.PositionServiceImpl;
import com.lahcen.rovers.service.impl.RoversCalculatePositionServiceImpl;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoversCalculatePositionServiceIntegrationTest {


    @Test
     void should_get_correct_rovers_positions() throws UnknownEntryException, OutOfRangeException, FileInputException, CollisionException {
        //Given
        PositionAndOrientation positionAndOrientation1 = new PositionAndOrientation(1,3, OrientationEnum.N);
        PositionAndOrientation positionAndOrientation2 = new PositionAndOrientation(5,1,OrientationEnum.E);
        IPositionService positionService = new PositionServiceImpl();
        IRoversCalculatePositionService roversCalculatePositionService = new RoversCalculatePositionServiceImpl(positionService);
        List<String> lines = Arrays.asList("55", "12N", "LMLMLMLMM", "33E", "MMRMMRMRRM");
        List<PositionAndOrientation> positionAndOrientations= roversCalculatePositionService.calculateRoversPosition(lines);
        assertEquals(2,positionAndOrientations.size());
        assertTrue(positionAndOrientations.contains(positionAndOrientation1));
        assertTrue(positionAndOrientations.contains(positionAndOrientation2));
    }


}
