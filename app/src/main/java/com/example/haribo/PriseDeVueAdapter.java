package com.example.haribo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PriseDeVueAdapter extends RecyclerView.Adapter<PriseDeVueAdapter.CustomViewHolder>  {
    private List<PriseDeVue> dataList;

    public PriseDeVueAdapter(Context context, List<PriseDeVue> dataList){
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView txtUrl;


        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtUrl = mView.findViewById(R.id.textView5);

        }
    }

    @Override
    public PriseDeVueAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.photos_row, parent, false);
        return new PriseDeVueAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PriseDeVueAdapter.CustomViewHolder holder, int position) {
        holder.txtUrl.setText("");

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
