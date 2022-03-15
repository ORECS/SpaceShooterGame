package com.example.spaceshootergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class Enemy extends Position implements GameObject {
    private Rect rectangle;
    private Bitmap[] EnemySprite = new Bitmap[3];
    private Context context;
    private int level = 0;
    private int bulletCounter = 0;
    public ArrayList<Bullet> bullets = new ArrayList<>();
    private Point bulletPoint;
    Paint paint;
    private int health = 200;
    private int speed = 6;
    private boolean bulletExplosion =false;
    public boolean isDead = false;


    /**
     * constructor sets up the rectangle, point, context and speed
     * also assings the spirtes into a bitmap
     *
     * @param rectangle
     * @param point
     */
    public Enemy(Rect rectangle, Point point, Context context,int speed) {
        super();
        this.rectangle = rectangle;
        this.setxPos(point.x);
        this.setyPos(point.y);
        this.speed = speed;
        this.context = context;

        this.setxVel(0);

        //Setting up the birdSprites
        EnemySprite[0] = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.enemy2);
        EnemySprite[1] = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.enemy);
        EnemySprite[2] = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.enemy4);

        //Setting up the points of the rectangle shape. This will draw the four points of the rectangle
        //left, top, right, bottom
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
    }

    /**
     * method for bullet allignment and counter calculations
     */
    private void bulletFunc() {

        if (bulletCounter > 80) {
            bulletPoint = new Point(this.getxPos() + 10, this.getyPos() + 15); // aligns with spaceship
            bullets.add(new Bullet(new Rect(0, 0, 10, 40), bulletPoint, context,1, 1));
            bulletCounter = 0;

        }

    }






    /**
     * draws enemy onto canvas
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        /*paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(rectangle,paint);

         */
        for (Bullet bullet : bullets) {
            bullet.draw(canvas);

        }

        Bitmap resizedEnemySprite = null;

        //drawing the Bitmap on to the canvas
        resizedEnemySprite =  Bitmap.createScaledBitmap(EnemySprite[level], 300, 300, true);


        //drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedEnemySprite, this.getxPos()-150 , this.getyPos()-200 , null);






    }


    /**
     * method for enemy movement
     */
    public void movement() {
        this.setxPos(this.getxPos()+speed);
        if(this.getxPos()> 800){
            speed *= -1;

        }
        if(this.getxPos()< 20){
            speed *= -1;
        }




    }


    /**
     * overrides method in gameobject which updates te enemy on the canvas screen
     */
    @Override
    public void update() {
        movement();
        rectangle.set((this.getxPos() - rectangle.width() / 2) + this.getxVel(), (this.getyPos() - rectangle.height() / 2) + this.getyVel(), (this.getxPos() + rectangle.width() / 2) + this.getxVel(), (this.getyPos() + rectangle.height() / 2) + this.getyVel());
        bulletCounter++;
        bulletFunc();
        for (Bullet bullet : bullets) {
            bullet.update();

        }
    }


    /**
     * Method is used to update the position
     *
     * @param n1
     * @param n2
     */
    @Override
    public void updatePos(int n1, int n2) {
        this.setxPos(n1);
        this.setyPos(n2);

    }



    public Rect getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rect rectangle) {
        this.rectangle = rectangle;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isBulletExplosion() {
        return bulletExplosion;
    }

    public void setBulletExplosion(boolean bulletExplosion) {
        this.bulletExplosion = bulletExplosion;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}

