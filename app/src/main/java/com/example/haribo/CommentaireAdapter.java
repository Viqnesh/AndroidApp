package com.example.haribo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentaireAdapter extends RecyclerView.Adapter<CommentaireAdapter.CustomViewHolder>  {
    private List<Commentaire> dataList;

    public CommentaireAdapter(Context context, List<Commentaire> dataList){
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView txtPrenom;
        TextView txtNom;
        TextView txtContenu;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtPrenom = mView.findViewById(R.id.prenom);
            txtNom = mView.findViewById(R.id.nom);
            txtContenu = mView.findViewById(R.id.contenu);

        }
    }

    @Override
    public CommentaireAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.comment_row, parent, false);
        return new CommentaireAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentaireAdapter.CustomViewHolder holder, int position) {
        holder.txtNom.setText(dataList.get(position).getUser().getNom());
        holder.txtPrenom.setText(dataList.get(position).getUser().getPrenom());
        holder.txtContenu.setText(dataList.get(position).getContenu());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
