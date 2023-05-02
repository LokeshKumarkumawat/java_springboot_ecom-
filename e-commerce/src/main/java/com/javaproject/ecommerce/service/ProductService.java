package com.javaproject.ecommerce.service;

import com.javaproject.ecommerce.configuration.JwtRequestFilter;
import com.javaproject.ecommerce.dao.CartDao;
import com.javaproject.ecommerce.dao.ProductDao;
import com.javaproject.ecommerce.dao.UserDao;
import com.javaproject.ecommerce.entity.Cart;
import com.javaproject.ecommerce.entity.Product;
import com.javaproject.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    public Product addNewProduct(Product product){
        return productDao.save(product);
    }

    public List<Product> getAllProducts(int pageNumber , String searchKey){
        Pageable pageable  = PageRequest.of(pageNumber,4);

        if (searchKey.equals("")){
            return (List<Product>) productDao.findAll(pageable);

        }else {
            return (List<Product>) productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
                    searchKey , searchKey , pageable
            );
        }

    }

    public Product getProductDetailsById(Integer productId){
        return productDao.findById(productId).get();
    }

    public void deleteProductDetailes(Integer productId){
        productDao.deleteById(productId);
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout , Integer productId){
        if(isSingleProductCheckout && productId !=0){
            //we are goving to buy a single Product

            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;

        }else {
            //we are goving to checkout entiire cart
            String username = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(username).get();
            List<Cart> carts = cartDao.findByUser(user);
            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
        }
    }
}
