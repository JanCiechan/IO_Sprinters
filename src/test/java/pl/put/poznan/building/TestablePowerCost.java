package pl.put.poznan.building;

import pl.put.poznan.building.logic.PowerCost;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestablePowerCost extends PowerCost {
    private static HttpURLConnection connection;

    public void setHttpURLConnection(HttpURLConnection connection) {
        this.connection = connection;
    }

    public static HttpURLConnection createHttpURLConnection(URL url)
            throws IOException {
        return connection;

    }
}