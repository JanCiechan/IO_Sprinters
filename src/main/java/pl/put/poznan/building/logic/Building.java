package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

public class Building extends Location {
    public List<Level> levels;
    public Building(int id, String name,String type) {
        super(id, name,type);
        levels = new ArrayList<>();
    }

    public void fillList(List<Level> addlevels){
        levels.clear();
        for(Level item:addlevels){
            if(item.getBuildingid()==this.getId()){
                levels.add(item);
            }
        }
    }
    public float getArea(){
        float result=0;
        for(Level item: levels){
            result+=item.getArea();
        }
        return result;
    }
    public float getCubature(){
        float result=0;
        for(Level item: levels){
            result+=item.getCubature();
        }
        return result;
    }
    public float getLight(){
        float result=0;
        for(Level item: levels){
            result+=item.getLight();
        }
        return result;
    }
    public float getHeating(){
        float result=0;
        for(Level item: levels){
            result+=item.getHeating();
        }
        return result;
    }
    public int getAmountOfLevels(){

        return getLevels().size();
    }
    public List<Level> getLevels(){
        return levels;
    }

}
