package com.example.applicationv3.data;

import android.view.View;
import android.widget.ProgressBar;


public class EmailPassword {

    private static final String TAG = "EmailPassword";
    private boolean etatConn,erreur=false;
    private View window;
    private ProgressBar chargement;

    public EmailPassword(String email, String password, View fenetre, ProgressBar barre) {
        this.etatConn = false;
        this.window = fenetre;
        this.chargement = barre;
    }}