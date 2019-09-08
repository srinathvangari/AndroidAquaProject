package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ssvtech.jalforce1.Utility.LogOutTimerUtil;
import com.ssvtech.jalforce1.Utility.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TotalCansWithCustomersActivity extends AppCompatActivity implements LogOutTimerUtil.LogOutListener{


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


    private TextView tv1 = null;
    private Button homeButton;
    private SessionManager session =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_cans_with_customers);


        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        homeButton = (Button) findViewById(R.id.button1);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });


        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

        Call<Integer> call = jsonPlaceHolderApi.getTotalCansWithCustomers();

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                Integer posts = response.body();
                System.out.println("posts  CANS with Customers : " + posts.intValue());
                tv1 = (TextView) findViewById(R.id.text_view3);
                //set text style bold on current font
                tv1.setTypeface(tv1.getTypeface(), Typeface.BOLD);
                tv1.setText("Total CANS with Customers :  "+posts.intValue());

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
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
