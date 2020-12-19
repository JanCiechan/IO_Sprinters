package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

public class Building extends Location {
    private final List<Level> levelList;
    public Building(int id, String name,String type) {
        super(id, name,type);
        levelList = new ArrayList<>();
    }

    public void fillList(List<Level> addlevels){
        levelList.clear();
        for(Level item:addlevels){
            if(item.getBuildingid()==this.getId()){
                levelList.add(item);
            }
        }
    }
    public float getArea(){
        float result=0;
        for(Level item:levelList){
            result+=item.getArea();
        }
        return result;
    }
    public float getCubature(){
        float result=0;
        for(Level item:levelList){
            result+=item.getCubature();
        }
        return result;
    }
    public float getLight(){
        float result=0;
        for(Level item:levelList){
            result+=item.getLight();
        }
        return result;
    }
    public float getHeating(){
        float result=0;
        for(Level item:levelList){
            result+=item.getHeating();
        }
        return result;
    }
    public int getAmountOfLevels(){

        return getLevelList().size();
    }
    public List<Level> getLevelList(){
        return levelList;
    }

}
