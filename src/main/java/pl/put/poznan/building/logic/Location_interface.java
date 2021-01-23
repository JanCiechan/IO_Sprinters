package pl.put.poznan.building.logic;

import java.util.ArrayList;
import java.util.List;

public interface Location_interface {



    public float getArea();

    public float getCubature();


    public float getLight();


    public float getHeating();

    public int getFatherID();

    public void fillList(List<Location> addlocations);
    public void setArea(float area);


    public void setCube(float cube);

    public void setHeating(float heating);

    public void setLight(float light);

    public int getAmountOfUnderlings();

}

