package com.example.spaceshootergame;

public abstract class Object {

    private int PosX;
    private int PosY;
    private int VelocityX;
    private int VelocityY;

    Object() {

    }

    /**
     * @return
     */
    public int getxPos() {

        return PosX;
    }

    /**
     * @param xPos
     */
    public void setxPos(int xPos) {

        this.PosX = xPos;
    }

    /**
     * @return
     */
    public int getyPos() {

        return PosY;
    }

    /**
     * @param yPos
     */
    public void setyPos(int yPos) {

        this.PosY = yPos;
    }

    /**
     * @return
     */
    public int getxVel() {

        return VelocityX;
    }

    /**
     * @param xVel
     */
    public void setxVel(int xVel) {

        this.VelocityX = xVel;
    }

    /**
     * @return
     */
    public int getyVel() {

        return VelocityY;
    }

    /**
     * @param yVel
     */
    public void setyVel(int yVel) {

        this.VelocityY = yVel;
    }

    public abstract void updatePos(int n1, int n2);


}
