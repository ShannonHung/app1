package com.example.requestforwardapp.controller;

import com.example.requestforwardapp.models.MessageResponse;
import com.example.requestforwardapp.models.Product;
import com.example.requestforwardapp.models.ProductTag;
import com.example.requestforwardapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value="/product",method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/product/{productsku}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductbyID(@PathVariable("productsku") String sku) {
        try {
            Product product = productService.getProductbyID(sku);
            if(product == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(product);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/productbytag",method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProductsByTag(@RequestParam String tag) {
        try {
            List<Product> products = productService.getAllProductsByTag(tag);
            if (products != null) {
                return ResponseEntity.ok(products);
            } else {
                throw new Exception("db access error");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/product", method = RequestMethod.POST)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            Product newProduct = productService.addProduct(product);
            return ResponseEntity.ok(newProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value="/product/{productsku}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable("productsku") String sku) {
        boolean result = productService.deleteProduct(sku);
        return result ? ResponseEntity.ok(new MessageResponse(200,"delete product success")) : ResponseEntity.badRequest().build();
    }

    @RequestMapping(value="/tag/{tagid}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> deleteProductTag(@PathVariable("tagid") Integer iid) {
        boolean result = productService.deleteProductTag(iid);
        return result ? ResponseEntity.ok(new MessageResponse(200,"delete product tag success")) : ResponseEntity.badRequest().build();
    }


    @RequestMapping(value="/product/{productsku}", method = RequestMethod.POST)
    public ResponseEntity<ProductTag> addProductTag(@PathVariable("productsku") String sku, @RequestParam String tag) {
        try {
            ProductTag newProductTag = productService.addProductTag(sku,tag);
            if(newProductTag==null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(newProductTag);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
