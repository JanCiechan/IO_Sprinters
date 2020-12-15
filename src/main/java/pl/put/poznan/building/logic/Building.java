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
        for(Level item:addlevels){
            if(item.getId()==this.getId()){
                levelList.add(item);
            }
        }
    }

    public int getAmountOfLevels(){
        if (levelList == null){
            return 0;
        }
        return this.levelList.size();
    }

}
