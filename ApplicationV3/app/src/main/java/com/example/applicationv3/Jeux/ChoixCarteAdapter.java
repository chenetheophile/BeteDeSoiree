package com.example.applicationv3.Jeux;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationv3.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ChoixCarteAdapter extends RecyclerView.Adapter<ChoixCarteAdapter.CartesHolder> {

    ArrayList<String> noms = new ArrayList<>();
    ArrayList<Integer> img = new ArrayList<>();
    Context ct;
    public static HashMap<String, Integer> numCartes = new HashMap<>();

    public ChoixCarteAdapter(Context ct, ArrayList<String> noms, ArrayList<Integer> img) {
        this.noms = noms;
        this.img = img;
        this.ct = ct;
    }

    @NonNull
    @Override
    public CartesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_choix_cartes, parent, false);
        CartesHolder holder = new CartesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartesHolder holder, int position) {
        holder.nomTextView.setText(noms.get(position));
        holder.carteImageView.setImageResource(img.get(position));

        if (numCartes.containsKey(noms.get(position))) {
            holder.numCartesTextView.setText(String.valueOf(numCartes.get(noms.get(position))));
        }

        holder.moinsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numCartes.containsKey(noms.get(position)) && numCartes.get(noms.get(position)) > 0) {
                    if (numCartes.get(noms.get(position)) == 1) {
                        numCartes.remove(noms.get(position));
                    } else {
                        numCartes.put(noms.get(position), numCartes.get(noms.get(position)) - 1);
                    }
                    holder.numCartesTextView.setText(String.valueOf(numCartes.get(noms.get(position))));
                }
            }
        });
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numCartes.containsKey(noms.get(position))){
                    numCartes.put(noms.get(position), numCartes.get(noms.get(position)) + 1);
                } else {
                    numCartes.put(noms.get(position), 1);
                }
                holder.numCartesTextView.setText(String.valueOf(numCartes.get(noms.get(position))));
            }
        });
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ct, DescCartes.class);
                intent.putExtra("pos", position);
                ct.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noms.size();
    }

    public class CartesHolder extends RecyclerView.ViewHolder {
        TextView nomTextView;
        ImageView carteImageView;
        TextView numCartesTextView;
        ImageButton moinsButton;
        ImageButton plusButton;
        CardView parentLayout;

        public CartesHolder(@NonNull View itemView) {
            super(itemView);
            nomTextView = (TextView) itemView.findViewById(R.id.nomCarteTextView);
            carteImageView = (ImageView) itemView.findViewById(R.id.cartesImageView);
            numCartesTextView = itemView.findViewById(R.id.numCartesTextView);
            moinsButton = itemView.findViewById(R.id.moinsCarteButton);
            plusButton = itemView.findViewById(R.id.plusCarteButton);
            parentLayout = itemView.findViewById(R.id.choixCarteLayout);
        }
    }
}
