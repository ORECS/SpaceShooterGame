package com.example.spaceshootergame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    private Button Replay;
    private Button quit;

    /**
     * constructor for gameoverclass which sets up the layout for when the game ends
     */
    public GameOver(){
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_over);

        Replay = findViewById(R.id.replay);
        Replay.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        });

        quit = findViewById(R.id.quit);
        quit.setOnClickListener(view -> {

            System.exit(0);

        });

    }


}
