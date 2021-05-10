package com.example.applicationv3.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationv3.BDD;
import com.example.applicationv3.R;
import com.example.applicationv3.data.EmailPassword;

public class LoginFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText usernameEditText = view.findViewById(R.id.username);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final Button loginButton = view.findViewById(R.id.login);
        final ImageButton fermer=view.findViewById(R.id.fermeLogin);

        fermer.setOnClickListener(new View.OnClickListener() {//si on click sur la fleche ferme la fenetre des param
            @Override
            public void onClick(View v) {
                view.setVisibility(View.GONE);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {//si appui sur le bouton va connecter l'user/lui creer un compte
            @Override
            public void onClick(View v) {
                EmailPassword Connexion=new EmailPassword(usernameEditText.getText().toString(),passwordEditText.getText().toString(),getView());
                if (Connexion.getEtat()){
                    new BDD(Connexion.getUser()).getDocument(getActivity());//une fois connecter passe a la suite de l'app
                }
            }
        });
    }
}