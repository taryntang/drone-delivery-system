package edu.gatech.deliveryservice.controller;

import edu.gatech.deliveryservice.jpa.Item;
import edu.gatech.deliveryservice.jpa.ServiceResponse;
import edu.gatech.deliveryservice.service.StoreSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class ItemController {

    private final StoreSystem storeSystem;

    @Autowired
    public ItemController(StoreSystem storeSystem) {
        this.storeSystem = storeSystem;
    }

    //item
    @GetMapping("/list_items")
    public ResponseEntity<Object> displayItems(@RequestParam("storeName") String storeName) {
        List<Item> items = storeSystem.displayItems(storeName);
        ServiceResponse<List<Item>> response = new ServiceResponse<>("success", items);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/add_item")
    public ResponseEntity<Object> sellItem(@RequestBody Item item) {
        log.info("Start thread executor for selling item");
        storeSystem.sellItem(item);
        log.info("End thread executor for selling item");
        ServiceResponse<Item> response = new ServiceResponse<>("success", item);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
