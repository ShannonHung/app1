package com.example.requestforwardapp.service;


import com.example.requestforwardapp.models.LineItem;
import com.example.requestforwardapp.models.SaleOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class OrderService {

    @Autowired
    WebClient webClientQueryApp;

    @Autowired
    WebClient webClientOrderApp;

    @Autowired
    WebClient webClientProductAppHost;

    public List<SaleOrder> getAllOrders() {
        return null;
    }

    public SaleOrder createOrders(SaleOrder saleOrder) {
        return null;
    }

    public SaleOrder getOrder(Integer orderid) {
        return null;
    }

    public LineItem createOrderLineItem(Integer orderid, LineItem lineItem) {
        return null;
    }

    public void deleteOrder(Integer orderid) {
    }


    public void deleteOrderLineItem(Integer orderid, Integer lineitemid) {
    }

    public int checkout(Integer orderid) {
        return 0;
    }

}
