package com.example.reskesen.toilettreasure;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;import com.example.reskesen.toilettreasure.TopT;

/**
 * Created by Henrik on 19/11/2015.
 */
public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter (FragmentManager fragManag){
        super(fragManag);

    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new MessagesFragment();
            case 1:
                return new TopT();
            case 2:
                return new CloseT();
        }
        return null;
    }
    public int getCount(){
        return 3;
    }
}
