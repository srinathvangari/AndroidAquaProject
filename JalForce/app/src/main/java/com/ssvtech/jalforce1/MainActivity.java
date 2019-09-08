package com.ssvtech.jalforce1;


import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.util.LocaleData;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GetTokenResult;
import com.ssvtech.jalforce1.Utility.LocaleHelper;
import com.ssvtech.jalforce1.Utility.MessageUtility;
import com.ssvtech.jalforce1.Utility.SessionManager;
import com.ssvtech.jalforce1.entity.EmployeeBean;
import com.ssvtech.jalforce1.entity.Route;
import com.ssvtech.jalforce1.entity.EmployeeBean;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {

    private EditText editViewResult;
    private EditText editViewResult1;
    private Button button;




    FirebaseAuth firebaseAuth;
    String usernameLoggedIN = null;

    SessionManager session = null;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final int PERMISSIONS_REQUEST = 1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        System.out.println("UUuuuuuuuuu: " );
        firebaseAuth = FirebaseAuth.getInstance();




        //Start Enable GPS


        // Check GPS is enabled
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Please enable location services", Toast.LENGTH_SHORT).show();
            finish();
        }

        // Check location permission is granted - if it is, start
        // the service, otherwise request the permission
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
           // startTrackerService();

            System.out.println("GRANTED PERMISSION HUrray");
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST);
        }




        session = new SessionManager(getApplicationContext());





        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editViewResult = findViewById(R.id.editText);
                editViewResult1 = findViewById(R.id.editText2);


                String username = editViewResult.getText().toString();
                String pwd = editViewResult1.getText().toString();

                System.out.println("Username 3 : " + editViewResult.getText() + "  pass : " + editViewResult1.getText());






                //for Authentication
                final Task<AuthResult> xyz12345 = firebaseAuth.signInWithEmailAndPassword(username, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {



                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("signInWithEmailAndPassword  :: " + task.isSuccessful());

                        // EXTRA_STRING = task.getResult().getUser().getEmail();
                        if(task.isSuccessful()) {
                            usernameLoggedIN  = task.getResult().getUser().getEmail();
                            // Session Manager
                            System.out.println("Before createUserFirebaseDB "+task.getResult().getUser().getUid());
                           // createUserFirebaseDB(task.getResult().getUser().getUid(), task.getResult().getUser().getEmail());
                           // DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/"+task.getResult().getUser().getUid());
                           // ref.setValue(task.getResult().getUser().getEmail());





                            // Get the transferred data from source activity.

                           Retrofit retrofit = RetrofitFactory.getRetrofit();
                            JsonPlcaeHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlcaeHolderApi.class);

                            Call<List<EmployeeBean>> call = jsonPlaceHolderApi.employeeByEmail(usernameLoggedIN);

                            System.out.println("INNININI  Call<List<Route>> call ");

                            call.enqueue(new Callback<List<EmployeeBean>>() {
                                @Override
                                public void onResponse(Call<List<EmployeeBean>> call, Response<List<EmployeeBean>> response) {

                                    System.out.println("INNININI  Call<List<Route>> onResponse ");

                                    if (!response.isSuccessful()) {
                                        //textViewResult.setText("Code: " + response.code());
                                        System.out.println("INNININI  NOT isSuccessful");
                                        return;
                                    }
                                    List<EmployeeBean> empList = response.body();

                                    for(EmployeeBean emp : empList){
                                        System.out.println("emp first Name "+emp.getFirstName()+ " role : "+emp.getRole()+"  isLocaked : "+emp.getLocked());
                                        if(emp.getLocked().equals("yes")){
                                            System.out.println("Account Locked");
                                            Toast.makeText(getApplicationContext(), "Account Locked " , Toast.LENGTH_LONG).show();
                                        }else{
                                            //  session.createLoginSession(usernameLoggedIN, usernameLoggedIN);
                                            session.createLoginSession(usernameLoggedIN, emp.getRole());



                                            System.out.println("ROle : "+emp.getRole()+" first name : "+emp.getFirstName()+" lastname : "+emp.getLastName());
                                            if(emp.getRole()!=null && emp.getRole().equals("worker")){
                                                createUserFirebaseDB(emp.getFirstName()+" "+emp.getLastName(), "");
                                            }

                                            openActivity2(emp);
                                        }
                                    }



                                    //Start



                                }



                                @Override
                                public void onFailure(Call<List<EmployeeBean>> call, Throwable t) {
                                    // public void onFailure(Call<CustomerBean> call, Throwable t) {
                                    // textViewResult.setText(t.getMessage());
                                    System.out.println(" Exception 88 :: t.getMessage(): " + t.getMessage());
                                }

                            });




                        }else{
                            System.out.println("LOGIN FAILED");
                            Toast.makeText(getApplicationContext(), "LOGIN FAILED " , Toast.LENGTH_LONG).show();

                        }
                    }


                });

            }
        });



        }


    private void createUserFirebaseDB(final String uid, final String email){

        System.out.println("IN createUserFirebaseDB 11 : uid : "+uid);
        LocationRequest request = new LocationRequest();

        request.setInterval(10000);
        request.setFastestInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        System.out.println("IN createUserFirebaseDB 22");
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        System.out.println("IN createUserFirebaseDB 33");
        if (permission == PackageManager.PERMISSION_GRANTED) {
       // if (true) {
            System.out.println("IN createUserFirebaseDB 44");
            // Request location updates and when an update is
            // received, store the location in Firebase
            client.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                   // DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/"+uid);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference(MessageUtility.APPLICATION_NAME+"/"+uid);
                    Location location = locationResult.getLastLocation();

                    System.out.println("IN createUserFirebaseDB 66");
                    if (location != null) {
                        System.out.println("location update " + location);
                        ref.setValue(location);

                      //  ref.setValue(email);
                        System.out.println("IN createUserFirebaseDB 77");


                    }
                }
            }, null);
        }
    }

    public void openActivity2(EmployeeBean employeeBean){
      //  Intent intent = new Intent(this, HomeActivity.class);
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        intent.putExtra("employeeBean", employeeBean);
        startActivity(intent);
    }




}
