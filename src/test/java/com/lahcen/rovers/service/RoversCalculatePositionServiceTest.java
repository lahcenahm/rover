package com.lahcen.rovers.service;

import com.lahcen.rovers.exception.CollisionException;
import com.lahcen.rovers.exception.FileInputException;
import com.lahcen.rovers.exception.OutOfRangeException;
import com.lahcen.rovers.exception.UnknownEntryException;
import com.lahcen.rovers.model.OrientationEnum;
import com.lahcen.rovers.model.PositionAndOrientation;
import com.lahcen.rovers.service.impl.RoversCalculatePositionServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
class RoversCalculatePositionServiceTest {

    private IPositionService positionService;

    private IRoversCalculatePositionService roversCalculatePositionService;

    @BeforeEach
     void setUp( ){
        positionService= Mockito.mock(IPositionService.class);
        roversCalculatePositionService = new RoversCalculatePositionServiceImpl(positionService);
    }
    @Test
     void should_get_x_max_position() throws UnknownEntryException {
        //Given
        List<String> lines = Arrays.asList("56", "12N", "LMLMRL");
        int xMaxPosition = roversCalculatePositionService.getXMaxPlateau(lines);
        assertEquals(5,xMaxPosition);
    }

    @Test
    void should_get_y_max_position()  {
        //Given
        List<String> lines = Arrays.asList("56", "12N", "LMLMRL");
        int yMaxPosition = roversCalculatePositionService.getYMaxPlateau(lines);
        assertEquals(6,yMaxPosition);
    }

    @Test
    void should_get_initial_rovers_positions(){
        PositionAndOrientation positionAndOrientation1 = new PositionAndOrientation(1,2, OrientationEnum.N);
        PositionAndOrientation positionAndOrientation2 = new PositionAndOrientation(3,3, OrientationEnum.E);
        List<String> lines = Arrays.asList("56", "12N", "LMLMRL","33E", "MMMMRRRMMM");
        List<PositionAndOrientation> roversPositionsAndOrientations = roversCalculatePositionService.getInitialRoversPositions(lines);
        assertEquals(2,roversPositionsAndOrientations.size());
        assertTrue(roversPositionsAndOrientations.contains(positionAndOrientation1));
        assertTrue(roversPositionsAndOrientations.contains(positionAndOrientation2));
    }

    @Test
    void should_get_instructions_rovers(){
        String instruction1 = "LMLMRL";
        String instruction2 = "MMRRLMMMMM";
        List<String> lines = Arrays.asList("56", "12N",instruction1 ,"33E", instruction2);
        List<String> instructions = roversCalculatePositionService.getInstructionsRovers(lines);
        assertEquals(2,instructions.size());
        assertTrue(instructions.contains(instruction1));
        assertTrue(instructions.contains(instruction2));
    }

    @Test
    void should_get_final_position() throws OutOfRangeException, CollisionException, UnknownEntryException {
        PositionAndOrientation positionAndOrientation1= new PositionAndOrientation(3,3, OrientationEnum.E);
        PositionAndOrientation positionAndOrientation12= new PositionAndOrientation(4,3,OrientationEnum.E);
        PositionAndOrientation positionAndOrientation13= new PositionAndOrientation(4,3,OrientationEnum.N);
        PositionAndOrientation positionAndOrientation14= new PositionAndOrientation(4,3,OrientationEnum.E);
        PositionAndOrientation positionAndOrientation15= new PositionAndOrientation(5,3,OrientationEnum.E);
        PositionAndOrientation positionAndOrientation2= new PositionAndOrientation(0,7,OrientationEnum.E);
        String instruction="MLRM";
        List<PositionAndOrientation> initialRoversPosition = Arrays.asList(positionAndOrientation1,positionAndOrientation2);
        Mockito.when(positionService.getNextPosition(positionAndOrientation1,'M')).thenReturn(positionAndOrientation12);
        Mockito.when(positionService.getNextPosition(positionAndOrientation12,'L')).thenReturn(positionAndOrientation13);
        Mockito.when(positionService.getNextPosition(positionAndOrientation13,'R')).thenReturn(positionAndOrientation14);
        Mockito.when(positionService.getNextPosition(positionAndOrientation14,'M')).thenReturn(positionAndOrientation15);
        PositionAndOrientation finalPositionAndOrientation = roversCalculatePositionService.getFinalPositionAndOrientation(positionAndOrientation1,instruction,initialRoversPosition,10,10);
        assertEquals(positionAndOrientation15,finalPositionAndOrientation);
    }

