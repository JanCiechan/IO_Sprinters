package pl.put.poznan.building.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    JButton goBuildings;
    JButton addLocation;
    JButton getInfo;

    public MenuPanel(){
         JLabel label = new JLabel("Witaj Administratorze!");
         label.setFont(new Font("Serif", Font.BOLD, 16));
         goBuildings = new JButton("Przejdz do bydynkow");
         addLocation = new JButton("Dodaj lokacje");
         getInfo = new JButton("Pobierz informacje");

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        label.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
        goBuildings.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        addLocation.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        getInfo.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(getInfo, gbc);
    }

    public void addGoBuildingsActionListener(ActionListener actionListener){
        goBuildings.addActionListener(actionListener);
    }

    public void addAddLocationActionListener(ActionListener actionListener){
        addLocation.addActionListener(actionListener);
    }

    public void addGetInfoActionListener(ActionListener actionListener){
        getInfo.addActionListener(actionListener);
    }
}
