package com.example.requestforwardapp.controller;

import com.example.requestforwardapp.config.RabbitConfig;
import com.example.requestforwardapp.models.LineItem;
import com.example.requestforwardapp.models.MessageResponse;
import com.example.requestforwardapp.models.SaleOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="api/v1")
@Slf4j
public class OrderController {

    @Autowired
    RabbitTemplate rabbitTemplate;


    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(value="/order",method = RequestMethod.GET)
    public ResponseEntity<List<SaleOrder>> getAllOrders() {

        return null;
    }

    @SneakyThrows()
    @RequestMapping(value="/order",method = RequestMethod.POST)
    public ResponseEntity<SaleOrder> createOrders(@RequestBody SaleOrder saleOrder) {
        SaleOrder result = (SaleOrder) rabbitTemplate.convertSendAndReceive(RabbitConfig.RPC_EXCHANGE, RabbitConfig.RPC_QUEUE1, saleOrder);
        if (result != null) {
            // 获取已发送的消息的 correlationId
            String correlationId = result.getMessageProperties().getCorrelationId();
            log.info("correlationId:{}", correlationId);

            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="/order/{orderid}", method = RequestMethod.GET)
    public ResponseEntity<SaleOrder> getOrder(@PathVariable("orderid") Integer orderid) {
        return null;
    }

    @RequestMapping(value="/order/{orderid}", method = RequestMethod.POST)
    public ResponseEntity<LineItem> createOrderLineItem(@PathVariable("orderid") Integer orderid, @RequestBody LineItem lineItem) {
        return null;
    }

    @RequestMapping(value="/order/{orderid}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> deleteOrder(@PathVariable("orderid") Integer orderid) {
        return null;
    }


    @RequestMapping(value="/order/{orderid}/{lineitemid}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> deleteOrderLineItem(@PathVariable("orderid") Integer orderid, @PathVariable("lineitemid") Integer lineitemid) {
        return null;
    }

    @RequestMapping(value="/order/{orderid}/checkout", method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> checkout(@PathVariable("orderid") Integer orderid) {
        return null;
    }

}

