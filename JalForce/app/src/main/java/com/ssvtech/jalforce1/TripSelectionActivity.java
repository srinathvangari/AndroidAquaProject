package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ssvtech.jalforce1.Utility.MessageUtility;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.adapter.TripActionAdapter;
import com.ssvtech.jalforce1.entity.Dailyroutesummary;
import com.ssvtech.jalforce1.entity.EmployeeBean;
import com.ssvtech.jalforce1.entity.Route;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TripSelectionActivity extends AppCompatActivity {

    private SessionManager session =null;
    private Button homeButton;

    JsonPlcaeHolderApi jsonPlaceHolderApi =null;
    Retrofit retrofit = null;
    Route route =null;

    String email =null;

    Date dateobj = new Date();
    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_selection);



        session = new SessionManager(getApplicationContext());
        session.checkLogin();


        HashMap<String, String> user = session.getUserDetails();

        // name
        email  = user.get(SessionManager.KEY_NAME);



        homeButton  = (Button) findViewById(R.id.button1);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });


        // Get the transferred data from source activity.

         retrofit = RetrofitFactory.getRetrofit();
         jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

        Call<List<Route>> call = jsonPlaceHolderApi.getRouteList();

        System.out.println("INNININI  Call<List<Route>> call ");

        call.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {

                System.out.println("INNININI  Call<List<Route>> onResponse ");

                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    System.out.println("INNININI  NOT isSuccessful");
                    return;
                }
                List<Route> posts = response.body();


                //Start


                final ListView lv = (ListView) findViewById(R.id.route_list);
                lv.setAdapter(new TripActionAdapter(getApplicationContext(), posts));
                System.out.println("CLICKED TRIP ACTION  route  code : ");

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {



                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        Route route = (Route) lv.getItemAtPosition(position);


                        System.out.println("CLICKED TRIP ACTION  route  code : "+route.getRouteCode()+" Selected :" + route.getRouteCode()
                                        +", "+ route.getRouteAddress()+"   ,  isLOCKED :  "+route.getIsroutestarted());



                        if(route!=null && route.getIsroutestarted().equals("no")){
                            //start
                            System.out.println("CLYEYEYEYEYES YES yES :");

                            // email

                        /*    Toast.makeText(TripSelectionActivity.this, "SEELECT STARTARSRSTSRTS :" + " " + route.getRouteCode()
                                            +" has been started",
                                    Toast.LENGTH_SHORT).show(); */

                            //WEB CALL STARTED for START TRIP


                            Call<Dailyroutesummary> call = jsonPlaceHolderApi.dailyroutesummaryStartTripTrigger(route.getRouteCode(), ""+df.format(dateobj),""+df.format(dateobj), email);

                            System.out.println("INNININI  Call<List<Route>> call 22");

                            call.enqueue(new Callback<Dailyroutesummary>() {
                                @Override
                                public void onResponse(Call<Dailyroutesummary> call, Response<Dailyroutesummary> response) {

                                    System.out.println("INNININI  Call<List<Route>> onResponse ");

                                    if (!response.isSuccessful()) {
                                        //textViewResult.setText("Code: " + response.code());
                                        System.out.println("INNININI  NOT isSuccessful");
                                        return;
                                    }else{
                                        System.out.println(" isSuccessful isSuccessful  isSuccessful");
                                        Dailyroutesummary dailyRoute = response.body();
                                        System.out.println(" isSuccessful isSuccessful  isSuccessful "+(dailyRoute!=null));
                                        if(dailyRoute!=null){
                                            System.out.println(" dailyRoute.getMessage() :  "+dailyRoute.getMessage());
                                            if(dailyRoute.getMessage()!=null && dailyRoute.getMessage().equals(MessageUtility.DAILY_ROUTE_SUMMARY_RECORD_NOT_THERE)){

                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                                        TripSelectionActivity.this);
                                                alertDialog.setCancelable(false);
                                                alertDialog.setTitle("Alert");


                                                alertDialog
                                                        .setMessage("" + MessageUtility.DAILY_ROUTE_SUMMARY_RECORD_NOT_THERE);

                                                alertDialog.setNegativeButton("OK",
                                                        new DialogInterface.OnClickListener() {

                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });

                                                alertDialog.show();

                                            } else if(dailyRoute.getMessage()!=null &&  dailyRoute.getMessage().equals(MessageUtility.DAILY_ROUTE_TRIP_HAS_ALREADY_ENDED)){

                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                                        TripSelectionActivity.this);
                                                alertDialog.setCancelable(false);
                                                alertDialog.setTitle("Alert");


                                                alertDialog
                                                        .setMessage("" + MessageUtility.DAILY_ROUTE_TRIP_HAS_ALREADY_ENDED);

                                                alertDialog.setNegativeButton("OK",
                                                        new DialogInterface.OnClickListener() {

                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });

                                                alertDialog.show();

                                            }
                                            else if(dailyRoute.getMessage()!=null && dailyRoute.getMessage().equals(MessageUtility.DAILY_ROUTE_SUMMARY_MULTIPLE_RECORDS_FOUNDED)){
                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                                        TripSelectionActivity.this);
                                                alertDialog.setCancelable(false);
                                                alertDialog.setTitle("Alert");


                                                alertDialog
                                                        .setMessage("" + MessageUtility.DAILY_ROUTE_SUMMARY_MULTIPLE_RECORDS_FOUNDED);

                                                alertDialog.setNegativeButton("OK",
                                                        new DialogInterface.OnClickListener() {

                                                            public void onClick(DialogInterface dialog, int which) {
                                                                dialog.cancel();
                                                            }
                                                        });

                                                alertDialog.show();

                                            }else if(dailyRoute.getMessage()!=null && dailyRoute.getMessage().equals(MessageUtility.DAILY_ROUTE_TRIP_STARTED_SUCESSFULLY)){

                                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                                        TripSelectionActivity.this);
                                                alertDialog.setCancelable(false);
                                                alertDialog.setTitle("Alert");


                                                alertDialog
                                                        .setMessage("" + MessageUtility.DAILY_ROUTE_TRIP_STARTED_SUCESSFULLY);

                                                alertDialog.setNegativeButton("OK",
                                                        new DialogInterface.OnClickListener() {

                                                            public void onClick(DialogInterface dialog, int which) {
                                                                finish();
                                                                startActivity(getIntent());
                                                                dialog.cancel();
                                                            }
                                                        });

                                                alertDialog.show();


                                            } else {


                                                System.out.println(" isSuccessful isSuccessful  isSuccessful " + dailyRoute.getRoutename());
                                                Toast.makeText(TripSelectionActivity.this, "Trip for :" + " " + dailyRoute.getRoutename()
                                                                + " has been Started",
                                                        Toast.LENGTH_SHORT).show();

                                                finish();
                                                startActivity(getIntent());
                                            }
                                        }

                                    }





                                }

                                @Override
                                public void onFailure(Call<Dailyroutesummary> call, Throwable t) {
                                    // public void onFailure(Call<CustomerBean> call, Throwable t) {
                                    // textViewResult.setText(t.getMessage());
                                    System.out.println("t.getMessage(): " + t.getMessage());
                                }

                            });




                            //StartTIP END  END


                                 }else if(route!=null && route.getIsroutestarted().equals("yes")){

                            Call<Dailyroutesummary> call = jsonPlaceHolderApi.dailyroutesummaryEndTripTrigger(route.getRouteCode(), ""+df.format(dateobj),""+df.format(dateobj), email);

                            System.out.println("INNININI  Call<List<Route>> call 22");

                            call.enqueue(new Callback<Dailyroutesummary>() {
                                @Override
                                public void onResponse(Call<Dailyroutesummary> call, Response<Dailyroutesummary> response) {

                                    System.out.println("INNININI  Call<List<Route>> onResponse ");

                                    if (!response.isSuccessful()) {
                                        //textViewResult.setText("Code: " + response.code());
                                        System.out.println("INNININI  NOT isSuccessful");
                                        return;
                                    }else {
                                        System.out.println(" isSuccessful isSuccessful  isSuccessful");
                                        Dailyroutesummary dailyRoute = response.body();
                                        System.out.println(" isSuccessful isSuccessful  isSuccessful " + (dailyRoute != null));
                                        if(dailyRoute != null){
                                        System.out.println(" dailyRoute.getMessage() :  " + dailyRoute.getMessage());
                                        if ( dailyRoute.getMessage()!=null && dailyRoute.getMessage().equals(MessageUtility.DAILY_ROUTE_SUMMARY_RECORD_NOT_THERE)) {

                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                                    TripSelectionActivity.this);
                                            alertDialog.setCancelable(false);
                                            alertDialog.setTitle("Alert");


                                            alertDialog
                                                    .setMessage("" + MessageUtility.DAILY_ROUTE_SUMMARY_RECORD_NOT_THERE);

                                            alertDialog.setNegativeButton("OK",
                                                    new DialogInterface.OnClickListener() {

                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.cancel();
                                                        }
                                                    });

                                            alertDialog.show();

                                        }else if(dailyRoute.getMessage()!=null && dailyRoute.getMessage().equals(MessageUtility.DAILY_ROUTE_TRIP_ENDED_SUCESSFULLY)){

                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                                    TripSelectionActivity.this);
                                            alertDialog.setCancelable(false);
                                            alertDialog.setTitle("Alert");


                                            alertDialog
                                                    .setMessage("" + MessageUtility.DAILY_ROUTE_TRIP_ENDED_SUCESSFULLY);

                                            alertDialog.setNegativeButton("OK",
                                                    new DialogInterface.OnClickListener() {

                                                        public void onClick(DialogInterface dialog, int which) {
                                                            finish();
                                                            startActivity(getIntent());
                                                            dialog.cancel();
                                                        }
                                                    });

                                            alertDialog.show();


                                        } else{
                                            System.out.println(" isSuccessful isSuccessful  isSuccessful " + dailyRoute.getRoutename());
                                            Toast.makeText(TripSelectionActivity.this, "Trip for :" + " " + dailyRoute.getRoutename()
                                                            + " has been Ended",
                                                    Toast.LENGTH_SHORT).show();

                                            finish();
                                            startActivity(getIntent());
                                        }
                                    }
                                    }




                                }

                                @Override
                                public void onFailure(Call<Dailyroutesummary> call, Throwable t) {
                                    // public void onFailure(Call<CustomerBean> call, Throwable t) {
                                    // textViewResult.setText(t.getMessage());
                                    System.out.println("t.getMessage(): " + t.getMessage());
                                }

                            });


                            ////END TRIP START


                                            }
                                        }
                                    });











                          //  openRouteReportActivity(route);
                        }//else if(route!=null && route.getIsroutestarted().equals("yes")){


                           // dailyDeliveryActivity(route);





            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {
                // public void onFailure(Call<CustomerBean> call, Throwable t) {
                // textViewResult.setText(t.getMessage());
                System.out.println("t.getMessage(): " + t.getMessage());
            }


        });
    }








    public void openHomeActivity(){
        Intent homeActivity = new Intent(this, HomeActivity.class);
        startActivity(homeActivity);
    }


}
