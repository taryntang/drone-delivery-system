package edu.gatech.deliveryservice.configuration;

import edu.gatech.deliveryservice.jpa.Customer;
import edu.gatech.deliveryservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner5(CustomerRepository customerRepository) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return args -> {
            customerRepository.save(new Customer("admin","Admin","Admin","000-000-0000",1,1,passwordEncoder.encode("admin")));
            customerRepository.save(new Customer("cust1","Alex","Roger","225-659-6691",100,200,passwordEncoder.encode("abcdefg")));
            customerRepository.save(new Customer("cust2","Emily","Tucker","808-593-0888",100,300,passwordEncoder.encode("ab4556cdefg")));
            customerRepository.save(new Customer("cust3","Joana","Mayer","708-684-2865",100,270,passwordEncoder.encode("ab3456cdefg")));
            customerRepository.save(new Customer("cust4","Zack","Harman","407-855-3872",100,390,passwordEncoder.encode("abcdefgyutu")));
            customerRepository.save(new Customer("cust8","Maria","Park","313-971-7775",100,88,passwordEncoder.encode("at7bcdefgyutu")));
        };
    }
}
