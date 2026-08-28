package edu.gatech.deliveryservice.jpa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public String customerID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer customerRating;
    private Integer customerCredit;
    public String customerPassword;

    public Customer(String customerID, String firstName, String lastName, String phoneNumber,
                    Integer customerRating, Integer customerCredit, String customerPassword) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.customerRating = customerRating;
        this.customerCredit = customerCredit;
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        this.customerPassword = passwordEncoder.encode(customerPassword);
////        user.setPassword(encodedPassword);
        this.customerPassword = customerPassword;
    }

    public Customer() {}

    public String getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getCustomerRating() {
        return customerRating;
    }

    public Integer getCustomerCredit() {
        return customerCredit;
    }

    public String getCustomerPassword() { return customerPassword; }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCustomerRating(Integer customerRating) {
        this.customerRating = customerRating;
    }

    public void setCustomerCredit(Integer customerCredit) {
        this.customerCredit = customerCredit;
    }

    public void setCustomerPassword(String customerPassword) { this.customerPassword = customerPassword; }

    public void updateCustomerCredit(Integer cost) {
        this.customerCredit -= cost;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", customerRating=" + customerRating +
                ", customerCredit=" + customerCredit +
                '}';
    }
}
