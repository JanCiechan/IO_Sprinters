package pl.put.poznan.building.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    JButton serverOutputButton;
    JButton addLocation;
    JButton getInfo;
    JButton goBuildings;

    public MenuPanel(){
         JLabel label = new JLabel("Witaj Administratorze!");
         label.setFont(new Font("Serif", Font.BOLD, 16));
         goBuildings = new JButton("Przejdz do budynkow");
         addLocation = new JButton("Dodaj lokacje");
         getInfo = new JButton("Pobierz informacje");
         serverOutputButton = new JButton("Pokaz dane serwera");

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        label.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
        serverOutputButton.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        addLocation.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        getInfo.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        goBuildings.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        addLocation.addActionListener(e -> {

        });

        gbc.insets = new Insets(5,5,5,5);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(label);

        gbc.gridy = 1;
        this.add(goBuildings, gbc);

        gbc.gridy = 2;
        this.add(addLocation, gbc);

        gbc.gridy = 3;
        this.add(getInfo, gbc);

        gbc.gridy = 4;
        this.add(serverOutputButton, gbc);
    }

    public void addServerOutputButtonActionListener(ActionListener actionListener){
        serverOutputButton.addActionListener(actionListener);
    }

    public void addAddLocationActionListener(ActionListener actionListener){
        addLocation.addActionListener(actionListener);
    }

    public void addGetInfoActionListener(ActionListener actionListener){
        getInfo.addActionListener(actionListener);
    }

    public void addGoBuildingsActionListener(ActionListener actionListener){
        goBuildings.addActionListener(actionListener);
    }
}
