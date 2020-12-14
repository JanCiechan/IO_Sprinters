package pl.put.poznan.building.app;

import com.google.gson.Gson;
import pl.put.poznan.building.logic.Building;
import pl.put.poznan.building.logic.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectLocationPanel extends JPanel {

    JButton addLocation;
    JButton getInfo;
    JButton goBuildings;
    JButton back;
    private ArrayList<String> buildingsNames;
    JComboBox buildingsList;
    JLabel label;
    GridBagConstraints gbc;

    public SelectLocationPanel(){
        label = new JLabel("Wybierz budynek");
        label.setFont(new Font("Serif", Font.BOLD, 16));

        goBuildings = new JButton("Przejdz do poziomow");
        addLocation = new JButton("Dodaj poziom");
        getInfo = new JButton("Pobierz informacje");
        back = new JButton("Wróć");
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gbc = new GridBagConstraints();

        label.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
        addLocation.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        getInfo.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        goBuildings.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        back.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        gbc.insets = new Insets(5,5,5,5);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        draw();
    }


    public void setBuildingsNames(ArrayList<String> buildingsNames) {
        this.buildingsNames = buildingsNames;
        this.removeAll();
        this.draw();
    }

    public void draw(){

        this.add(label);
        String json = ConnectionProvider.getDataFromRestApi("Location");
        Gson gson = new Gson();
        Building[] buildings = gson.fromJson(json, Building[].class);
        buildingsList = null;
        buildingsNames = new ArrayList<>();
        for(Building b: buildings){
            buildingsNames.add(b.getName());
        }

        if (buildingsNames.size() != 0){
            buildingsList = new JComboBox(buildingsNames.toArray());
            buildingsList.setSelectedIndex(buildingsNames.size()-1);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(label);

        gbc.gridy = gbc.gridy+1;
        if (buildingsList != null){
            buildingsList.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
            this.add(buildingsList, gbc);

            gbc.gridy = gbc.gridy+1;
            this.add(goBuildings, gbc);

            gbc.gridy = gbc.gridy+1;
            this.add(addLocation, gbc);

            gbc.gridy = gbc.gridy+1;
            this.add(getInfo, gbc);

        } else {
            JLabel empty = new JLabel("Brak budynkow");
            this.add(empty, gbc);
        }

        gbc.gridy = gbc.gridy+1;
        this.add(back, gbc);
    }

    public void addBackInfoActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }
}
