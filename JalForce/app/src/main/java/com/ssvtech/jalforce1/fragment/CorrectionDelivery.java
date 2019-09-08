package com.ssvtech.jalforce1.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ssvtech.jalforce1.fragment.report.*;

import com.ssvtech.jalforce1.CustomerSearchByIdActivity;
import com.ssvtech.jalforce1.DailyDeliveryActivity;
import com.ssvtech.jalforce1.JsonPlcaeHolderApi;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.RetrofitFactory;
import com.ssvtech.jalforce1.Utility.DateUtility;
import com.ssvtech.jalforce1.Utility.MessageUtility;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.CustomerBean;
import com.ssvtech.jalforce1.entity.DailyDeliveryBean;
import com.ssvtech.jalforce1.fragment.report.DailyDeliveryReportFragment;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.widget.AdapterView.OnItemSelectedListener;

public class CorrectionDelivery extends Fragment {

    private TextView routeTextViewResult;
    private TextView textViewResult;
    private TextView textViewResult1;
    private TextView dateViewResult3;
    private TextView pendingCansWithCustomer;
    private Button dailyCanDeliveryButton;
    private Button cancelButton;
    private Button homeButton;
    //  private EditText deleiverCan;
    // private EditText canDailyReturn;
    // private EditText canDailyDeliver;
    CustomerBean customerBean = null;

    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    Date dateobj = new Date();
    String returnString="0";
    String deliverString ="0";
    String fromcustomersearch = "";

    int returnCanCount =0;
    int deliveredCanCount=0;
    int transid=0;

