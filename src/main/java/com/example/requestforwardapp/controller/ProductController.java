package com.example.requestforwardapp.controller;

import com.example.requestforwardapp.models.MessageResponse;
import com.example.requestforwardapp.models.Product;
import com.example.requestforwardapp.models.ProductTag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class ProductController {

    @RequestMapping(value="/product",method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts() {
        return null;
    }

    @RequestMapping(value="/product/{productsku}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductbyID(@PathVariable("productsku") String sku) {
        return null;
    }

    @RequestMapping(value="/productbytag",method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProductsByTag(@RequestParam String tag) {
        return null;
    }

    @RequestMapping(value="/product", method = RequestMethod.POST)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return null;
    }

    @RequestMapping(value="/product/{productsku}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable("productsku") String sku) {
        return null;
    }

    @RequestMapping(value="/tag/{tagid}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> deleteProductTag(@PathVariable("tagid") Integer iid) {
        return null;
    }


    @RequestMapping(value="/product/{productsku}", method = RequestMethod.POST)
    public ResponseEntity<ProductTag> addProductTag(@PathVariable("productsku") String sku, @RequestParam String tag) {
        return null;
    }
}
