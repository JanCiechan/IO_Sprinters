package pl.put.poznan.building.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

public class PowerCost {

    public static String getPowerCost (){
    StringBuilder data = new StringBuilder();

        try {

        URL url = new URL("https://developer.nrel.gov/api/utility_rates/v3.json?api_key=DEMO_KEY&lat=35.45&lon=-82.98");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responsecode = conn.getResponseCode();

        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;

            while ((output = br.readLine()) != null) {
                data.append(output);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        String data2 = data.toString();
        return data2.substring(410,416);
}

}
