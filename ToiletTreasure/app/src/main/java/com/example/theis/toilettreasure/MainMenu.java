package com.example.theis.toilettreasure;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

/**
 * Created by Theis on 04-10-2015.
 */
public class MainMenu extends android.support.v4.app.Fragment implements View.OnClickListener {

    ImageButton b, messages;
    Button profile;
    FrameLayout profileContainer, menuContainer;
    boolean profileInflated = false;
    Animation mAnimation;
    Animation pAnimation;
    OvershootInterpolator interpolator = new OvershootInterpolator(2);
    ViewPager viewPager;

    @Override


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        View root = i.inflate(R.layout.mainmenu_layout, container, false);
        b = (ImageButton) root.findViewById(R.id.show_profile);
        profile = (Button) root.findViewById(R.id.profile_knap);
        messages = (ImageButton) root.findViewById(R.id.show_messages);
        profileContainer = (FrameLayout) root.findViewById(R.id.fragment_profile);
        Bundle arg = getArguments();
        viewPager = (ViewPager) root.findViewById(R.id.menus_fragment);
        PagerAdapter padapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(padapter);
        menuContainer = (FrameLayout) root.findViewById(R.id.menus);



        getActivity().getSupportFragmentManager().beginTransaction()
                .add(profileContainer.getId(), new ProfileFragment())
                .addToBackStack(null)
                .commit();



        setHasOptionsMenu(true);
        b.setOnClickListener(this);
        messages.setOnClickListener(this);
        profile.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View v) {
        if(mAnimation != null){
            mAnimation.cancel();
        }
        if(pAnimation != null){
            pAnimation.cancel();
        }
        if(v == profile && !profileInflated){
            mAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.messages_out);
            mAnimation.setInterpolator(interpolator);
            menuContainer.startAnimation(mAnimation);

            pAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.profile_in);
            pAnimation.setInterpolator(interpolator);
            profileContainer.startAnimation(pAnimation);
            profileInflated = true;
        }
        else if (v == profile && profileInflated){
            mAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.messages_in);
            mAnimation.setInterpolator(interpolator);
            menuContainer.startAnimation(mAnimation);

            pAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.profile_out);
            pAnimation.setInterpolator(interpolator);
            profileContainer.startAnimation(pAnimation);
            profileInflated = false;

        }


        //test 4







    }






}
