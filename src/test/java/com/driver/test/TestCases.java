package com.driver.test;

import com.driver.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;

@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {
    OrderController orderController = new OrderController();

    @Test
    void testAddOrder(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");

        assert(orderController.addOrder(order1).getBody().equals("New order added successfully"));
        assert(orderController.addOrder(order2).getBody().equals("New order added successfully"));
        assert(orderController.addOrder(order3).getBody().equals("New order added successfully"));
        assert(orderController.addOrder(order4).getBody().equals("New order added successfully"));
        assert(orderController.addOrder(order5).getBody().equals("New order added successfully"));
    }

    @Test
    void testAddPartner(){
        assert(orderController.addPartner("P301").getBody().equals("New delivery partner added successfully"));
        assert(orderController.addPartner("P302").getBody().equals("New delivery partner added successfully"));
        assert(orderController.addPartner("P303").getBody().equals("New delivery partner added successfully"));
    }

    @Test
    void testAddOrderPartnerPair(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        assert(orderController.addOrderPartnerPair("O101", "P301").getBody().equals("New order-partner pair added successfully"));
        assert(orderController.addOrderPartnerPair("O102", "P302").getBody().equals("New order-partner pair added successfully"));
        assert(orderController.addOrderPartnerPair("O108", "P303").getBody().equals("New order-partner pair added successfully"));
    }

    @Test
    void testGetOrderById(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        //get order by Id
        com.driver.Order rOrder1 = orderController.getOrderById("O101").getBody();
        assert(rOrder1.getId()=="O101" && rOrder1.getDeliveryTime()==390);

        com.driver.Order rOrder3 = orderController.getOrderById("O103").getBody();
        assert(rOrder3.getId()=="O103" && rOrder3.getDeliveryTime()==420);

        com.driver.Order rOrder10 = orderController.getOrderById("O110").getBody();
        assert(rOrder10.getId()=="O110" && rOrder10.getDeliveryTime()==731);

        Order rOrder6 = orderController.getOrderById("O106").getBody();
        assert(rOrder6.getId()=="O106" && rOrder6.getDeliveryTime()==1050);
    }

    @Test
    void testGetPartnerById(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        //get partner by Id
        DeliveryPartner partner1 = orderController.getPartnerById("P301").getBody();
        assert(partner1.getId().equals("P301") && partner1.getNumberOfOrders()==5);

        DeliveryPartner partner2 = orderController.getPartnerById("P302").getBody();
        assert(partner2.getId().equals("P302") && partner2.getNumberOfOrders()==3);

        DeliveryPartner partner3 = orderController.getPartnerById("P303").getBody();
        assert(partner3.getId().equals("P303") && partner3.getNumberOfOrders()==1);
    }

    @Test
    void testGetOrderCountByPartnerId(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        //get order count by partnerId
        assert(orderController.getOrderCountByPartnerId("P301").getBody()==5);
        assert(orderController.getOrderCountByPartnerId("P302").getBody()==3);
        assert(orderController.getOrderCountByPartnerId("P303").getBody()==1);
    }

    @Test
    void testGetOrdersByPartnerId(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        List<String> ordersByP1 = orderController.getOrdersByPartnerId("P301").getBody();
        HashSet<String> setP1 = new HashSet<>();
        for(String order: ordersByP1){
            setP1.add(order);
        }
        assert (setP1.contains("O101"));
        setP1.remove("O101");
        assert (setP1.contains("O104"));
        setP1.remove("O104");
        assert (setP1.contains("O106"));
        setP1.remove("O106");
        assert (setP1.contains("O109"));
        setP1.remove("O109");
        assert (setP1.contains("O111"));
        setP1.remove("O111");
        assert (setP1.isEmpty());

        List<String> ordersByP2 = orderController.getOrdersByPartnerId("P302").getBody();
        HashSet<String> setP2 = new HashSet<>();
        for(String order: ordersByP2){
            setP2.add(order);
        }
        assert (setP2.contains("O102"));
        setP2.remove("O102");
        assert (setP2.contains("O103"));
        setP2.remove("O103");
        assert (setP2.contains("O110"));
        setP2.remove("O110");
        assert (setP2.isEmpty());

        List<String> ordersByP3 = orderController.getOrdersByPartnerId("P303").getBody();
        HashSet<String> setP3 = new HashSet<>();
        for(String order: ordersByP3){
            setP3.add(order);
        }
        assert (setP3.contains("O108"));
        setP3.remove("O108");
        assert (setP3.isEmpty());
    }

    @Test
    void testGetAllOrders(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        List<String> allOrders = orderController.getAllOrders().getBody();
        HashSet<String> setOfAllOrders = new HashSet<>();
        for(String order: allOrders){
            setOfAllOrders.add(order);
        }
        assert(setOfAllOrders.contains("O101"));
        setOfAllOrders.remove("O101");
        assert(setOfAllOrders.contains("O102"));
        setOfAllOrders.remove("O102");
        assert(setOfAllOrders.contains("O103"));
        setOfAllOrders.remove("O103");
        assert(setOfAllOrders.contains("O104"));
        setOfAllOrders.remove("O104");
        assert(setOfAllOrders.contains("O105"));
        setOfAllOrders.remove("O105");
        assert(setOfAllOrders.contains("O106"));
        setOfAllOrders.remove("O106");
        assert(setOfAllOrders.contains("O107"));
        setOfAllOrders.remove("O107");
        assert(setOfAllOrders.contains("O108"));
        setOfAllOrders.remove("O108");
        assert(setOfAllOrders.contains("O109"));
        setOfAllOrders.remove("O109");
        assert(setOfAllOrders.contains("O110"));
        setOfAllOrders.remove("O110");
        assert(setOfAllOrders.contains("O111"));
        setOfAllOrders.remove("O111");
        assert(setOfAllOrders.isEmpty());
    }

    @Test
    void testGetCountOfUnassignedOrders(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        //get order by Id
        com.driver.Order rOrder1 = orderController.getOrderById("O101").getBody();
        assert(rOrder1.getId()=="O101" && rOrder1.getDeliveryTime()==390);

        com.driver.Order rOrder3 = orderController.getOrderById("O103").getBody();
        assert(rOrder3.getId()=="O103" && rOrder3.getDeliveryTime()==420);

        com.driver.Order rOrder10 = orderController.getOrderById("O110").getBody();
        assert(rOrder10.getId()=="O110" && rOrder10.getDeliveryTime()==731);

        Order rOrder6 = orderController.getOrderById("O106").getBody();
        assert(rOrder6.getId()=="O106" && rOrder6.getDeliveryTime()==1050);

        //get partner by Id
        DeliveryPartner partner1 = orderController.getPartnerById("P301").getBody();
        assert(partner1.getId()=="P301" && partner1.getNumberOfOrders()==5);

        DeliveryPartner partner2 = orderController.getPartnerById("P302").getBody();
        assert(partner2.getId()=="P302" && partner2.getNumberOfOrders()==3);

        DeliveryPartner partner3 = orderController.getPartnerById("P303").getBody();
        assert(partner3.getId()=="P303" && partner3.getNumberOfOrders()==1);

        //get order count by partnerId
        assert(orderController.getOrderCountByPartnerId("P301").getBody()==5);
        assert(orderController.getOrderCountByPartnerId("P302").getBody()==3);
        assert(orderController.getOrderCountByPartnerId("P303").getBody()==1);

        //get orders by partnerId
        List<String> ordersByP1 = orderController.getOrdersByPartnerId("P301").getBody();
        HashSet<String> setP1 = new HashSet<>();
        for(String order: ordersByP1){
            setP1.add(order);
        }
        assert (setP1.contains("O101"));
        setP1.remove("O101");
        assert (setP1.contains("O104"));
        setP1.remove("O104");
        assert (setP1.contains("O106"));
        setP1.remove("O106");
        assert (setP1.contains("O109"));
        setP1.remove("O109");
        assert (setP1.contains("O111"));
        setP1.remove("O111");
        assert (setP1.isEmpty());

        List<String> ordersByP2 = orderController.getOrdersByPartnerId("P302").getBody();
        HashSet<String> setP2 = new HashSet<>();
        for(String order: ordersByP2){
            setP2.add(order);
        }
        assert (setP2.contains("O102"));
        setP2.remove("O102");
        assert (setP2.contains("O103"));
        setP2.remove("O103");
        assert (setP2.contains("O110"));
        setP2.remove("O110");
        assert (setP2.isEmpty());

        List<String> ordersByP3 = orderController.getOrdersByPartnerId("P303").getBody();
        HashSet<String> setP3 = new HashSet<>();
        for(String order: ordersByP3){
            setP3.add(order);
        }
        assert (setP3.contains("O108"));
        setP3.remove("O108");
        assert (setP3.isEmpty());

        //get all orders
        List<String> allOrders = orderController.getAllOrders().getBody();
        HashSet<String> setOfAllOrders = new HashSet<>();
        for(String order: allOrders){
            setOfAllOrders.add(order);
        }
        assert(setOfAllOrders.contains("O101"));
        setOfAllOrders.remove("O101");
        assert(setOfAllOrders.contains("O102"));
        setOfAllOrders.remove("O102");
        assert(setOfAllOrders.contains("O103"));
        setOfAllOrders.remove("O103");
        assert(setOfAllOrders.contains("O104"));
        setOfAllOrders.remove("O104");
        assert(setOfAllOrders.contains("O105"));
        setOfAllOrders.remove("O105");
        assert(setOfAllOrders.contains("O106"));
        setOfAllOrders.remove("O106");
        assert(setOfAllOrders.contains("O107"));
        setOfAllOrders.remove("O107");
        assert(setOfAllOrders.contains("O108"));
        setOfAllOrders.remove("O108");
        assert(setOfAllOrders.contains("O109"));
        setOfAllOrders.remove("O109");
        assert(setOfAllOrders.contains("O110"));
        setOfAllOrders.remove("O110");
        assert(setOfAllOrders.contains("O111"));
        setOfAllOrders.remove("O111");
        assert(setOfAllOrders.isEmpty());

        //get count of unassigned orders
        assert(orderController.getCountOfUnassignedOrders().getBody()==2);
    }

    @Test
    void testGetCountOfOrdersLeftAfterGivenTime(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        //get count of orders after given time
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("00:00", "P301").getBody()==5);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("00:00", "P302").getBody()==3);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("00:00", "P303").getBody()==1);

        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("08:00", "P301").getBody()==4);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("08:00", "P302").getBody()==1);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("08:00", "P303").getBody()==1);

        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("10:30", "P301").getBody()==3);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("10:30", "P302").getBody()==1);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("10:30", "P303").getBody()==1);

        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("18:45", "P301").getBody()==1);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("18:45", "P302").getBody()==0);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("18:45", "P303").getBody()==1);

        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("23:59", "P301").getBody()==0);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("23:59", "P302").getBody()==0);
        assert (orderController.getOrdersLeftAfterGivenTimeByPartnerId("23:59", "P303").getBody()==0);
    }

    @Test
    void testGetLastDeliveryTime(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        //get last delivery time
        assert(orderController.getLastDeliveryTimeByPartnerId("P301").getBody().equals("21:00"));
        assert(orderController.getLastDeliveryTimeByPartnerId("P302").getBody().equals("12:11"));
        assert(orderController.getLastDeliveryTimeByPartnerId("P303").getBody().equals("19:30"));
    }

    @Test
    void testDeletePartnerById(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        //delete partner by id
        assert(orderController.deletePartnerById("P301").getBody().equals("P301" + " removed successfully"));
        assert(orderController.getCountOfUnassignedOrders().getBody()==7);
    }

    @Test
    void testOrderController(){
        com.driver.Order order1 = new com.driver.Order("O101", "06:30");
        com.driver.Order order2 = new com.driver.Order("O102", "07:30");
        com.driver.Order order3 = new com.driver.Order("O103", "07:00");
        com.driver.Order order4 = new com.driver.Order("O104", "09:30");
        com.driver.Order order5 = new com.driver.Order("O105", "09:30");
        com.driver.Order order6 = new com.driver.Order("O106", "17:30");
        com.driver.Order order7 = new com.driver.Order("O107", "23:00");
        com.driver.Order order8 = new com.driver.Order("O108", "19:30");
        com.driver.Order order9 = new com.driver.Order("O109", "15:33");
        com.driver.Order order10 = new com.driver.Order("O110", "12:11");
        com.driver.Order order11 = new com.driver.Order("O111", "21:00");

        orderController.addOrder(order1);
        orderController.addOrder(order2);
        orderController.addOrder(order3);
        orderController.addOrder(order4);
        orderController.addOrder(order5);
        orderController.addOrder(order6);
        orderController.addOrder(order7);
        orderController.addOrder(order8);
        orderController.addOrder(order9);
        orderController.addOrder(order10);
        orderController.addOrder(order11);

        orderController.addPartner("P301");
        orderController.addPartner("P302");
        orderController.addPartner("P303");

        orderController.addOrderPartnerPair("O101", "P301");
        orderController.addOrderPartnerPair("O102", "P302");
        orderController.addOrderPartnerPair("O103", "P302");
        orderController.addOrderPartnerPair("O104", "P301");
        orderController.addOrderPartnerPair("O106", "P301");
        orderController.addOrderPartnerPair("O108", "P303");
        orderController.addOrderPartnerPair("O109", "P301");
        orderController.addOrderPartnerPair("O110", "P302");
        orderController.addOrderPartnerPair("O111", "P301");

        //delete order by id
        assert(orderController.deleteOrderById("O108").getBody().equals("O108" + " removed successfully"));
        assert(orderController.getOrderCountByPartnerId("P302").getBody()==3);
        assert(orderController.getOrderCountByPartnerId("P303").getBody()==0);
    }
}


