package com.javaproject.ecommerce.dao;

import com.javaproject.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends CrudRepository<User , String> {
}
