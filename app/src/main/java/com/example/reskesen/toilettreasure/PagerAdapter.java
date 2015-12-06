package com.example.reskesen.toilettreasure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by Henrik on 19/11/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider{

    final int PAGE_COUNT = 3;
    int spiritanimal;
    String userName;

    private int tabIcons[] = new int[] {R.drawable.user_groups50, R.drawable.login_as_user50, R.drawable.paper50 };

    public PagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);



    }

    public Fragment getItem(int position){

        switch (position){
            case 0:
                return new MessageListFragment();
            case 1:
                Bundle bundle=new Bundle();
                bundle.putString("username", userName);
                bundle.putInt("spiritanimal", spiritanimal);
                ProfilePage fragobj=new ProfilePage();
                fragobj.setArguments(bundle);
                return fragobj;
            case 2:
                Bundle bundle2=new Bundle();
                bundle2.putString("username", userName);
                bundle2.putInt("spiritanimal", spiritanimal);

                PostPage posting = new PostPage();
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
