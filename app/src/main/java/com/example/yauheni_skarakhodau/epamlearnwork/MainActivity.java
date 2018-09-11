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

public class MainActivity extends AppCompatActivity {

    private final List<String> items = new ArrayList<>();
    private MyRecyclerAdapter adapter;
    private AlertDialog dialogToInputData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setItems();
        setAlertDialog();
        setAddButton();
        setRecyclerView();
    }

    private void setItems() {
        items.add("One");
        items.add("Two");
        items.add("Three");
        items.add("Four");
        items.add("Five");
        items.add("Six");
        items.add("Seven");
    }

    private void setRecyclerView() {
        RecyclerView recycler = findViewById(R.id.items);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerAdapter(items);
        adapter.setOnItemClickListener(new MyRecyclerAdapter.ClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Toast.makeText(MainActivity.this, items.get(pos), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int pos) {
                items.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
        recycler.setAdapter(adapter);
    }

    private void setAddButton() {
        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogToInputData.show();
            }
        });
    }

    private void setAlertDialog() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(editText);
        builder.setTitle("Write a new item here:");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                items.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialogToInputData = builder.create();
    }
}
