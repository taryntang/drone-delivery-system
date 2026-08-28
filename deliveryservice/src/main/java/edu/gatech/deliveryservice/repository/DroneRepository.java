package edu.gatech.deliveryservice.repository;

import edu.gatech.deliveryservice.jpa.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    @Query("SELECT d FROM Drone d WHERE d.storeName = ?1 order by d.droneID asc ")
    List<Drone> findDronesByStoreNameByOrderByDroneIDAsc(String storeName);

    Optional<Drone> findDroneByStoreNameAndDroneID(String storeName, String droneID);

    Optional<Drone> findDroneByPilotID(String pilotID);
}
