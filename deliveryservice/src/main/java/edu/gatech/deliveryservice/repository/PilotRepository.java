package edu.gatech.deliveryservice.repository;

import edu.gatech.deliveryservice.jpa.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, String> {
    @Query("SELECT p FROM Pilot p WHERE p.pilotID = ?1")
    Optional<Pilot> findPilotByPilotID(String pilotID);

    @Query("SELECT p FROM Pilot p WHERE p.licenseID = ?1")
    Optional<Pilot> findPilotByLicenseID(String licenseID);

    Optional<Pilot> findPilotByStoreNameAndDroneID(String storeName, String droneID);

    List<Pilot> findAllByOrderByPilotIDAsc();
}
