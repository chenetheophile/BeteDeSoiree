package com.example.applicationv3;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class LGChoixAdapter extends RecyclerView.Adapter<LGChoixAdapter.LGHolder> {

    ArrayList<String> roles;
    ArrayList<String> joueurs;
    ArrayList<Integer> images;
    ArrayList<Boolean> checked;
    Context context;
    int prev = -1;

    public LGChoixAdapter(ArrayList<String> roles, ArrayList<String> joueurs, ArrayList<Integer> images, ArrayList<Boolean> checked, Context context) {
        this.roles = roles;
        this.joueurs = joueurs;
        this.context = context;
        this.images = images;
        this.checked = checked;
    }

    @NonNull
    @Override
    public LGHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_choix_l_g, parent, false);
        return new LGHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LGHolder holder, int position) {
        System.out.println(roles.size());
        holder.roleTextView.setText(roles.get(position));
        holder.joueurTextView.setText(joueurs.get(position));
        holder.choixImageView.setImageResource(images.get(position));
        holder.choixButton.setChecked(checked.get(position));
        holder.choixButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (prev >= 0) {
                        checked.set(prev, false);
                    }
                    checked.set(position, true);
                    prev = position;
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (checked.get(position)) {
            return 1;
        } else {
            return 0;
        }
    }

    public class LGHolder extends RecyclerView.ViewHolder {
        TextView joueurTextView, roleTextView;
        ImageView choixImageView;
        CardView parentLayout;
        RadioButton choixButton;


        public LGHolder(@NonNull View itemView) {
            super(itemView);
            joueurTextView = itemView.findViewById(R.id.choixJoueurTextView);
            roleTextView = itemView.findViewById(R.id.choixRoleTextView);
            choixImageView = itemView.findViewById(R.id.choixImageView);
            parentLayout = itemView.findViewById(R.id.choixCardView);
            choixButton = itemView.findViewById(R.id.choixRadioButton);
        }
    }
}
