package pl.put.poznan.building.app;

import com.google.gson.Gson;
import pl.put.poznan.building.logic.Building;
import pl.put.poznan.building.logic.ConnectionProvider;
import pl.put.poznan.building.logic.Level;

import javax.swing.*;
import java.awt.*;
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
        idCounter=countBuildings();
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
        SelectLocationPanel selectBuildingpanel = new SelectLocationPanel(SelectLocationPanel.TYPE_BUILDING);
        SelectLocationPanel selectLevelpanel = new SelectLocationPanel(SelectLocationPanel.TYPE_LEVEL);
        SelectLocationPanel selectRoompanel = new SelectLocationPanel(SelectLocationPanel.TYPE_ROOM);

        menuPanel.addServerOutputButtonActionListener(e -> cardLayout.show(cardPanel, "3"));
        menuPanel.addAddLocationActionListener(e -> cardLayout.show(cardPanel, "2"));
        menuPanel.addGetInfoActionListener(e -> {
            cardLayout.show(cardPanel, "4");
            getInfoPanelBuildings.setAmountLabelText(String.valueOf(countBuildings()));
        });
        menuPanel.addGoBuildingsActionListener(e -> {
            cardLayout.show(cardPanel, "5");
            selectBuildingpanel.setBuildingsNames(getBuildingNamesList(),selectBuildingpanel.getType());
        });

        addBuildingPanel.addSaveActionListener(e -> {
            String buildingName = addBuildingPanel.getBuildingNameTFText();
            Building building = new Building(idCounter++,buildingName,"building");
            Gson gson = new Gson();
            String json = gson.toJson(building);
            data = ConnectionProvider.postDataToRestApi(json);
        });
        selectBuildingpanel.addGoLevelsInfoActionListener(e -> {
            selectLevelpanel.setFather(selectBuildingpanel.getCurrentLocation());
            cardLayout.show(cardPanel,"10");
        });
        selectLevelpanel.addGoLevelsInfoActionListener(e -> {
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


        selectLevelpanel.addGetInfoInfoActionListener(e -> {
            cardLayout.show(cardPanel,"9");
            int i = selectLevelpanel.getCurrentLocation();
            getInfoPanelRooms.setAmountLabelText(String.valueOf(countRooms(i)));
        });
        selectBuildingpanel.addGetInfoInfoActionListener(e -> {
            cardLayout.show(cardPanel, "6");
                int i = selectBuildingpanel.getCurrentLocation();
                getInfoPanelLevels.setAmountLabelText(String.valueOf(countLevels(i)));

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

        getContentPane().add(cardPanel, BorderLayout.CENTER);
    }

    private int countBuildings(){
        Building[] buildings = getLocations("Location");//chyba count lokejszyn co?
        return buildings.length;
    }
    private int countRooms(int id){
        Gson gson=new Gson();
        Level level = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)),Level.class);


        return level.getAmountOfRooms();
    }
    private int countLevels(int id){
        Gson gson=new Gson();
        Building building = gson.fromJson(ConnectionProvider.getDataFromRestApi(String.valueOf(id)),Building.class);


        return building.getAmountOfLevels();
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


    private ArrayList<String> getBuildingNamesList(){
        ArrayList<String> buildingsNames = new ArrayList<>();
        Building[] buildings = getLocations("Location");

        for(Building b: buildings){
            buildingsNames.add(b.getName());
        }
        return buildingsNames;
    }

    private ActionListener getBackActionListener(String previous){
        return (e -> cardLayout.show(cardPanel, previous));
    }

}
