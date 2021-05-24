package com.example.sosintimea;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class ParamètresActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void deconnexion(View view) {
        FirebaseAuth.getInstance().signOut(); //déconnexion
        startActivity(new Intent(getApplicationContext(),Login.class)); //retour à la page de connexion
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_Acceuil) {
            Intent aboutIntent = new Intent(ParamètresActivity.this, MainActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        if (id == R.id.action_Profil) {
            Intent aboutIntent = new Intent(ParamètresActivity.this, ProfilActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        if (id == R.id.action_Paramètres) {
            Intent aboutIntent = new Intent(ParamètresActivity.this, ParamètresActivity.class);
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
