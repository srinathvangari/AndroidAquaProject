package com.ssvtech.jalforce1.adapter;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ssvtech.jalforce1.Dailydelivery;
import com.ssvtech.jalforce1.R;

import com.ssvtech.jalforce1.entity.CustomerBean;


        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

        import java.util.ArrayList;
import java.util.Locale;

public class CustomerListAdapter extends BaseAdapter {
    private List<CustomerBean> listData;
    private LayoutInflater layoutInflater;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date dateobj = new Date();
    private String stringdate=df.format(dateobj);
    private String tempStringdate="";


    CustomerBean customerBean;


    public CustomerListAdapter(Context aContext, List<CustomerBean> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View v, ViewGroup vg) {
        com.ssvtech.jalforce1.adapter.CustomerListAdapter.ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.list_row, null);

            holder = new com.ssvtech.jalforce1.adapter.CustomerListAdapter.ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.customerId);
            holder.uDesignation = (TextView) v.findViewById(R.id.employeeId);
            holder.uLocation = (TextView) v.findViewById(R.id.returnCanCount);
            v.setTag(holder);
        } else {
            holder = (com.ssvtech.jalforce1.adapter.CustomerListAdapter.ViewHolder) v.getTag();
        }
        System.out.println("date from holder : "+listData.get(position).getDeliverydate1());
        v.setBackgroundColor(Color.WHITE);
        if(listData.get(position).getDeliverydate1()!=null) {
            tempStringdate = "" + df.format(listData.get(position).getDeliverydate1());
            if(stringdate.equals(tempStringdate)) {
                v.setBackgroundColor(Color.RED);
            }

        }else{
            tempStringdate = "";
        }
        holder.uName.setText(""+listData.get(position).getCustomerstringid()+" : "+listData.get(position).getFirstName()+" "+listData.get(position).getLastName());
        holder.uDesignation.setText("Stock Cans : "+listData.get(position).getPendingCans()+ "   Delivery Date : "+tempStringdate);
        holder.uLocation.setText("Delivered Cans : "+((listData.get(position).getDeliverecans()==null)?0:listData.get(position).getDeliverecans()));
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
    }




}