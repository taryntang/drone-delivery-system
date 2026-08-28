package edu.gatech.deliveryservice.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemLine {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String storeName;
    private String orderID;
    private String itemName;
    private Integer quantity;
    private Integer unitPrice;
    private Integer lineWeight;
    private Integer lineCost;

    public ItemLine(String storeName, String orderID, String itemName, Integer quantity, Integer unitPrice) {
        this.storeName = storeName;
        this.orderID = orderID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public ItemLine() {}

    public String getItemName() {
        return itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getOrderID() {
        return orderID;
    }

    public Integer getLineWeight() {
        return lineWeight;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void updateLinePrice() {
        this.lineCost = this.unitPrice * this.quantity;
    }

    public void setLineWeight(Integer lineWeight) {
        this.lineWeight = lineWeight;
    }

    public Integer getLineCost() {
        return lineCost;
    }

    public void setLineCost(Integer lineCost) {
        this.lineCost = lineCost;
    }

    @Override
    public String toString() {
        return "ItemLine{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", orderID='" + orderID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", lineWeight=" + lineWeight +
                '}';
    }
}
