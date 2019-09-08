package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.ssvtech.jalforce1.Utility.LogOutTimerUtil;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.adapter.CustomerListAdapter;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ssvtech.jalforce1.entity.CustomerBean;
import com.ssvtech.jalforce1.entity.Route;

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
import java.util.Map;
import java.util.HashMap;


public class DailyDeliveryActivity extends AppCompatActivity implements LogOutTimerUtil.LogOutListener{

    private TextView textViewResult;
    private Button homeButton;
    private SessionManager session =null;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_delivery);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();


        // Get the transferred data from source activity.
        Intent intent = getIntent();
        Route route33 = (Route)intent.getSerializableExtra("route");
        System.out.println("  DailyDeliveryActivity Route Code selected : "+route33.getRouteCode());


      //  textViewResult = findViewById(R.id.textView3);
        //   textViewResult.setText(textValue);


        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

        Call<List<CustomerBean>> call = jsonPlaceHolderApi.customerListByRoute(route33.getRouteCode());
        call.enqueue(new Callback<List<CustomerBean>>() {
            @Override
            public void onResponse(Call<List<CustomerBean>> call, Response<List<CustomerBean>> response) {

                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<CustomerBean> posts = response.body();


       /* Call<CustomerBean> call = jsonPlaceHolderApi.getCustomer(3);
        call.enqueue(new Callback<CustomerBean>() {
            @Override
            public void onResponse(Call<CustomerBean> call, Response<CustomerBean> response) {

                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }
                CustomerBean posts1 = response.body();

                List<CustomerBean> posts = new ArrayList<CustomerBean>();
                posts.add(posts1);*/


                homeButton = (Button) findViewById(R.id.button1);
                homeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        openHomeActivity();
                    }
                });




                final ListView lv = (ListView) findViewById(R.id.user_list);
                lv.setAdapter(new CustomerListAdapter( getApplicationContext(), posts));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        CustomerBean customerBean = (CustomerBean) lv.getItemAtPosition(position);
                        Toast.makeText(DailyDeliveryActivity.this, "Selected :" + " " + customerBean.getFirstName()
                                        +", "+ customerBean.getLastName()+ " Route Code : "+customerBean.getRouteCode(),
                                Toast.LENGTH_SHORT).show();
                       // String custname = customerBean.getFirstName()+" "+customerBean.getLastName();
                        //String custAddress = customerBean.getAddress();

                        openCanDevliery(customerBean);
                    }
                });


            }

            @Override
            public void onFailure(Call<List<CustomerBean>> call, Throwable t) {
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

    public void openCanDevliery(CustomerBean customerBean){
        Intent canDevliveryIntent = new Intent(this, CanDeliveryActivity.class);
        canDevliveryIntent.putExtra("customerBean", customerBean);
        startActivity(canDevliveryIntent);
    }
}
