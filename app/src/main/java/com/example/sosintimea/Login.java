package com.example.sosintimea;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    public static final String EXTRA_MESSAGE="com.example.sosintimea";

    EditText mEmail, mMotDePasse;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.Email);
        mMotDePasse = findViewById(R.id.MotDePasse);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.LoginBtn);
        mCreateBtn = findViewById(R.id.createText);

        Intent intent = null;


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String motdepasse = mMotDePasse.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Veuillez remplir toutes les cases");
                    return;
                }

                if (TextUtils.isEmpty(motdepasse)) {
                    mMotDePasse.setError("Veuillez remplir toutes les cases");
                    return;
                }

                if (motdepasse.length() < 6) {
                    mMotDePasse.setError("Votre mot de passe doit contenir plus de 6 caractères au minimum");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                //vérification de l'utilisateur

                fAuth.signInWithEmailAndPassword(email, motdepasse).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
/*
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            EditText editText=(EditText) findViewById(R.id.mess);
                            String str = editText.getText().toString();
                            intent.putExtra(EXTRA_MESSAGE, str);
                            startActivity(intent);*/
                        }
                        else {
                            Toast.makeText(Login.this, "Erreur !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });


            }
        });


        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class)); // va à la page d'inscription
            }
        });

    }
/*
    public void ChangeActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText=(EditText) findViewById(R.id.mess);
        String str = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, str);
        startActivity(intent);
    }*/
}