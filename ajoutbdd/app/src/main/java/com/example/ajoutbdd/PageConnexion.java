package com.example.ajoutbdd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PageConnexion extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private String textEmail="";
    private String textPassword="";

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.ui_page_connexion);

        Button Login=findViewById(R.id.connexion);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText();
                signIn();
                startActivity(new Intent(getApplicationContext(),premierePage.class));
            }
        });
        Button SignIn=findViewById(R.id.inscription);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText();
                createAccount();
                startActivity(new Intent(getApplicationContext(),premierePage.class));
            }
        });

    }
    private void createAccount(){
    mAuth.createUserWithEmailAndPassword(getTextEmail(), getTextPassword())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(PageConnexion.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        }
    });}

    private void signIn(){
        mAuth.signInWithEmailAndPassword(getTextEmail(), getTextPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(PageConnexion.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    private void updateUI(FirebaseUser user) {

    }
    private void updateText(){
        EditText log=findViewById(R.id.login);
        setTextEmail(log.getText().toString());
        EditText pwd=findViewById(R.id.password);
        setTextPassword(pwd.getText().toString());
    }

    public void setTextEmail(String textEmail) {
        this.textEmail = textEmail;
    }

    public void setTextPassword(String textPassword) {
        this.textPassword = textPassword;
    }
    public String getTextPassword() {
        return textPassword;
    }

    public String getTextEmail() {
        return textEmail;
    }
}