package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ssvtech.jalforce1.Utility.LogOutTimerUtil;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.Route;
import com.ssvtech.jalforce1.entity.Dailyroutesummary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DailyRoutePlan extends AppCompatActivity implements LogOutTimerUtil.LogOutListener{


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

    private TextView routeView;
    private TextView dateView;
    private TextView numberOfCustomers;
    private TextView pendingCans;
    private Button dailyRoutePlanButton;
    private Button submitButton;
    private Button backToListButton;
    private Button homeButton;
  //  private EditText loadCan;
 //   private EditText drivername;
    Date dateobj = new Date();
    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    Dailyroutesummary dailyroutesummary =null;
    Retrofit retrofit = null;
    JsonPlcaeHolderApi jsonPlaceHolderApi = null;
    private SessionManager session =null;

    private String loadCans="0";
    private String driverName="";
    private Spinner dynamicSpinner;
    private Spinner dynamicDriverSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_route_plan);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        //Initialize Fields Start
        dateView = (TextView) findViewById(R.id.dateView);
        routeView = (TextView) findViewById(R.id.routeView);
        numberOfCustomers = (TextView) findViewById(R.id.numberOfCustomers);
        pendingCans = (TextView) findViewById(R.id.pendingCans);
      //  loadCan = (EditText) findViewById(R.id.loadCan);
      //  drivername = (EditText) findViewById(R.id.drivername);



        //Call webservice and fetch values : Start

         retrofit = RetrofitFactory.getRetrofit();
        jsonPlaceHolderApi  = retrofit.create(JsonPlcaeHolderApi.class);

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        Route route33 = (Route)intent.getSerializableExtra("route");
        System.out.println(" ROiute COde selected : "+route33.getRouteCode());

        Call<Dailyroutesummary> call = jsonPlaceHolderApi.getdailyroutesummarySingle(route33.getRouteCode());
       // Call<Dailyroutesummary> call = jsonPlaceHolderApi.getdailyroutesummarySingle("A1");

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
                    System.out.println("INSERTED SUCCESFULLY");
                    //openCanDevliery();

                    //Initialize Fields End

                    //Populate Fileds : Start
                    dateView.setText(""+df.format(dateobj));
                    routeView.setText(""+dailyroutesummary.getRoutename());
                    numberOfCustomers.setText(""+dailyroutesummary.getNoofcustomers());
                    pendingCans.setText(""+dailyroutesummary.getInitalpendingcans());
                   // loadCan.setText("60");
                    //drivername.setText("Chinna");
                    //Populate Fileds : End

                }



            }

            @Override
            public void onFailure(Call<Dailyroutesummary> call, Throwable t) {
                System.out.println("t.getMessage(): " + t.getMessage());
            }
        });


                //Call webservice and fetch values : End


        //Spinner start
        String[] items = new String[] { "10","20","30","40","50","55","60","65","70","75","80","85","90","95","100" };

         dynamicSpinner = (Spinner) findViewById(R.id.loadCan);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("Value selected : "+(String) parent.getItemAtPosition(position));
                Log.v("item", (String) parent.getItemAtPosition(position));
                loadCans = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });



        //Sprinner End


        //Spinner start
        String[] driveritems =
        new String[] { "chinna@gmail.com","sachin@gmail.com", "yogesh@gmail.com","rakesh@gmail.com"};

        dynamicDriverSpinner = (Spinner) findViewById(R.id.drivername);

        ArrayAdapter<String> adapterDriver = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, driveritems);

        dynamicDriverSpinner.setAdapter(adapterDriver);

        dynamicDriverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("Value selected : "+(String) parent.getItemAtPosition(position));
                Log.v("item", (String) parent.getItemAtPosition(position));
                driverName = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                driverName="chinna@chippa.com";
            }
        });



        //Sprinner End





        //Submiting the value to insert in insertDailyDailyroutesummary  : Start

        submitButton = (Button) findViewById(R.id.btnSend);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in dailyCanDeliveryButton");
    //{routename}/{recorddate}/{initalpendingcans}/{loadcans}/{returncans}/{deliveredcans}/{finalpendingcans}/{noofcustomers}/{drivername}/posts
                retrofit = RetrofitFactory.getRetrofit();
                jsonPlaceHolderApi  = retrofit.create(JsonPlcaeHolderApi.class);

               /* Dailyroutesummary dailyroutesummary2= new Dailyroutesummary();
                String numberOfCustomersString= numberOfCustomers.getText().toString();
                int numberOfCustomersInt=Integer.parseInt(numberOfCustomersString);

                dailyroutesummary.setDeliveredcans(deliveredcans);
                dailyroutesummary.setFinalpendingcans(finalpendingcans);
                dailyroutesummary.setInitalpendingcans(initalpendingcans);
                dailyroutesummary.setLoadcans(loadcans);
                dailyroutesummary.setReturncans(returncans);
                dailyroutesummary.setRoutename(routeView.getText().toString());
                dailyroutesummary.setNoofcustomers(numberOfCustomersString);
                dailyroutesummary.setDrivername(drivername); */


       /* Call<Dailyroutesummary> call2 = jsonPlaceHolderApi.insertDailyDailyroutesummary(dailyroutesummary2.getRoutename(), df.format(dateobj),dailyroutesummary2.getInitalpendingcans(),dailyroutesummary2.getLoadcans()
        ,dailyroutesummary2.getReturncans(), dailyroutesummary2.getDeliveredcans(), dailyroutesummary2.getFinalpendingcans(), dailyroutesummary2.getNoofcustomers(),dailyroutesummary2.getDrivername());
*/

             /*   private TextView routeView;
                private TextView dateView;
                private TextView numberOfCustomers;
                private TextView pendingCans;
                private Button dailyRoutePlanButton;
                private Button submitButton;
                private Button homeButton;
                private EditText loadCan;
                private EditText drivername;  */

               // loadCan = findViewById(R.id.loadCan);
               // drivername = findViewById(R.id.drivername);

                String numberOfCustomers1= numberOfCustomers.getText().toString();
                int numberOfCustomers2=Integer.parseInt(numberOfCustomers1);
                String loadCan1= loadCans;
                int loadCan2=Integer.parseInt(loadCan1);

                String pendingCans1= pendingCans.getText().toString();
                int pendingCans2=Integer.parseInt(pendingCans1);


                Call<Dailyroutesummary> call2 = jsonPlaceHolderApi.insertDailyDailyroutesummary(routeView.getText().toString(), df.format(dateobj),
                        pendingCans2,loadCan2
                        ,0, 0, 0,
                        numberOfCustomers2,driverName);


        call2.enqueue(new Callback<Dailyroutesummary>() {
            @Override
            public void onResponse(Call<Dailyroutesummary> call2, Response<Dailyroutesummary> response) {
                Dailyroutesummary dailyroutesummary2 = response.body();
                System.out.println("Dailyroutesummary != NULL : "+(dailyroutesummary2!=null));
                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());\
                    System.out.println("EZXCEPTION : Dailyroutesummary INSERTED NOT SUCCESFULLY  Date");
                    return;
                }else{
                    System.out.println("INSERTED SUCCESFULLY  Dailyroutesummary");
                    Toast.makeText(DailyRoutePlan.this, "Plan Updated Sucessfully", Toast.LENGTH_SHORT).show();
                    dynamicSpinner.setEnabled(false);
                    dynamicDriverSpinner.setEnabled(false);
                    submitButton.setEnabled(false);
                    }

            }

            @Override
            public void onFailure(Call<Dailyroutesummary> call, Throwable t) {
                System.out.println("t.getMessage(): " + t.getMessage());
            }
        });


    }
});

        //Submiting the value to insert in insertDailyDailyroutesummary  : End





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

    public void openCanDevliery(){
        Intent dailyDeliveryActivity = new Intent(this, DailyDeliveryActivity.class);
        startActivity(dailyDeliveryActivity);
    }

    public void openHomeActivity(){
        Intent homeActivity = new Intent(this, HomeActivity.class);
        startActivity(homeActivity);
    }

    public void openBackToListActivity(){
        Intent homeActivity = new Intent(this, RouteSelectionActivity.class);
        startActivity(homeActivity);
    }



}
