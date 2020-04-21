package com.example.infocovid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NegaraAdapter extends RecyclerView.Adapter<NegaraAdapter.NegaraViewHolder> {

    private ArrayList<NegaraItem> mNegaraList;

    public static class NegaraViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_negara, tv_positif, tv_sembuh, tv_meninggal;

        public NegaraViewHolder(View itemView) {
            super(itemView);
            tv_negara = itemView.findViewById(R.id.tv_negara);
            tv_positif = itemView.findViewById(R.id.tv_positif_negara);
            tv_sembuh = itemView.findViewById(R.id.tv_sembuh_negara);
            tv_meninggal = itemView.findViewById(R.id.tv_meniggal_negara);
        }
    }

    public NegaraAdapter(ArrayList<NegaraItem> negaraList){
        mNegaraList  = negaraList;
    }

    @NonNull
    @Override
    public NegaraAdapter.NegaraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasus, parent, false);
        NegaraViewHolder nvh = new NegaraViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NegaraViewHolder holder, int position) {

        NegaraItem currentItem = mNegaraList.get(position);

        holder.tv_negara.setText(currentItem.getNamaNegara());
        holder.tv_positif.setText(currentItem.getNegaraPositif());
        holder.tv_sembuh.setText(currentItem.getNegaraSembuh());
        holder.tv_meninggal.setText(currentItem.getNegaraMeninggal());
    }

    @Override
    public int getItemCount() {
        return mNegaraList.size();
    }


}
