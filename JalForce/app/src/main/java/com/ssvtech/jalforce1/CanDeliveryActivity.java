package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ssvtech.jalforce1.Utility.LogOutTimerUtil;
import com.ssvtech.jalforce1.Utility.MessageUtility;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.CustomerBean;

import android.content.Intent;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.util.Log;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import com.ssvtech.jalforce1.entity.Route;

//public class CanDeliveryActivity extends AppCompatActivity {
public class CanDeliveryActivity extends AppCompatActivity implements LogOutTimerUtil.LogOutListener{



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


  //  public  static String CUST_ID ="CUST_ID";
   // public  static String EMP_ID ="EMP_ID";
  SessionManager session =null;

    public  static String CUST_ID ="CUST_ID";
    public  static String EMP_ID ="EMP_ID";

    public  static String CUST_FIRST_NAME ="CUST_ID";
    public  static String CUST_LAST_NAME ="EMP_ID";


    public  static String EMP_FIRST_NAME ="CUST_ID";
    public  static String EMP_LAST_NAME ="EMP_ID";


    public  static String CUST_MOBILE_NO ="CUST_ID";
    public  static String EMP_MOBILE_NO ="EMP_ID";


    public  static String CUST_ADDRESS ="CUST_ID";
    public  static String EMP_ADDRESS ="EMP_ID";


    public  static String RETURN_CAN ="CUST_ID";
    public  static String DELIVERED_CAN ="EMP_ID";

    private TextView routeTextViewResult;
    private TextView textViewResult;
    private TextView textViewResult1;
    private TextView dateViewResult3;
    private TextView pendingCansWithCustomer;
    private Button dailyCanDeliveryButton;
    private Button cancelButton;
    private Button homeButton;
   // private EditText canDailyReturn;
   // private EditText canDailyDeliver;
    CustomerBean customerBean = null;

    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    Date dateobj = new Date();
    String returnString="0";
    String deliverString ="0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_delivery);
        System.out.println("in CanDeliveryActivity ");



        session = new SessionManager(getApplicationContext());
        session.checkLogin();



        textViewResult = (TextView) findViewById(R.id.customerNameView);

        //Date Start

      //  DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        System.out.println("in CanDeliveryActivity  2222");
        dateViewResult3 = (TextView) findViewById(R.id.dateView);
        System.out.println("in CanDeliveryActivity  3333");
        dateViewResult3.setText(""+df.format(dateobj));
        System.out.println("in CanDeliveryActivity  4444");
        //Date End

        // Get the transferred data from source activity.
        Intent intent = getIntent();
         customerBean = (CustomerBean)intent.getSerializableExtra("customerBean");






