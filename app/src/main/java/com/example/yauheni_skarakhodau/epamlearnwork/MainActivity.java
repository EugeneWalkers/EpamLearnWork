package com.example.yauheni_skarakhodau.epamlearnwork;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyRecyclerAdapter.ClickListener{

    private final List<String> items = new ArrayList<>();
    private MyRecyclerAdapter adapter;
    private AlertDialog dialogToInputData;

    @Override
    public void onClick(final View view, final int pos) {
        Toast.makeText(this, items.get(pos), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(final View view, final int pos) {
        items.remove(pos);
        adapter.notifyItemRemoved(pos);
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

    private void initRecyclerViewItems() {
        items.add("One");
        items.add("Two");
        items.add("Three");
        items.add("Four");
        items.add("Five");
        items.add("Six");
        items.add("Seven");
        items.add("Eight");
        items.add("Nine");
        items.add("Ten");
        items.add("Eleven");
        items.add("Twelve");
        items.add("Thirteen");
        items.add("Fourteen");
        items.add("Fifteen");
        items.add("Sixteen");
        items.add("Seventeen");
        items.add("Eighteen");
        items.add("Nineteen");
        items.add("Twenty");
        items.add("Twenty one");
        items.add("Twenty two");
        items.add("Twenty three");
        items.add("Twenty four");
        items.add("Twenty five");
        items.add("Twenty six");
        items.add("Twenty seven");
        items.add("Twenty eight");
        items.add("Twenty nine");
        items.add("Thirty");
    }

    private void initAndSetRecyclerView() {
        final RecyclerView recycler = findViewById(R.id.items);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerAdapter(items);
        adapter.setClicker(this);
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
                items.add(editText.getText().toString());
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
