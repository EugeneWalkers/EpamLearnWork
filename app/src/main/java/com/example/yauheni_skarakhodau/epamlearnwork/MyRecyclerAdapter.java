package com.example.yauheni_skarakhodau.epamlearnwork;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class MyRecyclerAdapter extends RecyclerView.Adapter {

    private static final int SPECIAL_HOLDER = 0;
    private static final int SIMPLE_HOLDER = 1;
    private static final String TEXT_FOR_REPEATING = "This message again...";
    private final List<String> items;
    private final int HOW_OFTEN = 3;

    MyRecyclerAdapter(@NonNull final List<String> items) {
        this.items = Objects.requireNonNull(items) /*new ArrayList<>()*/;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        final View view;

        final RecyclerView.ViewHolder holder;

        switch (viewType) {
            //noinspection DefaultNotLastCaseInSwitch
            default:
            case SIMPLE_HOLDER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_simple_view_item, viewGroup, false);
                holder = new SimpleViewHolder(view);
                setSimpleHolderListeners(holder);
                break;
            case SPECIAL_HOLDER:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_special_view_item, viewGroup, false);
                holder = new SpecialViewHolder(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder myViewHolder, final int i) {
        if (i % (HOW_OFTEN + 1) != 0) {
            final SimpleViewHolder simpleViewHolder = (SimpleViewHolder) myViewHolder;
            simpleViewHolder.headerTextView.setText(items.get(i - howMuchEmptyElementsBeforePos(i)));
            simpleViewHolder.descriptionTextView.setText(DescriptionCreator.getNumericDescriptionFrom0ToI(i - howMuchEmptyElementsBeforePos(i)));
        } else {
            final SpecialViewHolder specialViewHolder = (SpecialViewHolder) myViewHolder;
            specialViewHolder.textOnCardView.setText(TEXT_FOR_REPEATING);
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return position % (HOW_OFTEN + 1) == 0 ? SPECIAL_HOLDER : SIMPLE_HOLDER;
    }

    @Override
    public int getItemCount() {
        return items.size() + (int)(Math.ceil(items.size()/(double)HOW_OFTEN));
    }

    private int howMuchEmptyElementsBeforePos(final int pos) {
        return (int) Math.ceil((pos + 1) / ((double) HOW_OFTEN + 1));
    }

    public void setClicker(final ClickListener clicker) {
        this.clicker = clicker;
    }

    private void setSimpleHolderListeners(final RecyclerView.ViewHolder holder) {
        ((SimpleViewHolder) holder).headerTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    position -= howMuchEmptyElementsBeforePos(position);
                    clicker.onTextClick(v, position);
                }
            }
        });

        ((SimpleViewHolder) holder).headerTextView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(final View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    position -= howMuchEmptyElementsBeforePos(position);
                    clicker.onTextLongClick(v, position);
                    return true;
                }

                return false;
            }
        });

        ((SimpleViewHolder) holder).descriptionTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    position -= howMuchEmptyElementsBeforePos(position);
                    clicker.onTextClick(v, position);
                }
            }
        });

        ((SimpleViewHolder) holder).descriptionTextView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(final View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    position -= howMuchEmptyElementsBeforePos(position);
                    clicker.onTextLongClick(v, position);
                    return true;
                }

                return false;
            }
        });

        ((SimpleViewHolder) holder).arrowButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    position -= howMuchEmptyElementsBeforePos(position);
                    clicker.onImageButtonClick(v, position);
                }
            }
        });
    }


    private ClickListener clicker;


    interface ClickListener {

        void onTextClick(View view, int pos);

        void onTextLongClick(View view, int pos);

        void onImageButtonClick(View view, int pos);
    }

    public static class SpecialViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textOnCardView;

        SpecialViewHolder(@NonNull final View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            textOnCardView = cardView.findViewById(R.id.special_text);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView headerTextView;
        TextView descriptionTextView;
        ImageButton arrowButton;

        SimpleViewHolder(@NonNull final View itemView) {
            super(itemView);
            cardView = ((CardView) itemView);
            headerTextView = cardView.findViewById(R.id.header_text);
            headerTextView.setTextAppearance(headerTextView.getContext(), R.style.TextHeaderStyle);
            descriptionTextView = cardView.findViewById(R.id.description);
            arrowButton = cardView.findViewById(R.id.arrow_button);

        }
    }

}