    SessionManager session =null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_correction_delivery, null);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new SessionManager(getContext());
        session.checkLogin();


        session = new SessionManager(getContext());
        session.checkLogin();



        textViewResult = (TextView) getView().findViewById(R.id.customerNameView);

        //Date Start

        //  DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

        System.out.println("in CanDeliveryActivity  2222");
        dateViewResult3 = (TextView) getView().findViewById(R.id.dateView);
        System.out.println("in CanDeliveryActivity  3333");
        dateViewResult3.setText(""+df.format(dateobj));
        System.out.println("in CanDeliveryActivity  4444");
        //Date End

        // Get the transferred data from source activity.
        // Intent intent = getActivity().getIntent();
        //customerBean = (CustomerBean)intent.getSerializableExtra("customerBean");



        Bundle bundle = this.getArguments();

        if(bundle != null){
            // handle your code here.
            customerBean = (CustomerBean)bundle.getSerializable("customerBean");
            fromcustomersearch = bundle.getString("fromcustomersearch");

            returnCanCount = bundle.getInt("returnCanCount");
            deliveredCanCount = bundle.getInt("deliveredCanCount");
            transid = bundle.getInt("transid");


        }
        System.out.println(" customerBean!=null : "+(customerBean!=null));




        String[] items = new String[] { "0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        Spinner dynamicSpinner = (Spinner) getView().findViewById(R.id.dynamic_spinner);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setSelection(returnCanCount);

        dynamicSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("Value selected : "+(String) parent.getItemAtPosition(position));
                Log.v("item", (String) parent.getItemAtPosition(position));
                returnString = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        //Spinner Logic :- Important
        Spinner dynamicdeleiverCanSpinner = (Spinner) getView().findViewById(R.id.deleiverCan);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);

        dynamicdeleiverCanSpinner.setAdapter(adapter);
        dynamicdeleiverCanSpinner.setSelection(deliveredCanCount);

        dynamicdeleiverCanSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("Value selected : "+(String) parent.getItemAtPosition(position));
                Log.v("item", (String) parent.getItemAtPosition(position));
                deliverString = (String) parent.getItemAtPosition(position);

            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


       /* deleiverCan =  getView().findViewById(R.id.deleiverCan);
        deliverString = deleiverCan.getText().toString();

        System.out.println("Deliver Cans : "+deliverString);*/




        System.out.println("in CanDeliveryActivity :FIrstNamw 1 "+customerBean.getFirstName()+ " , LastName : "+customerBean.getLastName());

        System.out.println("in CanDeliveryActivity :getCustomerstringid  "+customerBean.getCustomerstringid());

        routeTextViewResult = getView().findViewById(R.id.routeView);
        routeTextViewResult.setText(customerBean.getRouteCode());

        textViewResult.setText(""+customerBean.getFirstName() + " "+customerBean.getLastName());

        textViewResult1 = (TextView) getView().findViewById(R.id.customerAddressView2);
        textViewResult1.setText(""+customerBean.getAddress());

        pendingCansWithCustomer = (TextView) getView().findViewById(R.id.pendingCans);
        pendingCansWithCustomer.setText(""+customerBean.getPendingCans());




        cancelButton = (Button) getView().findViewById(R.id.btnCancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fromcustomersearch!=null && fromcustomersearch.equals("fromcustomersearch")){
                    openCuatomerSearchFragment();
                }else {
                    openBackToListActivity(customerBean);
                }
            }
        });



        homeButton  = (Button) getView().findViewById(R.id.btnHome);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openHomeActivity();
            }
        });

        dailyCanDeliveryButton = (Button) getView().findViewById(R.id.btnSend);
        dailyCanDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in dailyCanDeliveryButton  :  ROUTE CODE : "+customerBean.getRouteCode());

                // canDailyReturn = findViewById(R.id.returnCan);
                //canDailyDeliver = findViewById(R.id.deleiverCan);

                //String returnString= canDailyReturn.getText().toString();
                int returnInt=Integer.parseInt(returnString);
                // String deliverString= canDailyDeliver.getText().toString();
                int deliverInt=Integer.parseInt(deliverString);

                System.out.println(" (customerBean.getPendingCans()-returnInt) : "+(customerBean.getPendingCans()-returnInt));

                //Validation for Return Start
                if((customerBean.getPendingCans()-returnInt) <0) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                            getActivity());
                    alertDialog.setCancelable(false);
                    alertDialog.setTitle(R.string.Alert);


                    alertDialog
                            .setMessage(R.string.DAILY_DELIVERY_RETURN_CANS_EXCEDDING_PENDING_CANS);

                    alertDialog.setNegativeButton(R.string.ok,
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {



                                    dialog.cancel();
                                }
                            });

                    alertDialog.show();
                }else {


                    //Validation for Return ENd

                    //  System.out.println("Can Return Daily: " + canDailyReturn.getText() + " : Can Deliver Daily : " + canDailyDeliver.getText()+ " Pending Cans :"+pendingCansWithCustomer.getText());
                    Retrofit retrofit = RetrofitFactory.getRetrofit();
                    JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);
                    System.out.println("returnInt  : "+returnInt+"  : deliverInt  : "+deliverInt+ "  transid  : "+transid);
                    Call<CustomerBean> call = jsonPlaceHolderApi.correctDD(""+transid, returnInt, deliverInt,
                            customerBean.getId(), 2, customerBean.getFirstName(), customerBean.getLastName(),
                            "Chinna", "Chippa", customerBean.getRouteCode(), customerBean.getAddress(),
                            customerBean.getMobileNo(), "99993333", customerBean.getPendingCans(), df.format(dateobj),customerBean.getCustomerstringid());
                    call.enqueue(new Callback<CustomerBean>() {
                        @Override
                        public void onResponse(Call<CustomerBean> call, Response<CustomerBean> response) {
                            CustomerBean customerBean = response.body();
                            System.out.println("CustomerBean != NULL : " + (customerBean != null));
                            if (!response.isSuccessful()) {
                                //textViewResult.setText("Code: " + response.code());\
                                System.out.println("EZXCEPTION : INSERTED NOT SUCCESFULLY  Date");
                                return;
                            } else {

                                System.out.println("SUCESFULL SCUESFULL INSERTED");
//Alert Start
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                        getActivity());
                                alertDialog.setCancelable(false);
                                alertDialog.setTitle(R.string.Alert);

                                if (customerBean.getMessage().equals(MessageUtility.DAILY_ROUTE_SUMMARY_RECORD_NOT_THERE)) {
                                    alertDialog
                                            .setMessage(R.string.DAILY_ROUTE_SUMMARY_RECORD_NOT_THERE);
                                } else if (customerBean.getMessage().equals(MessageUtility.DAILY_DELIVERY_EXCEDDING_LOADED_CANS)) {
                                    alertDialog
                                            .setMessage(R.string.DAILY_DELIVERY_EXCEDDING_LOADED_CANS);
                                } else if (customerBean.getMessage().equals(MessageUtility.DAILY_DELIVERY_SUCCESSFUL_TO_CUSTOMER)) {
                                    alertDialog
                                            .setMessage(R.string.DAILY_DELIVERY_SUCCESSFUL_TO_CUSTOMER);
                                }
                                alertDialog.setNegativeButton(R.string.ok,
                                        new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.cancel();

                                            }
                                        });

                                alertDialog.show();



                                pendingCansWithCustomer.setText("" + customerBean.getPendingCans());
                                //  canDailyReturn.setEnabled(false);
                                //  canDailyDeliver.setEnabled(false);
                                dailyCanDeliveryButton.setEnabled(false);

                            }
                        }


                        @Override
                        public void onFailure(Call<CustomerBean> call, Throwable t) {
                            System.out.println("t.getMessage(): " + t.getMessage());
                        }
                    });
                }
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

    public void openCuatomerSearchFragment(){
        Fragment fragment = new CustomerSearchFragment();
        fillFragment(fragment);
    }

    public void openBackToListActivity(CustomerBean customerBean){


        Bundle bundle = new Bundle();
        System.out.println(" customerBean in ROUTEPLANFRAGMEN 22 : " + customerBean.getRouteCode());
        bundle.putString("route", customerBean.getRouteCode());
        Fragment fragment = new DailyDeliveryReportFragment();
        fragment.setArguments(bundle);
        if (fragment != null) {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }
    }

    public void fillFragment(Fragment fragment){
        if(fragment!=null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }
    }



    public void customersearchbyidActivity(){
        Intent customerSearchByIdActivity = new Intent(getActivity(), CustomerSearchByIdActivity.class);
        startActivity(customerSearchByIdActivity);
    }
}
