package pl.put.poznan.building.logic;

public class Room extends Location {
    private float area;
    private float cube;
    private float heating;
    private float light;
    public Room(int id, String name) {
        super(id, name);
    }
}
