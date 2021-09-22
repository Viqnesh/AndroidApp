package com.example.haribo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Callback;

public class HabitatAdapter extends RecyclerView.Adapter<HabitatAdapter.CustomViewHolder> {
    private Habitat dataList;

    public HabitatAdapter(Context context, Habitat dataList){
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView txtNom;
        TextView txtDescription;
        TextView txtPrix;
        TextView txtPays;
        ImageView imgHabitat ;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtNom = mView.findViewById(R.id.nom);
            txtDescription = mView.findViewById(R.id.description);
            txtPrix = mView.findViewById(R.id.prix);
            txtPays = mView.findViewById(R.id.pays);
            imgHabitat = mView.findViewById(R.id.imgHabitat);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_habitat, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtNom.setText(dataList.getNom());
        holder.txtDescription.setText(dataList.getDescription());
        holder.txtPrix.setText(dataList.getPrix());
        holder.txtPays.setText(dataList.getPays());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
