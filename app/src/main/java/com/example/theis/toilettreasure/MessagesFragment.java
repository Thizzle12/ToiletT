package com.example.theis.toilettreasure;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by Theis on 12-10-2015.
 */
public class MessagesFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String[] messages ={"my name is", "Hey my name is", "My name is", "The real slim shaty"};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.messages, R.id.user_name, messages) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);


                ImageView billede = (ImageView) view.findViewById(R.id.message_image);
                billede.setImageResource(R.drawable.smiley);

                return view;
            }
        };
        ListView listView = new ListView(getActivity());
        listView.setOnItemClickListener(this);



        listView.setAdapter(adapter);
        return listView;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }

}
