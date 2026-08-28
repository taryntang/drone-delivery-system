package edu.gatech.deliveryservice.configuration.security;

import edu.gatech.deliveryservice.jpa.Customer;
import edu.gatech.deliveryservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomerLoginDetails implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String customerID) throws UsernameNotFoundException{

        Customer customer = customerRepository.findCustomerByCustomerID(customerID).get();

        if(customer==null){
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomerDetails(customer);}

}
