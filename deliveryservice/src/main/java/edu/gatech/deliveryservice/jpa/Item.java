package edu.gatech.deliveryservice.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String storeName;
    private String itemName;
    private Integer itemWeight;

    public Item(String storeName, String itemName, Integer itemWeight) {
        this.storeName = storeName;
        this.itemName = itemName;
        this.itemWeight = itemWeight;
    }

    public Item() {}

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Integer itemWeight) {
        this.itemWeight = itemWeight;
    }
}
