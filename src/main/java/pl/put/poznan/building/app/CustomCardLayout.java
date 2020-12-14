package pl.put.poznan.building.app;

import com.google.gson.Gson;
import pl.put.poznan.building.logic.Building;
import pl.put.poznan.building.logic.ConnectionProvider;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomCardLayout extends JFrame {

    private CardLayout cardLayout ;
    ConnectionProvider connectionProvider;
    private static String data;
    private int idCounter = 0;

    public CustomCardLayout(){
        connectionProvider = new ConnectionProvider();
        JPanel mainPanel4 = new JPanel();
        JLabel buildingAmountLabel4 = new JLabel();

        setTitle("Building Info");
        setSize(800, 450);

        JPanel cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.addGoBuildingsActionListener(e -> {
            cardLayout.show(cardPanel, "3");
        });
        menuPanel.addAddLocationActionListener(e -> {
            cardLayout.show(cardPanel, "2");
        });
        menuPanel.addGetInfoActionListener(e -> {
            cardLayout.show(cardPanel, "4");
            buildingAmountLabel4.setText(String.valueOf(countBuildings()));
        });

        JPanel mainPanel2 = new JPanel();
        JPanel panel = new JPanel();
        GridBagLayout layout2 = new GridBagLayout();
        panel.setLayout(layout2);
        GridBagConstraints gbc2 = new GridBagConstraints();

        JPanel panelButtons = new JPanel();
        GridBagLayout layoutButtons = new GridBagLayout();
        panelButtons.setLayout(layoutButtons);
        GridBagConstraints gbcButtons = new GridBagConstraints();

        JLabel label2 = new JLabel("Dodawanie budynku:");
        label2.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel buildingNameLabel = new JLabel("Nazwa budynku: ");
        JTextField buildingNameTF = new JTextField(40);
        JButton save = new JButton("Zapisz");
        save.addActionListener(e -> {
            String buildingName = buildingNameTF.getText();
            Building building = new Building(idCounter++,buildingName,"building");
            Gson gson = new Gson();
            String json = gson.toJson(building);
            data = ConnectionProvider.postDataToRestApi(json);

        });
        JButton back = new JButton("Wróć");
        back.addActionListener(e -> {
            cardLayout.show(cardPanel, "1");
        });

        label2.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
        buildingNameLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        save.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        back.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        gbc2.insets = new Insets(5,5,5,5);

        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        panel.add(label2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        panel.add(buildingNameLabel, gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 1;
        panel.add(buildingNameTF, gbc2);

        gbcButtons.insets = new Insets(5,5,30,5);
        gbcButtons.gridx = 0;
        gbcButtons.gridy = 0;
        panelButtons.add(save, gbcButtons);

        gbcButtons.gridx = 1;
        panelButtons.add(back, gbcButtons);

        //Adding Components to the frame.
        mainPanel2.add(panel);
        mainPanel2.add(panelButtons);


        JPanel mainPanel3 = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel upperPanel = new JPanel();
        JLabel labelEnter = new JLabel("Enter JSON Text");
        JTextArea ta = new JTextArea();
        JLabel labelInfo = new JLabel("Output from server:");

        ta.setLineWrap(true);
        ta.setColumns(50);
        ta.setRows(15);
        ta.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextField tf = new JTextField(45);
        tf.setText("Location");

        tf.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton get = new JButton("GET");
        get.addActionListener(e -> {
            data = ConnectionProvider.getDataFromRestApi(tf.getText());
            ta.setText(null);
            ta.append(data);
        });

        JButton post = new JButton("POST");
        post.addActionListener(e -> {
            String jsonData = tf.getText();
            data = ConnectionProvider.postDataToRestApi(jsonData);
            ta.setText(null);
            ta.append(data);
        });

        upperPanel.add(labelInfo);
        bottomPanel.add(labelEnter);
        bottomPanel.add(tf);
        bottomPanel.add(get);
        bottomPanel.add(post);

        mainPanel3.add(upperPanel);
        mainPanel3.add(ta);
        mainPanel3.add(bottomPanel);


        GridBagLayout mainPanelLayout4 = new GridBagLayout();
        mainPanel4.setLayout(mainPanelLayout4);
        GridBagConstraints gbcMainPanel4 = new GridBagConstraints();

        JPanel panel4 = new JPanel();
        GridBagLayout layout4 = new GridBagLayout();
        panel4.setLayout(layout4);
        GridBagConstraints gbc4 = new GridBagConstraints();

        JPanel panelButtons4 = new JPanel();
        GridBagLayout layoutButtons4 = new GridBagLayout();
        panelButtons4.setLayout(layoutButtons4);
        GridBagConstraints gbcButtons4 = new GridBagConstraints();

        JLabel label4 = new JLabel("Informacje o wszystkich budynkach");
        label4.setFont(new Font("Serif", Font.BOLD, 16));
        JLabel buildingTitleLabel4 = new JLabel("Ilość budynków: ");

        JButton back4 = new JButton("Wróć");
        back4.addActionListener(e -> {
            cardLayout.show(cardPanel, "1");
        });

        label4.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        buildingTitleLabel4.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        back4.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        gbc4.insets = new Insets(5,5,5,5);

        gbc4.fill = GridBagConstraints.HORIZONTAL;
        gbc4.gridx = 0;
        gbc4.gridy = 0;
        panel4.add(label4,gbc4);

        gbc4.gridx = 0;
        gbc4.gridy = 1;
        panel4.add(buildingTitleLabel4, gbc4);

        gbc4.gridx = 1;
        gbc4.gridy = 1;
        panel4.add(buildingAmountLabel4, gbc4);


        gbcButtons4.insets = new Insets(30,5,30,5);
        gbcButtons4.gridy = 0;
        gbcButtons4.gridx = 0;
        panelButtons4.add(back4, gbcButtons4);


        gbcMainPanel4.fill = GridBagConstraints.HORIZONTAL;
        gbcMainPanel4.gridx = 0;
        gbcMainPanel4.gridy = 0;
        mainPanel4.add(panel4,gbcMainPanel4);

        gbcMainPanel4.gridx = 0;
        gbcMainPanel4.gridy = 1;
        mainPanel4.add(panelButtons4, gbcMainPanel4);

        cardPanel.add(menuPanel,"1");
        cardPanel.add(mainPanel2,"2");
        cardPanel.add(mainPanel3,"3");
        cardPanel.add(mainPanel4,"4");

        getContentPane().add(cardPanel, BorderLayout.CENTER);

    }

    private int countBuildings(){
        String json = ConnectionProvider.getDataFromRestApi("Location");
        Gson gson = new Gson();
        Building[] userArray = gson.fromJson(json, Building[].class);
        System.out.println(String.valueOf(userArray.length));
        return userArray.length;
    }

}
