package com.example.spaceshootergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Player extends Position implements GameObject {

    private Rect rectangle;
    private Bitmap playerSprite;
    private Context context;

    private int health = 1000;
    private double oldXPos = 0;
    private double oldYPos = 0;
    private int speed = 6;
    public boolean playerExplosion =false;


    /**
     * In order for the update to work with the velocity I need to pass point and  store the point values to x and y.
     * If I pass x and y then set point it will give errors
     *
     * @param rectangle
     * @param point
     */
    public Player(Rect rectangle, Point point, Context context) {
        super();
        this.rectangle = rectangle;
        this.setxPos(point.x);
        this.setyPos(point.y);

        this.context = context;

        //Setting up the playerSprite
        playerSprite = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.player);


        //Setting up the points of the rectangle shape. This will draw the four points of the rectangle
        //left, top, right, bottom
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
        // need to understand
    }




    /**
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {

        Bitmap resizedPlayerSprite = null;

        //resizing and setting the bitmap to the playerSprite
        resizedPlayerSprite =  Bitmap.createScaledBitmap(playerSprite, 200, 200, true);


        //drawing the Bitmap on to the canvas
        canvas.drawBitmap(resizedPlayerSprite, this.getxPos() - 35, this.getyPos() - 25, null);
        // the numbers are so the rectangle and sprite align

    }


    /**
     * @param event
     */
    public void movement(MotionEvent event) {

        double yPos = event.getY();
        double xPos = event.getX();

        //Checking if the older values of the y-axis was greater and current value is smaller that means velocity must be subtracted and if its vice versa I must increase it
        if (yPos >= oldYPos) { // meaning its moving down the screen
            this.setyVel(+speed);
        }
        if (yPos <= oldYPos) { //Checking if the player goes out of bound
            this.setyVel(-speed);
        }

        if (xPos >= oldXPos) { // moving to the right
            this.setxVel(+speed);
        }
        if (xPos <= oldXPos) { //Checking if the player goes out of bound
            this.setxVel(-speed);
        }

        //storing the older values to compare it in the conditions
        oldXPos = event.getX();
        oldYPos = event.getY();
        this.update(this.getxVel(), this.getyVel());
        this.setyVel(0);
        this.setxVel(0);
    }


    @Override
    public void update() {

    }


    /**
     * I just had to (getXPos() - rectangle.width() / 2)+velX
     */
    public void update(int velX, int velY) {

        this.setxPos(this.getxPos() + velX);
        this.setyPos(this.getyPos() + velY);
        rectangle.set((this.getxPos() - rectangle.width() / 2) + velX, (this.getyPos() - rectangle.height() / 2) + velY, (this.getxPos() + rectangle.width() / 2) + velX, (this.getyPos() + rectangle.height() / 2) + velY);
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

    public boolean isPlayerExplosion() {
        return playerExplosion;
    }

    public void setPlayerExplosion(boolean playerExplosion) {
        this.playerExplosion = playerExplosion;
    }


    @Override
    public void updatePos(int n1, int n2) {

    }
}
