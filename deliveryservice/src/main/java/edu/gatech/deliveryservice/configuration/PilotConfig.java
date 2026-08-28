package edu.gatech.deliveryservice.configuration;

import edu.gatech.deliveryservice.jpa.Pilot;
import edu.gatech.deliveryservice.repository.PilotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PilotConfig {

    @Bean
    CommandLineRunner commandLineRunner3(PilotRepository pilotRepository) {
        return args -> {
            pilotRepository.save(new Pilot("ffig8", "Finneas", "Fig", "888-888-888", "890-12-3456", "panam_10", 33,null, null));
            pilotRepository.save(new Pilot("123df", "AFD", "VDSD", "588-888-888", "120-12-3456", "431nam_10", 33,null, null));
        };
    }
}