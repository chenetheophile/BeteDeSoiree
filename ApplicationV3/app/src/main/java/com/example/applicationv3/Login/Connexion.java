package com.example.applicationv3.Login;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.applicationv3.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Connexion extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        Button loginButton=findViewById(R.id.login);
        EditText username=findViewById(R.id.username);
        EditText password=findViewById(R.id.password);
        loginButton.setEnabled(false);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>=5){
                    loginButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        loginButton.setOnClickListener(v -> {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            String url="http://bdes.ddns.net/Login/connexion.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                if (response.equalsIgnoreCase("0")){
                    try {
                        FileOutputStream outputStream;
                        String filename="Username";
                        outputStream = getApplicationContext().openFileOutput(filename, Context.MODE_APPEND);
                        outputStream.write(username.getText().toString().getBytes());
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, error -> error.printStackTrace()){@Override

            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("application",String.valueOf(true));
                params.put("login",username.getText().toString());
                params.put("motdepasse",password.getText().toString());
                return params;
            }

            };

            requestQueue.add(stringRequest);
        });
    }
}