package com.example.spaceshootergame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Random;

/**
 * SurfaceHolder.Callback
 * A client may implement this interface to receive information about changes to the surface.
 * When used with a SurfaceView, the Surface being held is only available between calls to
 * surfaceCreated(android.view.SurfaceHolder) and surfaceDestroyed(android.view.SurfaceHolder).
 * The Callback is set with SurfaceHolder.addCallback method.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    //Player that we made
    private Player player;
    private Point playerPoint;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Point enemyPoint;
    private int level =0;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private Point bulletPoint;
    private int bulletCounter = 0;
    private Random random = new Random();
    private int score = 0;
    private Paint paint;
    private int enemyCounter = 0;
    private int level1 =0;
    private int level2 =0;
    private int level3 =0;
    private Bitmap[] background = new Bitmap[3];
    private MediaPlayer bGMusic;


    public GamePanel(Context context) {
        super(context);

        //Access to the underlying surface is provided via the SurfaceHolder interface,
        // which can be retrieved by calling getHolder()
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(60);
        paint.setStyle(Paint.Style.FILL);
        //Instantiating MainThread class that we made

        bGMusic = MediaPlayer.create(context, R.raw.music);
        bGMusic.start();

        playerPoint = new Point(150, 1600);
        player = new Player(new Rect(0, 0, 100, 100), playerPoint, this.getContext());


        enemyPoint = new Point(500, 150);

        background[0] = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.background12);
        background[1] = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.background14);
        background[2] = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.background15);

        setFocusable(true);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void bulletFunc() {

        if (bulletCounter > 80) {
            bulletPoint = new Point(player.getxPos() + 65, player.getyPos() + 15); // aligns with spaceship
            bullets.add(new Bullet(new Rect(0, 0, 10, 40), bulletPoint, this.getContext(),0,0));
            bulletCounter = 0;

        }


        for (Bullet bullet : bullets) {
            for (Enemy enemy : enemies) {
                if(Rect.intersects(bullet.getRectangle(),enemy.getRectangle())){
                    enemy.setHealth(enemy.getHealth()-100);
                    bullet.setBulletExplosion(true);
                    score++;
                    //System.out.println(enemy.getHealth());
                }
            }
        }


        for(Enemy enemy : enemies){
            for(Bullet bullet : enemy.bullets){
                if(Rect.intersects(bullet.getRectangle(),player.getRectangle())){
                    player.setHealth(player.getHealth()-10);
                    bullet.setBulletExplosion(true);
                    System.out.println(player.getHealth());
                }
            }
        }



        //nemies.removeIf(enemy ->(enemy.isAlive));


        bullets.removeIf(bullet -> (bullet.isBulletExplosion()));

        for(Enemy enemy : enemies){

            enemy.bullets.removeIf(bullet -> (bullet.isBulletExplosion()));


        }







    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    private void enemyFunc() {


        switch (level) {

            case 0:
                if (enemies.size() < 1) {
                    enemyPoint.set(random.nextInt(600), random.nextInt(600));
                    enemies.add(new Enemy(new Rect(0, 0, 150, 200), enemyPoint, this.getContext(), 1 +random.nextInt(6)));

                }

                break;
            case 1:
                if (enemies.size() < 2 && level2 != 2) {
                    enemyPoint.set(random.nextInt(600), random.nextInt(600));
                    enemies.add(new Enemy(new Rect(0, 0, 100, 100), enemyPoint, this.getContext(), 1 +random.nextInt(10)));
                    level2++;
                }
                break;
            case 2:
                if (enemies.size() < 3 && level3 != 3) {
                    enemyPoint.set(random.nextInt(600), random.nextInt(600));
                    enemies.add(new Enemy(new Rect(0, 0, 100, 100), enemyPoint, this.getContext(),1 +random.nextInt(15)));
                    level3++;
                    break;
                }

        }


        for (Enemy enemy : enemies) {
            enemy.setLevel(level);

        }




        for (Enemy enemy: enemies) {
            if(enemy.getHealth() < 1){
                enemyCounter++;
                enemy.isDead = true;

            }

        }
        enemies.removeIf(enemy ->(enemy.isDead));
        if(enemies.size() == 0){
            level++;
        }
        //level = enemyCounter;
       // System.out.println(enemyCounter);
        //System.out.println("level is: " + level);


    }


    private void PlayerFunc() {

        if(player.getHealth() < 1){
            player.playerExplosion = true;

            thread.setRunning(false);
            Intent intent = new Intent(this.getContext(), GameOver.class);
            this.getContext().startActivity(intent);
            ((Activity)this.getContext()).finish();

        }



    }


    // default methods


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //In order to move any object this is the method that will detect any touch on the surfaceView
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

                player.movement(event);
                break;
        }

        //By making it true it will detect all the touches
        return true;

    }

    //If the object needs to move or it is need to constantly check for collision must use this method.
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void update() {

        for (Bullet bullet : bullets) {
            bullet.update();

        }
        for (Enemy enemy : enemies) {
            enemy.update();

        }

        bulletCounter++;

        bulletFunc();
        enemyFunc();
        PlayerFunc();



    }


    //This method will add any object on the surfaceView
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //Setting update the colour of the canvas
        switch (level) {

            case 0:
           canvas.drawBitmap(background[0],0,0,null);

                break;
            case 1:
                canvas.drawBitmap(background[1],0,0,null);
                break;
            case 2:
                canvas.drawBitmap(background[2],0,0,null);
                break;


        }

        //adding the player to the canvas
        player.draw(canvas);
        canvas.drawText("SCORE: " + score,100,100,paint);


        for (Bullet bullet : bullets) {
            bullet.draw(canvas);

        }
        for (Enemy enemy : enemies) {
            enemy.draw(canvas);

        }
    }

}

