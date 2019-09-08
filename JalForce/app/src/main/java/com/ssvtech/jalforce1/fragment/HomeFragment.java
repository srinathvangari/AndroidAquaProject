package com.ssvtech.jalforce1.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ssvtech.jalforce1.CustomerSearchByIdActivity;
import com.ssvtech.jalforce1.HomeActivity;
import com.ssvtech.jalforce1.LiveTrackingMapsFragment;
import com.ssvtech.jalforce1.MainActivity;
import com.ssvtech.jalforce1.MapActivity;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.TotalCansWithCustomersActivity;
import com.ssvtech.jalforce1.Utility.LocaleHelper;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.fragment.report.PreviousDateDeliveryReport;
import com.ssvtech.jalforce1.fragment.report.*;

public class HomeFragment extends Fragment {




    private Button dailyDeliveryButton;
    private Button  routePlanningButton;
    private Button totalCansInPlantButton;
    private Button totalCansWithCustomersButton;
    private Button paymentButton;
    private Button reportButton;
    private Button logoutButton;
    private Button tripAction;
    private Button customersearchbyid;
    private Button  customerledgersearchid;
    private Button logoutButton1;
    private Button counterSale;
    private Button  customersearch;
    private Button   liveTracking;
    private Button   dailydeliveryreportid;

    private Button   correctionDelivery;
    private Button   addDeliveryOtherDate;
    private Button   previousDateDeliveryReport;


    private LinearLayout linearLayoutFirstRow;
    private LinearLayout linearLayoutSecondRow;
    private LinearLayout linearLayoutThirdRow;
    private LinearLayout linearLayoutForthRow;
    private LinearLayout linearLayoutFiveRow;


    public final static String EXTRA_STRING ="REPORTROUTE";

    SessionManager session =null;



