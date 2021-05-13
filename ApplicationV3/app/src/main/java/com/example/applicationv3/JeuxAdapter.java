package com.example.applicationv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JeuxAdapter extends RecyclerView.Adapter<JeuxAdapter.ViewHolder> implements Filterable {

    LayoutInflater mInflater;
    TextView NomJeuTextView;
    TextView DetailJeuTextView;
    TextView DescJeuTextView ;
    ImageView ObjetImageView ;
    private String[] noms_jeux;
    private String[] joueurs_jeux;
    private String[] equipement_jeux;
    private int[] images_jeux;
    private ArrayList<Recette>listepleine;
    private ArrayList<Recette>listerecette;
    private Context context;

    public  void recetteAdapter(ArrayList<Recette> liste){
        this.listerecette=liste;
        listepleine=new ArrayList<>(liste);
    }
    public JeuxAdapter(Context c, String[] n, String[] j, String[] e, int[] i){
        this.context=c;
        noms_jeux = n;
        joueurs_jeux = j;
        equipement_jeux = e;
        images_jeux = i;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NomJeuTextView = (TextView) parent.findViewById(R.id.NomJeuTextView);
        DetailJeuTextView = (TextView) parent.findViewById(R.id.DetailJeuTextView);
        DescJeuTextView = (TextView) parent.findViewById(R.id.DescJeuTextView);
        ObjetImageView = (ImageView) parent.findViewById(R.id.ObjetImageView);
       return new ViewHolder(mInflater.inflate(R.layout.listview_jeux, null));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nom = noms_jeux[position];
        String detail = joueurs_jeux[position];
        String desc = equipement_jeux[position];
        NomJeuTextView.setText(nom);
        DetailJeuTextView.setText(detail);
        DescJeuTextView.setText(desc);
        ObjetImageView.setImageResource(images_jeux[position]);
    }

    @Override
    public int getItemCount() {
        return noms_jeux.length;
    }

    public Object getItem(int position) {
        return noms_jeux[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return filtre;
    }
    private Filter filtre=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Recette>listefiltre=new ArrayList<>();
            if (constraint==null ||constraint.length()==0){
                listefiltre.addAll(listepleine);
            }else{
                String lettrefiltre=constraint.toString().toLowerCase().trim();
                for (Recette recette:listepleine){
                    if (recette.getNom().toLowerCase().contains(lettrefiltre)){
                        listefiltre.add(recette);
                    }
                }
            }
            FilterResults resultat=new FilterResults();
            resultat.values=listefiltre;
            return resultat;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listerecette.clear();
            listerecette.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
