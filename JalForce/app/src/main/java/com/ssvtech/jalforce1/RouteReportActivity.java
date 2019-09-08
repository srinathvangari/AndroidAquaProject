package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ssvtech.jalforce1.Utility.LogOutTimerUtil;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.Dailyroutesummary;
import com.ssvtech.jalforce1.entity.Route;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//public class RouteReportActivity extends MyBaseActivity {
public class RouteReportActivity extends AppCompatActivity implements LogOutTimerUtil.LogOutListener{


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
    private Button backToListButton;
    public final static String EXTRA_STRING ="REPORTROUTE";
    Retrofit retrofit = null;
    JsonPlcaeHolderApi jsonPlaceHolderApi = null;
    Date dateobj = new Date();
    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

    private TextView routeView;
    private TextView dateView;
    private TextView numberOfCustomers;
    private TextView pendingCans;
    private TextView loadCan;
    private TextView drivername;
    private TextView finalPendingCansonthisroute;
    private TextView filledCansReturn;
    private TextView totaldeliveredcans;
    private SessionManager session =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_report);


        session = new SessionManager(getApplicationContext());
        session.checkLogin();


        //Initialize Fields Start
        dateView = (TextView) findViewById(R.id.dateView);
        routeView = (TextView) findViewById(R.id.routeView);
        numberOfCustomers = (TextView) findViewById(R.id.numberOfCustomers);
        pendingCans = (TextView) findViewById(R.id.pendingCans);
        loadCan = (TextView) findViewById(R.id.loadCan);
        drivername = (TextView) findViewById(R.id.drivername);

        finalPendingCansonthisroute = (TextView) findViewById(R.id.finalPendingCansonthisroute);
        filledCansReturn = (TextView) findViewById(R.id.filledCansReturn);
        totaldeliveredcans = (TextView) findViewById(R.id.totaldeliveredcans);

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        Route route33 = (Route)intent.getSerializableExtra("route");
        System.out.println(" Route Code selected : "+route33.getRouteCode());


        retrofit = RetrofitFactory.getRetrofit();
        jsonPlaceHolderApi  = retrofit.create(JsonPlcaeHolderApi.class);



        Call<Dailyroutesummary> call = jsonPlaceHolderApi.getdailydeliverybydatebyroute(route33.getRouteCode(),df.format(dateobj) );

        call.enqueue(new Callback<Dailyroutesummary>() {
            @Override
            public void onResponse(Call<Dailyroutesummary> call, Response<Dailyroutesummary> response) {
                Dailyroutesummary dailyroutesummary = response.body();

                System.out.println("dailyroutesummary != NULL : "+(dailyroutesummary!=null));
                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());\
                    System.out.println("EZXCEPTION : dailyroutesummary NOT SUCCESFULLY  Date");
                    return;
                }else{
                    System.out.println("INSERTED SUCCESFULLY hhhh");

                    dateView.setText(dateView.getText().toString()+" : "+df.format(dateobj));
                    routeView.setText(routeView.getText().toString()+" : "+dailyroutesummary.getRoutename());
                    numberOfCustomers.setText(numberOfCustomers.getText().toString()+" : "+dailyroutesummary.getNoofcustomers());
                    pendingCans.setText(pendingCans.getText().toString()+" : "+dailyroutesummary.getInitalpendingcans());
                    loadCan.setText(loadCan.getText().toString()+" : "+dailyroutesummary.getLoadcans());
                    drivername.setText(drivername.getText().toString()+" : "+dailyroutesummary.getDriveremail());

                    finalPendingCansonthisroute.setText(finalPendingCansonthisroute.getText().toString()+" : "+dailyroutesummary.getFinalpendingcans());
                    filledCansReturn.setText(filledCansReturn.getText().toString()+" : "+(dailyroutesummary.getLoadcans()-dailyroutesummary.getDeliveredcans()));
                    totaldeliveredcans.setText(totaldeliveredcans.getText().toString()+" : "+dailyroutesummary.getDeliveredcans());
                }



            }

            @Override
            public void onFailure(Call<Dailyroutesummary> call, Throwable t) {
                System.out.println("t.getMessage(): " + t.getMessage());
            }
        });






        homeButton  = (Button) findViewById(R.id.btnHome);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });


        backToListButton  = (Button) findViewById(R.id.backToListButton);
        backToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBackToListActivity();
            }
        });

    }


    public void openHomeActivity(){
        Intent homeActivity = new Intent(this, HomeActivity.class);
        startActivity(homeActivity);
    }

    public void openBackToListActivity(){
        Intent homeActivity = new Intent(this, RouteSelectionActivity.class);
        homeActivity.putExtra(EXTRA_STRING, "routeReport");
        startActivity(homeActivity);
    }
}
