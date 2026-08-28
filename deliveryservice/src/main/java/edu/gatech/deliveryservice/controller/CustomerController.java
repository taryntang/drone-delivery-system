package edu.gatech.deliveryservice.controller;

import edu.gatech.deliveryservice.jpa.Customer;
import edu.gatech.deliveryservice.jpa.ServiceResponse;
import edu.gatech.deliveryservice.service.OrderSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Semaphore;

@Slf4j
@RestController
public class CustomerController {
    private final OrderSystem orderSystem;

    @Autowired
    public CustomerController(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
    }

    //customer
    @GetMapping("/list_customers")
    public ResponseEntity<Object> displayCustomer() {
        List<Customer> customers = orderSystem.displayCustomers();
        ServiceResponse<List<Customer>> response = new ServiceResponse<>("success", customers);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/add_customer")
    public ResponseEntity<Object> makeCustomer(@RequestBody Customer customer) {

        log.info("Start thread executor for adding customer");
        orderSystem.makeCustomer(customer);
        log.info("End thread executor for adding customer");
        ServiceResponse<Customer> response = new ServiceResponse<>("success", customer);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
