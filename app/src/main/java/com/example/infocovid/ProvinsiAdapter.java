package com.example.infocovid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProvinsiAdapter extends RecyclerView.Adapter<ProvinsiAdapter.ProvinsiViewHolder> {

    private ArrayList<ProvinsiItem> mProvinsiList;

    public static class ProvinsiViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_negara, tv_positif, tv_sembuh, tv_meninggal;

        public ProvinsiViewHolder(View itemView){
            super(itemView);

            tv_negara = itemView.findViewById(R.id.tv_negara);
            tv_positif = itemView.findViewById(R.id.tv_positif_negara);
            tv_sembuh = itemView.findViewById(R.id.tv_sembuh_negara);
            tv_meninggal = itemView.findViewById(R.id.tv_meniggal_negara);
        }
    }

    public ProvinsiAdapter (ArrayList<ProvinsiItem> provinsiList){
        mProvinsiList  = provinsiList;
    }

    @NonNull
    @Override
    public ProvinsiAdapter.ProvinsiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasus_idn, parent, false);
        ProvinsiViewHolder pvh = new ProvinsiViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinsiViewHolder holder, int position) {

        ProvinsiItem currentItem = mProvinsiList.get(position);

        holder.tv_negara.setText(currentItem.getNamaProvinsi());
        holder.tv_positif.setText(currentItem.getProvinsiPositif());
        holder.tv_sembuh.setText(currentItem.getProvinsiSembuh());
        holder.tv_meninggal.setText(currentItem.getProvinsiMeninggal());
    }

    @Override
    public int getItemCount() {
        return mProvinsiList.size();
    }

}
