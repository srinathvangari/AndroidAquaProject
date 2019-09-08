package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ssvtech.jalforce1.Utility.LogOutTimerUtil;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.CustomerBean;
import com.ssvtech.jalforce1.entity.Route;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import com.ssvtech.jalforce1.adapter.RouteListAdapter;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.ssvtech.jalforce1.adapter.CustomerListAdapter;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ssvtech.jalforce1.entity.CustomerBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

public class RouteSelectionActivity extends AppCompatActivity implements LogOutTimerUtil.LogOutListener{



    //LOGOUT DTART
    @Override
    protected void onStart() {
        super.onStart();
        LogOutTimerUtil.startLogoutTimer(this, this);
        System.out.println("OnStart () &&& Starting timer");
    }
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        LogOutTimerUtil.startLogoutTimer(this, this);
        System.out.println( "User interacting with screen");
    }
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println( "onResume()");
    }
    /**
     * Performing idle time logout
     */
    @Override
    public void doLogout() {

        System.out.println("LOGOUT HAS BEEN CALLED  in HIOME");
        session.logoutUser();
        // write your stuff here
    }
    //LOGOUR END


    private Button homeButton;
    private SessionManager session =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("INNININI RouteSelectionActivity ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_selection);


        session = new SessionManager(getApplicationContext());
        session.checkLogin();



        homeButton  = (Button) findViewById(R.id.button1);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });

        // Get the transferred data from source activity.

        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

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
                lv.setAdapter(new RouteListAdapter(getApplicationContext(), posts));

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        Route route = (Route) lv.getItemAtPosition(position);
                        Toast.makeText(RouteSelectionActivity.this, "Selected :" + " " + route.getRouteCode()
                                        +", "+ route.getRouteAddress(),
                                Toast.LENGTH_SHORT).show();
                        // String custname = customerBean.getFirstName()+" "+customerBean.getLastName();
                        //String custAddress = customerBean.getAddress();

                        Intent intent = getIntent();
                        String routeReport = (String)intent.getSerializableExtra("REPORTROUTE");
                        System.out.println("INNININI routeReport  ::  value " +routeReport);
                        if(routeReport!=null && routeReport.equals("routeReport")){
                            openRouteReportActivity(route);
                        }else if(routeReport!=null && routeReport.equals("dailydelivery")){


                            dailyDeliveryActivity(route);
                        }else {


                            routePlanning(route);
                        }
                    }
                });

                //END

            }



        @Override
        public void onFailure(Call<List<Route>> call, Throwable t) {
            // public void onFailure(Call<CustomerBean> call, Throwable t) {
            // textViewResult.setText(t.getMessage());
            System.out.println("t.getMessage(): " + t.getMessage());
        }


    });


    }


    public void routePlanning(Route route){
        Intent dailyRoutePlanAcitivityIntent = new Intent(this, DailyRoutePlan.class);
        dailyRoutePlanAcitivityIntent.putExtra("route", route);
        startActivity(dailyRoutePlanAcitivityIntent);
    }

    public void openHomeActivity(){
        Intent homeActivity = new Intent(this, HomeActivity.class);
        startActivity(homeActivity);
    }

    public void openRouteReportActivity(Route route){
        Intent routeReportActivity = new Intent(this, RouteReportActivity.class);
        routeReportActivity.putExtra("route", route);
        startActivity(routeReportActivity);
    }

    public void dailyDeliveryActivity(Route route){
        Intent dailyDeliveryActivity = new Intent(this, DailyDeliveryActivity.class);
        dailyDeliveryActivity.putExtra("route", route);
        startActivity(dailyDeliveryActivity);
    }
}
