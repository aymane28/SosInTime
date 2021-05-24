package com.example.sosintimea;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;




public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        VideoView videoView =findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.vido;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    public void deconnexion(View view) {
        FirebaseAuth.getInstance().signOut(); //déconnexion
        startActivity(new Intent(getApplicationContext(),Login.class)); //retour à la page de connexion
        finish();
    }

    public void send_sms(View view) {
        FirebaseAuth.getInstance().signOut(); //déconnexion
        startActivity(new Intent(getApplicationContext(),test.class)); //retour à la page de connexion
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_Acceuil) {
            Intent aboutIntent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        if (id == R.id.action_Profil) {
            Intent aboutIntent = new Intent(MainActivity.this, ProfilActivity.class);
            startActivity(aboutIntent);
            return true;
        }
        if (id == R.id.action_Paramètres) {
            Intent aboutIntent = new Intent(MainActivity.this, ParamètresActivity.class);
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