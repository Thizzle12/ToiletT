package com.example.reskesen.toilettreasure;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.Toast;

import com.example.reskesen.toilettreasure.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.lang.Override;import java.lang.String;import java.lang.System;
import java.util.ArrayList;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Theis on 12-10-2015.
 */
public class MessagesFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{


    String strtext = null;
    Firebase firebase;
    ArrayList<String> messages = new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       // super.onCreate(savedInstanceState);
        final ListView listView = new ListView(getActivity());
        listView.setOnItemClickListener(this);


        Firebase.setAndroidContext(getActivity());
        firebase = new Firebase("https://vivid-inferno-5770.firebaseio.com/");

        //String strtext = getArguments().getString("edttext");
        //return inflater.inflate(R.layout.messages, container, false);
        final Firebase ref = firebase.child("Posts");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //dataSnapshot.getChildren();

                try {
                    strtext = dataSnapshot.child("1").child("Post").getValue().toString();
                    messages.add(strtext);
                    for (int i = 0; i < messages.size(); i++) {
                        System.out.println("data: " + messages.get(i));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.messages, R.id.user_name, messages.toArray()) {
                        @Override
                        public View getView(int position, View cachedView, ViewGroup parent) {
                            View view = super.getView(position, cachedView, parent);

                            ImageView billede = (ImageView) view.findViewById(R.id.message_image);
                            billede.setImageResource(R.drawable.smiley);

                            return view;
                        }
                    };

                    listView.setAdapter(adapter);

                } catch (Exception e) {
                    System.out.println("ERROR");
                    System.out.println(e.getMessage());
                }
            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        messages.add("hejsa");
        for (int i = 0; i < messages.size();i++) {
            System.out.println(messages.get(i));
    }







        return listView;
    }

     @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }
}
