package com.example.applicationv3.SansCategorie;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationv3.R;

import java.util.ArrayList;

public class ListeAdapter extends RecyclerView.Adapter<ListeAdapter.ListeHolder> {
    private final LayoutInflater mInflater;
    private Context ct;
    private ArrayList<String> liste;

    public ListeAdapter(Context ct, ArrayList<String> plist) {
        this.liste=plist;
        this.ct = ct;
        mInflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<String> getListe() {
        return liste;
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
        holder.contenuListe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                liste.set(holder.getAdapterPosition(),s.toString().trim());
            }
        });
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }


    public class ListeHolder extends RecyclerView.ViewHolder {
        TextView num;
        EditText contenuListe;

        public ListeHolder(@NonNull View itemView) {
            super(itemView);
            this.num=itemView.findViewById(R.id.numListe);
            this.contenuListe=itemView.findViewById(R.id.ContenuListe);
        }
    }
}
