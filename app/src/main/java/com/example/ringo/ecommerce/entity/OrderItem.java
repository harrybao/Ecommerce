package com.example.ringo.ecommerce.entity;

/**
 * Created by Ringo on 7/15/2017.
 */

public class OrderItem {
    private int imageId;
    private String name;
    private int cost;
    private int amount;

    public OrderItem() {
        super();
    }

    public OrderItem(int imageId, String name, int cost, int amount) {
        super();
        this.imageId = imageId;
        this.name = name;
        this.cost = cost;
        this.amount = amount;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
