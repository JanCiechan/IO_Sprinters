package pl.put.poznan.building.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PowerusageRoomsPanel extends JPanel{
    private final JButton back;
    private int id;
    public List<String> rooms = new ArrayList<>();;
    public List<String> powerUsages = new ArrayList<>();;
    public List<String> levels = new ArrayList<>();;
    private JPanel panel;
    public PowerusageRoomsPanel(){
        GridBagLayout mainPanelLayout = new GridBagLayout();
        this.setLayout(mainPanelLayout);
        GridBagConstraints gbcMainPanel = new GridBagConstraints();

        panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);


        JPanel panelButtons = new JPanel();
        GridBagLayout layoutButtons = new GridBagLayout();
        panelButtons.setLayout(layoutButtons);
        GridBagConstraints gbcButtons4 = new GridBagConstraints();

        back = new JButton("Wróć");
        back.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        gbcButtons4.insets = new Insets(30,5,30,5);
        gbcButtons4.gridy = 0;
        gbcButtons4.gridx = 0;
        panelButtons.add(back, gbcButtons4);

        gbcMainPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 0;
        this.add(panel,gbcMainPanel);

        gbcMainPanel.gridx = 0;
        gbcMainPanel.gridy = 1;
        this.add(panelButtons, gbcMainPanel);


    }

    public void clear(){
        panel.removeAll();
        rooms.clear();
        powerUsages.clear();
        levels.clear();
    }

    public void showInfo(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        if (!powerUsages.isEmpty()){
            JLabel levelsName = new JLabel("Poziom");
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(levelsName, gbc);
            JLabel roomNames = new JLabel("Pomieszczenie");
            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(roomNames, gbc);
            JLabel powers = new JLabel("Zużycie");
            gbc.gridx = 2;
            gbc.gridy = 0;
            panel.add(powers, gbc);

            for (int i=0;i<rooms.size();i++){
                JLabel levelName = new JLabel(levels.get(i));
                gbc.gridx = 0;
                gbc.gridy = i+1;
                panel.add(levelName, gbc);
                JLabel roomName = new JLabel(rooms.get(i));
                gbc.gridx = 1;
                gbc.gridy = i+1;
                panel.add(roomName, gbc);
                JLabel power = new JLabel(powerUsages.get(i));
                gbc.gridx = 2;
                gbc.gridy = i+1;
                panel.add(power, gbc);
            }
        }
        else{
            JLabel info = new JLabel("Wszystkie pomieszczenia znajdują się w normie.");
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(info, gbc);
        }
    }
    public void addBackActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }

    public void setLabels(String labels){
        labels = labels.substring(1, labels.length() - 1);
        labels = labels.replace("\"", "");
        List<String> labelsList = new ArrayList<String>(Arrays.asList(labels.split(",")));
        for(int i=0; i<labelsList.size();i++){
            if(i%3==0){
                this.rooms.add(labelsList.get(i));
            }
            else if (i%3==1){
                this.powerUsages.add(labelsList.get(i));
            }
            else{
                this.levels.add(labelsList.get(i));
            }
        }
    }



}
