package pl.put.poznan.building;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.building.rest.LocationController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLocationController {

    private LocationController locationControllerUnderTest;

    @BeforeEach
    void setUp() {
        locationControllerUnderTest = new LocationController();
    }

    @Test
    void testRefresh() throws Exception {

        String result = locationControllerUnderTest.refresh();
        String expected = "Data refreshed";
        assertEquals(expected, result);
        String json = "{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\",\"area\":\"14\"," +
                "\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}";

        locationControllerUnderTest.post(json);

        assertEquals(expected, result);

    }

    @Test
    void testCheckType() throws Exception {


        String json = "{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\",\"area\":\"14\"," +
                "\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}";
        locationControllerUnderTest.post(json);
        String result = locationControllerUnderTest.CheckType(1);
        String expected = "Room";
        assertEquals(expected, result);
    }


    @Test
    void testPostRoom() throws Exception {

        String json = "{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\",\"area\":\"14\"," +
                "\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}";

        String result = locationControllerUnderTest.post(json);
        String expected = "Pomieszczenie dodane";
        assertEquals(expected, result);


    }

    @Test
    void testPostLevel() throws Exception {

        String json = "{\"id\":\"4\",\"name\":\"level1\",\"type\":\"Level\",\"Buildingid\":\"3\"," +
                "\"rooms\":[{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}]}";

        String result = locationControllerUnderTest.post(json);
        String expected = "Poziom dodany";
        assertEquals(expected, result);
    }

    @Test
    void testPostBuilding() throws Exception {

        String json = "{\"id\":\"2\",\"name\":\"builidng1\",\"type\":\"Building\",\"levels\":[{\"id\":\"4\",\"name\":\"level1\",\"type\":\"Level\",\"Buildingid\":\"3\"," +
                "\"rooms\":[{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}]}]}";

        String result = locationControllerUnderTest.post(json);
        String expected = "Budynek dodany";
        assertEquals(expected, result);

    }

    @Test
    void testPost_ThrowsJSONException() {

        assertThrows(JSONException.class, () -> locationControllerUnderTest.post("Something"));
    }

    @Test
    void testDeleteByIDRoom() throws Exception {

        String json = "{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}";

        locationControllerUnderTest.post(json);

        String result = locationControllerUnderTest.deleteByID(json);

        String expected = "Pomieszczenie usuniete";
        assertEquals(expected, result);
    }

    @Test
    void testDeleteByIDLevel() throws Exception {

        String json = "{\"id\":\"4\",\"name\":\"level1\",\"type\":\"Level\",\"Buildingid\":\"3\"," +
                "\"rooms\":[{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}]}";

        locationControllerUnderTest.post(json);

        String result = locationControllerUnderTest.deleteByID(json);

        String expected = "Poziom usuniety";
        assertEquals(expected, result);
    }

    @Test
    void testDeleteByIDBuilding() throws Exception {

        String json = "{\"id\":\"2\",\"name\":\"builidng1\",\"type\":\"Building\",\"levels\":[{\"id\":\"4\",\"name\":\"level1\",\"type\":\"Level\",\"Buildingid\":\"3\"," +
                "\"rooms\":[{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}]}]}";

        locationControllerUnderTest.post(json);

        String result = locationControllerUnderTest.deleteByID(json);

        String expected = "Budynek usuniety";
        assertEquals(expected, result);
    }

    @Test
    void testDeleteByID_ThrowsJSONException() {

        assertThrows(JSONException.class, () -> locationControllerUnderTest.deleteByID("Something"));
    }

    @Test
    void testReplaceLocationBuiliding() throws JSONException {
        String json = "{\"id\":\"2\",\"name\":\"builidng1\",\"type\":\"Building\",\"levels\":[{\"id\":\"4\",\"name\":\"level1\",\"type\":\"Level\",\"Buildingid\":\"3\"," +
                "\"rooms\":[{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}]}]}";

        locationControllerUnderTest.post(json);

        String json2 = "{\"id\":\"2\",\"name\":\"builidng3\",\"type\":\"Building\",\"levels\":[{\"id\":\"4\",\"name\":\"level1\",\"type\":\"Level\",\"Buildingid\":\"3\"," +
                "\"rooms\":[{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}]}]}";

        String result = locationControllerUnderTest.replaceLocation(json2);
        String expected = "Budynek zaktualizowany";
        assertEquals(expected, result);

    }

    @Test
    void testReplaceLocationRoom() throws JSONException {
        String json = "{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}";

        locationControllerUnderTest.post(json);

        String json2 = "{\"id\":\"1\",\"name\":\"pokoj2\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}";

        String result = locationControllerUnderTest.replaceLocation(json2);
        String expected = "Pomieszczenie zaktualizowane";
        assertEquals(expected, result);

    }

    @Test
    void testReplaceLocationLevel() throws JSONException {
        String json = "{\"id\":\"4\",\"name\":\"level1\",\"type\":\"Level\",\"Buildingid\":\"3\"," +
                "\"rooms\":[{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}]}";

        locationControllerUnderTest.post(json);

        String json2 = "{\"id\":\"4\",\"name\":\"level2\",\"type\":\"Level\",\"Buildingid\":\"3\"," +
                "\"rooms\":[{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}]}";


        String result = locationControllerUnderTest.replaceLocation(json2);
        String expected = "Poziom zaktualizowany";
        assertEquals(expected, result);

    }

    @Test
    void testGetLight() throws Exception {

        String json = "{\"id\":\"1\",\"name\":\"pokoj1\",\"type\":\"Room\",\"Levelid\":\"8\"," +
                "\"area\":\"14\",\"cube\":\"12.3\",\"heating\":\"16\",\"light\":\"12\"}";

        locationControllerUnderTest.post(json);
        float result = locationControllerUnderTest.getLight(1);
        float expected = 12.0f;
        assertEquals(expected, result);


    }


    @Test
    void testReplaceLocation_ThrowsJSONException() {

        assertThrows(JSONException.class, () -> locationControllerUnderTest.replaceLocation("Something"));
    }


}
