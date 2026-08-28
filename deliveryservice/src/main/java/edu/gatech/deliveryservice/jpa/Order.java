package edu.gatech.deliveryservice.jpa;


import javax.persistence.*;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String orderID;
    private String storeName;
    private String droneID;
    private String customerID;
    private Integer orderTotalPrice = 0;
    private Integer orderTotalWeight = 0;
    private String orderDate = LocalDate.now().toString(); //yyyy-MM-dd
    private String orderStatus = "Pending";
    private int daysInSystem;
    public Order(String storeName, String orderID, String droneID, String customerID) {
        this.storeName = storeName;
        this.orderID = orderID;
        this.droneID = droneID;
        this.customerID = customerID;
    }

    public Order(String storeName, String orderID, String droneID, String customerID, String orderDate,
                 String orderStatus){
        this.storeName = storeName;
        this.orderID = orderID;
        this.droneID = droneID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.daysInSystem = setDaysInSystem();
        this.orderStatus = orderStatus;
    }


    public Order() {}

    public String getStoreName() {
        return storeName;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getDroneID() {
        return droneID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public Integer getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public Integer getOrderTotalWeight() {
        return orderTotalWeight;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setDroneID(String droneID) {
        this.droneID = droneID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setOrderTotalPrice(Integer orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public void setOrderTotalWeight(Integer orderTotalWeight) {
        this.orderTotalWeight = orderTotalWeight;
    }

    public void updateOrderTotalPrice(Integer linePrice) {
        this.orderTotalPrice += linePrice;
    }

    public void updateOrderTotalWeight(Integer lineWeight) {
        this.orderTotalWeight += lineWeight;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int setDaysInSystem() {
        LocalDate today = LocalDate.now();
        LocalDate orderDay = LocalDate.parse(orderDate);
        return (int) DAYS.between(orderDay,today);
    }

    public int getDaysInSystem() {
        return daysInSystem;
    }

    public void setOrderDate() {
        LocalDate today = LocalDate.now();
        this.orderDate = today.toString();
    }



    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", storeName='" + storeName + '\'' +
                ", droneID='" + droneID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", orderTotalPrice=" + orderTotalPrice +
                ", orderTotalWeight=" + orderTotalWeight +
                '}';
    }
}