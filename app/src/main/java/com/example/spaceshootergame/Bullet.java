package com.example.spaceshootergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Bullet extends Position implements GameObject {

    private Rect rectangle;
    private Bitmap[] bulletSprite = new Bitmap[2];
    private Context context;

    Paint paint;

    private int health = 99;
    private int speed = 15;
    private boolean bulletExplosion =false;
    private int[] bulletDir = {0,1};
    private int direction = 0;
    private int bulletAnimation;



    /**
     * In order for the update to work with the velocity I need to pass point and  store the point values to x and y.
     * If I pass x and y then set point it will give errors
     *
     * @param rectangle
     * @param point
     */
    public Bullet(Rect rectangle, Point point, Context context,int direction, int bulletAnimation) {
        super();
        this.rectangle = rectangle;
        this.setxPos(point.x);
        this.setyPos(point.y);
        this.direction = direction;
        this.bulletAnimation = bulletAnimation;


        this.context = context;

        this.setxVel(0);

        //Setting up the birdSprites
        bulletSprite[0] = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bullet);
        bulletSprite[1] = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.bullet23);


        //Setting up the points of the rectangle shape. This will draw the four points of the rectangle
        //left, top, right, bottom
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
    }



    /**
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {

        Bitmap resizedBulletSprite = null;

        //drawing the Bitmap on to the canvas
        resizedBulletSprite =  Bitmap.createScaledBitmap(bulletSprite[bulletAnimation], 50, 60, true);


        //drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedBulletSprite, this.getxPos()-24 , this.getyPos()-30 , null);

        paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(rectangle,paint);


    }



    public void movement() {

        // this.setyPos(this.getyPos()-speed); // causes bullet to go up
        //if(this.getyPos()>-500){
        // setyVel(0);
        // bulletExplosion=true;
        // }
        switch (bulletDir[direction]) {
            case 0:
                this.setyPos(this.getyPos()-speed);
                if (this.getyPos() < 150) {
                    speed=0;
                    bulletExplosion = true;
                }
                break;
            case 1:
                this.setyPos(this.getyPos()+speed);
                if(this.getyPos() > 2000){
                    speed=0;
                    bulletExplosion = true;
                }

        }
    }


    @Override
    public void update() {
        movement();
        rectangle.set((this.getxPos() - rectangle.width() / 2) + this.getxVel(), (this.getyPos() - rectangle.height() / 2) + this.getyVel(), (this.getxPos() + rectangle.width() / 2) + this.getxVel(), (this.getyPos() + rectangle.height() / 2) + this.getyVel());

    }


//    /**
//     * I just had to (getXPos() - rectangle.width() / 2)+velX  if I do it the way I code in java fx
//     */
//    public void update(int velX, int velY) {
//
//        this.setxPos(this.getxPos() + velX);
//        this.setyPos(this.getyPos() + velY);
//        rectangle.set((this.getxPos() - rectangle.width() / 2) + velX, (this.getyPos() - rectangle.height() / 2) + velY, (this.getxPos() + rectangle.width() / 2) + velX, (this.getyPos() + rectangle.height() / 2) + velY);
//    }



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



    @Override
    public void updatePos(int n1, int n2) {

    }
}