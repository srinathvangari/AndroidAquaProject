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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ssvtech.jalforce1.CanDeliveryActivity;
import com.ssvtech.jalforce1.DailyDeliveryActivity;
import com.ssvtech.jalforce1.JsonPlcaeHolderApi;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.RetrofitFactory;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.adapter.CustomerListAdapter;
import com.ssvtech.jalforce1.entity.CustomerBean;
import com.ssvtech.jalforce1.entity.Route;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DailyDeliveryCustomerListFragment extends Fragment {

    SessionManager session =null;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date dateobj = new Date();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_delivery_customer_list, null);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        session = new SessionManager(getContext());
        session.checkLogin();


        // Get the transferred data from source activity.
      //  Intent intent = getActivity().getIntent();
      //  Route route33 = (Route)intent.getSerializableExtra("route");

        Bundle bundle = this.getArguments();
String routeCode="";
        if(bundle != null){
            // handle your code here.
            routeCode = bundle.getString("route");
        }
        System.out.println(" routeCode!=null : "+(routeCode));

        System.out.println("  DailyDeliveryActivity Route Code selected : "+routeCode);

        System.out.println("  DailyDeliveryActivity Route Code selected : "+df.format(dateobj));


        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

        Call<List<CustomerBean>> call = jsonPlaceHolderApi.customerListByRoute(routeCode);
        call.enqueue(new Callback<List<CustomerBean>>() {
            @Override
            public void onResponse(Call<List<CustomerBean>> call, Response<List<CustomerBean>> response) {

                if (!response.isSuccessful()) {
                    return;
                }
                List<CustomerBean> posts = response.body();







                final ListView lv = (ListView) getView().findViewById(R.id.user_list);
                lv.setAdapter(new CustomerListAdapter( getContext(), posts));

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        CustomerBean customerBean = (CustomerBean) lv.getItemAtPosition(position);


                        openCanDevliery(customerBean);
                    }
                });


            }

            @Override
            public void onFailure(Call<List<CustomerBean>> call, Throwable t) {
                // public void onFailure(Call<CustomerBean> call, Throwable t) {
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

    public void openCanDevliery(CustomerBean customerBean){
        Bundle bundle = new Bundle();
        System.out.println(" customerBean in ROUTEPLANFRAGMEN : "+customerBean.getRouteCode());
        bundle.putSerializable("customerBean",customerBean);
        Fragment fragment = new DailyDeliveryCanToCustomersFragment();
        fragment.setArguments(bundle);
        if(fragment!=null){

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }
    }

    }
