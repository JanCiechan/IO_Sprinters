package pl.put.poznan.building;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TestPowerCost {

    @Test
    public void testGetPowerCost() {

        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        TestablePowerCost client = new TestablePowerCost();
        client.setHttpURLConnection(mockConnection);
        assertThrows(RuntimeException.class, () -> client.getPowerCost(new URL("https://developer.nrel.gov/api/utility_rates/von=-82.98")));

    }
}
