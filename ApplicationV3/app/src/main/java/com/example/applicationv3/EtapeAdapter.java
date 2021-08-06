package com.example.applicationv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EtapeAdapter extends RecyclerView.Adapter<EtapeAdapter.EtapeHolder> {
    private final LayoutInflater mInflater;
    private Context ct;
    private ArrayList<String> etape=new ArrayList<>();

    public EtapeAdapter(Context ct, ArrayList<String> step) {
        this.etape=step;
        this.ct = ct;
        mInflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public EtapeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.from(parent.getContext()).inflate(R.layout.listview_etape, parent, false);
        EtapeHolder holder = new EtapeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EtapeHolder holder, int position) {
        holder.num.setText(String.valueOf(position));
        holder.etape.setText(etape.get(position).replace("\\n",System.getProperty("line.separator")));
    }

    @Override
    public int getItemCount() {
        return etape.size();
    }


    public class EtapeHolder extends RecyclerView.ViewHolder {
        TextView num;
        TextView etape;

        public EtapeHolder(@NonNull View itemView) {
            super(itemView);
            this.num=itemView.findViewById(R.id.numero_etape);
            this.etape=itemView.findViewById(R.id.Etape);

        }
    }
}
