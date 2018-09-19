package com.example.yauheni_skarakhodau.epamlearnwork;

import android.annotation.SuppressLint;
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

    private static final int HEADER_TEXT = 0;
    private static final int SPECIAL_HOLDER = 1;
    private static final int SIMPLE_HOLDER = 2;

    private final int HOW_OFTEN = 3;

    private final List<ItemData> items;

    private RecyclerView.ViewHolder mainHeaderHolder;
    private int howManySelected;

    private LongClickListener longClicker;

    MyRecyclerAdapter(@NonNull final List<ItemData> items) {
        this.items = Objects.requireNonNull(items);
    }

    void removeElement(final int pos) {
        if (items.get(pos).isSelected()) {
            howManySelected--;
        }
        items.remove(pos);
    }

    void setLongClicker(final LongClickListener longClicker) {
        this.longClicker = longClicker;
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
            case HEADER_TEXT:
                view = new TextView(viewGroup.getContext());
                ((TextView) view).setTextAppearance(viewGroup.getContext(), R.style.TextMainHeaderStyle);
                holder = mainHeaderHolder = new HeaderViewHolder(view);
                initHowManySelected();
                updateHeaderTextView();
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder myViewHolder, final int i) {
        if (i == 0) {
            updateHeaderTextView();
        } else if ((i - 1) % (HOW_OFTEN + 1) != 0) {
            final SimpleViewHolder simpleViewHolder = (SimpleViewHolder) myViewHolder;
            simpleViewHolder.headerTextView.setText(items.get(getCurrentAdapterPosition(i)).getData());
            simpleViewHolder.descriptionTextView.setText(items.get(getCurrentAdapterPosition(i)).getDescription());
            simpleViewHolder.arrowButton.setVisibility(items.get(getCurrentAdapterPosition(i)).getVisibility());
        } else {
            final SpecialViewHolder specialViewHolder = (SpecialViewHolder) myViewHolder;
            specialViewHolder.textOnCardView.setText(DataProviderClass.TEXT_FOR_REPEATING);
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return position == 0 ? HEADER_TEXT : (position - 1) % (HOW_OFTEN + 1) == 0 ? SPECIAL_HOLDER : SIMPLE_HOLDER;
    }

    @Override
    public int getItemCount() {
        final int emptyElementsNumber = (int) (Math.ceil(items.size() / (double) HOW_OFTEN));
        return items.size() + emptyElementsNumber;
    }

    private void initHowManySelected() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isSelected()) {
                howManySelected++;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateHeaderTextView() {
        ((HeaderViewHolder) mainHeaderHolder).header.setText(DataProviderClass.HEADER + howManySelected);
    }

    private int getCurrentAdapterPosition(final int fakePosition) {

        return fakePosition - (int) Math.ceil((fakePosition) / ((double) HOW_OFTEN + 1));
    }

    private void setSimpleHolderListeners(final RecyclerView.ViewHolder holder) {

        ((SimpleViewHolder) holder).headerTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                int position = holder.getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    position = getCurrentAdapterPosition(position);
                    howManySelected += items.get(position).switchSelection();
                    ((SimpleViewHolder) holder).arrowButton.setVisibility(items.get(position).getVisibility());
                    updateHeaderTextView();
                }

            }
        });

        ((SimpleViewHolder) holder).descriptionTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                int position = holder.getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    position = getCurrentAdapterPosition(position);
                    howManySelected += items.get(position).switchSelection();
                    ((SimpleViewHolder) holder).arrowButton.setVisibility(items.get(position).getVisibility());
                    updateHeaderTextView();
                }
            }
        });

        ((SimpleViewHolder) holder).headerTextView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(final View v) {

                if (longClicker != null) {
                    int position = holder.getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        position = getCurrentAdapterPosition(position);
                        longClicker.onTextLongClick(v, position);

                        return true;
                    }

                }

                return false;
            }
        });

        ((SimpleViewHolder) holder).descriptionTextView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(final View v) {

                if (longClicker != null) {
                    int position = holder.getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        position = getCurrentAdapterPosition(position);
                        longClicker.onTextLongClick(v, position);
                        return true;
                    }
                }

                return false;
            }
        });
    }

    interface LongClickListener {

        void onTextLongClick(View view, int pos);

    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView header;

        HeaderViewHolder(@NonNull final View itemView) {
            super(itemView);
            header = (TextView) itemView;
        }
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
