package com.ssvtech.jalforce1.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.ssvtech.jalforce1.JsonPlcaeHolderApi;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.RetrofitFactory;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.DailyDeliveryBean;
import com.ssvtech.jalforce1.entity.Dailyroutesummary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DailyDeliveryRouteSevenDaysReport extends Fragment {

    private Button homeButton;
    private SessionManager session =null;


    private TextView routeid;
    private TextView dailyroutesummaryreport;

    Calendar cal = Calendar.getInstance();;

    Retrofit retrofit = null;
    JsonPlcaeHolderApi jsonPlaceHolderApi = null;


    private  String routeCode="";



    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    Date dateobj = new Date();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_delivery_route_seven_days_report, null);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        session = new SessionManager(getContext());
        session.checkLogin();  //Initialize Fields Start


        Bundle bundle = this.getArguments();
        routeCode="";
        if(bundle != null){
            // handle your code here.
            routeCode = bundle.getString("route");
        }
        System.out.println(" routeCode!=null : "+(routeCode));



        retrofit = RetrofitFactory.getRetrofit();
        jsonPlaceHolderApi  = retrofit.create(JsonPlcaeHolderApi.class);

        routeid = (TextView) getView().findViewById(R.id.routeid);

        dailyroutesummaryreport = (TextView) getView().findViewById(R.id.dailyroutesummaryreport);

        //Calling Rest Webservice Start
      /*  homeButton  = (Button) getView().findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });
*/

        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);
        Call<List<Dailyroutesummary>> call = jsonPlaceHolderApi.getdailydeliverydetailsbyrouteforlastsevendays(routeCode, ""+df.format(dateobj));
        call.enqueue(new Callback<List<Dailyroutesummary>>() {
            @Override
            public void onResponse(Call<List<Dailyroutesummary>> call, Response<List<Dailyroutesummary>> response) {
                System.out.println("response.isSuccessful() : "+response.isSuccessful());
                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Dailyroutesummary> dailyroutesummaryList = response.body();

                System.out.println("(dailyroutesummaryList!=null) : "+(dailyroutesummaryList!=null));
                if(dailyroutesummaryList!=null){
                    System.out.println("(dailyroutesummaryList size : "+(dailyroutesummaryList.size()));
                    if(dailyroutesummaryList.size()>0) {
                        Dailyroutesummary dailyroutesummary = dailyroutesummaryList.get(0);

                        routeid.setText(dailyroutesummary.getRoutename());
                        dailyroutesummaryreport.setText(R.string.dailyroutesummaryreport);



                    }else{
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                getActivity());
                        alertDialog.setCancelable(false);
                        alertDialog.setTitle(R.string.Alert);


                        alertDialog
                                .setMessage(R.string.NO_RECORD_FOUND);

                        alertDialog.setNegativeButton(R.string.ok,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        getActivity().finish();
                                        startActivity(getActivity().getIntent());
                                        dialog.cancel();
                                    }
                                });

                        alertDialog.show();
                    }
                }
                // openCustomerLedgerListActivity(customerstringid.getText().toString());
                init(dailyroutesummaryList);

            }

            @Override
            public void onFailure(Call<List<Dailyroutesummary>> call, Throwable t) {
                // public void onFailure(Call<CustomerBean> call, Throwable t) {
                // textViewResult.setText(t.getMessage());
                System.out.println("t.getMessage(): " + t.getMessage());
            }
        });

        //END of calling REST webservice



    }


    public void init(List<Dailyroutesummary> dailyroutesummaryList) {
        TableLayout stk = (TableLayout) getView().findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(getActivity());
        TextView tv0 = new TextView(getActivity());

        tv0.setText(R.string.date);
        tv0.setText(tv0.getText()+"  ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(getActivity());
        tv1.setText(R.string.customer);
        tv1.setText(tv1.getText()+"  ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
       // TextView tv2 = new TextView(getActivity());
        //tv2.setText(R.string.istock);
        //tv2.setText(tv2.getText()+"  ");
        //tv2.setTextColor(Color.WHITE);
        //tbrow0.addView(tv2);
        TextView tv3 = new TextView(getActivity());
        tv3.setText(R.string.fstock);
        tv3.setText(tv3.getText()+"  ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);


        TextView tv4 = new TextView(getActivity());
        tv4.setText(R.string.load);
        tv4.setText(tv4.getText()+"  ");
        tv4.setTextColor(Color.WHITE);
        tbrow0.addView(tv4);

        TextView tv7 = new TextView(getActivity());
        tv7.setText(R.string.delivery);
        tv7.setText(tv7.getText()+"  ");
        tv7.setTextColor(Color.WHITE);
        tbrow0.addView(tv7);

        TextView tv5 = new TextView(getActivity());
        tv5.setText(R.string.return1);
        tv5.setText(tv5.getText()+"  ");
        tv5.setTextColor(Color.WHITE);
        tbrow0.addView(tv5);


        TextView tv6 = new TextView(getActivity());
        tv6.setText(R.string.return2);
        tv6.setText(tv6.getText()+"  ");
        tv6.setTextColor(Color.WHITE);
        tbrow0.addView(tv6);



        TextView tv8 = new TextView(getActivity());
        tv8.setText(R.string.driver1);
        tv8.setText(tv8.getText()+"  ");
        tv8.setTextColor(Color.WHITE);
        tbrow0.addView(tv8);



        stk.addView(tbrow0);

        Dailyroutesummary dailyroutesummary =null;
        for (int i = 0; i < dailyroutesummaryList.size(); i++) {
            dailyroutesummary = dailyroutesummaryList.get(i);
            TableRow tbrow = new TableRow(getActivity());
            TextView t1v = new TextView(getActivity());
            cal.setTime(dailyroutesummary.getRecorddate());
            int transdate = cal.get(Calendar.DATE);
            int transMonth = cal.get(Calendar.MONTH);
            int month1 = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR); // 2016
            t1v.setText("" + (transdate)+ "-"+(month1+1) + "-" + year);
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(getActivity());
            t2v.setText("" + dailyroutesummary.getNoofcustomers());
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
           /* TextView t3v = new TextView(getActivity());
            t3v.setText("" + dailyroutesummary.getInitalpendingcans());
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);*/
            TextView t4v = new TextView(getActivity());
            t4v.setText("" + dailyroutesummary.getFinalpendingcans());
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);


            TextView t9v = new TextView(getActivity());
            t9v.setText("" + dailyroutesummary.getLoadcans());
            t9v.setTextColor(Color.WHITE);
            t9v.setGravity(Gravity.CENTER);
            tbrow.addView(t9v);


            TextView t7v = new TextView(getActivity());
            t7v.setText("" + dailyroutesummary.getDeliveredcans());
            t7v.setTextColor(Color.WHITE);
            t7v.setGravity(Gravity.CENTER);
            tbrow.addView(t7v);



            TextView t5v = new TextView(getActivity());
            t5v.setText("" + dailyroutesummary.getReturncans());
            t5v.setTextColor(Color.WHITE);
            t5v.setGravity(Gravity.CENTER);
            tbrow.addView(t5v);






           TextView t6v = new TextView(getActivity());
            t6v.setText("" + (dailyroutesummary.getLoadcans()-dailyroutesummary.getDeliveredcans()));
            t6v.setTextColor(Color.WHITE);
            t6v.setGravity(Gravity.CENTER);
            tbrow.addView(t6v);



            TextView t8v = new TextView(getActivity());
            t8v.setText(" " + dailyroutesummary.getDriveremail().substring(0,dailyroutesummary.getDriveremail().indexOf('@')));
            t8v.setTextColor(Color.WHITE);
            t8v.setGravity(Gravity.CENTER);
            tbrow.addView(t8v);




            stk.addView(tbrow);
        }

    }
    public void openHomeActivity(){
        Fragment fragment = new HomeFragment();
        fillFragment(fragment);
    }



    public void fillFragment(Fragment fragment){
        if(fragment!=null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }
    }

}
