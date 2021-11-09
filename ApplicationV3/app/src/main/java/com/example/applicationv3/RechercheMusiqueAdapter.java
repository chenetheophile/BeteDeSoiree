package com.example.applicationv3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RechercheMusiqueAdapter extends RecyclerView.Adapter<RechercheMusiqueAdapter.MusicHolder> implements Filterable {
    private LayoutInflater mInflater;
    private ArrayList<String> noms=new ArrayList<>();
    private ArrayList<String> auteur=new ArrayList<>();
    private ArrayList<String> duree=new ArrayList<>();
    private ArrayList<String> image=new ArrayList<>();
    private ArrayList<Musique> listeMusique;
    private ArrayList<Musique> listePleine;
    private Context context;

    public RechercheMusiqueAdapter(Context c, ArrayList<Musique> pMusique) {
        this.context=c;
        this.listeMusique=new ArrayList<>(pMusique);
        this.listePleine=new ArrayList<>(pMusique);
        for (Musique msc: listeMusique) {
            noms.add(msc.getName());
            auteur.add(msc.getAuthor());
            duree.add(msc.getDuree());
            image.add(msc.getLienImage());
        }
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("Adapter", "Creating viewHolder");
        return new MusicHolder(mInflater.inflate(R.layout.listview_recherche, null),viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, @SuppressLint("RecyclerView") int position) {
        if (listeMusique.size()==position-1 ||listeMusique.size()<=position){
            holder.layout.setVisibility(View.GONE);
        }else{

            Musique music=listeMusique.get(position);
            String nom = music.getName();
            String duree=music.getDuree();
            String auteur=music.getAuthor();
            String lienIMG=music.getLienImage();
            holder.layout.setVisibility(View.VISIBLE);
            holder.nomMusique.setText(nom);
            holder.dureeMusique.setText(duree);
            holder.nomAuteur.setText(auteur);

            if (!lienIMG.equalsIgnoreCase("")){
                Picasso.get().load(lienIMG).into(holder.imgMusique);
            }

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return listeMusique.size();
    }

    public class MusicHolder extends RecyclerView.ViewHolder {
        TextView nomMusique;
        TextView nomAuteur;
        TextView dureeMusique ;
        ImageView imgMusique ;
        CardView layout;

        public MusicHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            this.layout=itemView.findViewById(R.id.boiteMusic);
            nomAuteur = (TextView) itemView.findViewById(R.id.Auteur);
            nomMusique = (TextView) itemView.findViewById(R.id.TitreMusic);
            dureeMusique = (TextView) itemView.findViewById(R.id.TempsMusique);
            imgMusique = (ImageView) itemView.findViewById(R.id.ImageTitre);
        }
    }

    @Override
    public Filter getFilter() {
        return filtre;
    }

    private final Filter filtre = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.i("Filtre",constraint.toString());
            ArrayList<Musique> listeMusiquefiltre = new ArrayList<>();

            if (constraint==null ||constraint.length()==0){
                listeMusiquefiltre.addAll(listePleine);
            }else{
                String lettrefiltre=constraint.toString().toLowerCase().trim();
                for (Musique music:listePleine){
                    if (music.getName().contains(lettrefiltre)){
                        listeMusiquefiltre.add(music);
                    }
                }
            }

            FilterResults resultat = new FilterResults();
            resultat.values=listeMusiquefiltre;
            return resultat;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();

        }
    };


}

