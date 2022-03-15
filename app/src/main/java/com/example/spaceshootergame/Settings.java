package com.example.spaceshootergame;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

   private TextView info;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        info = findViewById(R.id.instructions);
        info.setText("The aim of the game is SPACE AND ENEMIES. You must fight your way to victory and destroy all your ENEMIES!!!!");
        info.setTextColor(Color.RED);
    }


}
