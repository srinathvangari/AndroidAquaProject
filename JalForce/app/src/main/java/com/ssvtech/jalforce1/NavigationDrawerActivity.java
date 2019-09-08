package com.ssvtech.jalforce1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.fragment.DailyDeliverRouteListFragment;
import com.ssvtech.jalforce1.fragment.DailyDeliveryReportRouteListFragment;
import com.ssvtech.jalforce1.fragment.HomeFragment;
import com.ssvtech.jalforce1.fragment.ImportFragment;
import com.ssvtech.jalforce1.fragment.LedgerCustomerSearchFragment;
import com.ssvtech.jalforce1.fragment.RoutePlanFragment;
import com.ssvtech.jalforce1.fragment.SummaryFragment;
import com.ssvtech.jalforce1.fragment.TripPlannerFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.TextView;

import java.util.HashMap;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView headerTitle;
    SessionManager session =null;
    Fragment fragment =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        String role = session.getUserDetails().get("role");
        System.out.println("IN NAVIGATION DRAWER FRAGMENT role : "+role);


        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_search).setVisible(false);

        if(role!=null && role.equals("worker")) {
            hideItem();
        }

        fragment = new HomeFragment();
        fillFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        /*session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();


        String name = user.get(SessionManager.KEY_NAME);
        headerTitle = findViewById(R.id.headerTitle);
        if(headerTitle!=null) {
            headerTitle.setText("Hello Srinath");
        }*/

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

       return true;

       //return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            System.out.println("Dashnoard");
            fragment = new HomeFragment();
            // Handle the camera action
        } else if (id == R.id.nav_delivery) {
            System.out.println("nav_delivery");
            fragment = new DailyDeliverRouteListFragment();
        } else if (id == R.id.nav_livetracking) {
            System.out.println("nav_livetracking");
        } else if (id == R.id.nav_payment) {
            alertComingSoon();
            System.out.println("nav_payment");
        } else if (id == R.id.nav_routeplan) {
            System.out.println("nav_routeplan");
            fragment = new RoutePlanFragment();
        } else if (id == R.id.nav_logout) {
            System.out.println("nav_logout");
            session.logoutUser();
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
        }else if (id == R.id.nav_tripAction) {
            fragment = new TripPlannerFragment();
        }else if (id == R.id.nav_report) {
            fragment = new DailyDeliveryReportRouteListFragment();
        }else if (id == R.id.nav_ledger) {
            fragment = new LedgerCustomerSearchFragment();
        }else if (id == R.id.nav_summary) {
            fragment = new SummaryFragment();
        }else if (id == R.id.nav_countersale) {
            alertComingSoon();
        }else if (id == R.id.customersearchbyid) {
            alertComingSoon();
        }



        fillFragment(fragment);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void fillFragment(Fragment fragment){
        if(fragment!=null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }
    }



    private void hideItem()
    {
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_payment).setVisible(false);
        nav_Menu.findItem(R.id.nav_livetracking).setVisible(false);
        nav_Menu.findItem(R.id.nav_routeplan).setVisible(false);
        nav_Menu.findItem(R.id.nav_report).setVisible(false);
        nav_Menu.findItem(R.id.nav_search).setVisible(false);
        nav_Menu.findItem(R.id.nav_summary).setVisible(false);
        nav_Menu.findItem(R.id.nav_tripAction).setVisible(false);
        nav_Menu.findItem(R.id.nav_countersale).setVisible(false);

    }


    private void alertComingSoon(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                this);
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
