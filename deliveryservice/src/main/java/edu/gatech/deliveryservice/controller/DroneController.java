package edu.gatech.deliveryservice.controller;

import edu.gatech.deliveryservice.jpa.Drone;
import edu.gatech.deliveryservice.jpa.ServiceResponse;
import edu.gatech.deliveryservice.service.StoreSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class DroneController {
    private final StoreSystem storeSystem;

    public DroneController(StoreSystem storeSystem) {
        this.storeSystem = storeSystem;
    }

    @GetMapping("/list_drones")
    public ResponseEntity<Object> displayDrones(@RequestParam("storeName") String storeName) {
        List<Drone> drones = storeSystem.displayDrones(storeName);
        ServiceResponse<List<Drone>> response = new ServiceResponse<>("success", drones);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/add_drone")
    public ResponseEntity<Object> makeDrone(@RequestBody Drone drone) {
        log.info("Start thread executor for making drone");
        storeSystem.makeDrone(drone);
        log.info("End thread executor for making drone");
        ServiceResponse<Drone> response = new ServiceResponse<>("success", drone);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
