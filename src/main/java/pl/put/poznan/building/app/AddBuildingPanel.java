package pl.put.poznan.building.app;

import com.google.gson.Gson;
import pl.put.poznan.building.logic.Building;
import pl.put.poznan.building.logic.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddBuildingPanel extends JPanel {

    private final JTextField buildingNameTF;
    private final JButton save;
    private final JButton back;

    public AddBuildingPanel(){

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelButtons = new JPanel();
        GridBagLayout layoutButtons = new GridBagLayout();
        panelButtons.setLayout(layoutButtons);
        GridBagConstraints gbcButtons = new GridBagConstraints();

        JLabel label = new JLabel("Dodawanie budynku:");
        label.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel buildingNameLabel = new JLabel("Nazwa budynku: ");

        buildingNameTF = new JTextField(40);
        save = new JButton("Zapisz");
        back = new JButton("Wróć");

        label.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
        buildingNameLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        save.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        back.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        gbc.insets = new Insets(5,5,5,5);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(buildingNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(buildingNameTF, gbc);

        gbcButtons.insets = new Insets(5,5,30,5);
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 0;
        panelButtons.add(save, gbcButtons);

        gbcButtons.gridx = 1;
        panelButtons.add(back, gbcButtons);

        this.add(panel);
        this.add(panelButtons);
    }

    public void addSaveActionListener(ActionListener actionListener){
        save.addActionListener(actionListener);
    }

    public void addBackActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }

    public String getBuildingNameTFText(){
        return buildingNameTF.getText();
    }
}
