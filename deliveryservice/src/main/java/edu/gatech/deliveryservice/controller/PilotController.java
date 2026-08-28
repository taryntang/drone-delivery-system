package edu.gatech.deliveryservice.controller;

import edu.gatech.deliveryservice.jpa.Pilot;
import edu.gatech.deliveryservice.jpa.ServiceResponse;
import edu.gatech.deliveryservice.service.StoreSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PilotController {

    private final StoreSystem storeSystem;

    public PilotController(StoreSystem storeSystem) {
        this.storeSystem = storeSystem;
    }

    @GetMapping("/list_pilots")
    public ResponseEntity<Object> displayPilots() {
        List<Pilot> pilots = storeSystem.displayPilots();
        ServiceResponse<List<Pilot>> response = new ServiceResponse<>("success", pilots);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/add_pilot")
    public ResponseEntity<Object> makePilot(@RequestBody Pilot pilot) {
        log.info("Start thread executor for making pilot");
        storeSystem.makePilot(pilot);
        log.info("End thread executor for making pilot");//"Multi-threads end to make store"
        ServiceResponse<Pilot> response = new ServiceResponse<>("success", pilot);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
