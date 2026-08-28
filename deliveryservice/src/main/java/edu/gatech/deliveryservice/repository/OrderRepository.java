package edu.gatech.deliveryservice.repository;

import edu.gatech.deliveryservice.jpa.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT s FROM Orders s WHERE s.orderID = ?1 and s.storeName = ?2")
    Optional<Order> findOrderByOrderIDAndStoreNameOrderByOrderID(String orderID, String storeName);


    @Query("SELECT s FROM Orders s WHERE s.storeName = ?1 ORDER BY s.orderID")
    List<Order> findOrdersByStoreNameOrderByOrderID(String storeName);

    @Query("SELECT s FROM Orders s WHERE s.customerID = ?1 ORDER BY s.orderID")
    List<Order> findOrdersByCustomerIDOrderByOrderID(String customerID);


    @Query
    List<Order> findOrdersByDaysInSystemGreaterThan(int days);

    @Modifying
    @Transactional
    @Query
    void deleteByDaysInSystemGreaterThan(int days);

}