    @Test
    void should_throw_collision_exception() throws UnknownEntryException {
        PositionAndOrientation positionAndOrientation1= new PositionAndOrientation(3,3,OrientationEnum.N);
        PositionAndOrientation positionAndOrientation2= new PositionAndOrientation(3,4,OrientationEnum.N);
        List<PositionAndOrientation> initialRoversPosition = Arrays.asList(positionAndOrientation1,positionAndOrientation2);
        Mockito.when(positionService.getNextPosition(positionAndOrientation1,'M')).thenReturn(positionAndOrientation2);
        CollisionException thrown = assertThrows(
                CollisionException.class,
                () -> roversCalculatePositionService.getFinalPositionAndOrientation(positionAndOrientation1,"M",initialRoversPosition,10,10),
                "Expected getFinalPositionAndOrientation() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Another rover exists already in this position for the rover"));
    }

    @Test
    void should_throw_out_of_range_exception() throws UnknownEntryException {
        PositionAndOrientation positionAndOrientation1= new PositionAndOrientation(0,3,OrientationEnum.W);
        PositionAndOrientation positionAndOrientation2= new PositionAndOrientation(3,4,OrientationEnum.N);
        PositionAndOrientation positionAndOrientation3= new PositionAndOrientation(12,4,OrientationEnum.N);
        List<PositionAndOrientation> initialRoversPosition = Arrays.asList(positionAndOrientation1,positionAndOrientation2);
        Mockito.when(positionService.getNextPosition(positionAndOrientation1,'M')).thenReturn(positionAndOrientation3);
        OutOfRangeException thrown = assertThrows(
                OutOfRangeException.class,
                () -> roversCalculatePositionService.getFinalPositionAndOrientation(positionAndOrientation1,"M",initialRoversPosition,10,10),
                "Expected getFinalPositionAndOrientation() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("the co-ordinates are out of range fro the rover"));
    }

    @Test
    void should_get_final_position_and_orientation() throws FileInputException, CollisionException, UnknownEntryException, OutOfRangeException {
        List<String> lines = Arrays.asList("55", "12N", "LMR","33E", "MR");
        PositionAndOrientation positionAndOrientation1= new PositionAndOrientation(1,2,OrientationEnum.N);
        PositionAndOrientation positionAndOrientation2 = new PositionAndOrientation(1,2,OrientationEnum.W);
        PositionAndOrientation positionAndOrientation3 = new PositionAndOrientation(0,2,OrientationEnum.W);
        PositionAndOrientation positionAndOrientation4 = new PositionAndOrientation(0,2,OrientationEnum.N);
        PositionAndOrientation positionAndOrientation5 = new PositionAndOrientation(3,3,OrientationEnum.E);
        PositionAndOrientation positionAndOrientation6 = new PositionAndOrientation(4,3,OrientationEnum.E);
        PositionAndOrientation positionAndOrientation7 = new PositionAndOrientation(4,3,OrientationEnum.S);
        Mockito.when(positionService.getNextPosition(positionAndOrientation1,'L')).thenReturn(positionAndOrientation2);
        Mockito.when(positionService.getNextPosition(positionAndOrientation2,'M')).thenReturn(positionAndOrientation3);
        Mockito.when(positionService.getNextPosition(positionAndOrientation3,'R')).thenReturn(positionAndOrientation4);
        Mockito.when(positionService.getNextPosition(positionAndOrientation5,'M')).thenReturn(positionAndOrientation6);
        Mockito.when(positionService.getNextPosition(positionAndOrientation6,'R')).thenReturn(positionAndOrientation7);
        List<PositionAndOrientation> positionAndOrientations = roversCalculatePositionService.calculateRoversPosition(lines);
        assertEquals(2, positionAndOrientations.size());
        assertTrue(positionAndOrientations.contains(positionAndOrientation4));
        assertTrue(positionAndOrientations.contains(positionAndOrientation7));
    }

    @Test
    void should_throw_file_input_exception()  {
        String instruction1 = "LMLMRL";
        String instruction2 = "MMRRLMMMMM";
        List<String> lines = Arrays.asList("56", "12N",instruction1 ,"33E", instruction2,"16S");
        FileInputException thrown = assertThrows(
                FileInputException.class,
                () -> roversCalculatePositionService.calculateRoversPosition(lines),
                "Expected calculateRoversPosition() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Incorrect format input file"));
    }
}
