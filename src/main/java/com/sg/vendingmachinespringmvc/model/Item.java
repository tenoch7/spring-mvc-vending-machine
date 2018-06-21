/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;

/**
 *
 * @author juanm
 */
public class Item {
    
    private int itemId;
    private String candyName;
    private BigDecimal price;
    private int inventoryAmount;

//    public Item(String candyName) {
//        this.candyName = candyName;
//    }

    
    
    
    public Item(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


    public String getCandyName() {
        return candyName;
    }

    public void setCandyName(String candyName) {
        this.candyName = candyName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getInventoryAmount() {
        return inventoryAmount;
    }

    public void setInventoryAmount(int inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }

    @Override
    public String toString() {
        return " Item: " + getCandyName() + " |Price: $" + getPrice() + " |#in inventory: "
                + getInventoryAmount();
    }

}
