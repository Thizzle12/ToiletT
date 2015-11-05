package com.example.theis.toilettreasure;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
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

    ImageButton b, messages, location, settings;
    Button profile;
    FrameLayout profileContainer, menuContainer;
    boolean profileInflated = false;
    Animation mAnimation;
    Animation pAnimation;
    OvershootInterpolator interpolator = new OvershootInterpolator(0);
    ViewPager viewPager;

    @Override


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState){
        View root = i.inflate(R.layout.mainmenu_layout, container, false);
        b = (ImageButton) root.findViewById(R.id.show_profile);
        profile = (Button) root.findViewById(R.id.profile_knap);
        location = (ImageButton) root.findViewById(R.id.show_friends);
        messages = (ImageButton) root.findViewById(R.id.show_messages);
        settings = (ImageButton) root.findViewById(R.id.show_settings);
        profileContainer = (FrameLayout) root.findViewById(R.id.fragment_profile);
        Bundle arg = getArguments();
        viewPager = (ViewPager) root.findViewById(R.id.menus);
        PagerAdapter padapter = new PagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(padapter);
        menuContainer = (FrameLayout) root.findViewById(R.id.menus_fragment);



        getActivity().getSupportFragmentManager().beginTransaction()
                .add(profileContainer.getId(), new ProfileFragment())
                .addToBackStack(null)
                .commit();

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(menuContainer.getId(), new MessagesFragment())
                .addToBackStack(null)
                .commit();

        setHasOptionsMenu(true);
        b.setOnClickListener(this);
        location.setOnClickListener(this);
        messages.setOnClickListener(this);
        profile.setOnClickListener(this);
        settings.setOnClickListener(this);
        return root;
    }
    @Override
    public void onClick(View v) {
        if(v == location){
            startLocalization();
                    /*
                    getFragmentManager().beginTransaction()
                    .replace(R.id.hovedlayout, new LocationFragment())
                    .addToBackStack(null)
                    .commit();
                    */
        }
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
        else if (v == settings){
            startSettings();
        }
    }
    private void startLocalization() { startActivity(new Intent(getActivity(), Localization.class)); }
    private void startSettings() { startActivity(new Intent(getActivity(), Settings.class)); }
}
