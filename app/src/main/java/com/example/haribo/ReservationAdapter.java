package com.example.haribo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Callback;

import static androidx.core.content.ContextCompat.startActivity;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.CustomViewHolder> {
    private List<Reservation> dataList;
    public ReservationAdapter(Context context, List<Reservation> dataList){
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView txtDateDebut;
        TextView txtDateFin;
        TextView txtNom;
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtDateDebut = mView.findViewById(R.id.dateDebut);
            txtDateFin = mView.findViewById(R.id.dateFin);
            txtNom = mView.findViewById(R.id.nom);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.reservation_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtDateDebut.setText(dataList.get(position).getDateDebut().substring(0,10));
        holder.txtDateFin.setText(dataList.get(position).getDateFin().substring(0,10));
        holder.txtNom.setText(dataList.get(position).getIdHabitatIdLocation().getNom());
        holder.txtNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),HabitatActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", dataList.get(position).getIdHabitatIdLocation().getId()); //IdHabitat
                intent.putExtras(b); //Put your id to your next Intent
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
