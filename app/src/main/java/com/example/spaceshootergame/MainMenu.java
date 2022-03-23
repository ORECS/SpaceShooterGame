package com.example.spaceshootergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


/**
 * class is used to set up the main method activity menu
 */
public class MainMenu extends AppCompatActivity {


    private Button play;
    private Button quit;
    private Button settings;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        //To make the windows full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main_menu);
        play = findViewById(R.id.play);
        play.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        });

        quit = findViewById(R.id.quit);
        quit.setOnClickListener(view -> {

         System.exit(0);

        });

        settings = findViewById(R.id.settings);
        settings.setOnClickListener(view -> {

            Intent intenttwo = new Intent(getApplicationContext(), Settings.class);
            startActivity(intenttwo);

        });
        txt = findViewById(R.id.SpaceShooter);
        txt.setText("SPACESHOOTER");
        txt.setTextColor(Color.RED);
        txt.setTextSize(20);

    }
}




