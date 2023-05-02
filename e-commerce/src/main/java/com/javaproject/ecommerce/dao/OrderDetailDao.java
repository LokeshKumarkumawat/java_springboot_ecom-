package com.javaproject.ecommerce.dao;


import com.javaproject.ecommerce.entity.OrderDetail;
import com.javaproject.ecommerce.entity.User;
import org.hibernate.criterion.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailDao extends CrudRepository<OrderDetail , Integer> {


    public List<OrderDetail> findByUser(User user);
    public List<OrderDetail> findByOrderStatus(String status);
}
