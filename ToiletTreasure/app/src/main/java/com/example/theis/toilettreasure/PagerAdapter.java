package com.example.theis.toilettreasure;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Theis on 19-10-2015.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MessagesFragment();
            case 1:
                return new Fragment1();
            case 2:
                return new Fragment2();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
