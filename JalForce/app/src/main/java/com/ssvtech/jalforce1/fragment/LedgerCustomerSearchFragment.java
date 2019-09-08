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

import com.ssvtech.jalforce1.CustomerLedgerListActivity;
import com.ssvtech.jalforce1.HomeActivity;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.Utility.SessionManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class LedgerCustomerSearchFragment extends Fragment {

    private Button homeButton;
    private SessionManager session =null;
    private Button searchButton;

    private TextView customerstringid;

    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    Date dateobj = new Date();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ledger_customer_search, null);


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

                customerstringid = (TextView) getView().findViewById(R.id.customerstringid);
                System.out.println("customerstringid : " + customerstringid.getText().toString());
                openCustomerLedgerListActivity(customerstringid.getText().toString());


            }
        });

    }




    public void openCustomerLedgerListActivity(String customerstringid) {
        Fragment fragment = new LedgerCustomerDetailListFragment();
        Bundle bundle = new Bundle();
        System.out.println(" customerstringid in customerstringid : " + customerstringid);
        bundle.putString("customerstringid", customerstringid);

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