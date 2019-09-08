package com.ssvtech.jalforce1.fragment;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.ssvtech.jalforce1.JsonPlcaeHolderApi;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.RetrofitFactory;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.adapter.CustomerListAdapter;
import com.ssvtech.jalforce1.entity.CustomerBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CustomerSearchFragment extends Fragment {
    private Button homeButton;
    private SessionManager session =null;
    private Button searchButton;

    private TextView customerstringid;

    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    Date dateobj = new Date();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customer_search, null);


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



        System.out.println(" customerstringid in customerstringid : " + customerstringid);

        //Calling customerbean webservice  to fetch customer bean : Start

        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

        Call<List<CustomerBean>> call = jsonPlaceHolderApi.customerById(customerstringid);
        call.enqueue(new Callback<List<CustomerBean>>() {
            @Override
            public void onResponse(Call<List<CustomerBean>> call, Response<List<CustomerBean>> response) {

                if (!response.isSuccessful()) {
                    return;
                }
                List<CustomerBean> customerBeanList = response.body();
                if(customerBeanList!=null && customerBeanList.size()>0){
                    CustomerBean customerBean =  customerBeanList.get(0);
                    Bundle bundle = new Bundle();
                    Fragment fragment = new DailyDeliveryCanToCustomersFragment();
                    System.out.println(" customerstringid in customerBean : " + customerBean);
                    bundle.putSerializable("customerBean", customerBean);
                    bundle.putString("fromcustomersearch", "fromcustomersearch");
                     fragment.setArguments(bundle);
                    fillFragment(fragment);
                }

            }
            @Override
            public void onFailure(Call<List<CustomerBean>> call, Throwable t) {

                System.out.println("t.getMessage(): " + t.getMessage());
            }

        });


        //Calling customerbean webservice  to fetch customer bean : End








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