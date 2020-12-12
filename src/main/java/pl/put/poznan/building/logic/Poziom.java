package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

public class Poziom extends Location {
    private List<Location> pomieszczenielist = new ArrayList<>();
    public Poziom(int id, String name) {
        super(id, name);
    }
}
