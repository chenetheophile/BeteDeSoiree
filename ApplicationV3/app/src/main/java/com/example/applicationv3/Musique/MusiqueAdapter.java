package com.example.applicationv3.Musique;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.example.applicationv3.R;
import com.example.applicationv3.SansCategorie.MenuActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class MusiqueAdapter extends RecyclerView.Adapter<MusiqueAdapter.MusicHolder> implements Filterable {
    private LayoutInflater mInflater;
    private ArrayList<String> noms=new ArrayList<>();
    private ArrayList<String> auteur=new ArrayList<>();
    private ArrayList<String> duree=new ArrayList<>();
    private ArrayList<String> image=new ArrayList<>();
    private ArrayList<Musique> listeMusiqueAffiche;
    private ArrayList<Musique> listePleine;
    private Context context;

    public MusiqueAdapter(Context c, ArrayList<Musique> pMusique) {
        this.context=c;
        this.listeMusiqueAffiche =new ArrayList<>(pMusique);
        this.listePleine=new ArrayList<>(pMusique);
        for (Musique msc: listeMusiqueAffiche) {
            noms.add(msc.getName());
            auteur.add(msc.getAuthor());
            duree.add(msc.getDuree());
            image.add(msc.getLienImage());
        }
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void updateData(Musique data,boolean add) {
        if(add){
            listeMusiqueAffiche.add(data);
            if (listeMusiqueAffiche.get(0).getDuree().equalsIgnoreCase("")&&!data.getDuree().equalsIgnoreCase("")){
                listeMusiqueAffiche.remove(0);
            }
        }else{
            listeMusiqueAffiche.remove(0);
            if (listeMusiqueAffiche.size()==0){
                updateData(new Musique("Aucune musique","","","",""),true);
            }
        }

        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicHolder(mInflater.inflate(R.layout.listview_musique, null),viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHolder holder, @SuppressLint("RecyclerView") int position) {
        if (listeMusiqueAffiche.size()==position-1 || listeMusiqueAffiche.size()<=position){
            holder.layout.setVisibility(View.GONE);
        }else{

            Musique music= listeMusiqueAffiche.get(position);
            String nom = music.getName();
            String duree=music.getDuree();
            String auteur=music.getAuthor();
            String lienIMG=music.getLienImage();
            holder.layout.setVisibility(View.VISIBLE);
            holder.nomMusique.setText(nom);
            holder.dureeMusique.setText(duree);
            holder.nomAuteur.setText(auteur);
            if (context.getClass().equals(MenuActivity.class)){
                holder.nomAuteur.setTextSize(14);
                holder.nomAuteur.setPadding(0,0,0,holder.nomMusique.getTotalPaddingTop()+30);
            }

            if (!lienIMG.equalsIgnoreCase("")){
                Picasso.get().load(lienIMG).into(holder.imgMusique);
            }

            holder.layout.setOnClickListener(v -> {

                if (context.getClass().equals(SearchSongsActivity.class)){
                    Intent result=new Intent();
                    Musique data= listeMusiqueAffiche.get(holder.getAdapterPosition());
                    result.putExtra("Musique", data);
                    ((Activity)context).setResult(Activity.RESULT_OK,result);
                    ((Activity) context).finish();
                }else if(context.getClass().equals(PlaylistPerso.class)){
                    if (!nom.contains("Aucune musique")){
                        Intent intent=new Intent(context,lecturePlaylist.class);
                        intent.putExtra("lecture",true);
                        context.startActivity(intent);
                    }
                }else if(context.getClass().equals(MenuActivity.class)){
                    Intent intent=new Intent(context, lecturePlaylist.class);
                    intent.putExtra("Playlist", listeMusiqueAffiche.get(position));
                    intent.putExtra("lecture",false);
                    context.startActivity(intent);
                }

            });

        }


    }

    @Override
    public int getItemCount() {
        return listeMusiqueAffiche.size();
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
            nomAuteur = itemView.findViewById(R.id.Auteur);
            nomMusique =  itemView.findViewById(R.id.TitreMusic);
            dureeMusique =  itemView.findViewById(R.id.TempsMusique);
            imgMusique =  itemView.findViewById(R.id.ImageTitre);
        }
    }

    @Override
    public Filter getFilter() {
        return filtre;
    }

    private final Filter filtre = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Musique> listeMusiquefiltre = new ArrayList<>();
            if (constraint==null ||constraint.length()==0){
                listeMusiquefiltre.addAll(listePleine);
            }else{
                String lettrefiltre=constraint.toString().toLowerCase().trim();
                for (Musique music:listePleine){

                    if (music.getName().toLowerCase(Locale.ROOT).contains(lettrefiltre.toLowerCase(Locale.ROOT))){
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
            listeMusiqueAffiche.clear();
            listeMusiqueAffiche.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };

}

