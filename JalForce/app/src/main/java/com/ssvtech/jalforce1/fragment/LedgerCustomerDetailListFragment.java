package com.ssvtech.jalforce1.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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


import com.ssvtech.jalforce1.HomeActivity;
import com.ssvtech.jalforce1.JsonPlcaeHolderApi;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.RetrofitFactory;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.DailyDeliveryBean;

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


public class LedgerCustomerDetailListFragment extends Fragment {
    DailyDeliveryBean dailyDeliveryBean = null;

    private Button homeButton;
    private SessionManager session =null;
    private Button searchButton;

    private TextView customerstringidtextview;
    private TextView customername;
    private TextView customermobile;
    Calendar cal = Calendar.getInstance();;

    private TextView month;



    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    Date dateobj = new Date();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ledger_customer_detail_list, null);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        session = new SessionManager(getContext());
        session.checkLogin();

        String customerstringid = "";

        Bundle bundle = this.getArguments();

        if(bundle != null){
            // handle your code here.
            customerstringid = bundle.getString("customerstringid");
        }

        System.out.println("customerstringid customerstringid customerstringid : "+customerstringid);


        customerstringidtextview = (TextView) getView().findViewById(R.id.customerstringidtextview);

        customername = (TextView) getView().findViewById(R.id.customername);

        customermobile = (TextView) getView().findViewById(R.id.customermobile);

        month = (TextView) getView().findViewById(R.id.month);



        //Calling Rest Webservice Start
        homeButton  = (Button) getView().findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });


        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);
        System.out.println("customerstringid : "+customerstringid+ " df.format(dateobj)  : "+df.format(dateobj));
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
                    if(dailyDeliveryBeanList.size()>0) {
                        DailyDeliveryBean dailyDeliveryBean = dailyDeliveryBeanList.get(0);
                        customerstringidtextview.setText(dailyDeliveryBean.getCustomerstringid());
                        customername.setText(dailyDeliveryBean.getCustFirstName() + " " + dailyDeliveryBean.getCustLastName());
                        customermobile.setText(dailyDeliveryBean.getCustMobileNo());


                        cal.setTime(dailyDeliveryBean.getTrans_date());
                        //System.out.println("Transdate : "+dailyDeliveryBean.getTrans_date());
                        //int date32 = cal.get(Calendar.DATE);
                        int month1 = cal.get(Calendar.MONTH); // 5
                        //  System.out.println("month1 :  "+month1);
                        // System.out.println(""+Month.of(month1+1).name());
                        int year = cal.get(Calendar.YEAR); // 2016
                        month.setText("" + Month.of(month1 + 1).name() + " " + year);
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
        TableLayout stk = (TableLayout) getView().findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(getActivity());
        TextView tv0 = new TextView(getActivity());

        tv0.setText(R.string.date);
        tv0.setText(tv0.getText()+"  ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(getActivity());
        tv1.setText(R.string.delivery);
        tv1.setText(tv1.getText()+"  ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(getActivity());
        tv2.setText(R.string.return1);
        tv2.setText(tv2.getText()+"  ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(getActivity());
        tv3.setText(R.string.stock);
        tv3.setText(tv3.getText()+"  ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        DailyDeliveryBean dailyDeliveryBean =null;
        for (int i = 0; i < dailyDeliveryBeanList.size(); i++) {
            dailyDeliveryBean = dailyDeliveryBeanList.get(i);
            TableRow tbrow = new TableRow(getActivity());
            TextView t1v = new TextView(getActivity());
            cal.setTime(dailyDeliveryBean.getTrans_date());
            int transdate = cal.get(Calendar.DATE);

            t1v.setText("" + (transdate));
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(getActivity());
            t2v.setText("" + dailyDeliveryBean.getDeliveredCanCount());
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(getActivity());
            t3v.setText("" + dailyDeliveryBean.getReturnCanCount());
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(getActivity());
            t4v.setText("" + dailyDeliveryBean.getPendingCans());
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
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
