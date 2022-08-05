package com.example.requestforwardapp.controller;

import com.example.requestforwardapp.models.LineItem;
import com.example.requestforwardapp.models.MessageResponse;
import com.example.requestforwardapp.models.SaleOrder;
import com.example.requestforwardapp.service.OrderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/order",method = RequestMethod.GET)
    public ResponseEntity<List<SaleOrder>> getAllOrders() {
        try {
            List<SaleOrder> orders = orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/order",method = RequestMethod.POST)
    public ResponseEntity<SaleOrder> createOrders(@RequestBody SaleOrder saleOrder) {
        try {
            SaleOrder generatedOrder = orderService.createOrders(saleOrder);
            return ResponseEntity.ok(generatedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/order/{orderid}", method = RequestMethod.GET)
    public ResponseEntity<SaleOrder> getOrder(@PathVariable("orderid") Integer orderid) {
        try {
            SaleOrder order = orderService.getOrder(orderid);
            if(order!=null)
                return ResponseEntity.ok(order);
            else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/order/{orderid}", method = RequestMethod.POST)
    public ResponseEntity<LineItem> createOrderLineItem(@PathVariable("orderid") Integer orderid, @RequestBody LineItem lineItem) {
        try {
            LineItem savedLineItem = orderService.createOrderLineItem(orderid,lineItem);
            return ResponseEntity.ok(savedLineItem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/order/{orderid}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> deleteOrder(@PathVariable("orderid") Integer orderid) {
            boolean result = orderService.deleteOrder(orderid);
            return result ? ResponseEntity.ok(new MessageResponse(200,"delete order success")) : ResponseEntity.badRequest().build();
    }


    @RequestMapping(value="/order/{orderid}/{lineitemid}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> deleteOrderLineItem(@PathVariable("orderid") Integer orderid, @PathVariable("lineitemid") Integer lineitemid) {
            boolean result = orderService.deleteOrderLineItem(orderid,lineitemid);
            return result ? ResponseEntity.ok(new MessageResponse(200,"delete lineitem success")) : ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="/order/{orderid}/checkout", method = RequestMethod.POST)
    public ResponseEntity<Integer> checkout(@PathVariable("orderid") Integer orderid) {
            int state = orderService.checkout(orderid);
            if(state==1)
                return ResponseEntity.ok(state);
            else if(state==0)
                return ResponseEntity.ok(state);
            else if(state==500)
                return  ResponseEntity.ok(500);
            else {
                return ResponseEntity.ok(state);
            }
    }

}