    private TextView textViewResult;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,null);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        session = new SessionManager(getContext());



        view.findViewById(R.id.tripAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in tripAction");
                openTripAction();
            }
        });


       dailyDeliveryButton = (Button) getView().findViewById(R.id.button1);
        dailyDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in dailyDeliveryButton");
                openDailiDevliery();
            }
        });

       counterSale  = (Button) getView().findViewById(R.id.counterSale);
        counterSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertComingSoon();
            }
        });



        routePlanningButton = (Button) getView().findViewById(R.id.routePlanningButton);
        routePlanningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in routePlanningButton");
                routePlanning();
            }
        });

        customerledgersearchid  = (Button) getView().findViewById(R.id.customerledgersearchid);
        customerledgersearchid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in customerledgersearchid");
                customerledgersearchid();
            }
        });


        customersearch  = (Button) getView().findViewById(R.id.customersearch);
        customersearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in customersearch");
                customersearch();
            }
        });


        customersearchbyid = (Button) getView().findViewById(R.id.customersearchbyid);
        customersearchbyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in customersearchbyid");
                customersearchbyidActivity();
            }
        });


        totalCansInPlantButton = (Button) getView().findViewById(R.id.button2);
        totalCansInPlantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in totalCansInPlantButton");
                openTotalCansInPlant();
            }
        });

        tripAction = (Button) getView().findViewById(R.id.tripAction);
        tripAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in tripAction");
               // tripAction();
                alertComingSoon();
            }
        });


        dailydeliveryreportid = (Button) getView().findViewById(R.id.dailydeliveryreportid);
        dailydeliveryreportid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in dailydeliveryreportid");
                // tripAction();
                daildeliveryreportlist();
            }
        });


        paymentButton  = (Button) getView().findViewById(R.id.button4);
        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertComingSoon();
            }
        });

        totalCansWithCustomersButton = (Button) getView().findViewById(R.id.unknownButton);
        totalCansWithCustomersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in totalCansWithCustomersButton");
                openTotalCansWithCustomers();

            }
        });

        liveTracking = (Button) getView().findViewById(R.id.liveTracking);
        liveTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in liveTracking");
                openMaps();

            }
        });




        previousDateDeliveryReport = (Button) getView().findViewById(R.id.previousDateDeliveryReport);
        previousDateDeliveryReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("HEllooooooooo in previousDateDeliveryReport");
                previousDateDeliveryReport();

            }
        });






        reportButton = (Button) getView().findViewById(R.id.reportButton);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routeReport();

            }
        });





        logoutButton = (Button) getView().findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
                logout();


            }
        });




        logoutButton1= (Button) getView().findViewById(R.id.logoutButton1);
        logoutButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
                logout();


            }
        });

        linearLayoutFirstRow = getView().findViewById(R.id.firstRow);
        linearLayoutSecondRow = getView().findViewById(R.id.secondRow);
        linearLayoutThirdRow = getView().findViewById(R.id.thirdRow);
        linearLayoutForthRow = getView().findViewById(R.id.forthRow);
        linearLayoutFiveRow  = getView().findViewById(R.id.fiveRow);
        linearLayoutForthRow.setVisibility(View.GONE);

        String role = session.getUserDetails().get("role");
        System.out.println("IN HOME FRAGMENT role : "+role);
        if(role!=null && role.equals("worker")){

            linearLayoutFirstRow.setVisibility(View.GONE);
            linearLayoutThirdRow.setVisibility(View.GONE);
            linearLayoutForthRow.setVisibility(View.GONE);

            routePlanningButton.setVisibility(View.GONE);
            totalCansInPlantButton.setVisibility(View.GONE);
            totalCansWithCustomersButton.setVisibility(View.GONE);
            paymentButton.setVisibility(View.GONE);
            tripAction.setVisibility(View.GONE);

            customersearchbyid.setVisibility(View.GONE);
           // customerledgersearchid.setVisibility(View.GONE);

            liveTracking.setVisibility(View.GONE);

            linearLayoutFiveRow.setVisibility(View.VISIBLE);

        }



    }



    public void logout(){
        Intent mainActivityIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(mainActivityIntent);
    }

    public void routePlanning(){

       Fragment fragment = new RoutePlanFragment();
       fillFragment(fragment);
    }

    public void openDailiDevliery(){

        Fragment fragment = new DailyDeliverRouteListFragment();
        fillFragment(fragment);
    }

    public void openTotalCansInPlant(){
        Fragment fragment = new SummaryFragment();
        fillFragment(fragment);
    }

    public void openTotalCansWithCustomers(){
        Intent intent = new Intent(getActivity(), TotalCansWithCustomersActivity.class);
        intent.putExtra(EXTRA_STRING, "TotalCansWithCustomersActivity Page ");
        startActivity(intent);
    }

    public void routeReport(){
        Fragment fragment = new DailyDeliveryReportRouteListFragment();
        fillFragment(fragment);
    }


    public void openTripAction(){
        Fragment fragment = new TripPlannerFragment();
        fillFragment(fragment);
    }

    public void daildeliveryreportlist(){
        Fragment fragment = new com.ssvtech.jalforce1.fragment.report.DailyDeliveryReportRouteListFragment();
        fillFragment(fragment);
    }


    public void customersearchbyidActivity(){
       /* Intent customerSearchByIdActivity = new Intent(getActivity(), CustomerSearchByIdActivity.class);
        customerSearchByIdActivity.putExtra(EXTRA_STRING, "dailydelivery");
        startActivity(customerSearchByIdActivity);*/
        Intent mainActivity = new Intent(getActivity(), HomeActivity.class);
        LocaleHelper.setLocale(getActivity(), "hi");

        startActivity(mainActivity);
    }

    public void customerledgersearchid(){
      Fragment fragment = new LedgerCustomerSearchFragment();
      fillFragment(fragment);
    }


    public void customersearch(){
        Fragment fragment = new CustomerSearchFragment();
        fillFragment(fragment);
    }


    public void tripAction(){
        Fragment fragment = new TripPlannerFragment();
        fillFragment(fragment);
    }

    public void openMaps(){
       Intent mapActivity = new Intent(getActivity(), MapActivity.class);
        startActivity(mapActivity);

       /* Fragment fragment = new LiveTrackingMapsFragment();
        fillFragment(fragment);*/
    }


    public void correctionDelivery(){
       System.out.println("in correctionDelivery");


    }


    public void addDeliveryOtherDate(){
        System.out.println("in addDeliveryOtherDate");

       /* Fragment fragment = new LiveTrackingMapsFragment();
        fillFragment(fragment);*/
    }

    public void previousDateDeliveryReport(){
        System.out.println("in previousDateDeliveryReport");

        Fragment fragment = new PreviousDateDeliveryReport();
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

    private void alertComingSoon(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getActivity());
        alertDialog.setCancelable(false);
        alertDialog.setTitle(R.string.Alert);


        alertDialog
                .setMessage(R.string.COMING_SOON);

        alertDialog.setNegativeButton(R.string.ok,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

}
