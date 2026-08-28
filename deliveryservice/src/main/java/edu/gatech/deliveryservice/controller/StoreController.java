package edu.gatech.deliveryservice.controller;

import edu.gatech.deliveryservice.jpa.ServiceResponse;
import edu.gatech.deliveryservice.jpa.Store;
import edu.gatech.deliveryservice.service.StoreSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class StoreController {

    private final StoreSystem storeSystem;

    @Autowired
    public StoreController(StoreSystem storeSystem) {
        this.storeSystem = storeSystem;
    }

    //store
    @GetMapping("/list_stores")
    public ResponseEntity<Object> displayStores() {
        List<Store> stores = storeSystem.displayStores();
        ServiceResponse<List<Store>> response = new ServiceResponse<>("success", stores);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


    /*@RequestMapping("/display_stores")
    public void displayStores(Model model) {
        List<Store> stores = storeSystem.displayStores();
        model.addAttribute("stores", stores);
    }*/


    @PostMapping("/add_store")
    public ResponseEntity<Object> makeStore(@RequestBody Store store) {
        storeSystem.makeStore(store);
        ServiceResponse<Store> response = new ServiceResponse<>("success", store);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


/*
    @RequestMapping("/make_store")
    public void makeStore(Model model) {
        Store store = new Store();
        model.addAttribute("newstore", store);
    }

    @PostMapping(value = "add")
    public String addStore(Store store) {
        storeSystem.makeStore(store);
        return "redirect:/display_stores";
    }
*/

}
