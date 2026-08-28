package edu.gatech.deliveryservice.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import edu.gatech.deliveryservice.jpa.ServiceResponse;
import edu.gatech.deliveryservice.service.StoreSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class FlyDroneController {
    private final StoreSystem storeSystem;

    public FlyDroneController(StoreSystem storeSystem) {
        this.storeSystem = storeSystem;
    }

    @PostMapping("/fly_drone")
    public ResponseEntity<Object> flyDrone (@RequestBody ObjectNode objectNode){
        log.info("Start thread executor for flying drone");
        String storeName = objectNode.get("storeName").asText();
        String droneID = objectNode.get("droneID").asText();
        String pilotID = objectNode.get("pilotID").asText();
        storeSystem.flyDrone(storeName, droneID, pilotID);
        log.info("End thread executor for flying drone");
        ServiceResponse<ObjectNode> response = new ServiceResponse<>("success", objectNode);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
