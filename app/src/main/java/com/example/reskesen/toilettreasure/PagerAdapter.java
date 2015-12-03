package com.example.reskesen.toilettreasure;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.example.reskesen.toilettreasure.TopT;

/**
 * Created by Henrik on 19/11/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider{

    final int PAGE_COUNT = 3;
    private int tabIcons[] = new int[] {R.drawable.ic_action, R.drawable.ic_action, R.drawable.ic_action };

    public PagerAdapter(FragmentManager fragManag){
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
            default:
                return null;

        }

    }
    public int getCount(){
        return PAGE_COUNT;
    }

    @Override
    public int getPageIconResId(int position) {
        // Generate title based on item position
        return tabIcons[position];
    }
}
