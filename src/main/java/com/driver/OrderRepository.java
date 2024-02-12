package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderRepository {
    HashMap<String,Order> totalOrders;
    HashMap<String,Order> orderAssigned;
    HashMap<String,DeliveryPartner> deliveryPartnerID;
    HashMap<String, ArrayList<String>> deliveryPartnerOrderAssignment;

    public OrderRepository(){
        totalOrders = new HashMap<>();
        orderAssigned = new HashMap<>();
        deliveryPartnerID = new HashMap<>();
        deliveryPartnerOrderAssignment = new HashMap<>();
    }
    public void addOrder(Order order){totalOrders.put(order.getId(),order);}
    public void addPartner(String partnerId){deliveryPartnerID.put(partnerId,new DeliveryPartner(partnerId));}
    public void addOrderPartnerPair(String orderId,String partnerId){
        int getNumberOfOrders = deliveryPartnerID.get(partnerId).getNumberOfOrders();
        ++getNumberOfOrders;
        deliveryPartnerID.get(partnerId).setNumberOfOrders(getNumberOfOrders);

        orderAssigned.put(orderId,new Order(orderId));
        if(!deliveryPartnerOrderAssignment.containsKey(partnerId)) deliveryPartnerOrderAssignment.put(partnerId, new ArrayList<>());
        ArrayList<String> temp = deliveryPartnerOrderAssignment.get(partnerId);
        temp.add(orderId);
        deliveryPartnerOrderAssignment.put(partnerId,temp);
    }
    public List<String> getAllOrders(){return new ArrayList<>(totalOrders.keySet());}
    public Order getOrderById(String orderId){return totalOrders.get(orderId);}
    public DeliveryPartner getPartnerById(String partnerId){ return deliveryPartnerID.get(partnerId);}
    public List<String> getOrdersByPartnerId(String partnerId){return deliveryPartnerOrderAssignment.get(partnerId);}
    public Integer getOrderCountById(String partnerId){return deliveryPartnerOrderAssignment.get(partnerId).size();}
    public Integer getCountOfUnassignedOrders(){return (totalOrders.size()-orderAssigned.size());}
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time ,String partnerID){
        List<String> orderList = deliveryPartnerOrderAssignment.get(partnerID);
        int count = 0;
        for(String orderID : orderList){
            String[] split= time.split(":");
            int totalTime = Integer.parseInt(split[0])*60 + Integer.parseInt(split[1]);
            if(totalOrders.get(orderID).getDeliveryTime()>totalTime)++count;
        }
        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        List<String> temp = deliveryPartnerOrderAssignment.get(partnerId);
        int maxTime  = Integer.MIN_VALUE;
        for(String orderID : temp){
            int time = totalOrders.get(orderID).getDeliveryTime();
            if (time > maxTime) maxTime = time;
        }
        String ans = "";
        if(maxTime%60>=10) ans = maxTime/60 + ":" + maxTime%60;
        else ans = maxTime/60 + ":0" +maxTime%60;
        return ans;
    }
    public void deletePartnerById(String partnerId){
        for(String partnerID : deliveryPartnerOrderAssignment.keySet()){
            if(partnerID.equals(partnerId)){
                for(String orderID : deliveryPartnerOrderAssignment.get(partnerID))
                    orderAssigned.remove(orderID);
            }
        }
        deliveryPartnerOrderAssignment.remove(partnerId);
        deliveryPartnerID.remove(partnerId);
    }
    public void deleteOrderById(String orderId){
        totalOrders.remove(orderId);
        orderAssigned.remove(orderId);

        String partnerID = "";
        for(String partner : deliveryPartnerOrderAssignment.keySet()){
            for(String orderID : deliveryPartnerOrderAssignment.get(partner))
                if(orderID.equals(orderId)) partnerID = partner;
        }
        ArrayList<String> temp = deliveryPartnerOrderAssignment.get(partnerID);
        temp.remove(orderId);
        deliveryPartnerOrderAssignment.put(partnerID,temp);
    }
}
