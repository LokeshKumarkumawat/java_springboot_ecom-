package com.example.jwt.service;

import com.example.jwt.dao.RoleDao;
import com.example.jwt.dao.UserDao;
import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    public User registerNewUser(User user){
        return userDao.save(user);
    }

    public void initRolesAndUser(){

        //Role Data Create
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("User role");
        roleDao.save(userRole);


        //User Data Created
        //user ->role = admin
        User adminUser = new User();
        adminUser.setUserName("admin@123");
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserPassword("admin@pass");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);


        //user ->role = user
        User user = new User();
        user.setUserName("lokesh@123");
        user.setUserFirstName("lokesh");
        user.setUserLastName("lokesh");
        user.setUserPassword("lokesh@pass");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }
}
