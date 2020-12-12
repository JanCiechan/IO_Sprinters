package pl.put.poznan.building.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class Location {

    private int id;
    private String name;

    public Location(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id = id;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "Location id: " + this.getId() + " Location name: " + this.name;
    }
}
