package pl.put.poznan.building.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GetInfoPanel extends JPanel {

    private final JButton back;
    private JLabel amountLabel;
    private JLabel area;
    private JLabel heating;
    private JLabel cubature;
    private JLabel light;


    private static final String BUILDINGS_INFO = "Informacje o wszystkich budynkach";
    private static final String BUILDINGS_AMOUNT = "Ilość budynków: ";
    private static final String LEVELS_INFO = "Informacje o wszystkich poziomach";
    private static final String LEVELS_AMOUNT = "Ilość poziomów: ";
    private static final String ROOMS_INFO = "Informacje o wszystkich pokojach";
    private static final String ROOMS_AMOUNT = "Ilość pokoi: ";
    public static final int TYPE_BUILDING = 0;
    public static final int TYPE_LEVEL = 1;
    public static final int TYPE_ROOM = 2;

    public GetInfoPanel(int type){

        GridBagLayout mainPanelLayout = new GridBagLayout();
        this.setLayout(mainPanelLayout);
        GridBagConstraints gbcMainPanel = new GridBagConstraints();
        amountLabel = new JLabel();
        area = new JLabel();
        heating = new JLabel();
        cubature = new JLabel();
        light = new JLabel();

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelButtons = new JPanel();
        GridBagLayout layoutButtons = new GridBagLayout();
        panelButtons.setLayout(layoutButtons);
        GridBagConstraints gbcButtons4 = new GridBagConstraints();
        JLabel buildingTitleLabel =  new JLabel();
        JLabel label = new JLabel();

        JLabel areaLabel = new JLabel("Powierchnia");
        JLabel heatingLabel = new JLabel("Energia ogrzewania");
        JLabel cubatureLabel = new JLabel("Kubatura");
        JLabel lightLabel = new JLabel("Moc oświetlenia");

        switch (type){
            case TYPE_BUILDING:
                 buildingTitleLabel.setText(BUILDINGS_AMOUNT);
                 label.setText(BUILDINGS_INFO);
                break;
            case TYPE_LEVEL:
                buildingTitleLabel.setText(LEVELS_AMOUNT);
                label.setText(LEVELS_INFO);
                break;
            case TYPE_ROOM:
                buildingTitleLabel.setText(ROOMS_AMOUNT);
                label.setText(ROOMS_INFO);
                break;
        }

        label.setFont(new Font("Serif", Font.BOLD, 16));


        back = new JButton("Wróć");
        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        buildingTitleLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        back.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        gbc.insets = new Insets(5,5,5,5);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(buildingTitleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(amountLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(areaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(area, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(heatingLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(heating, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(cubatureLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(cubature, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(lightLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(light, gbc);

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

    public void addBackInfoActionListener(ActionListener actionListener){
        back.addActionListener(actionListener);
    }

    public void setAmountLabelText(String text) {
        this.amountLabel.setText(text);
    }
    public void setAreaLabelText(String text) {
        this.area.setText(text);
    }
    public void setHeatingLabelText(String text) {
        this.heating.setText(text);
    }
    public void setLightLabelText(String text) {
        this.light.setText(text);
    }
    public void setCubatureLabelText(String text) {
        this.cubature.setText(text);
    }

}
