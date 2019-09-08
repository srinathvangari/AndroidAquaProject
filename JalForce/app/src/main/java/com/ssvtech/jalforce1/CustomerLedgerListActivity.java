package com.ssvtech.jalforce1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.graphics.Color;

import com.ssvtech.jalforce1.Utility.LogOutTimerUtil;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.CustomerBean;
import com.ssvtech.jalforce1.entity.DailyDeliveryBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.Calendar;
import java.time.Month;

public class CustomerLedgerListActivity extends AppCompatActivity implements LogOutTimerUtil.LogOutListener{


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


    DailyDeliveryBean dailyDeliveryBean = null;

    private Button homeButton;
    private SessionManager session =null;
    private Button searchButton;

    private TextView customerstringidtextview;
    private TextView customername;
    private TextView customermobile;
    Calendar cal = Calendar.getInstance();;

    private TextView month;

    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    Date dateobj = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_ledger_list);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

         // Get the transferred data from source activity.
        Intent intent = getIntent();
        String customerstringid = intent.getStringExtra("customerstringid");

        System.out.println("customerstringid customerstringid customerstringid : "+customerstringid);


        customerstringidtextview = (TextView) findViewById(R.id.customerstringidtextview);

        customername = (TextView) findViewById(R.id.customername);

        customermobile = (TextView) findViewById(R.id.customermobile);

        month = (TextView) findViewById(R.id.month);



        //Calling Rest Webservice Start
        homeButton  = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });


        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

        Call<List<DailyDeliveryBean>> call = jsonPlaceHolderApi.customerLedgerByID(customerstringid, ""+df.format(dateobj));
        call.enqueue(new Callback<List<DailyDeliveryBean>>() {
            @Override
            public void onResponse(Call<List<DailyDeliveryBean>> call, Response<List<DailyDeliveryBean>> response) {
                System.out.println("response.isSuccessful() : "+response.isSuccessful());
                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<DailyDeliveryBean> dailyDeliveryBeanList = response.body();

                System.out.println("(dailyDeliveryBeanList!=null) : "+(dailyDeliveryBeanList!=null));
                if(dailyDeliveryBeanList!=null){
                    System.out.println("(dailyDeliveryBeanList size : "+(dailyDeliveryBeanList.size()));
                    DailyDeliveryBean dailyDeliveryBean = dailyDeliveryBeanList.get(0);
                      customerstringidtextview.setText(dailyDeliveryBean.getCustomerstringid());
                      customername.setText(dailyDeliveryBean.getCustFirstName()+ " "+dailyDeliveryBean.getCustLastName());
                      customermobile.setText(dailyDeliveryBean.getCustMobileNo());



                    cal.setTime(dailyDeliveryBean.getTrans_date());
                    //System.out.println("Transdate : "+dailyDeliveryBean.getTrans_date());
                    //int date32 = cal.get(Calendar.DATE);
                    int month1 = cal.get(Calendar.MONTH); // 5
                  //  System.out.println("month1 :  "+month1);
                           // System.out.println(""+Month.of(month1+1).name());
                    int year = cal.get(Calendar.YEAR); // 2016
                    month.setText(""+Month.of(month1+1).name()+" "+year);
                }
               // openCustomerLedgerListActivity(customerstringid.getText().toString());
                init(dailyDeliveryBeanList);

            }

            @Override
            public void onFailure(Call<List<DailyDeliveryBean>> call, Throwable t) {
                // public void onFailure(Call<CustomerBean> call, Throwable t) {
                // textViewResult.setText(t.getMessage());
                System.out.println("t.getMessage(): " + t.getMessage());
            }
        });

        //END of calling REST webservice



    }



    public void init(List<DailyDeliveryBean> dailyDeliveryBeanList) {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);

        tv0.setText(" Date ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Delivered ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Return ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Pending ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        DailyDeliveryBean dailyDeliveryBean =null;
        for (int i = 0; i < dailyDeliveryBeanList.size(); i++) {
            dailyDeliveryBean = dailyDeliveryBeanList.get(i);
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            cal.setTime(dailyDeliveryBean.getTrans_date());
            int transdate = cal.get(Calendar.DATE);
            t1v.setText("" + (transdate));
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("" + dailyDeliveryBean.getDeliveredCanCount());
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("" + dailyDeliveryBean.getReturnCanCount());
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("" + dailyDeliveryBean.getPendingCans());
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }

    }

    public void openHomeActivity(){
        Intent homeActivity = new Intent(this, HomeActivity.class);
        startActivity(homeActivity);
    }

}
