package pl.put.poznan.building.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GetInfoPanel extends JPanel {

    private final JButton back;
    private JLabel buildingAmountLabel;

    public GetInfoPanel(){

        GridBagLayout mainPanelLayout = new GridBagLayout();
        this.setLayout(mainPanelLayout);
        GridBagConstraints gbcMainPanel = new GridBagConstraints();
        buildingAmountLabel = new JLabel();

        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelButtons = new JPanel();
        GridBagLayout layoutButtons = new GridBagLayout();
        panelButtons.setLayout(layoutButtons);
        GridBagConstraints gbcButtons4 = new GridBagConstraints();

        JLabel label = new JLabel("Informacje o wszystkich budynkach");
        label.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel buildingTitleLabel = new JLabel("Ilość budynków: ");

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
        panel.add(buildingAmountLabel, gbc);

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

    public void setBuildingAmountLabelText(String text) {
        this.buildingAmountLabel.setText(text);
    }
}
