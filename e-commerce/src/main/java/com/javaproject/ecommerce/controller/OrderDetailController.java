package com.javaproject.ecommerce.controller;


import com.javaproject.ecommerce.entity.OrderDetail;
import com.javaproject.ecommerce.entity.OrderInput;
import com.javaproject.ecommerce.entity.TransactionDetails;
import com.javaproject.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailController {


    @Autowired
    private OrderDetailService orderDetailService;





    @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder/{isSingleProductCheckout}"})
    public void placeOrder(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout, @RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput , isSingleProductCheckout);
    }


    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getOrderDetails"})
    public List<OrderDetail> getOrderDetails(){
        return orderDetailService.getOrderDetails();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllOrderDetails/{status}"})
    public List<OrderDetail> getAlltOrderDetails(@PathVariable(name = "status")String status){
        return orderDetailService.geAlltOrderDetails(status);
    }



    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/markOrderAsDelivered/{orderId}"})
    public void markOrderAsDelivered(@PathVariable(name = "orderId") Integer orderId){
        orderDetailService.markOrderAsDelivered(orderId);
    }


    @PreAuthorize("hasRole('User')")
    @GetMapping({"/createTransation/{amount}"})
    public TransactionDetails createTransaction(@PathVariable(name = "amount") Double amount){
        return orderDetailService.createTransaction(amount);
    }


}
