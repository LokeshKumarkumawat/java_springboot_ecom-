package com.javaproject.ecommerce.service;


import com.javaproject.ecommerce.dao.RoleDao;
import com.javaproject.ecommerce.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;
    public Role createNewRole(Role role){
        return roleDao.save(role);
    }
}
