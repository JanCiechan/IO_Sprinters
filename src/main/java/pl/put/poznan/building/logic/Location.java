package pl.put.poznan.building.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class Location {

    private int id;
    private String name;
    private String type;
    public Location(int id, String name,String type){
        this.type=type;
        this.id = id;
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }
    @Override
    public String toString() {
        return "Location id: " + this.getId() + " Location name: " + this.name + this.type ;
    }
}
