package com.ssvtech.jalforce1.fragment.report;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.ssvtech.jalforce1.JsonPlcaeHolderApi;
import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.RetrofitFactory;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.CustomerBean;
import com.ssvtech.jalforce1.entity.DailyDeliveryBean;
import com.ssvtech.jalforce1.fragment.CorrectionDelivery;
import com.ssvtech.jalforce1.fragment.HomeFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PreviousDeliveryDetailsReport extends Fragment {

    private Button homeButton;
    private SessionManager session = null;

    private TextView routeid;
    private TextView dailyroutesummaryreport;

    private TextView number;
    private TextView delivery;

    private TextView return1;
    private TextView stock;

    int totalCount=0;
    int totalDelivery=0;
    int totalReturn=0;
    int totalPending=0;


    Calendar cal = Calendar.getInstance();;

    Retrofit retrofit = null;
    JsonPlcaeHolderApi jsonPlaceHolderApi = null;


    private  String routeCode="";
    private  String dateValue="";





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_previous_delivery_details_report, null);




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        session = new SessionManager(getContext());
        session.checkLogin();
        Bundle bundle = this.getArguments();
        routeCode="";
        if(bundle != null){
            // handle your code here.
            routeCode = bundle.getString("route");
            dateValue = bundle.getString("dateValue");
        }
        System.out.println(" routeCode!=null : "+(routeCode)+ "  dateValue : "+dateValue);



        retrofit = RetrofitFactory.getRetrofit();
        jsonPlaceHolderApi  = retrofit.create(JsonPlcaeHolderApi.class);

        routeid = (TextView) getView().findViewById(R.id.routeid);

        dailyroutesummaryreport = (TextView) getView().findViewById(R.id.dailyroutesummaryreport);

        number = (TextView) getView().findViewById(R.id.number);

        delivery = (TextView) getView().findViewById(R.id.delivery);

        return1 = (TextView) getView().findViewById(R.id.return1);

        stock = (TextView) getView().findViewById(R.id.stock);



        Retrofit retrofit = RetrofitFactory.getRetrofit();
        JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);
        Call<List<DailyDeliveryBean>> call = jsonPlaceHolderApi.getDailyDeliveryReportDateandRoutewise(routeCode, ""+dateValue);
        call.enqueue(new Callback<List<DailyDeliveryBean>>() {
            @Override
            public void onResponse(Call<List<DailyDeliveryBean>> call, Response<List<DailyDeliveryBean>> response) {
                System.out.println("response.isSuccessful() : "+response.isSuccessful());
                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<DailyDeliveryBean> dailyDeliveryBeanList = response.body();

                System.out.println("(dailyDeliveryBeanList!=null) : "+(dailyDeliveryBeanList!=null));
                if(dailyDeliveryBeanList!=null){
                    System.out.println("(dailyroutesummaryList size : "+(dailyDeliveryBeanList.size()));
                    if(dailyDeliveryBeanList.size()>0) {
                        DailyDeliveryBean dailyroutesummary = dailyDeliveryBeanList.get(0);
                        System.out.println("ROute : "+dailyroutesummary.getRouteCode());
                        routeid.setText(dailyroutesummary.getRouteCode());
                        dailyroutesummaryreport.setText(R.string.dailydeliveryreport);



                    }else{
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                                getActivity());
                        alertDialog.setCancelable(false);
                        alertDialog.setTitle(R.string.Alert);


                        alertDialog
                                .setMessage(R.string.NO_RECORD_FOUND);

                        alertDialog.setNegativeButton(R.string.ok,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        getActivity().finish();
                                        startActivity(getActivity().getIntent());
                                        dialog.cancel();
                                    }
                                });

                        alertDialog.show();
                    }
                }
                // openCustomerLedgerListActivity(customerstringid.getText().toString());
                init(dailyDeliveryBeanList);

            }

            @Override
            public void onFailure(Call<List<DailyDeliveryBean>> call, Throwable t) {
                // public void onFailure(Call<CustomerBean> call, Throwable t) {
                // textViewResult.setText(t.getMessage());
                System.out.println("t.getMessage(): " + t.getMessage());
            }
        });

        //END of calling REST webservice



    }



    public void init(List<DailyDeliveryBean> dailyDeliveryBeanList) {
        TableLayout stk = (TableLayout) getView().findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(getActivity());
        TextView tv0 = new TextView(getActivity());

        tv0.setText(R.string.serial);
        tv0.setText(tv0.getText()+"  ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(getActivity());
        tv1.setText(R.string.customer);
        tv1.setText(tv1.getText()+"  ");
        tv1.setTextColor(Color.WHITE);

        tbrow0.addView(tv1);
        TextView tv2 = new TextView(getActivity());
        tv2.setText(R.string.customerid);
        tv2.setText(tv2.getText()+"  ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(getActivity());
        tv3.setText(R.string.delivery);
        tv3.setText(tv3.getText()+"  ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);


        TextView tv4 = new TextView(getActivity());
        tv4.setText(R.string.return1);
        tv4.setText(tv4.getText()+"  ");
        tv4.setTextColor(Color.WHITE);
        tbrow0.addView(tv4);


        TextView tv5 = new TextView(getActivity());
        tv5.setText(R.string.stock);
        tv5.setText(tv5.getText()+"  ");
        tv5.setTextColor(Color.WHITE);
        tbrow0.addView(tv5);




        TextView tv8 = new TextView(getActivity());
        tv8.setText(R.string.driver1);
        tv8.setText(tv8.getText()+"  ");
        tv8.setTextColor(Color.WHITE);
        tbrow0.addView(tv8);



        stk.addView(tbrow0);

        DailyDeliveryBean dailyDeliveryBean =null;
        for (int i = 0; i < dailyDeliveryBeanList.size(); i++) {
            dailyDeliveryBean = dailyDeliveryBeanList.get(i);
            TableRow tbrow = new TableRow(getActivity());

            final TextView t1v = new TextView(getActivity());

            ++totalCount;
            totalDelivery = totalDelivery + dailyDeliveryBean.getDeliveredCanCount();
            totalReturn = totalReturn + dailyDeliveryBean.getReturnCanCount();
            totalPending = totalPending + dailyDeliveryBean.getPendingCans();


            t1v.setText("" +dailyDeliveryBean.getId());
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            t1v.setEnabled(true);

            t1v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("CLicked : "+t1v.getText());



                    //WEBSERVICE START

                    //  System.out.println("Can Return Daily: " + canDailyReturn.getText() + " : Can Deliver Daily : " + canDailyDeliver.getText()+ " Pending Cans :"+pendingCansWithCustomer.getText());
                    Retrofit retrofit = RetrofitFactory.getRetrofit();
                    JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

                    Call<DailyDeliveryBean> call = jsonPlaceHolderApi.correctDailyDeliveryById(""+t1v.getText());

                    System.out.println("called  correctionDailyDeliveryByIDanddate : ");
                    call.enqueue(new Callback<DailyDeliveryBean>() {
                        @Override
                        public void onResponse(Call<DailyDeliveryBean> call, Response<DailyDeliveryBean> response) {
                            DailyDeliveryBean dailyDeliveryBean = response.body();
                            System.out.println("dailyDeliveryBean != NULL : " + (dailyDeliveryBean != null));
                            if (!response.isSuccessful()) {

                                System.out.println("EZXCEPTION : INSERTED NOT SUCCESFULLY  Date");
                                return;
                            } else {

                                System.out.println("SUCESFULL SCUESFULL FETCH SUCESFULLL : "+dailyDeliveryBean.getPendingCans()+ " Delivery cans : "+dailyDeliveryBean.getDeliveredCanCount());
//Alert Start
                                //WEBSERVICE END

                                int pendingCans = (dailyDeliveryBean.getPendingCans()-dailyDeliveryBean.getDeliveredCanCount()) +dailyDeliveryBean.getReturnCanCount();
                                System.out.println("Inital Pending : "+dailyDeliveryBean.getPendingCans()+"  after correction : "+ pendingCans);
                                CustomerBean customerBean = new CustomerBean();
                                customerBean.setId(dailyDeliveryBean.getCustomerId());
                                customerBean.setPendingCans(pendingCans);
                                customerBean.setFirstName(dailyDeliveryBean.getCustFirstName());
                                customerBean.setLastName(dailyDeliveryBean.getCustLastName());
                                customerBean.setRouteCode(dailyDeliveryBean.getRouteCode());
                                customerBean.setDeliverecans(""+dailyDeliveryBean.getDeliveredCanCount());
                                customerBean.setAddress(dailyDeliveryBean.getCustAddress());
                                customerBean.setCustomerstringid(dailyDeliveryBean.getCustomerstringid());
                                customerBean.setDeliverydate1(dailyDeliveryBean.getTrans_date());
                                customerBean.setMobileNo(dailyDeliveryBean.getCustMobileNo());

                                Bundle bundle = new Bundle();

                                bundle.putSerializable("customerBean", customerBean);
                                bundle.putInt("returnCanCount", dailyDeliveryBean.getReturnCanCount());
                                bundle.putInt("deliveredCanCount",dailyDeliveryBean.getDeliveredCanCount());
                                bundle.putInt("transid",dailyDeliveryBean.getId());

                                Fragment fragment = new CorrectionDelivery();
                                fragment.setArguments(bundle);
                                fillFragment(fragment);


                            }
                        }


                        @Override
                        public void onFailure(Call<DailyDeliveryBean> call, Throwable t) {
                            System.out.println("t.getMessage(): " + t.getMessage());
                        }
                    });


                    //webservice end





                }
            });
            tbrow.addView(t1v);
            final TextView t2v = new TextView(getActivity());
            t2v.setText(" " + dailyDeliveryBean.getCustFirstName()+" "+dailyDeliveryBean.getCustLastName());
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);

            tbrow.addView(t2v);
            TextView t3v = new TextView(getActivity());
            t3v.setText("" + dailyDeliveryBean.getCustomerstringid());
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(getActivity());
            t4v.setText("" + dailyDeliveryBean.getDeliveredCanCount());
            t4v.setTextColor(Color.WHITE);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);


            TextView t9v = new TextView(getActivity());
            t9v.setText("" + dailyDeliveryBean.getReturnCanCount());
            t9v.setTextColor(Color.WHITE);
            t9v.setGravity(Gravity.CENTER);
            tbrow.addView(t9v);



            TextView t5v = new TextView(getActivity());
            t5v.setText("" + dailyDeliveryBean.getPendingCans());
            t5v.setTextColor(Color.WHITE);
            t5v.setGravity(Gravity.CENTER);
            tbrow.addView(t5v);








            TextView t8v = new TextView(getActivity());
            t8v.setText("" + dailyDeliveryBean.getEmpFirstName());
            t8v.setTextColor(Color.WHITE);
            t8v.setGravity(Gravity.CENTER);
            tbrow.addView(t8v);




            stk.addView(tbrow);
        }

        number.setText(R.string.serial);
        number.setText(number.getText()+" : "+totalCount);
        delivery.setText(R.string.delivery);
        delivery.setText(delivery.getText()+" : "+totalDelivery);
        return1.setText(R.string.return1);
        return1.setText(return1.getText()+" : "+totalReturn);
        stock.setText(R.string.stock);
        stock.setText(stock.getText()+" : "+totalPending);

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