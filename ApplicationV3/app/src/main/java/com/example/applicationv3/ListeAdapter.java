package com.example.applicationv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListeAdapter extends RecyclerView.Adapter<ListeAdapter.ListeHolder> {
    private final LayoutInflater mInflater;
    private Context ct;
    private ArrayList<String> liste=new ArrayList<>();

    public ListeAdapter(Context ct, ArrayList<String> plist) {
        this.liste=plist;
        this.ct = ct;
        mInflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ListeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.from(parent.getContext()).inflate(R.layout.listview_liste_proposition, parent, false);
        ListeHolder holder = new ListeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListeHolder holder, int position) {
        holder.num.setText(String.valueOf(position+1));
        holder.contenuListe.setText(liste.get(position).replace("\\n",System.getProperty("line.separator")));
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }


    public class ListeHolder extends RecyclerView.ViewHolder {
        TextView num;
        TextView contenuListe;

        public ListeHolder(@NonNull View itemView) {
            super(itemView);
            this.num=itemView.findViewById(R.id.numListe);
            this.contenuListe=itemView.findViewById(R.id.ContenuListe);
        }
    }
}
