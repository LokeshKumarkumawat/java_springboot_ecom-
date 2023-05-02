package com.javaproject.ecommerce.service;


import com.javaproject.ecommerce.configuration.JwtRequestFilter;
import com.javaproject.ecommerce.dao.CartDao;
import com.javaproject.ecommerce.dao.OrderDetailDao;
import com.javaproject.ecommerce.dao.ProductDao;
import com.javaproject.ecommerce.dao.UserDao;
import com.javaproject.ecommerce.entity.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACE = "Placed";


    private static final String KEY ="rzp_test_NRlknsw9KloKEq";
    private static final String KEY_SECRET ="w5i2lKBZCj77UDlsQ307Jypk";
    private static final String CURRENCY ="INR";


    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;


    public void placeOrder(OrderInput orderInput , boolean isSingleProductCheckout){
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity o: productQuantityList ){

            Product product = productDao.findById(o.getProductId()).get();


            String currentUser = JwtRequestFilter.CURRENT_USER;

            User user = userDao.findById(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACE,
                    product.getProductDiscountedPrice()* o.getQuantity(),
                    product,
                    user
            );

            //empty the cart
            if(!isSingleProductCheckout){
                List<Cart> carts =  cartDao.findByUser(user);
                carts.stream().forEach(x-> cartDao.deleteById(x.getCartId()));
            }

            orderDetailDao.save(orderDetail);
        }
    }



    public List<OrderDetail> getOrderDetails(){
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user =  userDao.findById(currentUser).get();
        return orderDetailDao.findByUser(user);
    }

    public List<OrderDetail> geAlltOrderDetails(String status){

        List<OrderDetail> orderDetails = new ArrayList<>();

        if(status.equals("All")){
            orderDetailDao.findAll().forEach(
                    x->orderDetails.add(x)
            );
        }else {
            orderDetailDao.findByOrderStatus(status).forEach(
                    x -> orderDetails.add(x)
            );
        }


        return orderDetails;
    }


    public void markOrderAsDelivered(Integer orderId){
        OrderDetail orderDetail = orderDetailDao.findById(orderId).get();

        if(orderDetail != null){
            orderDetail.setOrderStatus("Delivered");
            orderDetailDao.save(orderDetail);
        }
    }

    public TransactionDetails createTransaction(Double amount){
        //amount
        //current
        //key
        //secretKey

        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount", (amount * 100));
            jsonObject.put("currency", CURRENCY);

            RazorpayClient razorpayClient = new RazorpayClient(KEY , KEY_SECRET);
            Order order =  razorpayClient.orders.create(jsonObject);
            TransactionDetails transactionDetails = prepareTransactionDetails(order);
            return transactionDetails;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;

    }

    private TransactionDetails prepareTransactionDetails(Order order){
        String orderId = order.get("id");
        String currency = order.get("currency");
        Integer amount = order.get("amount");

        TransactionDetails transactionDetails = new TransactionDetails(orderId , currency , amount);
        return transactionDetails;

    }

}
