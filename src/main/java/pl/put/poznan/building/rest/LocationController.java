package pl.put.poznan.building.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.building.logic.Location;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/{text}")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
    private List<Location> locationList = new ArrayList<>();


    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Location> getAll() {
        // perform the transformation, you should run your logic here, below is just a silly example
        //TextTransformer transformer = new TextTransformer(transforms);
        return locationList;
    }


    //@RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @GetMapping("/{id}")
    public Location getLocationByID(@PathVariable("id") int id) {
        // log the parameters
        logger.debug(String.valueOf(id));
        for (Location location : locationList) {
            if (location.getId() == id) {
                return location;
            }
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@RequestBody Location location) {

        // log the parameters
        logger.debug(location.toString());

        locationList.add(location);
        return "Added location.";
    }



}


