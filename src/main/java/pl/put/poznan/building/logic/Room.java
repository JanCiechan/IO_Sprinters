package pl.put.poznan.building.logic;

public class Room extends Location {
    private int Levelid;
    private float area;
    private float cube;
    private float heating;
    private float light;
    public Room(int id, String name,String type,int Levelid,float area,float cube,float heating,float light) {
        super(id, name,type);
        this.Levelid=Levelid;
        this.area=area;
        this.cube=cube;
        this.heating=heating;
        this.light=light;
    }

    public float getArea() {
        return area;
    }

    public float getCubature() {
        return cube;
    }

    public float getHeating() {
        return heating;
    }

    public float getLight() {
        return light;
    }

    public int getLevelid() {
        return Levelid;
    }
}
