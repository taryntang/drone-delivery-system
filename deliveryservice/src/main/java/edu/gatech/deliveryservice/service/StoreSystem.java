package edu.gatech.deliveryservice.service;

import edu.gatech.deliveryservice.jpa.Drone;
import edu.gatech.deliveryservice.jpa.Item;
import edu.gatech.deliveryservice.jpa.Pilot;
import edu.gatech.deliveryservice.jpa.Store;
import edu.gatech.deliveryservice.repository.DroneRepository;
import edu.gatech.deliveryservice.repository.ItemRepository;
import edu.gatech.deliveryservice.repository.PilotRepository;
import edu.gatech.deliveryservice.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreSystem {

    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;
    private final PilotRepository pilotRepository;
    private final DroneRepository droneRepository;
    private String error_msg;

    @Autowired
    public StoreSystem(StoreRepository storeRepository, ItemRepository itemRepository,
                       PilotRepository pilotRepository, DroneRepository droneRepository) {
        this.storeRepository = storeRepository;
        this.itemRepository = itemRepository;
        this.pilotRepository = pilotRepository;
        this.droneRepository = droneRepository;
    }

    //store
    @Async(value = "asyncMakeStoreThread")
    public void makeStore(Store store) {
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(store.getStoreName());

        try {Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (storeByStoreName.isPresent()) {
            error_msg = "ERROR:store_identifier_already_exists";
        } else {
            storeRepository.save(store);
            error_msg = "OK:change_completed";
        }
        System.out.println(error_msg);
    }

    public List<Store> displayStores() {
        error_msg = "OK:display_completed";
        System.out.println(error_msg);
        return storeRepository.findAllByOrderByStoreNameAsc();
    }


    //item
    @Async(value = "asyncSellItemThread")
    public void sellItem(Item item) {
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(item.getStoreName());
        Optional<Item> itemByItemNameAndStoreName =
                itemRepository.findItemByItemNameAndAndStoreName(item.getItemName(), item.getStoreName());

        try {Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!storeByStoreName.isPresent()) {
            error_msg = "ERROR:store_identifier_does_not_exist";
        } else if (itemByItemNameAndStoreName.isPresent()) {
            error_msg = "ERROR:item_identifier_already_exists";
        } else {
            itemRepository.save(item);
            error_msg = "OK:change_completed";
        }
        System.out.println(error_msg);
    }

    public List<Item> displayItems(String storeName) {
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(storeName);
        if (!storeByStoreName.isPresent()) {
            error_msg = "ERROR:store_identifier_does_not_exist";
            throw new IllegalStateException("ERROR:store_identifier_does_not_exist");
        }
        List<Item> itemsByStoreName = itemRepository.findItemsByStoreNameByOrderByItemNameAsc(storeName);
        error_msg = "OK:display_completed";
        System.out.println(error_msg);
        return itemsByStoreName;
    }

    //pilot
    @Async(value = "asyncMakePilotThread")
    public void makePilot(Pilot pilot) {
        Optional<Pilot> pilotByPilotID = pilotRepository.findPilotByPilotID(pilot.getPilotID());
        Optional<Pilot> pilotByLicenceID = pilotRepository.findPilotByLicenseID(pilot.getLicenseID());
        try {Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (pilotByPilotID.isPresent()) {
            error_msg = "ERROR: pilot_identifier_already_exists";
        } else if (pilotByLicenceID.isPresent()) {
            error_msg = "ERROR:pilot_license_already_exists";
        } else {
            pilotRepository.save(pilot);
            error_msg = "OK:change_completed";
        }
        System.out.println(error_msg);
    }

    public List<Pilot> displayPilots() {
        error_msg = "OK:display_completed";
        System.out.println(error_msg);
        return pilotRepository.findAllByOrderByPilotIDAsc();
    }

    //drone
    public List<Drone> displayDrones(String storeName) {
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(storeName);
        if (!storeByStoreName.isPresent()) {
            error_msg = "ERROR:store_identifier_does_not_exist";
            throw new IllegalStateException("ERROR:store_identifier_does_not_exist");
        }
        List<Drone> dronesByStoreName = droneRepository.findDronesByStoreNameByOrderByDroneIDAsc(storeName);
        error_msg = "OK:display_completed";
        System.out.println(error_msg);
        return dronesByStoreName;
    }

    @Async(value = "asyncMakeDroneThread")
    public void makeDrone(Drone drone) {
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(drone.getStoreName());
        Optional<Drone> droneByStoreNameAndDroneID = droneRepository.
                findDroneByStoreNameAndDroneID(drone.getStoreName(), drone.getDroneID());
        try {Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!storeByStoreName.isPresent()) {
            error_msg = "ERROR:store_identifier_does_not_exist";
        } else if (droneByStoreNameAndDroneID.isPresent()) {
            error_msg = "ERROR:drone_identifier_already_exists";
        } else if (drone.getRemainingDelivery() == 0) {
            error_msg = ("ERROR:drone_needs_fuel");
        } else {
            drone.setRemainingCap(drone.getWeightCap());
            droneRepository.save(drone);
            error_msg = "OK:change_completed";
        }
        System.out.println(error_msg);
    }

    @Async(value = "asyncFlyDroneThread")
    public void flyDrone(String storeName, String droneID, String pilotID) {
        Optional<Store> storeByStoreName = storeRepository.findStoreByStoreName(storeName);
        Optional<Drone> droneByStoreNameAndDroneID = droneRepository.findDroneByStoreNameAndDroneID(storeName, droneID);
        Optional<Pilot> pilotByPilotID = pilotRepository.findPilotByPilotID(pilotID);
        Optional<Drone> droneByPilotID = droneRepository.findDroneByPilotID(pilotID);
        Optional<Pilot> pilotByStoreNameAndDroneID = pilotRepository.findPilotByStoreNameAndDroneID(storeName, droneID);

        try {Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!storeByStoreName.isPresent()) {
            error_msg = "ERROR:store_identifier_does_not_exist";
        } else if (!droneByStoreNameAndDroneID.isPresent()) {
            error_msg = "ERROR:drone_identifier_does_not_exist";
        } else if (!pilotByPilotID.isPresent()) {
            error_msg = "ERROR:pilot_identifier_does_not_exist";
        } else {
            if (pilotByStoreNameAndDroneID.isPresent()) {
                pilotByStoreNameAndDroneID.get().deleteAssignedDrone();
                pilotRepository.save(pilotByStoreNameAndDroneID.get());
            }
            if (droneByPilotID.isPresent()) {
                if (droneByPilotID.get().getRemainingDelivery() == 0) {
                    error_msg = ("ERROR:drone_needs_fuel");
                } else {
                    droneByPilotID.get().deleteDronePilotID();
                    droneRepository.save(droneByPilotID.get());
                }
            }
            pilotByPilotID.get().assignDrone(storeName, droneID);
            pilotRepository.save(pilotByPilotID.get());
            droneByStoreNameAndDroneID.get().updateDronePilotID(pilotID);
            droneRepository.save(droneByStoreNameAndDroneID.get());
            error_msg = "OK:change_completed";
        }
        System.out.println(error_msg);
    }

    public String getError_msg() {
        return error_msg;
    }
}
