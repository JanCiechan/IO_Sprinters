package pl.put.poznan.building.app;

import com.google.gson.Gson;
import pl.put.poznan.building.logic.Building;
import pl.put.poznan.building.logic.ConnectionProvider;

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

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);



        MenuPanel menuPanel = new MenuPanel();
        AddBuildingPanel addBuildingPanel = new AddBuildingPanel();
        ServerOutputPanel serverOutputPanel = new ServerOutputPanel();
        GetInfoPanel getInfoPanelBuildings = new GetInfoPanel(GetInfoPanel.TYPE_BUILDING);
        GetInfoPanel getInfoPanelLevels = new GetInfoPanel(GetInfoPanel.TYPE_LEVEL);
        GetInfoPanel getInfoPanelRooms = new GetInfoPanel(GetInfoPanel.TYPE_ROOM    );
        SelectLocationPanel selectLocationPanel = new SelectLocationPanel();

        menuPanel.addServerOutputButtonActionListener(e -> cardLayout.show(cardPanel, "3"));
        menuPanel.addAddLocationActionListener(e -> cardLayout.show(cardPanel, "2"));
        menuPanel.addGetInfoActionListener(e -> {
            cardLayout.show(cardPanel, "4");
            getInfoPanelBuildings.setAmountLabelText(String.valueOf(countBuildings()));
        });
        menuPanel.addGoBuildingsActionListener(e -> {
            cardLayout.show(cardPanel, "5");
            selectLocationPanel.setBuildingsNames(getBuildingNamesList());
        });

        addBuildingPanel.addSaveActionListener(e -> {
            String buildingName = addBuildingPanel.getBuildingNameTFText();
            Building building = new Building(idCounter++,buildingName,"building");
            Gson gson = new Gson();
            String json = gson.toJson(building);
            data = ConnectionProvider.postDataToRestApi(json);
        });

        addBuildingPanel.addBackActionListener(getBackActionListener("1"));

        serverOutputPanel.addGetActionListener(e -> {
            data = ConnectionProvider.getDataFromRestApi(serverOutputPanel.getTf());
            serverOutputPanel.setTa(data);
        });

        serverOutputPanel.addPostActionListener(e -> {
            String jsonData = serverOutputPanel.getTf();
            data = ConnectionProvider.postDataToRestApi(jsonData);
            serverOutputPanel.setTa(data);
        });

        serverOutputPanel.addBackActionListener(getBackActionListener("1"));
        getInfoPanelBuildings.addBackInfoActionListener(getBackActionListener("1"));
        getInfoPanelLevels.addBackInfoActionListener(getBackActionListener("5"));
        selectLocationPanel.addBackInfoActionListener(getBackActionListener("1"));

        selectLocationPanel.addGetInfoInfoActionListener(e -> {
            cardLayout.show(cardPanel, "6");
                int i = selectLocationPanel.getCurrentLocation();
                getInfoPanelLevels.setAmountLabelText(String.valueOf(countLevels(i)));

        });

        cardPanel.add(menuPanel,"1");
        cardPanel.add(addBuildingPanel,"2");
        cardPanel.add(serverOutputPanel,"3");
        cardPanel.add(getInfoPanelBuildings,"4");
        cardPanel.add(selectLocationPanel,"5");
        cardPanel.add(getInfoPanelLevels,"6");

        getContentPane().add(cardPanel, BorderLayout.CENTER);
    }

    private int countBuildings(){
        Building[] buildings = getLocations("Location");
        return buildings.length;
    }

    private int countLevels(int id){
        Building building = getLocation(String.valueOf(id));
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
