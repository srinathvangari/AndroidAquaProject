package com.ssvtech.jalforce1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ssvtech.jalforce1.R;
import com.ssvtech.jalforce1.entity.Route;

import java.util.List;

public class TripActionAdapter extends BaseAdapter {

    private List<Route> listData;
    private LayoutInflater layoutInflater;

    Route route;


    public TripActionAdapter(Context aContext, List<Route> listData) {
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

        if(listData.get(position).getIsroutestarted().equals("no")) {

            holder.uName.setText("Start Trip of Route : "+listData.get(position).getRouteCode());
            holder.uDesignation.setText("Is Route Trip Started ? : " + listData.get(position).getIsroutestarted());
            holder.uLocation.setText(" ");
        }else {

            holder.uName.setText("End Trip of Route : "+listData.get(position).getRouteCode());
            holder.uDesignation.setText("Is Route Trip Started ?  : " + listData.get(position).getIsroutestarted());
            holder.uLocation.setText("Updated By " + listData.get(position).getUpdatedby());
        }if(listData.get(position).getEndtime()!=null) {
            v.setBackgroundColor(16711680);
        }
        return v;
    }
    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
    }

}
