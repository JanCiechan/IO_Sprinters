package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

public class Building extends Location {
    private List<Level> LevelList = new ArrayList<>();
    public Building(int id, String name) {
        super(id, name);
    }
}
