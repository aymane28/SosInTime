package com.example.sosintimea;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        Toolbar abouttoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(abouttoolbar);

        ActionBar ab = getSupportActionBar();
        ((ActionBar) ab).setDisplayHomeAsUpEnabled(true);

    }
}