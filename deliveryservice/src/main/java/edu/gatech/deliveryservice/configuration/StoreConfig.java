package edu.gatech.deliveryservice.configuration;

import edu.gatech.deliveryservice.jpa.Store;
import edu.gatech.deliveryservice.repository.StoreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StoreConfig {

@Bean
CommandLineRunner commandLineRunner(StoreRepository storeRepository) {
      return args -> {
        Store walmart = new Store("Walmart", 2000);
        Store food_city = new Store("Food City", 3000);
        Store wholefoods = new Store("Wholefoods", 1000);
        Store giddy_grocer = new Store("Giddy Grocer", 1500);
        Store hmart = new Store("Hmart", 1800);
        Store target = new Store("Target", 1800);
        storeRepository.saveAll(List.of(walmart,food_city,wholefoods,giddy_grocer,hmart,target));
    };
  }
}
