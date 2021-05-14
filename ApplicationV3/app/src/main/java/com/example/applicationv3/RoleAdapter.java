package com.example.applicationv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.RoleHolder> {
    ArrayList<String> nomsJoueurs = new ArrayList<>();
    ArrayList<String> nomsRoles = new ArrayList<>();
    ArrayList<Integer> img = new ArrayList<>();
    Context ct;

    public RoleAdapter(Context ct, ArrayList<String> noms, ArrayList<Integer> img, ArrayList<String> nomsRoles) {
        this.nomsJoueurs = noms;
        this.img = img;
        this.nomsRoles = nomsRoles;
        this.ct = ct;
    }

    @NonNull
    @Override
    public RoleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_roles, parent, false);
        RoleHolder holder = new RoleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoleHolder holder, int position) {
        holder.roleTextView.setText(nomsRoles.get(position));
        holder.roleImageView.setImageResource(img.get(position));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ct, android.R.layout.simple_spinner_item, nomsJoueurs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.roleSpinner.setAdapter(adapter);
        holder.roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                AttributionLG.roles.put(nomsRoles.get(position), nomsJoueurs.get(pos));
                System.out.println("changed");
                for (Object value : AttributionLG.roles.values().toArray()) {
                    System.out.println(value);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return nomsRoles.size();
    }

    public class RoleHolder extends RecyclerView.ViewHolder {

        TextView roleTextView;
        ImageView roleImageView;
        Spinner roleSpinner;

        public RoleHolder(@NonNull View itemView) {
            super(itemView);
            roleTextView = itemView.findViewById(R.id.nomRoleTextView);
            roleImageView = itemView.findViewById(R.id.roleImageView);
            roleSpinner = itemView.findViewById(R.id.roleSpinner);
        }
    }
}
