package pl.put.poznan.building.app;

import com.google.gson.Gson;
import pl.put.poznan.building.logic.Building;
import pl.put.poznan.building.logic.ConnectionProvider;
import pl.put.poznan.building.logic.Level;
import pl.put.poznan.building.logic.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectLocationPanel extends JPanel {
    public static final int TYPE_BUILDING = 0;
    public static final int TYPE_LEVEL = 1;
    public static final int TYPE_ROOM = 2;
    private int  type;
    private int father;
    private int fatherId;
    private  JButton addLocation=new JButton();
    private final JButton getInfo;
    private  JButton goLevels=new JButton();
    private final JButton back;
    private ArrayList<String> buildingsNames;
    private JComboBox buildingsList;
    private final JLabel label=new JLabel();
    private final GridBagConstraints gbc;
    private int currentLocation;

    public SelectLocationPanel(int type){
        setType(type);
        //setFather(father);
        switch (type){
            case TYPE_BUILDING:
                label.setText("Wybierz budynek");
                label.setFont(new Font("Serif", Font.BOLD, 16));

                goLevels.setText("Przejdz do poziomow");
                addLocation = new JButton("Dodaj poziom");
                break;
            case TYPE_LEVEL:
                label.setText("Wybierz poziom");
                label.setFont(new Font("Serif", Font.BOLD, 16));

                goLevels.setText("Przejdz do pomieszczenia");
                addLocation = new JButton("Dodaj pomieszczenie");
                break;
            case TYPE_ROOM:
                label.setText("Wybierz pomieszczenie");
                label.setFont(new Font("Serif", Font.BOLD, 16));

                goLevels.setText("Nie idziemy glebiej :/");
                addLocation = new JButton("tu moze edycja kto wie?");
                break;

        }
        getInfo = new JButton("Pobierz informacje");
        back = new JButton("Wróć");
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gbc = new GridBagConstraints();

        label.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
        addLocation.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        getInfo.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        goLevels.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        back.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        gbc.insets = new Insets(5,5,5,5);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        draw(type);
    }


    public void setBuildingsNames(ArrayList<String> buildingsNames,int type) {
        this.buildingsNames = buildingsNames;
        this.removeAll();
        this.draw(type);
    }

    public void draw(int type){

        this.setType(type);
        String json="";
        Gson gson=new Gson();
        switch(getType()){
            case TYPE_BUILDING:
                json = ConnectionProvider.getDataFromRestApi("Buildings");
                Building[] buildings = gson.fromJson(json, Building[].class);
                buildingsList = null;
                buildingsNames = new ArrayList<>();
                for(Building b: buildings){
                    buildingsNames.add(b.getName());
                }
                if (buildingsNames.size() != 0){
                    buildingsList = new JComboBox(buildingsNames.toArray());
                    buildingsList.setSelectedIndex(buildingsNames.size()-1);
                    currentLocation=buildings[0].getId();
                    buildingsList.addActionListener(e -> {
                        JComboBox cb = (JComboBox)e.getSource();
                        String name = (String)cb.getSelectedItem();
                        for(Building b: buildings){
                            if (b.getName().equals(name)){
                                currentLocation = b.getId();
                            }
                        }
                    });
                }
                break;
            case TYPE_LEVEL:
                json = ConnectionProvider.getDataFromRestApi("Levels");
                Level[] levels = gson.fromJson(json, Level[].class);

                buildingsList = null;
                buildingsNames = new ArrayList<>();
                for(Level b: levels) {
                    if (b.getBuildingid() == father) {
                        buildingsNames.add(b.getName());
                    }
                }

                if (buildingsNames.size() != 0){
                    buildingsList = new JComboBox(buildingsNames.toArray());
                    buildingsList.setSelectedIndex(buildingsNames.size()-1);
                    currentLocation=levels[0].getId();
                    buildingsList.addActionListener(e -> {
                        JComboBox cb = (JComboBox)e.getSource();
                        String name = (String)cb.getSelectedItem();
                        for(Level b: levels){
                            if (b.getName().equals(name)){
                                currentLocation = b.getId();
                                System.out.println("level location = "  + currentLocation);
                                break;
                            }
                        }
                    });
                }
                break;
            case TYPE_ROOM:
                json = ConnectionProvider.getDataFromRestApi("Rooms");

                Room[] rooms = gson.fromJson(json, Room[].class);
                buildingsList = null;
                buildingsNames = new ArrayList<>();
                for(Room b: rooms){
                    if (b.getLevelid() == father) {
                        buildingsNames.add(b.getName());
                    }
                }

                if (buildingsNames.size() != 0){
                    buildingsList = new JComboBox(buildingsNames.toArray());
                    buildingsList.setSelectedIndex(buildingsNames.size()-1);
                    currentLocation=rooms[0].getId();
                    buildingsList.addActionListener(e -> {
                        JComboBox cb = (JComboBox)e.getSource();
                        String name = (String)cb.getSelectedItem();
                        for(Room b: rooms){
                            if (b.getName().equals(name)){
                                currentLocation = b.getId();
                            }
                        }

                    });
                }
                break;
        }



        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(label);

        gbc.gridy = gbc.gridy+1;
        if (buildingsList != null){
            buildingsList.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
            this.add(buildingsList, gbc);

            gbc.gridy = gbc.gridy+1;
            this.add(goLevels, gbc);

            gbc.gridy = gbc.gridy+1;
            this.add(addLocation, gbc);

            gbc.gridy = gbc.gridy+1;
            this.add(getInfo, gbc);

        } else {
            JLabel empty = new JLabel("Brak budynków");
            this.add(empty, gbc);
        }

        gbc.gridy = gbc.gridy+1;
        this.add(back, gbc);
    }
    public void setType(int i){
        type=i;
    }
    public int getType(){
        return type;
    }
    public void addBackInfoActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }

    public void addGetInfoInfoActionListener(ActionListener actionListener){
        getInfo.addActionListener(actionListener);

    } public void addGoLevelsInfoActionListener(ActionListener actionListener){
        goLevels.addActionListener(actionListener);
    }
    public void addAddLevelsActionListener(ActionListener actionListener){
        addLocation.addActionListener(actionListener);
    }
    public int getCurrentLocation(){
        return currentLocation;
    }

    public String getCurrentLocationName(){
        return buildingsNames.get(fatherId);
    }
    public void setCurrentLocation(int currentLocation){
        this.currentLocation = currentLocation;
    }

    public int getFather() {
        return father;
    }

    public void setFather(int father) {
        this.father = father;
    }
}
