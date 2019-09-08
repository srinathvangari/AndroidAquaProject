package com.ssvtech.jalforce1.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.ssvtech.jalforce1.HomeActivity;
import com.ssvtech.jalforce1.JsonPlcaeHolderApi;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.RetrofitFactory;
import com.ssvtech.jalforce1.RouteSelectionActivity;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.CustomerBean;
import com.ssvtech.jalforce1.entity.Dailyroutesummary;
import com.ssvtech.jalforce1.entity.Route;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DailyDeliveryRouteReportDetailsFragment extends Fragment {
    private SessionManager session = null;

    private Button homeButton;
    private Button backToListButton;
    public final static String EXTRA_STRING ="REPORTROUTE";
    Retrofit retrofit = null;
    JsonPlcaeHolderApi jsonPlaceHolderApi = null;
    Date dateobj = new Date();
    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    private TextView routeView;
    private TextView dateView;
    private TextView numberOfCustomers;
    private TextView pendingCans;
    private TextView loadCan;
    private TextView drivername;
    private TextView finalPendingCansonthisroute;
    private TextView filledCansReturn;
    private TextView totaldeliveredcans;
    private TextView  emptyCansReturn;
    private  String routeCode="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_delivery_route_report_details, null);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        session = new SessionManager(getContext());
        session.checkLogin();  //Initialize Fields Start
        dateView = (TextView) getView().findViewById(R.id.dateView);
        routeView = (TextView) getView().findViewById(R.id.routeView);
        numberOfCustomers = (TextView) getView().findViewById(R.id.numberOfCustomers);
        pendingCans = (TextView) getView().findViewById(R.id.pendingCans);
        loadCan = (TextView) getView().findViewById(R.id.loadCan);
        drivername = (TextView) getView().findViewById(R.id.drivername);
        emptyCansReturn = (TextView) getView().findViewById(R.id.emptyCansReturn);

        finalPendingCansonthisroute = (TextView) getView().findViewById(R.id.finalPendingCansonthisroute);
        filledCansReturn = (TextView) getView().findViewById(R.id.filledCansReturn);
        totaldeliveredcans = (TextView) getView().findViewById(R.id.totaldeliveredcans);

        Bundle bundle = this.getArguments();
         routeCode="";
        if(bundle != null){
            // handle your code here.
            routeCode = bundle.getString("route");
        }
        System.out.println(" routeCode!=null : "+(routeCode));



        retrofit = RetrofitFactory.getRetrofit();
        jsonPlaceHolderApi  = retrofit.create(JsonPlcaeHolderApi.class);



        Call<Dailyroutesummary> call = jsonPlaceHolderApi.getdailydeliverybydatebyroute(routeCode,df.format(dateobj) );

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

                    emptyCansReturn.setText(emptyCansReturn.getText().toString()+" : "+dailyroutesummary.getReturncans());


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






        homeButton  = (Button) getView().findViewById(R.id.btnHome);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });


        backToListButton  = (Button) getView().findViewById(R.id.backToListButton);
        backToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openBackToListActivity();
            }
        });

    }


    public void openBackToListActivity(){
        Fragment fragment = new DailyDeliveryReportRouteListFragment();
        fillFragment(fragment);
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
