package com.javaproject.ecommerce.entity;

import javax.persistence.*;

@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private String orderFirstName;
    private String orderLastName;
    private String orderFullName;
    private String orderEmailAddress;
    private String orderContactNumber;
    private String orderAlternateContactNumber;
    private String orderFullAddress;
    private String orderCityTown;
    private String orderPostCode;
    private String orderMessage;
    private String orderStatus;
    private Double orderAmount;
    @OneToOne
    private Product product;
    @OneToOne
    private User user;


    public OrderDetail(String orderFirstName,String orderLastName , String orderFullName, String orderEmailAddress , String orderContactNumber, String orderAlternateContactNumber,String orderFullAddress,String orderCityTown, String orderPostCode,String orderMessage, String orderStatus, Double orderAmount, Product product, User user) {
        this.orderFirstName = orderFirstName;
        this.orderLastName = orderLastName;
        this.orderFullName = orderFullName;
        this.orderEmailAddress = orderEmailAddress;
        this.orderContactNumber = orderContactNumber;
        this.orderAlternateContactNumber = orderAlternateContactNumber;
        this.orderFullAddress = orderFullAddress;
        this.orderCityTown = orderCityTown;
        this.orderPostCode = orderPostCode;
        this.orderMessage = orderMessage;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.product = product;
        this.user = user;
    }

    public String getOrderFirstName() {
        return orderFirstName;
    }

    public void setOrderFirstName(String orderFirstName) {
        this.orderFirstName = orderFirstName;
    }

    public String getOrderLastName() {
        return orderLastName;
    }

    public void setOrderLastName(String orderLastName) {
        this.orderLastName = orderLastName;
    }

    public String getOrderEmailAddress() {
        return orderEmailAddress;
    }

    public void setOrderEmailAddress(String orderEmailAddress) {
        this.orderEmailAddress = orderEmailAddress;
    }

    public String getOrderCityTown() {
        return orderCityTown;
    }

    public void setOrderCityTown(String orderCityTown) {
        this.orderCityTown = orderCityTown;
    }

    public String getOrderPostCode() {
        return orderPostCode;
    }

    public void setOrderPostCode(String orderPostCode) {
        this.orderPostCode = orderPostCode;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public OrderDetail(){}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderFullName() {
        return orderFullName;
    }

    public void setOrderFullName(String orderFullName) {
        this.orderFullName = orderFullName;
    }

    public String getOrderFullAddress() {
        return orderFullAddress;
    }

    public void setOrderFullAddress(String orderFullAddress) {
        this.orderFullAddress = orderFullAddress;
    }

    public String getOrderContactNumber() {
        return orderContactNumber;
    }

    public void setOrderContactNumber(String orderContactNumber) {
        this.orderContactNumber = orderContactNumber;
    }

    public String getOrderAlternateContactNumber() {
        return orderAlternateContactNumber;
    }

    public void setOrderAlternateContactNumber(String orderAlternateContactNumber) {
        this.orderAlternateContactNumber = orderAlternateContactNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }
}
