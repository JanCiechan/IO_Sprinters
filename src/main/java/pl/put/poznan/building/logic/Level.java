package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

public class Level extends Location {
    private List<Room> RoomList = new ArrayList<>();
    public Level(int id, String name) {
        super(id, name);
    }
}
