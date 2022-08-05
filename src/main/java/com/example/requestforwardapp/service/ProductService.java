package com.example.requestforwardapp.service;

import com.example.requestforwardapp.models.Product;
import com.example.requestforwardapp.models.ProductTag;
import com.example.requestforwardapp.models.SaleOrder;
import com.example.requestforwardapp.util.RequestApp2;
import com.example.requestforwardapp.util.RequestApp3;
import com.example.requestforwardapp.util.ReuqestApp4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ProductService {

    @Autowired
    private RequestApp2 app2;
    @Autowired
    private RequestApp3 app3;


    //     @RequestMapping(value="/product",method = RequestMethod.GET)
    @Cacheable(value = "productCache", key = "'productList'")
    public List<Product> getAllProducts()  {
        return app2.getListMethod(
                Product.class,
                uri -> uri.path("/product").build()
        );
    }

    //     @RequestMapping(value="/product/{productsku}", method = RequestMethod.GET)
    @Cacheable(value = "productCache", key = "#sku")
    public Product getProductbyID(String productsku) {
        return app2.getMethod(
                Product.class,
                uri -> uri.path("/product/{productsku}").build(productsku)
        );
    }


    //    @RequestMapping(value="/productbytag",method = RequestMethod.GET)
    @Cacheable(value = "productCache", key = "#tag")
    public List<Product> getAllProductsByTag(String tag) {
        return app2.getListMethod(
                Product.class,
                uri -> uri.path("/productbytag").queryParam("tag", tag).build()
        );
    }

    //    @RequestMapping(value="/product", method = RequestMethod.POST)
    @CachePut(value = "productCache", key = "#product.sku")
    @CacheEvict(value = "productCache", key = "'productList'")
    public Product addProduct(Product product) {
        return app3.postMethod(
                Product.class,
                uri -> uri.path("/product").build(),
                product
        );
    }

    //    @RequestMapping(value="/product/{productsku}", method = RequestMethod.DELETE)
    @CacheEvict(value = "productCache",allEntries=true)
    public boolean deleteProduct(String productsku) {
        return app3.deleteMethod(
                Boolean.class,
                uri -> uri.path("/product/{productsku}").build(productsku)
        );
    }

    //    @RequestMapping(value="/tag/{tagid}", method = RequestMethod.DELETE)
    @CacheEvict(value = "productCache",allEntries=true)
    public boolean deleteProductTag(Integer tagid) {
        return app3.deleteMethod(
                Boolean.class,
                uri -> uri.path("/tag/{tagid}").build(tagid)
        );
    }

    //    @RequestMapping(value="/product/{productsku}", method = RequestMethod.POST)
    @CachePut(value = "productCache",key = "#sku")
    @CacheEvict(value = "productCache",key = "'productList'")
    public ProductTag addProductTag(String productsku, String tag) {
        return app3.postMethod(
                ProductTag.class,
                uri -> uri.path("/product/{productsku}").queryParam("tag", tag).build(productsku)
        );
    }
}
