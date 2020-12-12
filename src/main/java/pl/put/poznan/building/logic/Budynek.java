package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

public class Budynek extends Location {
    private List<Location> poziomlist = new ArrayList<>();
    public Budynek(int id, String name) {
        super(id, name);
    }
}
