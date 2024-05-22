package com.example.shoppingmall.order;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    private Map<Integer, Order> orderTable = new HashMap<>();
    private int id = 0;
    public void saveOrder(Order order){
        order.setId(id++);
        orderTable.put(order.getId(), order);
    }
}
