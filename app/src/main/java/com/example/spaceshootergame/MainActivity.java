package com.example.spaceshootergame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To make the windows full screen



        //To remove the activity bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Setting up the view using the GamePanel class that we build
        setContentView(new GamePanel(this));

    }
}