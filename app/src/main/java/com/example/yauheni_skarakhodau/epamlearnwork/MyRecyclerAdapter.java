package com.example.yauheni_skarakhodau.epamlearnwork;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<String> items;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(new TextView(viewGroup.getContext()));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    MyRecyclerAdapter(@NonNull List<String> items) {
        this.items = items;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView textView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = ((TextView) itemView);
            textView.setTextSize(40);
            textView.setOnClickListener(this);
            textView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clicker.onClick(itemView, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            clicker.onLongClick(v, getAdapterPosition());
            return true;
        }
    }

    interface ClickListener {
        void onClick(View view, int pos);
        void onLongClick(View view, int pos);
    }

    public void setOnItemClickListener(ClickListener listener) {
        clicker = listener;
    }

    private static ClickListener clicker;

}
