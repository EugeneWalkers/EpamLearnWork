package com.example.yauheni_skarakhodau.epamlearnwork;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private final List<String> items;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        final RecyclerView.ViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    clicker.onClick(view, adapterPosition);
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(final View v) {
                final int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION){
                    clicker.onLongClick(v, adapterPosition);
                    return true;
                }
                return false;
            }
        });
        return (MyViewHolder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.textView.setText(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    MyRecyclerAdapter(@NonNull final List<String> items) {
        this.items = Objects.requireNonNull(items);
    }

    public void setClicker(final ClickListener clicker) {
        this.clicker = clicker;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView textView;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            cardView = ((CardView)itemView);
            textView = cardView.findViewById(R.id.header_text);
            textView.setTextAppearance(textView.getContext(), R.style.TextHeaderStyle);

        }
    }

    interface ClickListener {
        void onClick(View view, int pos);
        void onLongClick(View view, int pos);

    }

    private ClickListener clicker;

}
