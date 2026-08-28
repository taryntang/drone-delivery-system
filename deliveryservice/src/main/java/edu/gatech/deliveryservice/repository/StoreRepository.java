package edu.gatech.deliveryservice.repository;

import edu.gatech.deliveryservice.jpa.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Store s WHERE s.storeName = ?1")
    Optional<Store> findStoreByStoreName(String storeName);

    List<Store> findAllByOrderByStoreNameAsc();
}
