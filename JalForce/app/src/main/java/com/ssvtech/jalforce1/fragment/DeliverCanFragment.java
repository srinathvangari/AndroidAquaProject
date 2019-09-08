package com.ssvtech.jalforce1.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ssvtech.jalforce1.CustomerLedgerSearchByIdActivity;
import com.ssvtech.jalforce1.CustomerSearchByIdActivity;
import com.ssvtech.jalforce1.DailyDeliveryActivity;
import com.ssvtech.jalforce1.DailyRoutePlan;
import com.ssvtech.jalforce1.HomeActivity;
import com.ssvtech.jalforce1.JsonPlcaeHolderApi;
import com.ssvtech.jalforce1.MainActivity;
import com.ssvtech.jalforce1.MapActivity;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.RetrofitFactory;
import com.ssvtech.jalforce1.RouteSelectionActivity;
import com.ssvtech.jalforce1.TotalCansInPlantActivity;
import com.ssvtech.jalforce1.TotalCansWithCustomersActivity;
import com.ssvtech.jalforce1.TripSelectionActivity;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.adapter.RouteListAdapter;
import com.ssvtech.jalforce1.entity.Dailyroutesummary;
import com.ssvtech.jalforce1.entity.Route;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DeliverCanFragment extends Fragment {


    private Button homeButton;
    private SessionManager session = null;
    private TextView routeView;
    private TextView dateView;
    private TextView numberOfCustomers;
    private TextView pendingCans;
    private Button dailyRoutePlanButton;
    private Button submitButton;
    private Button backToListButton;

    private EditText loadCan;

    //  private EditText loadCan;
    //   private EditText drivername;
    Date dateobj = new Date();
    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    Dailyroutesummary dailyroutesummary =null;
    Retrofit retrofit = null;
    JsonPlcaeHolderApi jsonPlaceHolderApi = null;


    private String loadCans="0";
    private String driverName="";
   // private Spinner dynamicSpinner;
    private Spinner dynamicDriverSpinner;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deliver_can, null);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new SessionManager(getContext());
        session.checkLogin();

        //Initialize Fields Start
        dateView = (TextView) getView().findViewById(R.id.dateView);
        routeView = (TextView) getView().findViewById(R.id.routeView);
        numberOfCustomers = (TextView) getView().findViewById(R.id.numberOfCustomers);
        pendingCans = (TextView) getView().findViewById(R.id.pendingCans);
        //  loadCan = (EditText) findViewById(R.id.loadCan);
        //  drivername = (EditText) findViewById(R.id.drivername);

        loadCan = (EditText) getView().findViewById(R.id.loadCan);

        //Call webservice and fetch values : Start

        retrofit = RetrofitFactory.getRetrofit();
        jsonPlaceHolderApi  = retrofit.create(JsonPlcaeHolderApi.class);

        // Get the transferred data from source activity.
       // Intent intent = getActivity().getIntent();
        //Route route33 = (Route)intent.getSerializableExtra("route");
        Bundle bundle = this.getArguments();
