package pl.put.poznan.building.logic;

public class Pomieszczenie extends Location {
    private float area;
    private float cube;
    private float heating;
    private float light;
    public Pomieszczenie(int id, String name) {
        super(id, name);
    }
}
