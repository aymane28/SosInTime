package com.example.sosintimea;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


public class test extends AppCompatActivity {

    private EditText editTextNumber;
    private EditText editTextMessage;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActivityCompat.requestPermissions(test.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        editTextMessage = findViewById(R.id.editText);
        editTextNumber = findViewById(R.id.editTextNumber);
    }

    public void sendSMS(View view){

        String message = "Au secours! Je suis en danger, viens aide moi le plutôt possible.";
        String number = editTextNumber.getText().toString();

        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(number,null, message, null, null);
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