package edu.gatech.deliveryservice.repository;

import edu.gatech.deliveryservice.jpa.ItemLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemLineRepository extends JpaRepository<ItemLine, Long> {
//    @Query("SELECT s FROM ItemLine s WHERE s.orderID = ?1 AND s.itemName = ?2")
//    Optional<ItemLine> findItemLineByOrderIDAndItemName(String orderID, String ItemName);
//
//    @Query("SELECT s FROM ItemLine s WHERE s.storeName = ?1")
//    Optional<ItemLine> findItemLineByStoreName(String storeName);

    @Query("SELECT s FROM ItemLine s WHERE s.storeName = ?1 AND s.orderID =?2")
    List<ItemLine> findItemLinesByStoreNameAndOrderID(String storeName, String orderID);

    @Query("SELECT s FROM ItemLine s WHERE s.storeName = ?1 AND s.orderID = ?2 AND s.itemName = ?3")
    Optional<ItemLine> findItemLinesByStoreNameAndOrderIDAndItemName(String storeName, String orderID, String itemName);

}
