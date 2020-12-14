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

        ActionListener backActionListener = (e -> cardLayout.show(cardPanel, "1"));

        MenuPanel menuPanel = new MenuPanel();
        AddBuildingPanel addBuildingPanel = new AddBuildingPanel();
        ServerOutputPanel serverOutputPanel = new ServerOutputPanel();
        GetInfoPanel getInfoPanel = new GetInfoPanel();
        SelectLocationPanel selectLocationPanel = new SelectLocationPanel();

        menuPanel.addServerOutputButtonActionListener(e -> cardLayout.show(cardPanel, "3"));
        menuPanel.addAddLocationActionListener(e -> cardLayout.show(cardPanel, "2"));
        menuPanel.addGetInfoActionListener(e -> {
            cardLayout.show(cardPanel, "4");
            getInfoPanel.setBuildingAmountLabelText(String.valueOf(countBuildings()));
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

        addBuildingPanel.addBackActionListener(backActionListener);

        serverOutputPanel.addGetActionListener(e -> {
            data = ConnectionProvider.getDataFromRestApi(serverOutputPanel.getTf());
            serverOutputPanel.setTa(data);
        });

        serverOutputPanel.addPostActionListener(e -> {
            String jsonData = serverOutputPanel.getTf();
            data = ConnectionProvider.postDataToRestApi(jsonData);
            serverOutputPanel.setTa(data);
        });

        serverOutputPanel.addBackActionListener(backActionListener);

        getInfoPanel.addBackInfoActionListener(backActionListener);

        selectLocationPanel.addBackInfoActionListener(backActionListener);


        cardPanel.add(menuPanel,"1");
        cardPanel.add(addBuildingPanel,"2");
        cardPanel.add(serverOutputPanel,"3");
        cardPanel.add(getInfoPanel,"4");
        cardPanel.add(selectLocationPanel,"5");

        getContentPane().add(cardPanel, BorderLayout.CENTER);
    }

    private int countBuildings(){
        Building[] buildings = getBuildings();
        return buildings.length;
    }

    private Building[] getBuildings(){
        String json = ConnectionProvider.getDataFromRestApi("Location");
        Gson gson = new Gson();
        return gson.fromJson(json, Building[].class);
    }


    private ArrayList<String> getBuildingNamesList(){
        ArrayList<String> buildingsNames = new ArrayList<>();
        Building[] buildings = getBuildings();

        for(Building b: buildings){
            buildingsNames.add(b.getName());
        }
        return buildingsNames;
    }

}
