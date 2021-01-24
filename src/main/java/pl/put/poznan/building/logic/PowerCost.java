package pl.put.poznan.building.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;
/**
 * Służy do pobrania ceny prądu z internetu
 *
 */
public class PowerCost {
    /**
     * Pobiera cene prądu z internetu
     * @param url adres sieciowy do pobrania danych o cenie prądu
     *
     * */
    public static String getPowerCost(URL url){

        StringBuilder data = new StringBuilder();

        try {
            HttpURLConnection connection = createHttpURLConnection(url);
            connection.setRequestMethod("GET");
            connection.connect();

            int responsecode = connection.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (connection.getInputStream())));
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
    /**
     * Tworzy połączenie na danym adresie url
     * @param url adres sieciowy do pobrania danych o cenie prądu
     *
     * */
    protected static HttpURLConnection createHttpURLConnection(URL url)
            throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

}