package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id){
        this.id = id;
    }
    public Order(String id, String deliveryTime) {
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;
        String[] deliveryTimeArray = deliveryTime.split(":");
        this.deliveryTime = Integer.parseInt(deliveryTimeArray[0])*60 + Integer.parseInt(deliveryTimeArray[1]);
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
