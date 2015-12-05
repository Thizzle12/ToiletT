package com.example.reskesen.toilettreasure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.reskesen.toilettreasure.TopT;

/**
 * Created by Henrik on 19/11/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider{

    final int PAGE_COUNT = 3;
    int spiritanimal;
    String userName;

    private int tabIcons[] = new int[] {R.drawable.user_groups50, R.drawable.login_as_user50, R.drawable.paper50 };

    public PagerAdapter(FragmentManager fragManag){
        super(fragManag);



    }

    public Fragment getItem(int position){

        switch (position){
            case 0:
                return new MessagesFragment();
            case 1:


                Bundle bundle=new Bundle();
                bundle.putString("username", userName);
                bundle.putInt("spiritanimal", spiritanimal);
                MyPage fragobj=new MyPage();
                fragobj.setArguments(bundle);
                return fragobj;
            case 2:
                Bundle bundle2=new Bundle();
                bundle2.putString("username", userName);
                bundle2.putInt("spiritanimal", spiritanimal);

                CloseT posting = new CloseT();
                posting.setArguments(bundle2);

                return posting;
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
