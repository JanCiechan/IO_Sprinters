package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

public class Building extends Location {
    public List<Level> LevelList = new ArrayList<>();
    public Building(int id, String name,String type) {
        super(id, name,type);
    }

    public void fillList(List<Level> addlevels){
        for(Level item:addlevels){
            if(item.getId()==this.getId()){
                LevelList.add(item);
            }
        }

    }

}
