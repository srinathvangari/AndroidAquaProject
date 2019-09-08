package com.ssvtech.jalforce1.entity;

import java.util.Date;

public class CustomerBean implements java.io.Serializable{

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Integer id;

    private String firstName;

    private String lastName;

    private String mobileNo;

    private String address;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getCustomerstringid() {
        return customerstringid;
    }

    public void setCustomerstringid(String customerstringid) {
        this.customerstringid = customerstringid;
    }

    private String customerstringid;

    public Integer getPendingCans() {
        return pendingCans;
    }

    public void setPendingCans(Integer pendingCans) {
        this.pendingCans = pendingCans;
    }

    private Integer pendingCans;

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    private String routeCode;

    private Date deliverydate1;

    public Date getDeliverydate1() {
        return deliverydate1;
    }

    public void setDeliverydate1(Date deliverydate1) {
        this.deliverydate1 = deliverydate1;
    }

    public String isIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    private String isactive;

    private String deliverecans;

    public String getDeliverecans() {
        return deliverecans;
    }

    public void setDeliverecans(String deliverecans) {
        this.deliverecans = deliverecans;
    }

}
