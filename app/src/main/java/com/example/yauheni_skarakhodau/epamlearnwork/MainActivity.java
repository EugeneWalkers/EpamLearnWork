package com.example.yauheni_skarakhodau.epamlearnwork;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyRecyclerAdapter.LongClickListener {

    private static final String LIST_KEY = "list_key";

    private final List<ItemData> items = new ArrayList<>();
    private MyRecyclerAdapter adapter;
    private AlertDialog dialogToInputData;

    @Override
    public void onTextLongClick(final View view, final int pos) {
        adapter.removeElement(pos);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerViewItems();
        setAlertDialogForAdding();
        setAddButton();
        initAndSetRecyclerView();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("hahaha", "hohoho");
        outState.putParcelableArrayList(LIST_KEY, (ArrayList<? extends Parcelable>) items);
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("hahaha", "hehehe");
        items.clear();
        items.addAll(savedInstanceState.<ItemData>getParcelableArrayList(LIST_KEY));
    }

    private void initRecyclerViewItems() {
        final List<String> listOfStringData = DataProviderClass.getItemsList();
        for (int i = 0; i < listOfStringData.size(); i++) {
            items.add(new ItemData(listOfStringData.get(i), i));
        }
    }

    private void initAndSetRecyclerView() {
        final RecyclerView recycler = findViewById(R.id.items);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerAdapter(items);
        adapter.setLongClicker(this);
        recycler.setAdapter(adapter);
    }

    private void setAddButton() {
        final FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                dialogToInputData.show();
            }
        });
    }

    private void setAlertDialogForAdding() {
        final EditText editText = new EditText(this);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(editText);
        builder.setTitle("Write a new item here:");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                items.add(new ItemData(editText.getText().toString(), items.size()));
                adapter.notifyDataSetChanged();
                editText.setText("");
                dialog.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.cancel();
            }
        });
        dialogToInputData = builder.create();
    }
}
