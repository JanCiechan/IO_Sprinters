package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

public class Level extends Location {
    public int Buildingid;
    public List<Room> rooms;
    public Level(int id, String name,String type,int Buildingid) {
        super(id, name,type);
        this.Buildingid=Buildingid;
        rooms =new ArrayList<>();
    }

    public void fillList(List<Room> addrooms) {
        rooms.clear();
        for(int i = 0;i<addrooms.size();i++)

            if (addrooms.get(i).getLevelid() == this.getId()) {
                rooms.add(addrooms.get((i)));
            }
        }

    public float getArea(){
            float result=0;
            for (Room item: rooms){

                result+=item.getArea();

            }
            return result;
        }
    public float getCubature(){
        float result=0;
        for (Room item: rooms){
            result+=item.getCubature();
        }
        return result;
    }
    public float getLight(){
        float result=0;
        for (Room item: rooms){
            result+=item.getLight();
        }
        return result;
    }
    public float getHeating(){
        float result=0;
        for (Room item: rooms){
            result+=item.getHeating();
        }
        return result;
    }

    public int getBuildingid(){
        return this.Buildingid;
    }

    public int getAmountOfRooms() {
        return rooms.size();
    }
}
