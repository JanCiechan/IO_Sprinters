package pl.put.poznan.building.rest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.building.logic.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{text}")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
    private List<Location> locationList = new ArrayList<>();
    private  List<Location> roomList= new ArrayList<>();
    private  List<Location> levelList= new ArrayList<>();
    private  List<Location> buildingList= new ArrayList<>();


    @PostConstruct
    public void dataLoader() {
        String file = "src/main/resources/data.json";
        String jsonFile = null;
        try {
            jsonFile = new String(Files.readAllBytes(Paths.get(file)));
            JSONArray locations = new JSONArray(jsonFile);
            for (int i=0; i < locations.length(); i++) {
                String location = locations.getString(i);
                post(location);
            }
            refresh();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET,value="Location",produces = "application/json")
    public List<Location> getAll() {
        return locationList;
    }
    @RequestMapping(method = RequestMethod.GET,value="Rooms",produces = "application/json")
    public List<Location> getAllRooms() {
        return roomList;

    }

    @RequestMapping(method = RequestMethod.GET,value="Levels",produces = "application/json")
    public List<Location> getAllLevels() {

        return levelList;
    }
    @RequestMapping(method = RequestMethod.GET,value="Buildings",produces = "application/json")
    public List<Location> getAllBuildings() {
        return buildingList;
    }
    @RequestMapping(method=RequestMethod.GET,value="refresh",produces="application/json")
    public String refresh(){
        for(Location item:levelList){
            item.fillList(roomList);
        }
        for(Location item:buildingList){
            item.fillList(levelList);
        }
        return "Data refreshed";
    }

    public String CheckType(int id){
        for(Location item:locationList){
            if(item.getId()==id){
                return item.getType();
            }
        }
        return null;
    }
    @RequestMapping(method=RequestMethod.GET,value="area/{id}",produces="application/json")
    public float getArea(@PathVariable("id")int id){
        float result=0;
        String type=CheckType(id);
        if(type.equals("Building")){
            for(Location item:buildingList){
                if(item.getId()==id) return item.getArea();
            }
        }
        else if(type.equals("Level")){
            for(Location item:levelList){
                if(item.getId()==id) return item.getArea();
            }
        }
        else if(type.equals("Room")){
            for(Location item:roomList){
                if(item.getId()==id) return item.getArea();
            }
        }
        return result;

    }
    @RequestMapping(method=RequestMethod.GET,value="cube/{id}",produces="application/json")
    public float getCubature(@PathVariable("id")int id){
        float result=0;
        String type=CheckType(id);
        if(type.equals("Building")){
            for(Location item:buildingList){
                if(item.getId()==id) return item.getCubature();
            }
        }
        else if(type.equals("Level")){
            for(Location item:levelList){
                if(item.getId()==id) return item.getCubature();
            }
        }
        else if(type.equals("Room")){
            for(Location item:roomList){
                if(item.getId()==id) return item.getCubature();
            }
        }
        return result;

    }
    @RequestMapping(method=RequestMethod.GET,value="light/{id}",produces="application/json")
    public float getLight(@PathVariable("id")int id){
        float result=0;
        String type=CheckType(id);
        if(type.equals("Building")){
            for(Location item:buildingList){
                if(item.getId()==id) return item.getLight();
            }
        }
        else if(type.equals("Level")){
            for(Location item:levelList){
                if(item.getId()==id) return item.getLight();
            }
        }
        else if(type.equals("Room")){
            for(Location item:roomList){
                if(item.getId()==id) return item.getLight();
            }
        }
        return result;

    }
    @RequestMapping(method=RequestMethod.GET,value="heat/{id}",produces="application/json")
    public float getHeating(@PathVariable("id")int id){
        float result=0;
        String type=CheckType(id);
        if(type.equals("Building")){
            for(Location item:buildingList){
                if(item.getId()==id) return item.getHeating();
            }
        }
        else if(type.equals("Level")){
            for(Location item:levelList){
                if(item.getId()==id) return item.getHeating();
            }
        }
        else if(type.equals("Room")){
            for(Location item:roomList){
                if(item.getId()==id) return item.getHeating();
            }
        }
        return result;

    }
    @RequestMapping(method=RequestMethod.GET,value="lightinensity/{id}",produces="application/json")
    public float getLightIntensity(@PathVariable("id")int id){
        float light=0;
        float area=0;
        String type=CheckType(id);
        if(type.equals("Building")){
            for(Location item:buildingList){
                if(item.getId()==id){
                    light=item.getLight();
                    area=item.getArea();
                } ;
            }
        }
        else if(type.equals("Level")){
            for(Location item:levelList){
                if(item.getId()==id) {
                    light=item.getLight();
                    area=item.getArea();
                };
            }
        }
        else if(type.equals("Room")){
            for(Location item:roomList){
                if(item.getId()==id) {
                    light=item.getLight();
                    area=item.getArea();
                };
            }
        }
        return light/area;

    }
    @RequestMapping(method=RequestMethod.GET,value="powerusage/{id}",produces="application/json")
    public float getPowerUsage(@PathVariable("id")int id){
        float heating=0;
        float cubature=0;
        String type=CheckType(id);
        if(type.equals("Building")){
            for(Location item:buildingList){
                if(item.getId()==id){
                    heating=item.getHeating();
                    cubature=item.getCubature();
                } ;
            }
        }
        else if(type.equals("Level")){
            for(Location item:levelList){
                if(item.getId()==id) {
                    heating=item.getHeating();
                    cubature=item.getCubature();
                };
            }
        }
        else if(type.equals("Room")){
            for(Location item:roomList){
                if(item.getId()==id) {
                    heating=item.getHeating();
                    cubature=item.getCubature();
                };
            }
        }
        return heating/cubature;

    }

    @RequestMapping(method=RequestMethod.GET,value="powerusage/over/{value}/{id}",produces="application/json")
    public List<String> getPowerUsageOverValue(@PathVariable("value")int value, @PathVariable("id")int id ){
        float heating=0;
        float cubature=0;
        List<String> roomsUsages =new ArrayList<>();
        List<Location> roomsInLevel;
        for(Location item:levelList) {
            if (item.getFatherID()==id){
                roomsInLevel = item.getUnderlings();
                for(Location room:roomsInLevel){
                    heating=room.getHeating();
                    cubature=room.getCubature();
                    if (heating/cubature >= value){
                        roomsUsages.add(room.getName());
                        roomsUsages.add(Float.toString(heating/cubature));
                        roomsUsages.add(item.getName());
                    }
                }
            }
        }
        return roomsUsages;
    }

    //@RequestMapping(method = RequestMethod.GET,value="{id}", produces = "application/json")
    @GetMapping("/{id}")
    public Location getLocationByID(@PathVariable("id") int id) {
        // log the parameters
        logger.debug(String.valueOf(id));
        for (Location building : buildingList) {
            if (building.getId() == id) {

                return building;
            }
        }
        for (Location level : levelList) {
            if (level.getId() == id) {
                return level;
            }
        }
        for (Location room : roomList) {
            if (room.getId() == id) {
                return room;
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
            JSONArray levels = jsonObject.getJSONArray("levels");
            for (int i=0; i < levels.length(); i++) {
                JSONObject jsonLevel = levels.getJSONObject(i);
                Level poziom = new Level(jsonLevel.getInt("id"),jsonLevel.getString("name"),jsonLevel.getString("type"),jsonLevel.getInt("Buildingid"));
                JSONArray rooms = jsonLevel.getJSONArray("rooms");
                for (int j=0; j < rooms.length(); j++) {
                    JSONObject jsonRoom = rooms.getJSONObject(j);
                    Room pomieszczenie = new Room(jsonRoom.getInt("id"),jsonRoom.getString("name"),jsonRoom.getString("type"),jsonRoom.getInt("Levelid"), Float.parseFloat(jsonRoom.getString("area")),Float.parseFloat(jsonRoom.getString("cube")),Float.parseFloat(jsonRoom.getString("heating")),Float.parseFloat(jsonRoom.getString("light")));
                    roomList.add(pomieszczenie);
                    Location location=new Location(jsonRoom.getInt("id"),jsonRoom.getString("name"),jsonRoom.getString("type"));
                    locationList.add(location);
                }
                levelList.add(poziom);
                Location location=new Location(jsonLevel.getInt("id"),jsonLevel.getString("name"),jsonLevel.getString("type"));
                locationList.add(location);
            }
            buildingList.add(budynek);
            wiadomosc="Budynek dodany";
        }
        if(("Level").equals(jsonObject.getString("type"))){
            Level poziom = new Level(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("type"),jsonObject.getInt("Buildingid"));
            JSONArray rooms = jsonObject.getJSONArray("rooms");
            for (int j=0; j < rooms.length(); j++) {
                JSONObject jsonRoom = rooms.getJSONObject(j);
                Room pomieszczenie = new Room(jsonRoom.getInt("id"),jsonRoom.getString("name"),jsonRoom.getString("type"),jsonRoom.getInt("Levelid"), Float.parseFloat(jsonRoom.getString("area")),Float.parseFloat(jsonRoom.getString("cube")),Float.parseFloat(jsonRoom.getString("heating")),Float.parseFloat(jsonRoom.getString("light")));
                roomList.add(pomieszczenie);
                Location location=new Location(jsonRoom.getInt("id"),jsonRoom.getString("name"),jsonRoom.getString("type"));
                locationList.add(location);
            }
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
        refresh();
        return wiadomosc;
    }

    @RequestMapping(method = RequestMethod.DELETE,produces = "application/json")
    public String deleteByID(@RequestBody String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        //System.out.println(jsonObject);
        String wiadomosc="";
        for(Location item:buildingList){
            if (item.getId()==jsonObject.getInt("id")){
                buildingList.remove(item);
                wiadomosc= "Budynek usuniety";
                break;
            }
        }

        for(Location item:levelList){
           if (item.getId()==jsonObject.getInt("id")){
               levelList.remove(item);
               wiadomosc= "Poziom usuniety";
               break;
           }
        }
        for(Location item:roomList){
            if (item.getId()==jsonObject.getInt("id")){
                roomList.remove(item);
                wiadomosc= "Pomieszczenie usuniete";
                break;
            }
        }
        for (Location item : locationList) {
            if (item.getId()==jsonObject.getInt("id")) {
                locationList.remove(item);
                break;
            }
        }
        refresh();
        return wiadomosc;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public String replaceLocation(@RequestBody String json) throws JSONException{

        JSONObject jsonObject = new JSONObject(json);
        String wiadomosc="";
        if(("Building").equals(jsonObject.getString("type"))){
            for(Location item:buildingList) {
                if (item.getId() == jsonObject.getInt("id")) {
                    item.setName(jsonObject.getString("name"));
                    wiadomosc = "Budynek zaktualizowany";
                    break;
                }
            }
        }
        if(("Level").equals(jsonObject.getString("type"))){
            for(Location item:levelList) {
                if (item.getId() == jsonObject.getInt("id")) {
                    item.setName(jsonObject.getString("name"));
                    wiadomosc = "Poziom zaktualizowany";
                    break;
                }
            }
        }
        if(("Room").equals(jsonObject.getString("type"))){
            for(Location item:roomList) {
                if (item.getId() == jsonObject.getInt("id")) {
                    item.setName(jsonObject.getString("name"));
                    item.setArea(Float.parseFloat(jsonObject.getString("area")));
                    item.setCube(Float.parseFloat(jsonObject.getString("cube")));
                    item.setHeating(Float.parseFloat(jsonObject.getString("heating")));
                    item.setLight(Float.parseFloat(jsonObject.getString("light")));
                    wiadomosc= "Pomieszczenie zaktualizowane";
                    break;
                }
            }
        }
        if (!"".equals(wiadomosc)) {
            for (Location item : locationList) {
                if (item.getId() == jsonObject.getInt("id")) {
                    item.setName(jsonObject.getString("name"));
                    break;
                }
            }
        }
        refresh();
        return wiadomosc;
    }
}


