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

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.CustomViewHolder> {
        private List<Notification> dataList;

        public NotifAdapter(Context context, List<Notification> dataList){
            this.dataList = dataList;
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            TextView txtTitle;
            TextView txtContent;
            TextView txtDate;

            CustomViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                txtTitle = mView.findViewById(R.id.textView8);
                txtContent = mView.findViewById(R.id.textView10);
            }
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.recyclerview_row, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            holder.txtTitle.setText(dataList.get(position).getTitre());
            holder.txtContent.setText(dataList.get(position).getContenu());

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
}
