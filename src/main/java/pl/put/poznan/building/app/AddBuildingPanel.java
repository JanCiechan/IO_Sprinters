package pl.put.poznan.building.app;

import com.google.gson.Gson;
import pl.put.poznan.building.logic.Building;
import pl.put.poznan.building.logic.ConnectionProvider;
import pl.put.poznan.building.logic.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddBuildingPanel extends JPanel {

    private final JTextField buildingNameTF;
    private final JButton save;
    private final JButton back;
    private static final String BUILDINGS_INFO = "Nazwa budrynku";
    private static final String BUILDINGS_AMOUNT = "Dodawanie budynku ";
    private static final String LEVELS_INFO = "Nazwa poziomu";
    private static final String LEVELS_AMOUNT = "Dodawanie poziomu";
    private static final String ROOMS_INFO = "Nazwa pomieszczenia";
    private static final String ROOMS_AMOUNT = "Dodawanie pomieszczenia ";
    public static final int TYPE_BUILDING = 0;
    public static final int TYPE_LEVEL = 1;
    public static final int TYPE_ROOM = 2;
    public JTextField Levelid;
    public JTextField area;
    public JTextField heating;
    public JTextField cubature;
    public JTextField light;
    public JTextField BuildingIdLabel;

    public AddBuildingPanel(int type){

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelButtons = new JPanel();
        GridBagLayout layoutButtons = new GridBagLayout();
        panelButtons.setLayout(layoutButtons);
        GridBagConstraints gbcButtons = new GridBagConstraints();

        JLabel label = new JLabel("");
        label.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel buildingNameLabel = new JLabel("");

        switch (type){
            case TYPE_BUILDING:
                label.setText(BUILDINGS_AMOUNT);


                buildingNameLabel.setText(BUILDINGS_INFO);
                break;
            case TYPE_LEVEL:
                label.setText(LEVELS_AMOUNT);
                buildingNameLabel.setText(LEVELS_INFO);

                break;
            case TYPE_ROOM:
                label.setText(ROOMS_AMOUNT);
                buildingNameLabel.setText(ROOMS_INFO);
                break;
        }

        buildingNameTF = new JTextField(40);
        JTextField buildingNameTF2 = new JTextField(40);
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
        if(type==TYPE_LEVEL){
            gbc.gridx = 0;
            gbc.gridy = 2;
            JLabel BuildingId = new JLabel("Building id");
            BuildingId.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.add(BuildingId,gbc);
            gbc.gridx=1;

            BuildingIdLabel = new JTextField(40);
            panel.add(BuildingIdLabel,gbc);
        }
        else if(type==TYPE_ROOM){
            gbc.gridx = 1;
            gbc.gridy = 3;

            Levelid = new JTextField(40);
            panel.add(Levelid,gbc);
            gbc.gridx = 0;
            JLabel LevelidLabel=new JLabel("Level id");
            LevelidLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.add(LevelidLabel,gbc);
            gbc.gridy=4;
            gbc.gridx = 1;

            area = new JTextField(40);
            panel.add(area,gbc);
            gbc.gridx = 0;
            JLabel AreaLabel=new JLabel("Area");
            AreaLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.add(AreaLabel,gbc);
            gbc.gridx = 1;
            gbc.gridy=5;

            heating = new JTextField(40);
            panel.add(heating,gbc);
            gbc.gridx = 0;
            JLabel HeatingLabel=new JLabel("Heating");
            HeatingLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.add(HeatingLabel,gbc);
            gbc.gridx = 1;
            gbc.gridy=6;

            cubature = new JTextField(40);
            panel.add(cubature,gbc);
            gbc.gridx = 0;
            JLabel CubatureLabel=new JLabel("Cubature");
            CubatureLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.add(CubatureLabel,gbc);
            gbc.gridx = 1;
            gbc.gridy=7;

            light = new JTextField(40);
            panel.add(light,gbc);
            gbc.gridx = 0;
            JLabel LightLabel=new JLabel("Light");
            LightLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            panel.add(LightLabel,gbc);


        }
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
