package pl.put.poznan.building.app;

import com.google.gson.Gson;
import pl.put.poznan.building.logic.Building;
import pl.put.poznan.building.logic.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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
        SelectLocationPanel selectLocationPanel = new SelectLocationPanel();
        GetInfoPanel getInfoPanel = new GetInfoPanel();

        menuPanel.addGoBuildingsActionListener(e -> cardLayout.show(cardPanel, "3"));
        menuPanel.addAddLocationActionListener(e -> cardLayout.show(cardPanel, "2"));
        menuPanel.addGetInfoActionListener(e -> {
            cardLayout.show(cardPanel, "4");
            getInfoPanel.setBuildingAmountLabelText(String.valueOf(countBuildings()));
        });

        addBuildingPanel.addSaveActionListener(e -> {
            String buildingName = addBuildingPanel.getBuildingNameTFText();
            Building building = new Building(idCounter++,buildingName,"building");
            Gson gson = new Gson();
            String json = gson.toJson(building);
            data = ConnectionProvider.postDataToRestApi(json);
        });

        addBuildingPanel.addBackActionListener(backActionListener);

        selectLocationPanel.addGetActionListener(e -> {
            data = ConnectionProvider.getDataFromRestApi(selectLocationPanel.getTf());
            selectLocationPanel.setTa(data);
        });

        selectLocationPanel.addPostActionListener(e -> {
            String jsonData = selectLocationPanel.getTf();
            data = ConnectionProvider.postDataToRestApi(jsonData);
            selectLocationPanel.setTa(data);
        });

        selectLocationPanel.addBackActionListener(backActionListener);

        getInfoPanel.addBackInfoActionListener(backActionListener);

        cardPanel.add(menuPanel,"1");
        cardPanel.add(addBuildingPanel,"2");
        cardPanel.add(selectLocationPanel,"3");
        cardPanel.add(getInfoPanel,"4");

        getContentPane().add(cardPanel, BorderLayout.CENTER);
    }

    private int countBuildings(){
        String json = ConnectionProvider.getDataFromRestApi("Location");
        Gson gson = new Gson();
        Building[] userArray = gson.fromJson(json, Building[].class);
        return userArray.length;
    }

}
