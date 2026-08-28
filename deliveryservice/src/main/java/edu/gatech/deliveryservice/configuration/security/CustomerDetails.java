package edu.gatech.deliveryservice.configuration.security;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import edu.gatech.deliveryservice.jpa.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerDetails implements UserDetails {

    private Customer customer;


    public CustomerDetails (Customer customer) {this.customer = customer; }

    @Override
    public String getPassword() {return customer.getCustomerPassword();}

    @Override
    public String getUsername() {
        return customer.getCustomerID();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
