package com.example.applicationv3.data;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.applicationv3.BDD;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


public class EmailPassword {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private boolean etatConn,erreur=false;
    private View window;
    private ProgressBar chargement;

    public EmailPassword(String email, String password, View fenetre, ProgressBar barre){
        this.mAuth = FirebaseAuth.getInstance();
        this.etatConn=false;
        this.window=fenetre;
        this.chargement=barre;
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){//regarde si on est deja connecte
            setEtatConn(true);
            window.setVisibility(View.GONE);
        }else{
            signIn(email,password);
        }
    }
    public boolean getError(){
        return this.erreur;
    }
    public void setError(boolean err){
        this.erreur=err;
    }
    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            signIn(email,password);
                        } else {
                            Toast.makeText(window.getContext(),"Erreur d'inscription",Toast.LENGTH_LONG).show();
                            setError(true);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    public FirebaseUser getUser() {
        FirebaseUser usr=mAuth.getCurrentUser();
        mAuth.signOut();
        return usr;

    }
    private void signIn(String email, String password) {//check si l email et le mdp correspondent a un compte dans la db si oui ca connecte sinon ca cree un nouveau compte
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                                new BDD(getUser(),chargement).getDocument(window.getContext());//une fois connecter passe a la suite de l'app
                        } else {
                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                            switch (errorCode){
                                case "ERROR_INVALID_EMAIL":
                                    Toast.makeText(window.getContext(), "Adresse mail non valide", Toast.LENGTH_LONG).show();
                                    setError(true);
                                    break;
                                case "ERROR_WRONG_PASSWORD":
                                    Toast.makeText(window.getContext(), "Mot de passe ou adresse mail non valide", Toast.LENGTH_LONG).show();
                                    setError(true);
                                    break;
                                default:
                                    createAccount(email,password);
                                    break;
                            }
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void reload() {
        Log.d(TAG, "already connect");

    }
    public boolean getEtat(){
        return this.etatConn;
    }

    public void setEtatConn(boolean etatConn) {
        if (etatConn){
            window.setVisibility(View.GONE);
        }
        this.etatConn = etatConn;
    }

    private void updateUI(FirebaseUser user) {

    }
}