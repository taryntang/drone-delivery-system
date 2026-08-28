package edu.gatech.deliveryservice.configuration;

import edu.gatech.deliveryservice.jpa.Drone;
import edu.gatech.deliveryservice.repository.DroneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DroneConfig {

    @Bean
    CommandLineRunner commandLineRunner4(DroneRepository droneRepository) {
        return args -> {
            Drone d1 = new Drone("1","Wholefoods",100,10);
            d1.setRemainingCap(10);
            d1.setPilotID(null);
            Drone d2 = new Drone("2","Wholefoods",100,10);
            d2.setRemainingCap(10);
            d2.setPilotID(null);
            Drone d3 = new Drone("1","Publix",100,10);
            d3.setRemainingCap(10);
            d3.setPilotID(null);
            Drone d4 = new Drone("2","Publix",100,10);
            d4.setRemainingCap(10);
            d4.setPilotID(null);
            Drone d5 = new Drone("11","Hmart",100,10);
            d5.setRemainingCap(10);
            d5.setPilotID(null);
            Drone d6 = new Drone("2","Hmart",100,10);
            d6.setRemainingCap(10);
            d6.setPilotID(null);
            Drone d7 = new Drone("7","Target",100,10);
            d7.setRemainingCap(10);
            d7.setPilotID(null);
            Drone d8 = new Drone("8","Target",100,10);
            d8.setRemainingCap(10);
            d8.setPilotID(null);

            droneRepository.saveAll(List.of(d1,d2,d3,d4,d5,d6,d7,d8));
        };
    }
}
