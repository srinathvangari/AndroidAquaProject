package com.ssvtech.jalforce1.adapter;



import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ssvtech.jalforce1.Dailydelivery;
import com.ssvtech.jalforce1.R;

import com.ssvtech.jalforce1.Utility.DateUtility;
import com.ssvtech.jalforce1.entity.Route;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import java.util.ArrayList;

public class RouteListAdapter extends BaseAdapter {
    private List<Route> listData;
    private LayoutInflater layoutInflater;

    Route route;


    public RouteListAdapter(Context aContext, List<Route> listData) {
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
        holder.uName.setText("Route : "+listData.get(position).getRouteCode());
        holder.uDesignation.setText("Address : "+listData.get(position).getRouteAddress());
        holder.uLocation.setText(""+ listData.get(position).getUpdatedby());
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
    }




}