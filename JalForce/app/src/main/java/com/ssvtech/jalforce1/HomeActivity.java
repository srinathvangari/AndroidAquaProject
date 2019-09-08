package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.ssvtech.jalforce1.Utility.SessionManager;
import android.widget.Toast;
import com.ssvtech.jalforce1.Utility.LogOutTimerUtil;
import com.ssvtech.jalforce1.entity.EmployeeBean;
import com.ssvtech.jalforce1.entity.Route;

public class HomeActivity extends AppCompatActivity implements LogOutTimerUtil.LogOutListener
{



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






    private Button dailyDeliveryButton;
    private Button  routePlanningButton;
    private Button totalCansInPlantButton;
    private Button totalCansWithCustomersButton;
    private Button paymentButton;
    private Button reportButton;
    private Button logoutButton;
    private Button tripAction;
    private Button customersearchbyid;
    private Button  customerledgersearchid;

    private Button   liveTracking;

    public final static String EXTRA_STRING ="REPORTROUTE";

    SessionManager session =null;



    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        // Session class instance
        session = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String role = user.get(SessionManager.KEY_ROLE);



        tripAction  = (Button) findViewById(R.id.tripAction);
        tripAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in tripAction");
                openTripAction();
            }
        });


        dailyDeliveryButton = (Button) findViewById(R.id.button1);
        dailyDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in dailyDeliveryButton");
                openDailiDevliery();
            }
        });

        // dailyDeliveryButton.setVisibility(View.GONE);


        routePlanningButton = (Button) findViewById(R.id.routePlanningButton);
        routePlanningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in routePlanningButton");
                routePlanning();
            }
        });

        customerledgersearchid  = (Button) findViewById(R.id.customerledgersearchid);
        customerledgersearchid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in customerledgersearchid");
                customerledgersearchid();
            }
        });


        customersearchbyid = (Button) findViewById(R.id.customersearchbyid);
        customersearchbyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in customersearchbyid");
                customersearchbyidActivity();
            }
        });


        totalCansInPlantButton = (Button) findViewById(R.id.button2);
        totalCansInPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in totalCansInPlantButton");
                openTotalCansInPlant();
            }
        });


        paymentButton  = (Button) findViewById(R.id.button4);
        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in paymentButton");
            alertComingSoon();

            }
        });


        totalCansWithCustomersButton = (Button) findViewById(R.id.button3);
        totalCansWithCustomersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in totalCansWithCustomersButton");
                openTotalCansWithCustomers();

            }
        });

        liveTracking = (Button) findViewById(R.id.liveTracking);
        liveTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in liveTracking");
                openMaps();

            }
        });




        reportButton = (Button) findViewById(R.id.reportButton);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routeReport();

            }
        });

        // Get the transferred data from source activity.
     /*   Intent intent = getIntent();
        EmployeeBean employeeBean = (EmployeeBean)intent.getSerializableExtra("employeeBean");
        System.out.println(" ROLE selected : "+employeeBean.getRole()); */

        System.out.println(" ROLE selected : "+role);


        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
                logout();

            }
        });

        //   if(!(MainActivity.OWNER.equals(email) || MainActivity.OWNER1.equals(email) || MainActivity.OWNER2.equals(email))){
        // if(!(employeeBean.getRole().equals("owner"))){
        if(!(role.equals("owner"))){
            // dailyDeliveryButton.setVisibility(View.GONE);
            routePlanningButton.setVisibility(View.GONE);
            totalCansInPlantButton.setVisibility(View.GONE);
            totalCansWithCustomersButton.setVisibility(View.GONE);
            paymentButton.setVisibility(View.GONE);
            tripAction.setVisibility(View.GONE);
        }






    }

    public void logout(){
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    public void routePlanning(){
        Intent dailyRoutePlanAcitivityIntent = new Intent(this, RouteSelectionActivity.class);
        dailyRoutePlanAcitivityIntent.putExtra(EXTRA_STRING, "In D aily Route Plan Acitivity");
        startActivity(dailyRoutePlanAcitivityIntent);
    }

    public void openDailiDevliery(){
        Intent dailyDevliveryIntent = new Intent(this, RouteSelectionActivity.class);
        dailyDevliveryIntent.putExtra(EXTRA_STRING, "dailydelivery");
        startActivity(dailyDevliveryIntent);
    }

    public void openTotalCansInPlant(){
        Intent totalCansInPlantIntent = new Intent(this, TotalCansInPlantActivity.class);
        totalCansInPlantIntent.putExtra(EXTRA_STRING, "TotalCansInPlantIntent Page");
        startActivity(totalCansInPlantIntent);
    }

    public void openTotalCansWithCustomers(){
        Intent intent = new Intent(this, TotalCansWithCustomersActivity.class);
        intent.putExtra(EXTRA_STRING, "TotalCansWithCustomersActivity Page ");
        startActivity(intent);
    }

    public void routeReport(){
        Intent intent = new Intent(this, RouteSelectionActivity.class);
        intent.putExtra(EXTRA_STRING, "routeReport");
        startActivity(intent);
    }


    public void openTripAction(){
        Intent tripActionIntent = new Intent(this, TripSelectionActivity.class);
        tripActionIntent.putExtra(EXTRA_STRING, "dailydelivery");
        startActivity(tripActionIntent);
    }


    public void customersearchbyidActivity(){
        alertComingSoon();
    }

    public void customerledgersearchid(){
        Intent customerLedgerSearchByIdActivity = new Intent(this, CustomerLedgerSearchByIdActivity.class);
        customerLedgerSearchByIdActivity.putExtra(EXTRA_STRING, "dailydelivery");
        startActivity(customerLedgerSearchByIdActivity);
    }

    public void openMaps(){
        Intent mapActivity = new Intent(this, MapActivity.class);
        startActivity(mapActivity);
    }


    private void alertComingSoon(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                this);
        alertDialog.setCancelable(false);
        alertDialog.setTitle(R.string.Alert);


        alertDialog
                .setMessage(R.string.COMING_SOON);

        alertDialog.setNegativeButton(R.string.ok,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }


}


