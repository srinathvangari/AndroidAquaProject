package com.ssvtech.jalforce1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private List<Dailydelivery> listData;
    private LayoutInflater layoutInflater;
    public CustomListAdapter(Context aContext, List<Dailydelivery> listData) {
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
        ViewHolder holder;
        if (v == null) {
            v = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.customerId);
            holder.uDesignation = (TextView) v.findViewById(R.id.employeeId);
            holder.uLocation = (TextView) v.findViewById(R.id.returnCanCount);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.uName.setText(""+listData.get(position).getCustFirstName()+" "+listData.get(position).getCustLastName());
        holder.uDesignation.setText(""+listData.get(position).getEmpFirstName()+" "+listData.get(position).getEmpLastName());
        holder.uLocation.setText(""+listData.get(position).getRouteAddress());
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
    }
}