package com.example.requestforwardapp.service;


import com.example.requestforwardapp.models.LineItem;
import com.example.requestforwardapp.models.SaleOrder;
import com.example.requestforwardapp.util.RequestApp2;
import com.example.requestforwardapp.util.RequestApp3;
import com.example.requestforwardapp.util.ReuqestApp4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class OrderService {
    @Autowired
    private RequestApp2 app2;
    @Autowired
    private ReuqestApp4 app4;

    //    @RequestMapping(value="/order",method = RequestMethod.GET)
    @Cacheable(value = "orderCache", key = "'orderList'")
    public List<SaleOrder> getAllOrders() {
        return app2.getListMethod(
                SaleOrder.class,
                uri -> uri.path("/order").build()
        );
    }

    //  @RequestMapping(value="/order",method = RequestMethod.POST)
    @CachePut(value = "orderCache", key = "#saleOrder.iid")
    @CacheEvict(value = "orderCache", key = "'orderList'")
    public SaleOrder createOrders(SaleOrder saleOrder) {
        return app4.postMethod(
                SaleOrder.class,
                uri -> uri.path("/order").build(),
                saleOrder
        );
    }

    //  @RequestMapping(value="/order/{orderid}", method = RequestMethod.GET)
    @Cacheable(value = "orderCache", key = "#orderid")
    public SaleOrder getOrder(Integer orderid) {
        return app2.getMethod(
                SaleOrder.class,
                uri -> uri.path("/order/{orderid}").build(orderid)
        );
    }

    // @RequestMapping(value="/order/{orderid}", method = RequestMethod.POST)
    @CachePut(value = "orderCache", key = "#orderid")
    @CacheEvict(value = "orderCache", key = "'productList'")
    public LineItem createOrderLineItem(Integer orderid, LineItem lineItem) {
        return app4.postMethod(
                LineItem.class,
                uri -> uri.path("/order/{orderid}").build(orderid),
                lineItem
        );
    }

    // @RequestMapping(value="/order/{orderid}", method = RequestMethod.DELETE)
    @CacheEvict(value = "orderCache",allEntries=true)
    public Boolean deleteOrder(Integer orderid) {
        return app4.deleteMethod(
                Boolean.class,
                uri -> uri.path("/order/{orderid}").build(orderid)
        );
    }


    // @RequestMapping(value="/order/{orderid}/{lineitemid}", method = RequestMethod.DELETE)
    @CacheEvict(value = "orderCache",allEntries=true)
    public Boolean deleteOrderLineItem(Integer orderid, Integer lineitemid) {
        return app4.deleteMethod(
                Boolean.class,
                uri -> uri.path("/order/{orderid}/{lineitemid}").build(orderid, lineitemid)
        );
    }

    //     @RequestMapping(value="/order/{orderid}/checkout", method = RequestMethod.POST)
    @CachePut(value = "orderCache", key = "#orderid")
    @CacheEvict(value = "orderCache", key = "'orderList'")
    public int checkout(Integer orderid) {
        return app4.postMethod(
                Integer.class,
                uri -> uri.path("/order/{orderid}/checkout").build(orderid)
        );
    }

}
