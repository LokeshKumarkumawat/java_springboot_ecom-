package com.javaproject.ecommerce.service;

import com.javaproject.ecommerce.dao.ProductDao;
import com.javaproject.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product addNewProduct(Product product){
        return productDao.save(product);
    }
}
