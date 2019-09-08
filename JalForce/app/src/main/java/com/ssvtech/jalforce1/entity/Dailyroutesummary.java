package com.ssvtech.jalforce1.entity;

import java.util.Date;
public class Dailyroutesummary {

    private Integer id;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String routename;

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    private String drivername;

    private Date recorddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public Integer getInitalpendingcans() {
        return initalpendingcans;
    }

    public void setInitalpendingcans(Integer initalpendingcans) {
        this.initalpendingcans = initalpendingcans;
    }

    public Integer getLoadcans() {
        return loadcans;
    }

    public void setLoadcans(Integer loadcans) {
        this.loadcans = loadcans;
    }

    public Integer getReturncans() {
        return returncans;
    }

    public void setReturncans(Integer returncans) {
        this.returncans = returncans;
    }

    public Integer getDeliveredcans() {
        return deliveredcans;
    }

    public void setDeliveredcans(Integer deliveredcans) {
        this.deliveredcans = deliveredcans;
    }

    public Integer getFinalpendingcans() {
        return finalpendingcans;
    }

    public void setFinalpendingcans(Integer finalpendingcans) {
        this.finalpendingcans = finalpendingcans;
    }

    public Integer getNoofcustomers() {
        return noofcustomers;
    }

    public void setNoofcustomers(Integer noofcustomers) {
        this.noofcustomers = noofcustomers;
    }

    private Integer initalpendingcans;

    private Integer loadcans;

    private Integer returncans;

    private Integer deliveredcans;

    private Integer finalpendingcans;

    private Integer noofcustomers;

    public String getDriveremail() {
        return driveremail;
    }

    public void setDriveremail(String driveremail) {
        this.driveremail = driveremail;
    }

    private String driveremail;

}


