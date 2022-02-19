package com.example.applicationv3.ui.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DialogueConnexion extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage("Voulez-vous vous connecter pour voir vos propositions sur le site ?")
                .setPositiveButton("oui", (dialog, which) -> {
                    Intent inte=new Intent(getContext(), Connexion.class);
                    startActivity(inte);
                } )
                .setNegativeButton("non", (dialog, which) -> {
                    getActivity().finish();
                } )
                .setNeutralButton("Non, ne plus demander",(dialog, which) -> {
                    try {
                        FileOutputStream outputStream;
                        String filename="Options";
                        outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
                        outputStream.write("".getBytes(StandardCharsets.UTF_8));
                        outputStream.flush();
                        outputStream.close();
                        getActivity().finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                })
                .create();
    }

    public static String TAG = "Connexion";
}
