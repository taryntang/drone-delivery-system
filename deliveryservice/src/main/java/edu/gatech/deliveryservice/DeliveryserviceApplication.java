package edu.gatech.deliveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryserviceApplication{

	public static void main(String[] args) {
		SpringApplication.run(DeliveryserviceApplication.class, args);
		System.out.print("Welcome to the Delivery Service System");
	}
}
