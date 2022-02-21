package com.example.applicationv3.Jeux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationv3.R;

import java.util.ArrayList;

public class CartePalmierAdapter extends RecyclerView.Adapter<CartePalmierAdapter.CartePalmierHolder> {

    private Context context;
    private ArrayList<Integer> images;
    private ArrayList<String> desc;

    public CartePalmierAdapter(Context context, ArrayList<Integer> images, ArrayList<String> desc) {
        this.context = context;
        this.images = images;
        this.desc = desc;
    }

    @NonNull
    @Override
    public CartePalmierHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_cartes_palmier, parent, false);
        CartePalmierHolder holder = new CartePalmierHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartePalmierHolder holder, int position) {
        holder.imageCarte.setImageResource(images.get(position));
        holder.descCarte.setText(desc.get(position));
    }

    @Override
    public int getItemCount() {
        return desc.size();
    }

    public class CartePalmierHolder extends RecyclerView.ViewHolder {

        ImageView imageCarte;
        TextView descCarte;

        public CartePalmierHolder(@NonNull View itemView) {
            super(itemView);
            imageCarte = itemView.findViewById(R.id.cartePalmierImageView);
            descCarte = itemView.findViewById(R.id.descCartePalmierTextView);
        }
    }
}
