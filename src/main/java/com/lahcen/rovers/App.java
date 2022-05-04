package com.lahcen.rovers;

import com.lahcen.rovers.exception.CollisionException;
import com.lahcen.rovers.exception.FileInputException;
import com.lahcen.rovers.exception.OutOfRangeException;
import com.lahcen.rovers.exception.UnknownEntryException;
import com.lahcen.rovers.model.PositionAndOrientation;
import com.lahcen.rovers.service.IPositionService;
import com.lahcen.rovers.service.IRoversCalculatePositionService;
import com.lahcen.rovers.service.impl.PositionServiceImpl;
import com.lahcen.rovers.service.impl.RoversCalculatePositionServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {

        try (Scanner scanner = new Scanner(new File(args[0]))) {
            List<String> lines = new ArrayList<>();
            IPositionService positionService = new PositionServiceImpl();
            IRoversCalculatePositionService roversCalculatePositionService = new RoversCalculatePositionServiceImpl(positionService);
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine().replaceAll("\\s", ""));
            }
                List<PositionAndOrientation> finalPositionAndOrientations = roversCalculatePositionService.calculateRoversPosition(lines);
                finalPositionAndOrientations.forEach(System.out::println);

        } catch (FileNotFoundException | FileInputException | OutOfRangeException | CollisionException |
                 UnknownEntryException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
