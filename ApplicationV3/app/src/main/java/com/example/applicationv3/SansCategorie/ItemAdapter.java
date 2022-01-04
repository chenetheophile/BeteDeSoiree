package com.example.applicationv3.SansCategorie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import com.example.applicationv3.Jeux.Joueurs;
import com.example.applicationv3.Musique.lecturePlaylist;
import com.example.applicationv3.R;
import com.example.applicationv3.Recette.affichage_Recette;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> implements Filterable{

    LayoutInflater mInflater;
    private ArrayList<String> listeNomItem =new ArrayList<>();
    private ArrayList<String> listeDescription =new ArrayList<>();
    private ArrayList<String> listeIngredient =new ArrayList<>();
    private ArrayList<Integer> listeImage =new ArrayList<>();
    private ArrayList<Item> listeRecetteAfficher;
    private ArrayList<Item>listepleine;
    private Context context;


    public Context getContext() {
        return context;
    }

    public ItemAdapter(Context c, ArrayList<Item>recette){
        this.context=c;
        this.listeRecetteAfficher =recette;
        listepleine=new ArrayList<>(recette);
        for(int i=0;i<recette.size();i++){
            listeNomItem.add(recette.get(i).getNom());
            listeIngredient.add(recette.get(i).getIngredient());
            listeDescription.add(recette.get(i).getDescription());
        }
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(mInflater.inflate(R.layout.listview_items, null),viewType);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, @SuppressLint("RecyclerView") int position) {
        if (listeRecetteAfficher.size()==position-1 || listeRecetteAfficher.size()<=position){
            holder.layout.setVisibility(View.GONE);
        }else{
            listeRecetteAfficher.get(position).setFavori(new affichage_Recette().verifier(listeRecetteAfficher.get(position).getNom(),context));

            Item item= listeRecetteAfficher.get(position);
            String nom = item.getNom();
            String detail="";
            if(!item.getType().equalsIgnoreCase("Musique")){
                detail = item.getPrincip();
            }

            String desc = item.getDescription();

            holder.layout.setVisibility(View.VISIBLE);

            holder.nom.setText(nom);
            holder.ingredientPrincipal.setText(detail);
            holder.descriptionRecette.setText(desc);
            Log.i("itemLien",item.getLien());
            if (item.getLien().contains("avec_alcool")){
                holder.imgRecette.setImageResource(R.drawable.avec_alcool);
            }else if (item.getLien().contains("sans_alcool")){
                holder.imgRecette.setImageResource(R.drawable.sans_alcool);
            }else{
                Picasso.get().load(item.getLien()).into(holder.imgRecette);
            }


            holder.layout.setOnClickListener(v -> {
                Intent intent;
                switch (listeRecetteAfficher.get(position).getType()){
                    case "Cocktail":
                    case "Recette":
                        intent=new Intent(context,affichage_Recette.class);
                        intent.putExtra("Recette", listeRecetteAfficher.get(position));
                        break;
                    case "Jeux":
                        intent=new Intent(context, Joueurs.class);
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

            });

        }


    }

    @Override
    public int getItemCount() {
        return listeNomItem.size();
    }


    public Object getItem(int position) {
        return listeNomItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ItemHolder extends RecyclerView.ViewHolder{
        TextView nom;
        TextView ingredientPrincipal;
        TextView descriptionRecette;
        ImageView imgRecette;
        CardView layout;


        public ItemHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomItem);
            ingredientPrincipal = (TextView) itemView.findViewById(R.id.ingredientPrincip);
            descriptionRecette = (TextView) itemView.findViewById(R.id.descItem);
            imgRecette = (ImageView) itemView.findViewById(R.id.imageItem);
            layout=itemView.findViewById(R.id.itemCard);
        }
    }
    @Override
    public Filter getFilter() {
        return filtre;
    }

    private Filter filtre=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Item>listefiltre=new ArrayList<>();
            if (constraint==null ||constraint.length()==0){
                listefiltre.addAll(listepleine);
            }else{
                String lettrefiltre=constraint.toString().toLowerCase().trim();
                for (Item recette:listepleine){
                    if (recette.getNom().toLowerCase().contains(lettrefiltre)||"Favoris".toLowerCase().contains(lettrefiltre)&&recette.isFavori()){
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
            listeRecetteAfficher.clear();
            listeRecetteAfficher.addAll((ArrayList)results.values);

            notifyDataSetChanged();

        }

        private String toString(Item item) {
            String chaine = "";
            chaine += item.getNom();
            return chaine;
        }

    };

}

