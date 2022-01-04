package com.example.applicationv3.Jeux;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationv3.Musique.lecturePlaylist;
import com.example.applicationv3.R;
import com.example.applicationv3.Recette.affichage_Recette;
import com.example.applicationv3.SansCategorie.Item;

import java.util.ArrayList;

public class JeuxAdapter extends RecyclerView.Adapter<JeuxAdapter.JeuxHolder>{

    LayoutInflater mInflater;
    private ArrayList<String> noms_jeux=new ArrayList<>();
    private ArrayList<String> joueurs_jeux=new ArrayList<>();
    private ArrayList<String> equipement_jeux=new ArrayList<>();
    private ArrayList<Integer> images_jeux=new ArrayList<>();
    private ArrayList<Item>recipe;
    private ArrayList<Item>listepleine;
    private Context context;


    public Context getContext() {
        return context;
    }

    public JeuxAdapter(Context c, ArrayList<Item>recette , ArrayList<Integer> img){
        this.context=c;
        this.recipe=recette;
        listepleine=new ArrayList<>(recette);
        for(int i=0;i<recette.size();i++){
            noms_jeux.add(recette.get(i).getNom());
            joueurs_jeux.add(recette.get(i).getIngredient());
            equipement_jeux.add(recette.get(i).getDescription());
            images_jeux.addAll(img);
        }
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public JeuxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JeuxHolder(mInflater.inflate(R.layout.listview_jeux, null),viewType);

    }

    @Override
    public void onBindViewHolder(@NonNull JeuxHolder holder, @SuppressLint("RecyclerView") int position) {
        if (recipe.size()==position-1 ||recipe.size()<=position){
            holder.layout.setVisibility(View.GONE);
        }else{
            recipe.get(position).setFavori(new affichage_Recette().verifier(recipe.get(position).getNom(),context));

            Item item=recipe.get(position);
            String nom = item.getNom();
            String detail="";
            if(!item.getType().equalsIgnoreCase("Musique")){
                detail = item.getPrincip();
            }

            String desc = item.getDescription();

            holder.layout.setVisibility(View.VISIBLE);

            holder.NomJeuTextView.setText(nom);
            holder.DetailJeuTextView.setText(detail);
            holder.DescJeuTextView.setText(desc);
            holder.ObjetImageView.setImageResource(images_jeux.get(position));

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    switch (recipe.get(position).getType()){
                        case "Cocktail":
                        case "Recette":
                            intent=new Intent(context,affichage_Recette.class);
                            intent.putExtra("Recette",recipe.get(position));
                            break;
                        case "Jeux":
                            intent=new Intent(context,Joueurs.class);
                            intent.putExtra("IdJeu",position);
                            break;
                        case "Musique":
                            intent=new Intent(context, lecturePlaylist.class);
                            intent.putExtra("Playlist",item);
                            break;
                        default:
                            intent=null;
                            break;

                    }
                    context.startActivity(intent);

                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return noms_jeux.size();
    }


    public Object getItem(int position) {
        return noms_jeux.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class JeuxHolder extends RecyclerView.ViewHolder{
        TextView NomJeuTextView;
        TextView DetailJeuTextView;
        TextView DescJeuTextView ;
        ImageView ObjetImageView ;
        CardView layout;


        public JeuxHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            NomJeuTextView = (TextView) itemView.findViewById(R.id.NomJeuTextView);
            DetailJeuTextView = (TextView) itemView.findViewById(R.id.DetailJeuTextView);
            DescJeuTextView = (TextView) itemView.findViewById(R.id.DescJeuTextView);
            ObjetImageView = (ImageView) itemView.findViewById(R.id.ObjetImageView);
            layout=itemView.findViewById(R.id.boiteItem);
        }
    }



}

