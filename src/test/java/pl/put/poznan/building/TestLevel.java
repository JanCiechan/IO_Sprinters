package pl.put.poznan.building;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.building.logic.Level;
import pl.put.poznan.building.logic.Location;
import pl.put.poznan.building.logic.Room;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLevel {

    private Level levelUnderTest;

    @BeforeEach
    void setUp() {
        levelUnderTest = new Level(2, "level1", "Level", 1);
        List<Location> addrooms = List.of(new Room(0, "pokoj1", "Room", 2, 5.0f, 7.0f, 13.0f, 7.0f),
                new Room(1, "pokoj2", "Room", 2, 7.0f, 3.0f, 11.0f, 6.0f));
        levelUnderTest.fillList(addrooms);
    }


    @Test
    void testgetAmountOfRooms() {

        int result = levelUnderTest.getAmountOfUnderlings();
        int expected = 2;
        assertEquals(expected, result);

    }

    @Test
    void testGetArea() {

        float result = levelUnderTest.getArea();
        float expected = 12.0f;
        assertEquals(expected, result);
    }

    @Test
    void testGetCubature() {
        // Setup

        // Run the test
        final float result = levelUnderTest.getCubature();

        float expected = 10.0f;
        assertEquals(expected, result);
    }

    @Test
    void testGetLight() {
        // Setup

        // Run the test
        final float result = levelUnderTest.getLight();

        float expected = 13.0f;
        assertEquals(expected, result);
    }

    @Test
    void testGetHeating() {

        float result = levelUnderTest.getHeating();
        float expected = 24.0f;
        assertEquals(expected, result);
    }


}

