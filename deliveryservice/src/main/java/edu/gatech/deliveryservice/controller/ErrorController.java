package edu.gatech.deliveryservice.controller;

import edu.gatech.deliveryservice.service.OrderSystem;
import edu.gatech.deliveryservice.service.StoreSystem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    private final StoreSystem storeSystem;
    private final OrderSystem orderSystem;

    public ErrorController(StoreSystem storeSystem, OrderSystem orderSystem) {
        this.storeSystem = storeSystem;
        this.orderSystem = orderSystem;
    }

    @GetMapping("/error_msg")
    public ResponseEntity<String> displayError() {
        String error = storeSystem.getError_msg();
        //ServiceResponse<String> response = new ServiceResponse<>("success", error);
        return ResponseEntity.ok(error);
    }

    @GetMapping("/error_msg_order_system")
    public ResponseEntity<String> displayErrorOrderSystem() {
        String error = orderSystem.getError_msg();
        return ResponseEntity.ok(error);
    }

    /*
    @GetMapping("/error_msg")
    public void getError(Model model) {
        String errorMsg = storeSystem.getError_msg();
        model.addAttribute("error", errorMsg);
    }*/
}
