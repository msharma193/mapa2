package com.week1.cashregisterpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    private Button histButton, restockButton, backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        histButton = findViewById(R.id.history_btn);
        restockButton = findViewById(R.id.restock_btn);
        backButton = findViewById(R.id.backButton);




        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the ManageActivity
                Intent intent = new Intent(ManagerActivity.this, MainActivity.class);

                // Start the ManageActivity
                startActivity(intent);
            }
        });


        restockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the ManageActivity
                Intent intent = new Intent(ManagerActivity.this, RestockActivity.class);

                // Start the ManageActivity
                startActivity(intent);
            }
        });

        histButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the ManageActivity
                Intent intent = new Intent(ManagerActivity.this, HistoryActivity.class);

                // Start the ManageActivity
                startActivity(intent);
            }
        });

    }
}