package com.example.applicationv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RechercheMusiqueAdapter extends RecyclerView.Adapter<RechercheMusiqueAdapter.MusicHolder> implements Filterable {
    LayoutInflater mInflater;
    String token;

    public void setToken(String token) {
        this.token = token;
    }

    public RechercheMusiqueAdapter(Context c) {
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicHolder(mInflater.inflate(R.layout.listview_recherche, null),viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MusicHolder extends RecyclerView.ViewHolder {
        public MusicHolder(@NonNull View itemView, int viewType) {
            super(itemView);
        }
    }

    @Override
    public Filter getFilter() {
        return filtre;
    }

    private Filter filtre = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Musique> listeMusique=new ArrayList<>();
            String Url= "https://api.spotify.com/v1/search?q="+constraint.toString().toLowerCase().trim()+"&type=track\" -H \"Accept: application/json\"\n                    -H \"Content-Type: application/json\" -H\n        \"Authorization: Bearer "+token;

            FilterResults resultat = new FilterResults();
            return resultat;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();

        }
    };
}

