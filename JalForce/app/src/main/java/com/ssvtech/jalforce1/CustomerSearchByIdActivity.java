package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ssvtech.jalforce1.Utility.LogOutTimerUtil;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.adapter.CustomerListAdapter;
import com.ssvtech.jalforce1.entity.CustomerBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomerSearchByIdActivity extends AppCompatActivity implements LogOutTimerUtil.LogOutListener{

    private Button homeButton;
    private SessionManager session =null;
    private Button searchButton;

    private TextView customerstringid;


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
        setContentView(R.layout.activity_customer_search_by_id);


        session = new SessionManager(getApplicationContext());
        session.checkLogin();



        homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });


        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 customerstringid = (TextView) findViewById(R.id.customerstringid);
                 System.out.println("customerstringid : "+customerstringid.getText().toString());

        //Calling Rest Webservice Start



        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

        Call<List<CustomerBean>> call = jsonPlaceHolderApi.customerById(customerstringid.getText().toString());
        call.enqueue(new Callback<List<CustomerBean>>() {
            @Override
            public void onResponse(Call<List<CustomerBean>> call, Response<List<CustomerBean>> response) {
                System.out.println("response.isSuccessful() : "+response.isSuccessful());
                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<CustomerBean> custList = response.body();

                for (CustomerBean customerBean : custList) {
                    openCanDevliery(customerBean);
                }
            }

            @Override
            public void onFailure(Call<List<CustomerBean>> call, Throwable t) {
                // public void onFailure(Call<CustomerBean> call, Throwable t) {
                // textViewResult.setText(t.getMessage());
                System.out.println("t.getMessage(): " + t.getMessage());
            }
        });

        //END of calling REST webservice
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
