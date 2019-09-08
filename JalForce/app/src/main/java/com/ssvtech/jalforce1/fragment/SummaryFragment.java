package com.ssvtech.jalforce1.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.Cansummary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;




public class SummaryFragment extends Fragment {
    private SessionManager session =null;

    private TextView cansInPlant = null;
    private TextView cans_with_customer = null;



    private Button homeButton;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary, null);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        session = new SessionManager(getContext());
        session.checkLogin();

        homeButton = (Button) getView().findViewById(R.id.button1);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });

        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

        Call<Cansummary> call = jsonPlaceHolderApi.getTotalCansInPlant();

        call.enqueue(new Callback<Cansummary>() {
            @Override
            public void onResponse(Call<Cansummary> call, Response<Cansummary> response) {

                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    // textViewResult.setText("Code: " + response.code());
                    return;
                }

                Cansummary cansummary = response.body();
                System.out.println("posts  TOTALCANS in PLANT: " + cansummary.getTotalcans());
                cansInPlant = (TextView) getView().findViewById(R.id.cansInPlant);
                //set text style bold on current font
                cansInPlant.setTypeface(cansInPlant.getTypeface(), Typeface.BOLD);
                cansInPlant.setText(""+(cansummary.getTotalcans()- cansummary.getTotalPendingCanAtCustomers()));


                cans_with_customer = getView().findViewById(R.id.cans_with_customer);
                cans_with_customer.setTypeface(cans_with_customer.getTypeface(), Typeface.BOLD);
                cans_with_customer.setText(""+cansummary.getTotalPendingCanAtCustomers());

            }

            @Override
            public void onFailure(Call<Cansummary> call, Throwable t) {
                // textViewResult.setText(t.getMessage());
                System.out.println("t.getMessage(): " + t.getMessage());
            }
        });


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
