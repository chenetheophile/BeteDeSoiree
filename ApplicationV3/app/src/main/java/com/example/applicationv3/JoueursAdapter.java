package com.example.applicationv3;

import android.app.AppOpsManager;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class JoueursAdapter extends RecyclerView.Adapter<JoueursAdapter.JoueursHolder> {

    ArrayList<Integer> images = new ArrayList<>();
    Context context;

    public JoueursAdapter(Context c, ArrayList<Integer> i) {
        images = i;
        context = c;
    }

    @NonNull
    @Override
    public JoueursHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_joueurs, parent, false);
        JoueursHolder holder = new JoueursHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JoueursHolder holder, int position) {
        holder.nomJoueurEditText.setHint("Nom du j" + String.valueOf(position + 1));
        //holder.nomJoueurEditText.setText(Joueurs.nomsJoueurs.get(position));
        holder.JoueurImageView.setImageResource(images.get(position));
        holder.samCheckBox.setText("SAM");

        holder.nomJoueurEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = Joueurs.nomsJoueurs.get(position);
                if (value != null) {
                    Joueurs.nomsJoueurs.put(position, holder.nomJoueurEditText.getText().toString());
                } else {
                    if (Joueurs.nomsJoueurs.containsKey(position)) {
                        Joueurs.nomsJoueurs.put(position, holder.nomJoueurEditText.getText().toString());
                    } else {
                        Joueurs.nomsJoueurs.put(position, holder.nomJoueurEditText.getText().toString());
                    }
                }
            }
        });
        holder.nomJoueurEditText.setText(Joueurs.nomsJoueurs.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public class JoueursHolder extends RecyclerView.ViewHolder {
        EditText nomJoueurEditText;
        ImageView JoueurImageView;
        CheckBox samCheckBox;

        public JoueursHolder(@NonNull View itemView) {
            super(itemView);
            nomJoueurEditText = itemView.findViewById(R.id.NomJoueurEditText);
            JoueurImageView = itemView.findViewById(R.id.JoueurImageView);
            samCheckBox = itemView.findViewById(R.id.SAMCheckBox);
        }
    }
}