String routeCode="";
        if(bundle != null){
            // handle your code here.
            routeCode = bundle.getString("route");
        }
        System.out.println(" ROiute COde selected : "+routeCode);

        //Call<Dailyroutesummary> call = jsonPlaceHolderApi.getdailyroutesummarySingle(route33.getRouteCode());
        Call<Dailyroutesummary> call = jsonPlaceHolderApi.getdailyroutesummarySingle(routeCode);
        // Call<Dailyroutesummary> call = jsonPlaceHolderApi.getdailyroutesummarySingle("A1");

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
                    System.out.println("INSERTED SUCCESFULLY");
                    //openCanDevliery();

                    //Initialize Fields End

                    //Populate Fileds : Start
                    dateView.setText(""+df.format(dateobj));
                    routeView.setText(""+dailyroutesummary.getRoutename());
                    numberOfCustomers.setText(""+dailyroutesummary.getNoofcustomers());
                    pendingCans.setText(""+dailyroutesummary.getInitalpendingcans());
                    // loadCan.setText("60");
                    //drivername.setText("Chinna");
                    //Populate Fileds : End

                }



            }

            @Override
            public void onFailure(Call<Dailyroutesummary> call, Throwable t) {
                System.out.println("t.getMessage(): " + t.getMessage());
            }
        });


        //Call webservice and fetch values : End


        //Spinner start
     /*   String[] items = new String[] { "10","20","30","40","50","55","60","65","70","75","80","85","90","95","100" };

        dynamicSpinner = (Spinner) getView().findViewById(R.id.loadCan);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("Value selected : "+(String) parent.getItemAtPosition(position));
                Log.v("item", (String) parent.getItemAtPosition(position));
                loadCans = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
*/


        //Sprinner End




        //Spinner start
        String[] driveritems =
                new String[] { "pandu@gmail.com","pravin@gmail.com", "faruk@gmail.com","naganath@gmail.com","jivan@gmail.com", "shrikanth@gmail.com","shudhu@gmail.com"};

        dynamicDriverSpinner = (Spinner) getView().findViewById(R.id.drivername);

        ArrayAdapter<String> adapterDriver = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, driveritems);

        dynamicDriverSpinner.setAdapter(adapterDriver);
        dynamicDriverSpinner.setDropDownWidth(300);

        dynamicDriverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("Value selected : "+(String) parent.getItemAtPosition(position));
                Log.v("item", (String) parent.getItemAtPosition(position));
                driverName = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                driverName="chinna@chippa.com";
            }
        });



        //Sprinner End





        //Submiting the value to insert in insertDailyDailyroutesummary  : Start

        submitButton = (Button) getView().findViewById(R.id.btnSend);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in dailyCanDeliveryButton");
                retrofit = RetrofitFactory.getRetrofit();
                jsonPlaceHolderApi  = retrofit.create(JsonPlcaeHolderApi.class);



                String numberOfCustomers1= numberOfCustomers.getText().toString();
                int numberOfCustomers2=Integer.parseInt(numberOfCustomers1);

                loadCans = loadCan.getText().toString();


                String loadCan1= loadCans;
                int loadCan2=Integer.parseInt(loadCan1);

                String pendingCans1= pendingCans.getText().toString();
                int pendingCans2=Integer.parseInt(pendingCans1);


                Call<Dailyroutesummary> call2 = jsonPlaceHolderApi.insertDailyDailyroutesummary(routeView.getText().toString(), df.format(dateobj),
                        pendingCans2,loadCan2
                        ,0, 0, 0,
                        numberOfCustomers2,driverName);


                call2.enqueue(new Callback<Dailyroutesummary>() {
                    @Override
                    public void onResponse(Call<Dailyroutesummary> call2, Response<Dailyroutesummary> response) {
                        Dailyroutesummary dailyroutesummary2 = response.body();
                        System.out.println("Dailyroutesummary != NULL : "+(dailyroutesummary2!=null));
                        if (!response.isSuccessful()) {
                            //textViewResult.setText("Code: " + response.code());\
                            System.out.println("EZXCEPTION : Dailyroutesummary INSERTED NOT SUCCESFULLY  Date");
                            return;
                        }else{
                            System.out.println("INSERTED SUCCESFULLY  Dailyroutesummary");
                           // dynamicSpinner.setEnabled(false);
                            loadCan.setEnabled(false);
                            dynamicDriverSpinner.setEnabled(false);
                            submitButton.setEnabled(false);
                            DialogFragment newFragment = new MessageDialogFragment();
                            newFragment.show(getFragmentManager(), "Sucecess");
                        }

                    }

                    @Override
                    public void onFailure(Call<Dailyroutesummary> call, Throwable t) {
                        System.out.println("t.getMessage(): " + t.getMessage());
                    }
                });


            }
        });

        //Submiting the value to insert in insertDailyDailyroutesummary  : End





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




    public void openCanDevliery(){
        Intent dailyDeliveryActivity = new Intent(getActivity(), DailyDeliveryActivity.class);
        startActivity(dailyDeliveryActivity);
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
