package com.javaproject.ecommerce.dao;

import com.javaproject.ecommerce.entity.Cart;
import com.javaproject.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends CrudRepository<Cart , Integer> {
    public List<Cart> findByUser(User user);
}
