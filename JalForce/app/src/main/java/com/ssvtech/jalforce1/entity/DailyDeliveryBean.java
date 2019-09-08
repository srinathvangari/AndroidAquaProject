package com.ssvtech.jalforce1.entity;
import java.util.Date;

public class DailyDeliveryBean implements java.io.Serializable{



    private Integer id;


    private Date trans_date;

    private Integer customerId;

    private Integer returnCanCount;

    private Integer deliveredCanCount;

    private Integer employeeId;

    private String custFirstName;

    private String custLastName;

    private String empFirstName;

    private String empLastName;

    private String routeAddress;

    private String custAddress;

    private String custEmail;

    private String empEmail;



    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String customerstringid;



    public void setCustomerstringid(String customerstringid) {
        this.customerstringid = customerstringid;
    }

    public String getCustomerstringid() {
        return customerstringid;
    }





    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    private String custMobileNo;

    private String empMobileNo;

    private Integer pendingCans;

    private String routeCode;

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public Integer getPendingCans() {
        return pendingCans;
    }

    public void setPendingCans(Integer pendingCans) {
        this.pendingCans = pendingCans;
    }

    public String getEmpMobileNo() {
        return empMobileNo;
    }

    public void setEmpMobileNo(String empMobileNo) {
        this.empMobileNo = empMobileNo;
    }

    public String getCustMobileNo() {
        return custMobileNo;
    }

    public void setCustMobileNo(String custMobileNo) {
        this.custMobileNo = custMobileNo;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getRouteAddress() {
        return routeAddress;
    }

    public void setRouteAddress(String routeAddress) {
        this.routeAddress = routeAddress;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTrans_date() {
        return trans_date;
    }

    public void setTrans_date(Date trans_date) {
        this.trans_date = trans_date;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getReturnCanCount() {
        return returnCanCount;
    }

    public void setReturnCanCount(Integer returnCanCount) {
        this.returnCanCount = returnCanCount;
    }

    public Integer getDeliveredCanCount() {
        return deliveredCanCount;
    }

    public void setDeliveredCanCount(Integer deliveredCanCount) {
        this.deliveredCanCount = deliveredCanCount;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }



}