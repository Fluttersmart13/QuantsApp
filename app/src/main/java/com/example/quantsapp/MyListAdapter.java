
package com.example.quantsapp;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {


    private List<String> arrayList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context context;
    private String key;

    MyListAdapter(Context context, List<String>  arrayList,String key) {
        this.mInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
        this.context = context;
        this.key = key;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = arrayList.get(position);
        String[] array = name.split(",");
        holder.name.setText(array[0]);
        holder.price.setText(array[1] + "%");
        if(key.equalsIgnoreCase("a")){
            holder.cardview.setCardBackgroundColor(Color.parseColor("#D1CFCD"));
        }
        if(key.equalsIgnoreCase("l")){
            holder.cardview.setCardBackgroundColor(Color.parseColor("#00ff00"));
        }
        if(key.equalsIgnoreCase("lu")){
            holder.cardview.setCardBackgroundColor(Color.parseColor("#00ffff"));
        }
        if(key.equalsIgnoreCase("s")){
            holder.cardview.setCardBackgroundColor(Color.parseColor("#ff0000"));
        }
        if(key.equalsIgnoreCase("sc")){
            holder.cardview.setCardBackgroundColor(Color.parseColor("#ffff00"));
        }


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,price;
        CardView cardview;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            cardview = itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}