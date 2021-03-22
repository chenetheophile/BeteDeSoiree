package com.example.appinnov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

        setContentView(R.layout.activity_main);
        Button GoButton = (Button) findViewById(R.id.GoButton);
        EditText NbJoueursEditText = (EditText) findViewById(R.id.NbJoueursEditText);
        CheckBox CartesCheckBox = (CheckBox) findViewById(R.id.CartesCheckBox);
        CheckBox DesCheckBox = (CheckBox) findViewById(R.id.DesCheckBox);
        boolean cartes = CartesCheckBox.isChecked();
        boolean des = DesCheckBox.isChecked();
        GoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int NbJoueurs;
                if (NbJoueursEditText.getText().toString().equalsIgnoreCase("")) {
                    NbJoueurs = 0;
                } else {
                    NbJoueurs = Integer.parseInt(NbJoueursEditText.getText().toString());
                }
                if (NbJoueurs > 1 && NbJoueurs < 21) {
                    Intent startIntent = new Intent(getApplicationContext(), lol.class);
                    startIntent.putExtra("NbJoueurs", NbJoueurs);
                    startIntent.putExtra("cartes", cartes);
                    startIntent.putExtra("des", des);
                    startActivity(startIntent);
                } else if (NbJoueurs < 2){
                    Snackbar.make(v, "Entrez un nombre de joueurs supérieur à 1", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (NbJoueurs > 10){
                    Snackbar.make(v, "Entrez un nombre de joueurs inférieur à 20", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    public void updateUI(FirebaseUser account) {

        if (account != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "U Didnt signed in", Toast.LENGTH_LONG).show();
        }

    }
}