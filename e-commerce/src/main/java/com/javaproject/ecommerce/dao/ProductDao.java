package com.javaproject.ecommerce.dao;

import com.javaproject.ecommerce.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product , Integer> {

}
