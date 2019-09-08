package com.ssvtech.jalforce1.fragment.report;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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


import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.Utility.MessageUtility;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.fragment.HomeFragment;
import com.ssvtech.jalforce1.fragment.LedgerCustomerDetailListFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class PreviousDateDeliveryReport extends Fragment {
    private Button homeButton;
    private SessionManager session =null;
    private Button searchButton;

    private TextView dateValue;
    private TextView routeValue;

    boolean flag = false;


    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_previous_date_delivery_report, null);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        session = new SessionManager(getContext());
        session.checkLogin();


        homeButton = (Button) getView().findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });


        searchButton = (Button) getView().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dateValue = (TextView) getView().findViewById(R.id.dateValue);
                flag =  validate(dateValue.getText().toString());
                routeValue = (TextView) getView().findViewById(R.id.routeValue);
                System.out.println("dateValue : " + dateValue.getText().toString()+"  routeValue : "+routeValue.getText()+" : flag : "+flag);
                if(flag) {
                    openDeliveryReport(dateValue.getText().toString(), routeValue.getText().toString());
                }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                            getActivity());
                    alertDialog.setCancelable(false);
                    alertDialog.setTitle(R.string.Alert);

                    alertDialog
                            .setMessage(R.string.ENTERED_DATE_NOT_VALID);


                    alertDialog.setNegativeButton(R.string.ok,
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.cancel();

                                }
                            });

                    alertDialog.show();
                }


            }
        });

    }


        public boolean validate(String dateValue){
                try {
                    Date date = df.parse(dateValue);
                    return true;
                }catch(Exception e){

        return false;

                }
        }

    public void openDeliveryReport(String dateValue, String route) {
        Fragment fragment = new PreviousDeliveryDetailsReport();
        Bundle bundle = new Bundle();
        System.out.println(" dateValue in dateValue : " + dateValue);
        bundle.putString("dateValue", dateValue);
        bundle.putString("route", route);


        fragment.setArguments(bundle);
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