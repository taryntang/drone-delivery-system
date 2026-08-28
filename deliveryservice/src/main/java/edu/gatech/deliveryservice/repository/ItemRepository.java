package edu.gatech.deliveryservice.repository;

import edu.gatech.deliveryservice.jpa.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT s FROM Item s WHERE s.storeName = ?1")
    Optional<Item> findItemByStoreName(String storeName);

    @Query("SELECT s FROM Item s WHERE s.storeName = ?2 AND s.itemName = ?1")
    Optional<Item> findItemByItemNameAndAndStoreName(String itemName, String storeName);

    @Query("SELECT s FROM Item s WHERE s.storeName = ?1 order by s.itemName")
    List<Item> findItemsByStoreNameByOrderByItemNameAsc(String storeName);

    @Query("SELECT s FROM Item s WHERE s.storeName = ?2 AND s.itemName = ?1")
    Item getItemByItemNameAndAndStoreName(String itemName, String storeName);
}
