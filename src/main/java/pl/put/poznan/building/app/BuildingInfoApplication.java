package pl.put.poznan.building.app;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.building.rest.LocationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.building.rest"})
public class BuildingInfoApplication {

    private static String data;

    public static void main(String[] args){
        SpringApplication.run(BuildingInfoApplication.class, args);

        System.setProperty("java.awt.headless", "false"); //Disables headless
        JFrame frame = new JFrame("Building Info");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);

        //Creating the panels and adding components
        JPanel bottomPanel = new JPanel();
        JPanel upperPanel = new JPanel();
        JLabel labelEnter = new JLabel("Enter JSON Text");
        JTextArea ta = new JTextArea();
        JLabel labelInfo = new JLabel("Output from server:");

        ta.setLineWrap(true);
        ta.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextField tf = new JTextField(45);

        tf.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JButton get = new JButton("GET");
        get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data = getDataFromRestApi(tf.getText());
                ta.setText(null);
                ta.append(data);
            }
        });

        JButton post = new JButton("POST");
        post.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jsonData = tf.getText();
                data = postDataToRestApi(jsonData);
                ta.setText(null);
                ta.append(data);
            }
        });

        upperPanel.add(labelInfo);
        bottomPanel.add(labelEnter);
        bottomPanel.add(tf);
        bottomPanel.add(get);
        bottomPanel.add(post);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        frame.getContentPane().add(BorderLayout.NORTH, upperPanel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }

    private static String getDataFromRestApi(String request){
        StringBuilder data = new StringBuilder("");

        try {

            URL url = new URL("http://localhost:8080/application/"+request);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream()))); // Getting the response from the webservice

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    // Instead of this, you could append all your response to a StringBuffer and use `toString()` to get the entire JSON response as a String.
                    // This string json response can be parsed using any json library. Eg. GSON from Google.
                    data.append(output);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toString();
    }

    private static String postDataToRestApi(String jsonInputString){

        StringBuilder result = new StringBuilder("");

        try {
            URL url = new URL("http://localhost:8080/application");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.connect();

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String output;
                while ((output = br.readLine()) != null) {
                    result.append(output);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
