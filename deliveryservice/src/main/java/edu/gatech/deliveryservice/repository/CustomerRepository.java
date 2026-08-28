package edu.gatech.deliveryservice.repository;

import edu.gatech.deliveryservice.jpa.Customer;
import edu.gatech.deliveryservice.jpa.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT s FROM Customer s WHERE s.customerID = ?1")
    Optional<Customer> findCustomerByCustomerID(String customerID);


    @Query("SELECT s FROM Customer s WHERE s.firstName = ?1 AND s.lastName = ?2")
    Optional<Customer> findCustomerByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT s FROM Customer s WHERE s.customerID <> 'admin'")
    List<Customer> findCustomersByCustomerIDIsNotAdmin();
}
