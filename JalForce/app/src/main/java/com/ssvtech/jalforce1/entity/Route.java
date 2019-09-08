package com.ssvtech.jalforce1.entity;

import java.util.Date;

public class Route implements java.io.Serializable{
    private Integer id;


    private Date starttime;

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    private Date plandate;

    public Date getPlandate() {
        return plandate;
    }

    public void setPlandate(Date plandate) {
        this.plandate = plandate;
    }


    private Date endtime;


    private String routeAddress;

    private String routeCode;

    public String getIsroutestarted() {
        return isroutestarted;
    }

    public void setIsroutestarted(String isroutestarted) {
        this.isroutestarted = isroutestarted;
    }

    private String isroutestarted;

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    private String updatedby;

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getRouteAddress() {
        return routeAddress;
    }

    public void setRouteAddress(String routeAddress) {
        this.routeAddress = routeAddress;
    }

}
