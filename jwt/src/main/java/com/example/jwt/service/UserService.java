package com.example.jwt.service;

import com.example.jwt.dao.RoleDao;
import com.example.jwt.dao.UserDao;
import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private RoleDao roleDao;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    public User registerNewUser(User user){
//
//        Role role = roleDao.findById("User").get();
//
//        Set<Role> roles = new HashSet<>();
//
//        roles.add(role);
//        user.setRole(roles);
//
//        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
//
//        return userDao.save(user);
//    }
//
//    public void initRolesAndUser(){
//
//        //Role Data Create
//        Role adminRole = new Role();
//        adminRole.setRoleName("Admin");
//        adminRole.setRoleDescription("Admin role");
//        roleDao.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setRoleName("User");
//        userRole.setRoleDescription("User role");
//        roleDao.save(userRole);
//
//
//        //User Data Created
//        //user ->role = admin
//        User adminUser = new User();
//        adminUser.setUserName("admin123");
//        adminUser.setUserFirstName("admin");
//        adminUser.setUserLastName("admin");
//        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(adminRole);
//        adminUser.setRole(adminRoles);
//        userDao.save(adminUser);
//
//
//        //COMMIT // user ->role = user
////        User user = new User();
////        user.setUserName("lokesh@123");
////        user.setUserFirstName("lokesh");
////        user.setUserLastName("lokesh");
////        user.setUserPassword(getEncodedPassword("lokesh@pass"));
////        Set<Role> userRoles = new HashSet<>();
////        userRoles.add(userRole);
////        user.setRole(userRoles);
////        userDao.save(user);
//    }
//
//    public String getEncodedPassword(String password){
//        return passwordEncoder.encode(password);
//    }
//}














import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }




    public void initRolesAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User user = new User();
        user.setUserName("lokeshkumawat");
        user.setUserPassword(getEncodedPassword("1234"));
        user.setUserFirstName("raj");
        user.setUserLastName("sharma");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }



    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
