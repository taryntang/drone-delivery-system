package edu.gatech.deliveryservice.jpa;

import javax.persistence.*;

@Entity
public class Store {
    @Id
    private String storeName;
    private Integer revenue;

    public Store(String storeName, Integer revenue) {
        this.storeName = storeName;
        this.revenue = revenue;
    }

    public Store() {}

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", revenue=" + revenue +
                '}';
    }
}