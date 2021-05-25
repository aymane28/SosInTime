package com.example.sosintimea;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;


public class test extends AppCompatActivity {

    private EditText editTextNumber;
    private EditText editTextMessage;
    private Toolbar toolbar;

    private static final int PERMISSIONS_FINE_LOCATION = 99;
    TextView tv_lat, tv_lon, tv_altitude, tv_accuracy, tv_speed, tv_sensor, tv_updates, tv_address;
    Switch sw_locationsupdates, sw_gps;
    LocationRequest locationRequest;
    LocationCallback locationCallBack;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        /*location*/

        //tv_lat = findViewById(R.id.tv_lat);
        //tv_lon = findViewById(R.id.tv_lon);
        //tv_altitude = findViewById(R.id.tv_altitude);
        //tv_accuracy = findViewById(R.id.tv_accuracy);
        //tv_speed = findViewById(R.id.tv_speed);
        tv_sensor = findViewById(R.id.tv_sensor);
        tv_updates = findViewById(R.id.tv_updates);
        tv_address = findViewById(R.id.tv_address);
        sw_gps = findViewById(R.id.sw_gps);
        sw_locationsupdates = findViewById(R.id.sw_locationsupdates);

        tv_address = findViewById(R.id.tv_address);

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000 * 30);
        locationRequest.setFastestInterval(1000 * 5);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationCallBack = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                updateUIValues(locationResult.getLastLocation());
            }
        };

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw_gps.isChecked()) {
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Using GPS sensors");
                } else {
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("Using Towers + WIFI");
                }
            }
        });
        sw_locationsupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw_locationsupdates.isChecked()) {
                    startLocationUpdates();
                } else {
                    stopLocationUpdates();
                }

            }
        });

        updateGPS();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActivityCompat.requestPermissions(test.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        editTextMessage = findViewById(R.id.editText);
        editTextNumber = findViewById(R.id.editTextNumber);
    }


    private void stopLocationUpdates(){
        tv_updates.setText("not tracked");
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);

    }

    private void startLocationUpdates() {
        tv_updates.setText("tracked");

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                }
                else {
                    Toast.makeText(this, "permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
    private  void updateGPS(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(test.this);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    updateUIValues(location);
                }
            });
        }
        else
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }
        }
    }

    private void updateUIValues(Location location) {

        Geocoder geocoder = new Geocoder(test.this);
        try{
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
            tv_address.setText(addresses.get(0).getAddressLine(0));
        }
        catch (Exception e){
            tv_address.setText("No address");
        }
    }


    public void sendSMS(View view){

        String mess=tv_address.getText().toString();
        String message = "Au secours! Je suis en danger, viens aide moi le plutôt possible.";
        String number = editTextNumber.getText().toString();

        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(number,null, mess, null, null);
        startActivity(new Intent(getApplicationContext(),ValidateActivity.class)); //retour à la page de connexion
        finish();
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_Acceuil) {
            Intent aboutIntent = new Intent(test.this, MainActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        if (id == R.id.action_Profil) {
            Intent aboutIntent = new Intent(test.this, ProfilActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        if (id == R.id.action_Paramètres) {
            Intent aboutIntent = new Intent(test.this, ParamètresActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

}