package com.example.requestforwardapp.service;

import com.example.requestforwardapp.models.Product;
import com.example.requestforwardapp.models.ProductTag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ProductService {

    public List<Product> getAllProducts()  {
        return null;
    }

    public Product getProductbyID(String sku) {
        return null;
    }


    public List<Product> getAllProductsByTag(String tag) {
        return null;
    }

    public Product addProduct(Product product) {
        return null;
    }

    public void deleteProduct(String sku) {

    }

    public void deleteProductTag(Integer iid) {

    }

    public ProductTag addProductTag(String sku, String tag) {
        return null;
    }
}
