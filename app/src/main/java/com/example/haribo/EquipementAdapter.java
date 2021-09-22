package com.example.haribo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquipementAdapter extends RecyclerView.Adapter<EquipementAdapter.CustomViewHolder>  {
    private List<Equipement> dataList;

    public EquipementAdapter(Context context, List<Equipement> dataList){
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView txtTitre;
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtTitre = mView.findViewById(R.id.nomServ);
        }
    }

    @Override
    public EquipementAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.equipement_row, parent, false);
        return new EquipementAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EquipementAdapter.CustomViewHolder holder, int position) {
        holder.txtTitre.setText(dataList.get(position).getNom());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
