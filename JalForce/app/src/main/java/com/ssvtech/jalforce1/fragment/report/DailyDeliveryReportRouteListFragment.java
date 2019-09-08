package com.ssvtech.jalforce1.fragment.report;

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

import com.ssvtech.jalforce1.JsonPlcaeHolderApi;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.RetrofitFactory;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.adapter.RouteListAdapter;
import com.ssvtech.jalforce1.entity.Route;
import com.ssvtech.jalforce1.fragment.DailyDeliveryCustomerListFragment;
import com.ssvtech.jalforce1.fragment.HomeFragment;
import com.ssvtech.jalforce1.fragment.RoutePlanFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DailyDeliveryReportRouteListFragment extends Fragment {

    private Button homeButton;
    private SessionManager session = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_delivery_report_route_list2, null);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        session = new SessionManager(getContext());
        session.checkLogin();



        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

        Call<List<Route>> call = jsonPlaceHolderApi.getRouteList();

        System.out.println("INNININI  Call<List<Route>> call ");

        call.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {

                System.out.println("INNININI  Call<List<Route>> onResponse ");

                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    System.out.println("INNININI  NOT isSuccessful");
                    return;
                }
                List<Route> posts = response.body();

                if(posts!=null ) {
                    System.out.println("posts.size : " + posts.size());
                }else{
                    System.out.println("NULL POST IS NULL : ");
                }
                //Start


                final ListView lv = (ListView) getView().findViewById(R.id.routelist);

                System.out.println("lv != null : "+(lv!=null));
                lv.setAdapter(new RouteListAdapter(getContext(), posts));

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        Route route = (Route) lv.getItemAtPosition(position);

                        routePlanning(route);

                    }
                });

                //END


            }


            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {
                // public void onFailure(Call<CustomerBean> call, Throwable t) {
                // textViewResult.setText(t.getMessage());
                System.out.println("t.getMessage(): " + t.getMessage());
            }


        });



    }

    public void routePlanning(Route route) {

        Bundle bundle = new Bundle();
        System.out.println(" ROUTECODE in ROUTEPLANFRAGMEN : " + route.getRouteCode());
        bundle.putString("route", route.getRouteCode());
        Fragment fragment = new DailyDeliveryReportFragment();
        fragment.setArguments(bundle);
        if (fragment != null) {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }
    }


    public void openHomeActivity(){
        Fragment fragment = new HomeFragment();
        fillFragment(fragment);
    }

    public void openBackToListActivity(){
        Fragment fragment = new RoutePlanFragment();
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
