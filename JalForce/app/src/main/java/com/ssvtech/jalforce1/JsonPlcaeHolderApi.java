package com.ssvtech.jalforce1;


import com.ssvtech.jalforce1.entity.Cansummary;
import com.ssvtech.jalforce1.entity.CustomerBean;
import com.ssvtech.jalforce1.entity.EmployeeBean;
import com.ssvtech.jalforce1.entity.Dailyroutesummary;
import com.ssvtech.jalforce1.entity.DailyDeliveryBean;

import com.ssvtech.jalforce1.entity.Route;
import java.util.List;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.Map;


public interface JsonPlcaeHolderApi {

    @GET("jpa/dailydelivery")
    Call<List<Dailydelivery>> getDailydelivery();

    @GET("/jpa/caninplant")
    Call<Cansummary> getTotalCansInPlant();

    @GET("/jpa/totalpendingcanatcustomers")
    Call<Integer> getTotalCansWithCustomers();


    @GET("/jpa/allcustomers")
    Call<List<CustomerBean>> getAllCustomerList();

    //http://localhost:8080/jpa/customerById/A121/posts
    @GET("/jpa/customerById/{customerstringid}/posts")
    Call<List<CustomerBean>> customerById(
            @Path("customerstringid") String customerstringid
    );

    @GET("/jpa/customer/{id}/posts")
    Call<CustomerBean> getCustomer(
            @Path("id") int custId
    );

    @GET("/jpa/dailyroutesummarySingle/{routename}/posts")
    Call<Dailyroutesummary> getdailyroutesummarySingle(
            @Path("routename") String routename
    );

   // http://192.168.1.6:8080/jpa/routes
    @GET("/jpa/routes")
    Call<List<Route>> getRouteList();

    //http://192.168.1.6:8080/jpa/insertDailyDailyroutesummary/A/03-AUG-2019/10/70/20/30/10/44/Chinna/posts
    @GET("/jpa/insertDailyDailyroutesummary/{routename}/{recorddate}/{initalpendingcans}/{loadcans}/{returncans}/{deliveredcans}/{finalpendingcans}/{noofcustomers}/{drivername}/posts")
    Call<Dailyroutesummary> insertDailyDailyroutesummary(
            @Path("routename") String routename,
            @Path("recorddate") String recorddate,
            @Path("initalpendingcans") int initalpendingcans,
            @Path("loadcans") int loadcans,
            @Path("returncans") int returncans,
            @Path("deliveredcans") int deliveredcans,
            @Path("finalpendingcans") int finalpendingcans,
            @Path("noofcustomers") int noofcustomers,
            @Path("drivername") String drivername
    );


    //  http://192.168.1.6:8080/jpa/insertDD/1/3/1/2/Srinath/Vangari/Chinna/Chippa/Ashok Chock/MIDC/99292992/88283883/posts

        @GET("/jpa/insertDD/{returnCanCount}/{deliveredCanCount}/{customerId}/{employeeId}/{custFirstName}/{custLastName}/{empFirstName}/{empLastName}/{routeAddress}/{custAddress}/{custMobileNo}/{empMobileNo}/{pendingCanCount}/{dateOfDelivery}/{customerstringid}/posts")
        Call<CustomerBean> insertDD(
        @Path("returnCanCount") int returnCanCount,
        @Path("deliveredCanCount") int deliveredCanCount,
        @Path("customerId") int customerId,
        @Path("employeeId") int employeeId,
        @Path("custFirstName") String custFirstName,
        @Path("custLastName") String custLastName,
        @Path("empFirstName") String empFirstName,
        @Path("empLastName") String empLastName,
        @Path("routeAddress") String routeAddress,
        @Path("custAddress") String custAddress,
        @Path("custMobileNo") String custMobileNo,
        @Path("empMobileNo") String empMobileNo,
        @Path("pendingCanCount") int pendingCanCount,
         @Path("dateOfDelivery") String dateOfDelivery,
        @Path("customerstringid") String customerstringid
        );

  //  http://192.168.1.6:8080/jpa/getdailydeliverybydatebyroute/A3/04-AUG-2019/posts
    @GET("/jpa/getdailydeliverybydatebyroute/{routename}/{recorddate}/posts")
    Call<Dailyroutesummary> getdailydeliverybydatebyroute(
            @Path("routename") String routename,
            @Path("recorddate") String recorddate
    );


    //  http://192.168.1.6:8080/jpa/getdailydeliverydetailsbyrouteforlastsevendays/A3/04-AUG-2019/posts
    @GET("/jpa/getdailydeliverydetailsbyrouteforlastsevendays/{routename}/{recorddate}/posts")
    Call<List<Dailyroutesummary>> getdailydeliverydetailsbyrouteforlastsevendays(
            @Path("routename") String routename,
            @Path("recorddate") String recorddate
    );

