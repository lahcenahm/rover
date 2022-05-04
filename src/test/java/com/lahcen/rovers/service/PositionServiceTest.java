package com.lahcen.rovers.service;

import com.lahcen.rovers.exception.UnknownEntryException;
import com.lahcen.rovers.model.OrientationEnum;
import com.lahcen.rovers.model.PositionAndOrientation;
import com.lahcen.rovers.service.impl.PositionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class PositionServiceTest {

     private IPositionService positionService;
     @BeforeEach
     void setUp(){
         positionService = new PositionServiceImpl();
     }
    @Test
     void should_spin_to_left_from_est(){
        PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(1, 2, OrientationEnum.E);
        PositionAndOrientation positionAndOrientationOutput = positionService.spinToLeft(positionAndOrientationInput);
        assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(),positionAndOrientationOutput.getxPosition()),
                ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                () -> assertEquals(OrientationEnum.N, positionAndOrientationOutput.getOrientation()));
    }
     @Test
     void should_spin_to_left_from_north(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(1, 2, OrientationEnum.N);
         PositionAndOrientation positionAndOrientationOutput = positionService.spinToLeft(positionAndOrientationInput);
         assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(),positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.W, positionAndOrientationOutput.getOrientation()));
     }

     @Test
     void should_spin_to_left_from_west(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(1, 2, OrientationEnum.W);
         PositionAndOrientation positionAndOrientationOutput = positionService.spinToLeft(positionAndOrientationInput);
         assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(),positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.S, positionAndOrientationOutput.getOrientation()));
     }
     @Test
     void should_spin_to_left_from_south(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(1, 2, OrientationEnum.S);
         PositionAndOrientation positionAndOrientationOutput = positionService.spinToLeft(positionAndOrientationInput);
         assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(),positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.E, positionAndOrientationOutput.getOrientation()));
     }

     @Test
     void should_spin_to_right_from_est(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(1, 2, OrientationEnum.E);
         PositionAndOrientation positionAndOrientationOutput = positionService.spinToRight(positionAndOrientationInput);
         assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(),positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.S, positionAndOrientationOutput.getOrientation()));
     }
     @Test
     void should_spin_to_right_from_north(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(1, 2, OrientationEnum.N);
         PositionAndOrientation positionAndOrientationOutput = positionService.spinToRight(positionAndOrientationInput);
         assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(),positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.E, positionAndOrientationOutput.getOrientation()));
     }

     @Test
     void should_spin_to_right_from_west(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(1, 2, OrientationEnum.W);
         PositionAndOrientation positionAndOrientationOutput = positionService.spinToRight(positionAndOrientationInput);
         assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(),positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.N, positionAndOrientationOutput.getOrientation()));
     }
     @Test
     void should_spin_to_right_from_south(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(1, 2, OrientationEnum.S);
         PositionAndOrientation positionAndOrientationOutput = positionService.spinToRight(positionAndOrientationInput);
         assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(),positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.W, positionAndOrientationOutput.getOrientation()));
     }

     @Test
     void should_move_from_est(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(3, 2, OrientationEnum.E);
         PositionAndOrientation positionAndOrientationOutput = positionService.move(positionAndOrientationInput);
         assertAll(()-> assertEquals(4,positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.E, positionAndOrientationOutput.getOrientation()));

     }

     @Test
     void should_move_from_west(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(3, 2, OrientationEnum.W);
         PositionAndOrientation positionAndOrientationOutput = positionService.move(positionAndOrientationInput);
         assertAll(()-> assertEquals(2,positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(positionAndOrientationInput.getyPosition(),positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.W, positionAndOrientationOutput.getOrientation()));

     }

     @Test
     void should_move_from_south(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(3, 2, OrientationEnum.S);
         PositionAndOrientation positionAndOrientationOutput = positionService.move(positionAndOrientationInput);
         assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(),positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(1,positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.S, positionAndOrientationOutput.getOrientation()));

     }

     @Test
     void should_move_from_north(){
         PositionAndOrientation positionAndOrientationInput = new PositionAndOrientation(3, 2, OrientationEnum.N);
         PositionAndOrientation positionAndOrientationOutput = positionService.move(positionAndOrientationInput);
         assertAll(()-> assertEquals(positionAndOrientationInput.getxPosition(), positionAndOrientationOutput.getxPosition()),
                 ()-> assertEquals(3, positionAndOrientationOutput.getyPosition()),
                 () -> assertEquals(OrientationEnum.N, positionAndOrientationOutput.getOrientation()));

     }

     @Test
     void should_get_next_position_left() throws UnknownEntryException {
        PositionAndOrientation positionAndOrientation = new PositionAndOrientation(3,5,OrientationEnum.E);
         PositionAndOrientation expectedPositionAndOrientation = new PositionAndOrientation(3,5,OrientationEnum.N);
        PositionAndOrientation result = positionService.getNextPosition(positionAndOrientation,'L');
        assertEquals(expectedPositionAndOrientation,result);
     }

     @Test
     void should_get_next_position_right() throws UnknownEntryException {
         PositionAndOrientation positionAndOrientation = new PositionAndOrientation(5,6,OrientationEnum.N);
         PositionAndOrientation expectedPositionAndOrientation = new PositionAndOrientation(5,6,OrientationEnum.E);
         PositionAndOrientation result = positionService.getNextPosition(positionAndOrientation,'R');
         assertEquals(expectedPositionAndOrientation,result);
     }

     @Test
     void should_get_next_position_move() throws UnknownEntryException {
         PositionAndOrientation positionAndOrientation = new PositionAndOrientation(5,6,OrientationEnum.N);
         PositionAndOrientation expectedPositionAndOrientation = new PositionAndOrientation(5,7,OrientationEnum.N);
         PositionAndOrientation result = positionService.getNextPosition(positionAndOrientation,'M');
         assertEquals(expectedPositionAndOrientation,result);
     }

     @Test
     void should_throw_unknown_exception(){
         PositionAndOrientation positionAndOrientation = new PositionAndOrientation(5,6,OrientationEnum.N);
         UnknownEntryException thrown = assertThrows(
                 UnknownEntryException.class,
                 () -> positionService.getNextPosition(positionAndOrientation,'T')
         );

         assertTrue(thrown.getMessage().contains("Unknown Entry"));
     }
}
