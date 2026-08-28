package edu.gatech.deliveryservice.jpa;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String droneID;
    private String storeName;
    private Integer weightCap;
    private Integer remainingCap;
    private Integer remainingDelivery;
    private Integer numOrders = 0;
    private String pilotID;

    public Drone() {}

    public Drone(String droneID, String storeName, Integer weightCap, Integer remainingDelivery) {
        this.droneID = droneID;
        this.storeName = storeName;
        this.weightCap = weightCap;
        this.remainingDelivery = remainingDelivery;
    }

    public Long getId() {
        return id;
    }

    public String getDroneID() {
        return droneID;
    }

    public void setDroneID(String droneID) {
        this.droneID = droneID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getWeightCap() {
        return weightCap;
    }

    public void setWeightCap(Integer weightCap) {
        this.weightCap = weightCap;
    }

    public Integer getRemainingCap() {
        return remainingCap;
    }

    public void setRemainingCap(Integer remainingCap) {
        this.remainingCap = remainingCap;
    }

    public Integer getRemainingDelivery() {
        return remainingDelivery;
    }

    public void setRemainingDelivery(Integer remainingDelivery) {
        this.remainingDelivery = remainingDelivery;
    }

    public Integer getNumOrders() {
        return numOrders;
    }

    public void setNumOrders(Integer numOrders) {
        this.numOrders = numOrders;
    }

    public void updateNumOrders(Integer numOrders) {
        this.numOrders += numOrders;
    }

    public String getPilotID() {
        return pilotID;
    }

    public void setPilotID(String pilotID) {
        this.pilotID = pilotID;
    }

    public void updateDronePilotID(String pilotID) {
        this.pilotID = pilotID;
    }

    public void deleteDronePilotID() {
        this.pilotID = null;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "droneID=" + droneID +
                ", storeName='" + storeName + '\'' +
                ", weightCap=" + weightCap +
                ", remainingCap=" + remainingCap +
                ", remainingDelivery=" + remainingDelivery +
                ", numOrders=" + numOrders +
                '}';
    }
}