    //  http://192.168.1.6:8080/jpa/getDailyDeliveryReportDateandRoutewise/A3/11-AUG-2019/posts
    @GET("/jpa/getDailyDeliveryReportDateandRoutewise/{routename}/{recorddate}/posts")
    Call<List<DailyDeliveryBean>> getDailyDeliveryReportDateandRoutewise(
            @Path("routename") String routename,
            @Path("recorddate") String recorddate
    );


    //http://localhost:8080//jpa/customerListByRoute/A2/posts
    @GET("/jpa/customerListByRoute/{routename}/posts")
    Call<List<CustomerBean>> customerListByRoute(
            @Path("routename") String routename
            );


    //http://localhost:8080//jpa/customerListByRoute/A2/posts
    @GET("/jpa/employeeByEmail/{email}/posts")
    Call<List<EmployeeBean>> employeeByEmail(
            @Path("email") String email
    );


    //http://localhost:8080/jpa/dailyroutesummaryStartTripTrigger/A1/09-AUG-2019/06-AUG-2019/ee@ee.com/posts
    @GET("/jpa/dailyroutesummaryStartTripTrigger/{routename}/{recorddate}/{starttime}/{email}/posts")
    Call<Dailyroutesummary> dailyroutesummaryStartTripTrigger(
            @Path("routename") String routename,
            @Path("recorddate") String recorddate,
            @Path("starttime") String starttime,
             @Path("email") String email
    );

    //http://localhost:8080/jpa/dailyroutesummaryStartTripTrigger/A1/09-AUG-2019/06-AUG-2019/ee@ee.com/posts
    @GET("/jpa/dailyroutesummaryEndTripTrigger/{routename}/{recorddate}/{endtime}/{email}/posts")
    Call<Dailyroutesummary> dailyroutesummaryEndTripTrigger(
            @Path("routename") String routename,
            @Path("recorddate") String recorddate,
            @Path("endtime") String endtime,
            @Path("email") String email
    );


    //http://192.168.1.6:8080/jpa/customerLedgerByID/A1/11-AUG-2019/posts
    @GET("/jpa/customerLedgerByID/{customerStringid}/{recorddate}/posts")
    Call<List<DailyDeliveryBean>> customerLedgerByID(
            @Path("customerStringid") String customerStringid,
            @Path("recorddate") String recorddate
    );


    //http://192.168.1.6:8080/jpa/correctDailyDeliveryByCustomerIdandDate/A301/8-SEP-2019/posts
    @GET("/jpa/correctDailyDeliveryByCustomerIdandDate/{customerStringid}/{recorddate}/posts")
    Call<DailyDeliveryBean>  correctDailyDeliveryByCustomerIdandDate(
            @Path("customerStringid") String customerStringid,
            @Path("recorddate") String recorddate
    );

    //http://192.168.1.6:8080/jpa/correctDailyDeliveryById/98/posts
    @GET("/jpa/correctDailyDeliveryById/{transid}/posts")
    Call<DailyDeliveryBean>  correctDailyDeliveryById(
            @Path("transid") String transid

    );


    //  http://192.168.1.6:8080/jpa/insertDD/1/3/1/2/Srinath/Vangari/Chinna/Chippa/Ashok Chock/MIDC/99292992/88283883/posts

    @GET("/jpa/correctDD/{transId}/{returnCanCount}/{deliveredCanCount}/{customerId}/{employeeId}/{custFirstName}/{custLastName}/{empFirstName}/{empLastName}/{routeAddress}/{custAddress}/{custMobileNo}/{empMobileNo}/{pendingCanCount}/{dateOfDelivery}/{customerstringid}/posts")
    Call<CustomerBean> correctDD(
            @Path("transId") String transId,
            @Path("returnCanCount") int returnCanCount,
            @Path("deliveredCanCount") int deliveredCanCount,
            @Path("customerId") int customerId,
            @Path("employeeId") int employeeId,
            @Path("custFirstName") String custFirstName,
            @Path("custLastName") String custLastName,
            @Path("empFirstName") String empFirstName,
            @Path("empLastName") String empLastName,
            @Path("routeAddress") String routeAddress,
            @Path("custAddress") String custAddress,
            @Path("custMobileNo") String custMobileNo,
            @Path("empMobileNo") String empMobileNo,
            @Path("pendingCanCount") int pendingCanCount,
            @Path("dateOfDelivery") String dateOfDelivery,
            @Path("customerstringid") String customerstringid
    );

}





