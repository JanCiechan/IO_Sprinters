package pl.put.poznan.building.app;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.building.rest"})
public class BuildingInfoApplication {

    private static String data;


    public static void main(String[] args){


        SpringApplication.run(BuildingInfoApplication.class, args);

        System.setProperty("java.awt.headless", "false"); //Disables headless

        CustomCardLayout cardLayout = new CustomCardLayout();
        cardLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout.setVisible(true);
    }

}