        String[] items = new String[] { "0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("Value selected : "+(String) parent.getItemAtPosition(position));
                Log.v("item", (String) parent.getItemAtPosition(position));
                returnString = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        Spinner dynamicdeleiverCanSpinner = (Spinner) findViewById(R.id.deleiverCan);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicdeleiverCanSpinner.setAdapter(adapter);

        dynamicdeleiverCanSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("Value selected : "+(String) parent.getItemAtPosition(position));
                Log.v("item", (String) parent.getItemAtPosition(position));
                deliverString = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


         //Temp start
       // customerBean =  new CustomerBean();
       // customerBean.setFirstName("ANIL");
       // customerBean.setLastName("CHIPPA");
       // customerBean.setAddress("Ashok CHock");
        //customerBean.setPendingCans(5);

          //Temp END

        System.out.println("in CanDeliveryActivity :FIrstNamw 1 "+customerBean.getFirstName()+ " , LastName : "+customerBean.getLastName());

        System.out.println("in CanDeliveryActivity :getCustomerstringid  "+customerBean.getCustomerstringid());

        routeTextViewResult = findViewById(R.id.routeView);
        routeTextViewResult.setText(customerBean.getRouteCode());

        textViewResult.setText(""+customerBean.getFirstName() + " "+customerBean.getLastName());

        textViewResult1 = (TextView) findViewById(R.id.customerAddressView2);
        textViewResult1.setText(""+customerBean.getAddress());

        pendingCansWithCustomer = (TextView) findViewById(R.id.pendingCans);
        pendingCansWithCustomer.setText(""+customerBean.getPendingCans());

        cancelButton = (Button) findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Route route = new Route();
               // route.setRouteCode(customerBean.getRouteCode());
                customersearchbyidActivity();
            }
        });

        homeButton  = (Button) findViewById(R.id.btnHome);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });

        dailyCanDeliveryButton = (Button) findViewById(R.id.btnSend);
        dailyCanDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in dailyCanDeliveryButton  :  ROUTE CODE : "+customerBean.getRouteCode());

               // canDailyReturn = findViewById(R.id.returnCan);
                //canDailyDeliver = findViewById(R.id.deleiverCan);

                //String returnString= canDailyReturn.getText().toString();
                int returnInt=Integer.parseInt(returnString);
               // String deliverString= canDailyDeliver.getText().toString();
                int deliverInt=Integer.parseInt(deliverString);

                System.out.println(" (customerBean.getPendingCans()-returnInt) : "+(customerBean.getPendingCans()-returnInt));

                //Validation for Return Start
                if((customerBean.getPendingCans()-returnInt) <0) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                            CanDeliveryActivity.this);
                    alertDialog.setCancelable(false);
                    alertDialog.setTitle("Alert");


                    alertDialog
                            .setMessage("" + MessageUtility.DAILY_DELIVERY_RETURN_CANS_EXCEDDING_PENDING_CANS);

                    alertDialog.setNegativeButton("OK",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {



                                    dialog.cancel();
                                }
                            });

                    alertDialog.show();
                }else {


                    //Validation for Return ENd

                    //  System.out.println("Can Return Daily: " + canDailyReturn.getText() + " : Can Deliver Daily : " + canDailyDeliver.getText()+ " Pending Cans :"+pendingCansWithCustomer.getText());
                    Retrofit retrofit = RetrofitFactory.getRetrofit();
                    JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);
                    System.out.println("JJJJJJJJJJJ");
                    Call<CustomerBean> call = jsonPlaceHolderApi.insertDD(returnInt, deliverInt,
                            customerBean.getId(), 2, customerBean.getFirstName(), customerBean.getLastName(),
                            "Chinna", "Chippa", customerBean.getRouteCode(), customerBean.getAddress(),
                            customerBean.getMobileNo(), "99993333", customerBean.getPendingCans(), df.format(dateobj),customerBean.getCustomerstringid());
                    call.enqueue(new Callback<CustomerBean>() {
                        @Override
                        public void onResponse(Call<CustomerBean> call, Response<CustomerBean> response) {
                            CustomerBean customerBean = response.body();
                            System.out.println("CustomerBean != NULL : " + (customerBean != null));
                            if (!response.isSuccessful()) {
                                //textViewResult.setText("Code: " + response.code());\
                                System.out.println("EZXCEPTION : INSERTED NOT SUCCESFULLY  Date");
                                return;
                            } else {

                                System.out.println("SUCESFULL SCUESFULL INSERTED");
//Alert Start
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                        CanDeliveryActivity.this);
                                alertDialog.setCancelable(false);
                                alertDialog.setTitle("Alert");

                                if (customerBean.getMessage().equals(MessageUtility.DAILY_ROUTE_SUMMARY_RECORD_NOT_THERE)) {
                                    alertDialog
                                            .setMessage("" + MessageUtility.DAILY_ROUTE_SUMMARY_RECORD_NOT_THERE);
                                } else if (customerBean.getMessage().equals(MessageUtility.DAILY_DELIVERY_EXCEDDING_LOADED_CANS)) {
                                    alertDialog
                                            .setMessage("" + MessageUtility.DAILY_DELIVERY_EXCEDDING_LOADED_CANS);
                                } else if (customerBean.getMessage().equals(MessageUtility.DAILY_DELIVERY_SUCCESSFUL_TO_CUSTOMER)) {
                                    alertDialog
                                            .setMessage("" + MessageUtility.DAILY_DELIVERY_SUCCESSFUL_TO_CUSTOMER);
                                }
                                alertDialog.setNegativeButton("OK",
                                        new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(CanDeliveryActivity.this,
                                                        CustomerSearchByIdActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                        | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);

                                                dialog.cancel();
                                            }
                                        });

                                alertDialog.show();

//Alert End


                                //   System.out.println("INSERTED SUCCESFULLY");
                                //Toast.makeText(CanDeliveryActivity.this, "Inserted Sucessfully", Toast.LENGTH_SHORT).show();
                                //openCanDevliery();

                                pendingCansWithCustomer.setText("" + customerBean.getPendingCans());
                                //  canDailyReturn.setEnabled(false);
                                //  canDailyDeliver.setEnabled(false);
                                dailyCanDeliveryButton.setEnabled(false);

                            }
                        }


                        @Override
                        public void onFailure(Call<CustomerBean> call, Throwable t) {
                            System.out.println("t.getMessage(): " + t.getMessage());
                        }
                    });
                }
            }
        });

    }

    public void refresh(){
        Intent refresh = new Intent(this, CanDeliveryActivity.class);
        startActivity(refresh);
    }

    public void openCanDevliery(Route route){
        Intent dailyDeliveryActivity = new Intent(this, DailyDeliveryActivity.class);
        dailyDeliveryActivity.putExtra("route", route);
         startActivity(dailyDeliveryActivity);
    }

    public void openHomeActivity(){
        Intent homeActivity = new Intent(this, HomeActivity.class);
        startActivity(homeActivity);
    }

    public void customersearchbyidActivity(){
        Intent customerSearchByIdActivity = new Intent(this, CustomerSearchByIdActivity.class);
        startActivity(customerSearchByIdActivity);
    }
}
