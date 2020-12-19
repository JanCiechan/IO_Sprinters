package pl.put.poznan.building.logic;

public class Room extends Location {
    public int Levelid;
    public float area;
    public float cube;
    public float heating;
    public float light;
    public Room(int id, String name,String type,int Levelid,float area,float cube,float heating,float light) {
        super(id, name,type);

        this.Levelid=Levelid;
        this.area=area;
        this.cube=cube;
        this.heating=heating;
        this.light=light;
    }
    public void setArea(float area) { this.area = area; }

    public void setCube(float cube) { this.cube = cube; }

    public void setHeating(float heating) { this.heating = heating; }

    public void setLight(float light) { this.light = light; }

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
