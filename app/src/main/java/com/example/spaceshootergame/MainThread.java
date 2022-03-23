package com.example.spaceshootergame;

import android.graphics.Canvas;
import android.os.Build;
import android.view.SurfaceHolder;

import androidx.annotation.RequiresApi;


public class MainThread extends Thread {

    public static final int FRAME_MAX = 30;
    private double Frame_ON_AVERAGE;
    private SurfaceHolder SH;
    private GamePanel gp;
    private boolean running;
    public static Canvas canvas;

    /**
     * constructor for main thread class
     * @param surfaceHolder
     * @param gamePanel
     */
    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.SH = surfaceHolder;
        this.gp = gamePanel;
    }

    /**
     * The run method is part of the run class
     */

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000 / FRAME_MAX;
        long waitTime;
        int frameCount = 0;
        int totalTime = 0;
        long targetTime = 1000 / FRAME_MAX;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.SH.lockCanvas();
                synchronized (SH) {
                    this.gp.update();//update the objects position
                    this.gp.draw(canvas);//draw the object position
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(canvas != null){
                    try{
                        SH.unlockCanvasAndPost(canvas);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;
            try{
                if(waitTime>0){
                    this.sleep(waitTime);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if(frameCount == FRAME_MAX){
                Frame_ON_AVERAGE= 1000/((totalTime/frameCount)/1000000);
                frameCount=0;
                totalTime=0;
                // System.out.println(averageFPS);
            }
        }
    }

    public void setRunning(boolean running){
        this.running = running;
    }

}

