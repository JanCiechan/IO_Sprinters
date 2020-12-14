package pl.put.poznan.building.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConnectionProvider {

    public static String getDataFromRestApi(String request){
        StringBuilder data = new StringBuilder();

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

    public static String postDataToRestApi(String jsonInputString){

        StringBuilder result = new StringBuilder();

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
