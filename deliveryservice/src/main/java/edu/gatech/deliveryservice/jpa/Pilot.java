package edu.gatech.deliveryservice.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pilot {
    @Id
    private String pilotID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String taxID;
    private String licenseID;
    private Integer numDelivery;
    private String droneID;
    private String storeName;

    public Pilot() {}

    public Pilot(String pilotID, String firstName, String lastName, String phoneNumber,
                 String taxID, String licenseID, Integer numDelivery, String droneID, String storeName) {
        this.pilotID = pilotID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.taxID = taxID;
        this.licenseID = licenseID;
        this.numDelivery = numDelivery;
        this.droneID = droneID;
        this.storeName = storeName;
    }

    public void setPilotID(String pilotID) {
        this.pilotID = pilotID;
    }

    public String getPilotID() {
        return pilotID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }

    public Integer getNumDelivery() {
        return numDelivery;
    }

    public void setNumDelivery(Integer numDelivery) {
        this.numDelivery = numDelivery;
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

    public void assignDrone(String storeName, String droneID) {
        this.storeName = storeName;
        this.droneID = droneID;
    }

    public void deleteAssignedDrone() {
        this.droneID = null;
        this.storeName = null;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "id=" + pilotID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", taxID='" + taxID + '\'' +
                ", licenseID='" + licenseID + '\'' +
                ", numDelivery=" + numDelivery +
                '}';
    }
}
