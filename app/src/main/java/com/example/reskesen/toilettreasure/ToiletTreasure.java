package com.example.reskesen.toilettreasure;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by Theis on 04-12-2015.
 */
public class ToiletTreasure extends Application {

    private static ToiletTreasure singleton;

    public ToiletTreasure getInstance(){
        return singleton;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }


    public int getImage(int position) {
        try {
            switch (position) {
                case 0:
                    return R.drawable.bird;
                case 1:
                    return R.drawable.bear;
                case 2:
                    return R.drawable.beaver;
                case 3:
                    return R.drawable.penguin;
                case 4:
                    return R.drawable.clown_fish;
                case 5:
                    return R.drawable.wolf;
                case 6:
                    return R.drawable.turtle;
                case 7:
                    return R.drawable.octopus;
                case 8:
                    return R.drawable.panda;
                case 9:
                    return R.drawable.falcon;
                case 10:
                    return R.drawable.snail;
                default:
                    return R.drawable.wolf;


            }
        } catch (Exception e) {
            System.out.println("Der skete en fejl");
            e.printStackTrace();
            return R.drawable.shark;
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }



    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
