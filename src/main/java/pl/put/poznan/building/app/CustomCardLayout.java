package pl.put.poznan.building.app;

import com.google.gson.Gson;
import pl.put.poznan.building.logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CustomCardLayout extends JFrame {

    private final CardLayout cardLayout ;
    private static String data;
    private final JPanel cardPanel;
    private int idCounter = 0;


    public CustomCardLayout(){

        setTitle("Building Info");
        setSize(800, 450);
        Building[] buildings = getLocations("Location");//chyba count lokejszyn co?

        idCounter=buildings.length+1;
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);



        MenuPanel menuPanel = new MenuPanel();
        AddBuildingPanel addBuildingPanel = new AddBuildingPanel(AddBuildingPanel.TYPE_BUILDING);
        AddBuildingPanel addLevelPanel = new AddBuildingPanel(AddBuildingPanel.TYPE_LEVEL);
        AddBuildingPanel addRoomPanel = new AddBuildingPanel(AddBuildingPanel.TYPE_ROOM);
        ServerOutputPanel serverOutputPanel = new ServerOutputPanel();
        GetInfoPanel getInfoPanelBuildings = new GetInfoPanel(GetInfoPanel.TYPE_BUILDING);
        GetInfoPanel getInfoPanelLevels = new GetInfoPanel(GetInfoPanel.TYPE_LEVEL);
        GetInfoPanel getInfoPanelRooms = new GetInfoPanel(GetInfoPanel.TYPE_ROOM    );
        GetInfoPanel getInfoPanelRoom = new GetInfoPanel(GetInfoPanel.TYPE_ROOM_SINGULAR);
        SelectLocationPanel selectBuildingpanel = new SelectLocationPanel(SelectLocationPanel.TYPE_BUILDING);
        SelectLocationPanel selectLevelpanel = new SelectLocationPanel(SelectLocationPanel.TYPE_LEVEL);
        SelectLocationPanel selectRoompanel = new SelectLocationPanel(SelectLocationPanel.TYPE_ROOM);
        PowerusageRoomsPanel powerusageRoomsPanel = new PowerusageRoomsPanel();

        menuPanel.addServerOutputButtonActionListener(e -> cardLayout.show(cardPanel, "3"));
        menuPanel.addAddLocationActionListener(e -> cardLayout.show(cardPanel, "2"));
        menuPanel.addGetInfoActionListener(e -> {
            cardLayout.show(cardPanel, "4");
            int x= countBuildings();
            double area=0,cube=0,powerusage=0,lightintensity=0;
            getInfoPanelBuildings.setAmountLabelText(String.valueOf(x));
            String json="";
            Gson gson=new Gson();
            json = ConnectionProvider.getDataFromRestApi("Buildings");
            Building[] budynki = gson.fromJson(json, Building[].class);
            for(Building b : budynki) {
            area+=Double.parseDouble(ConnectionProvider.getDataFromRestApi("area/"+String.valueOf(b.getId())));
            cube+=Double.parseDouble(ConnectionProvider.getDataFromRestApi("cube/"+String.valueOf(b.getId())));
            powerusage+=Double.parseDouble(ConnectionProvider.getDataFromRestApi("powerusage/"+String.valueOf(b.getId())));
            lightintensity+=Double.parseDouble(ConnectionProvider.getDataFromRestApi("lightinensity/"+String.valueOf(b.getId())));
            }
            getInfoPanelBuildings.setAreaLabelText(String.valueOf(area));
            getInfoPanelBuildings.setCubatureLabelText(String.valueOf(cube));
            getInfoPanelBuildings.setHeatingLabelText(String.valueOf(powerusage));
            getInfoPanelBuildings.setLightLabelText(String.valueOf(lightintensity));



        });
        menuPanel.addGoBuildingsActionListener(e -> {
            cardLayout.show(cardPanel, "5");
            selectBuildingpanel.setBuildingsNames(getBuildingNamesList(),selectBuildingpanel.getType());
        });
        addLevelPanel.addSaveActionListener(e->{
          // Level level=new Level(idCounter++,addLevelPanel.getBuildingNameTFText(),"Level",Integer.parseInt(addLevelPanel.getBuildingIdTFText()));
            Level level=new Level(idCounter++,addLevelPanel.getBuildingNameTFText(),"Level",selectBuildingpanel.getCurrentLocation());
           Gson gson = new Gson();
            String json=gson.toJson(level);
            data = ConnectionProvider.postDataToRestApi(json);
        });
        addRoomPanel.addSaveActionListener(e -> {
            Room room=new Room(idCounter++,addRoomPanel.getBuildingNameTFText(),"Room",selectLevelpanel.getCurrentLocation(),Float.parseFloat(addRoomPanel.getArea()),Float.parseFloat(addRoomPanel.getCubature()),Float.parseFloat(addRoomPanel.getHeating()),Float.parseFloat(addRoomPanel.getLight()));
            Gson gson = new Gson();

            String json = gson.toJson(room);
            data=ConnectionProvider.postDataToRestApi(json);
        });
        addBuildingPanel.addSaveActionListener(e -> {
            String buildingName = addBuildingPanel.getBuildingNameTFText();
            Building building = new Building(idCounter++,buildingName,"Building");
            Gson gson = new Gson();
            String json = gson.toJson(building);
            data = ConnectionProvider.postDataToRestApi(json);
        });
        selectBuildingpanel.addGoLevelsInfoActionListener(e -> {
            selectLevelpanel.setFather(selectBuildingpanel.getCurrentLocation());
            selectLevelpanel.setBuildingsNames(getLevelsNamesList(selectBuildingpanel.getCurrentLocation()),selectLevelpanel.getType());
            cardLayout.show(cardPanel,"10");
        });
        selectLevelpanel.addGoLevelsInfoActionListener(e -> {
            selectRoompanel.setFather(selectLevelpanel.getCurrentLocation());
            selectRoompanel.setBuildingsNames(getRoomsNamesList(selectLevelpanel.getCurrentLocation()),selectRoompanel.getType());
            cardLayout.show(cardPanel,"11");
        });
        selectLevelpanel.addBackInfoActionListener(getBackActionListener("5"));
        selectRoompanel.addBackInfoActionListener(getBackActionListener("10"));
        selectLevelpanel.addAddLevelsActionListener(e -> {
            cardLayout.show(cardPanel,"8");
        });

        addBuildingPanel.addBackActionListener(getBackActionListener("1"));
        addLevelPanel.addBackActionListener(getBackActionListener("5"));
        serverOutputPanel.addGetActionListener(e -> {
            data = ConnectionProvider.getDataFromRestApi(serverOutputPanel.getTf());
            serverOutputPanel.setTa(data);
        });

        serverOutputPanel.addPostActionListener(e -> {
            String jsonData = serverOutputPanel.getTf();
            data = ConnectionProvider.postDataToRestApi(jsonData);
            serverOutputPanel.setTa(data);
        });
        serverOutputPanel.addPutActionListener(e -> {
            String jsonData = serverOutputPanel.getTf();
            data=ConnectionProvider.postDataToRestApi(jsonData);
            serverOutputPanel.setTa(data);
        });
        serverOutputPanel.addDeleteActionListener(e -> {
            String jsonData=serverOutputPanel.getTf();
            data=ConnectionProvider.deleteDataFromRestApi(jsonData);
            serverOutputPanel.setTa(data);
        });
        serverOutputPanel.addBackActionListener(getBackActionListener("1"));
        getInfoPanelBuildings.addBackInfoActionListener(getBackActionListener("1"));
        getInfoPanelLevels.addBackInfoActionListener(getBackActionListener("5"));
        selectBuildingpanel.addBackInfoActionListener(getBackActionListener("1"));
        getInfoPanelRooms.addBackInfoActionListener(getBackActionListener("10"));
        addRoomPanel.addBackActionListener(getBackActionListener("10"));
        selectBuildingpanel.addAddLevelsActionListener(e -> {
            cardLayout.show(cardPanel,"7");
        });
        selectRoompanel.addGetInfoInfoActionListener(e -> {
            cardLayout.show(cardPanel,"12");
            int i = selectRoompanel.getCurrentLocation();

            getInfoPanelRoom.setAreaLabelText(ConnectionProvider.getDataFromRestApi("area/"+String.valueOf(i)));
            getInfoPanelRoom.setCubatureLabelText(ConnectionProvider.getDataFromRestApi("cube/"+String.valueOf(i)));
            getInfoPanelRoom.setHeatingLabelText(ConnectionProvider.getDataFromRestApi("powerusage/"+String.valueOf(i)));
            getInfoPanelRoom.setLightLabelText(ConnectionProvider.getDataFromRestApi("lightinensity/"+String.valueOf(i)));
        });
        getInfoPanelRoom.addBackInfoActionListener(getBackActionListener("11"));
        selectLevelpanel.addGetInfoInfoActionListener(e -> {
            cardLayout.show(cardPanel,"9");
            int i = selectLevelpanel.getCurrentLocation();
            getInfoPanelRooms.setAmountLabelText(String.valueOf(countRooms(i)));
            /*getInfoPanelRooms.setAreaLabelText(String.valueOf(calculateArea(i, Level.class)));
            getInfoPanelRooms.setCubatureLabelText(String.valueOf(calculateCubature(i, Level.class)));
            getInfoPanelRooms.setHeatingLabelText(String.valueOf(calculateHeating(i, Level.class)));
            getInfoPanelRooms.setLightLabelText(String.valueOf(calculateArea(i, Level.class)));*/
            getInfoPanelRooms.setAreaLabelText(ConnectionProvider.getDataFromRestApi("area/"+String.valueOf(i)));
            getInfoPanelRooms.setCubatureLabelText(ConnectionProvider.getDataFromRestApi("cube/"+String.valueOf(i)));
            getInfoPanelRooms.setHeatingLabelText(ConnectionProvider.getDataFromRestApi("powerusage/"+String.valueOf(i)));
            getInfoPanelRooms.setLightLabelText(ConnectionProvider.getDataFromRestApi("lightinensity/"+String.valueOf(i)));
        });
        selectBuildingpanel.addGetInfoInfoActionListener(e -> {
            cardLayout.show(cardPanel, "6");
            int i = selectBuildingpanel.getCurrentLocation();

            getInfoPanelLevels.setAmountLabelText(String.valueOf(countLevels(i)));
            getInfoPanelLevels.setAreaLabelText(ConnectionProvider.getDataFromRestApi("area/"+String.valueOf(i)));
            getInfoPanelLevels.setCubatureLabelText(ConnectionProvider.getDataFromRestApi("cube/"+String.valueOf(i)));
            getInfoPanelLevels.setHeatingLabelText(ConnectionProvider.getDataFromRestApi("powerusage/"+String.valueOf(i)));
            getInfoPanelLevels.setLightLabelText(ConnectionProvider.getDataFromRestApi("lightinensity/"+String.valueOf(i)));

        });
        getInfoPanelLevels.addFindInfoActionListener(e -> {
            cardLayout.show(cardPanel, "13");
            int i = selectBuildingpanel.getCurrentLocation();
            powerusageRoomsPanel.setLabels(ConnectionProvider.getDataFromRestApi("powerusage/over/"+getInfoPanelLevels.getValue()+"/"+String.valueOf(i)));
            powerusageRoomsPanel.showInfo();
        });
        powerusageRoomsPanel.addBackActionListener(e -> {
            cardLayout.show(cardPanel, "6");
            int i = selectBuildingpanel.getCurrentLocation();
            powerusageRoomsPanel.clear();
        });

        cardPanel.add(menuPanel,"1");
        cardPanel.add(addBuildingPanel,"2");
        cardPanel.add(serverOutputPanel,"3");
        cardPanel.add(getInfoPanelBuildings,"4");
        cardPanel.add(selectBuildingpanel,"5");
        cardPanel.add(getInfoPanelLevels,"6");
        cardPanel.add(addLevelPanel,"7");
        cardPanel.add(addRoomPanel,"8");
        cardPanel.add(getInfoPanelRooms,"9");
        cardPanel.add(selectLevelpanel,"10");
        cardPanel.add(selectRoompanel,"11");
        cardPanel.add(getInfoPanelRoom,"12");
        cardPanel.add(powerusageRoomsPanel,"13");

        getContentPane().add(cardPanel, BorderLayout.CENTER);
    }

    private int countBuildings(){
        Building[] buildings = getLocations("Buildings");//chyba count lokejszyn co?
        return buildings.length;
    }
    private int countRooms(int id){
        Gson gson=new Gson();
        Level level = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)),Level.class);


        return level.getAmountOfUnderlings();
    }
    private int countLevels(int id){
        Gson gson=new Gson();
        Building building = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)),Building.class);
        return building.getAmountOfUnderlings();
    }

    private Building[] getLocations(String type){
        String json = ConnectionProvider.getDataFromRestApi(type);
        Gson gson = new Gson();
        return gson.fromJson(json, Building[].class);
    }

    private Building getLocation(String type){
        String json = ConnectionProvider.getDataFromRestApi(type);
        Gson gson = new Gson();
        return gson.fromJson(json, Building.class);
    }

    private float calculateArea(int id, Class<?> cls){
        Gson gson=new Gson();
        if (Location.class.equals(cls)) {
            Location building = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Location.class);
            return building.getArea();
        } else if (Location.class.equals(cls)) {
            Location level = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Location.class);
            return level.getArea();
        } else {
            Location room = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Location.class);
            return room.getArea();
        }
    }

    private float calculateCubature(int id, Class<?> cls){
        Gson gson=new Gson();
        if (Location.class.equals(cls)) {
            Location building = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Location.class);
            return building.getCubature();
        } else if (Level.class.equals(cls)) {
            Level level = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Level.class);
            return level.getCubature();
        } else {
            Room room = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Room.class);
            return room.getCubature();
        }
    }

    private float calculateHeating(int id, Class<?> cls){
        Gson gson=new Gson();
        if (Building.class.equals(cls)) {
            Building building = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Building.class);
            return building.getHeating();
        } else if (Level.class.equals(cls)) {
            Level level = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Level.class);
            return level.getHeating();
        } else {
            Room room = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Room.class);
            return room.getHeating();
        }
    }

    private float calculateLight(int id, Class<?> cls){
        Gson gson=new Gson();
        if (Building.class.equals(cls)) {
            Building building = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Building.class);
            return building.getLight();
        } else if (Level.class.equals(cls)) {
            Level level = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Level.class);
            return level.getLight();
        } else {
            Room room = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)), Room.class);
            return room.getLight();
        }
    }


    private ArrayList<String> getBuildingNamesList(){
        ArrayList<String> buildingsNames = new ArrayList<>();
        Building[] buildings = getLocations("Location");

        for(Building b: buildings){
            buildingsNames.add(b.getName());
        }
        return buildingsNames;
    }

    private ArrayList<String> getLevelsNamesList(int id){
        ArrayList<String> levelNames = new ArrayList<>();
        String json = ConnectionProvider.getDataFromRestApi(String.valueOf(id));
        Gson gson = new Gson();
        Building building = gson.fromJson(json, Building.class);

        for(Location l: building.levels){
            levelNames.add(l.getName());
           // System.out.println(l.getName());
        }

        return levelNames;
    }

    private ArrayList<String> getRoomsNamesList(int id){
        ArrayList<String> levelNames = new ArrayList<>();
       // System.out.println("Id levelu: " + id);
        String json = ConnectionProvider.getDataFromRestApi(String.valueOf(id));
        Gson gson = new Gson();
        Level level = gson.fromJson(json, Level.class);

        for(Location r: level.rooms){
            levelNames.add(r.getName());
          //  System.out.println(r.getName());
        }

        return levelNames;
    }

    private ActionListener getBackActionListener(String previous){
        return (e -> cardLayout.show(cardPanel, previous));
    }

}
