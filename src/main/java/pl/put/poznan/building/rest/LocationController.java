package pl.put.poznan.building.rest;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.building.logic.Building;
import pl.put.poznan.building.logic.Level;
import pl.put.poznan.building.logic.Location;
import pl.put.poznan.building.logic.Room;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/{text}")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
    private List<Location> locationList = new ArrayList<>();
    private  List<Room> roomList= new ArrayList<>();
    private  List<Level> levelList= new ArrayList<>();
    private  List<Building> buildingList= new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET,value="Location",produces = "application/json")
    public List<Location> getAll() {
        return locationList;
    }
    @RequestMapping(method = RequestMethod.GET,value="Rooms",produces = "application/json")
    public List<Room> getAllRooms() {

        return roomList;
    }
    @RequestMapping(method = RequestMethod.GET,value="Levels",produces = "application/json")
    public List<Level> getAllLevels() {

        return levelList;
    }
    @RequestMapping(method = RequestMethod.GET,value="Buildings",produces = "application/json")
    public List<Building> getAllBuildings() {
        return buildingList;
    }
    @RequestMapping(method=RequestMethod.GET,value="refresh",produces="application/json")
    public String refresh(){
        for(Level item:levelList){
            item.fillList(roomList);
        }
        for(Building item:buildingList){
            item.fillList(levelList);
        }
        return "Data refreshed";
    }


    @RequestMapping(method=RequestMethod.GET,value="area/{id}",produces="application/json")
    public float getArea(@PathVariable("id")int id){
        float result=0;
        for(Level item:levelList){
            if(id==item.getId()){
               result+=item.getarea();
               break;
            }
        }
        return result;

    }
    //@RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @GetMapping("/{id}")
    public Location getLocationByID(@PathVariable("id") int id) {
        // log the parameters
        logger.debug(String.valueOf(id));
        for (Location location : locationList) {
            if (location.getId() == id) {
                return location;
            }
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@RequestBody String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        String wiadomosc="";
        // log the parameters
        /*logger.debug(location.toString());


        locationList.add(location);*/

        if(("Building").equals(jsonObject.getString("type"))){
            Building budynek = new Building(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("type"));
            buildingList.add(budynek);

            wiadomosc="Budynek dodany";
        }
        if(("Level").equals(jsonObject.getString("type"))){
            Level poziom = new Level(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("type"),jsonObject.getInt("Buildingid"));
            levelList.add(poziom);
            wiadomosc="Poziom dodany";
        }
        if(("Room").equals(jsonObject.getString("type"))){
            //Room pomieszczenie = new Room(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("type"),jsonObject.getInt("Levelid"), BigDecimal.valueOf(jsonObject.getDouble("area")).floatValue(),BigDecimal.valueOf(jsonObject.getDouble("cube")).floatValue(),BigDecimal.valueOf(jsonObject.getDouble("heating")).floatValue(),BigDecimal.valueOf(jsonObject.getDouble("light")).floatValue());
            Room pomieszczenie = new Room(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("type"),jsonObject.getInt("Levelid"), Float.parseFloat(jsonObject.getString("area")),Float.parseFloat(jsonObject.getString("cube")),Float.parseFloat(jsonObject.getString("heating")),Float.parseFloat(jsonObject.getString("light")));

            roomList.add(pomieszczenie);
            wiadomosc= "Pomieszczenie dodane";
        }
        Location location=new Location(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("type"));
        locationList.add(location);
        return wiadomosc;
    }



}


