package com.example.reskesen.toilettreasure;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
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
   // ArrayList<String> messages = new ArrayList<String>();
    ListView list;
    SwipeRefreshLayout swipe;
    ArrayList<Integer> spiritanimal = new ArrayList<>();
    ArrayList<Message> messages = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.message_fragment_layout, container, false);
        swipe = (SwipeRefreshLayout) root.findViewById(R.id.refresh);
        // super.onCreate(savedInstanceState);
        list = (ListView) root.findViewById(R.id.list);
        list.setOnItemClickListener(this);



        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMessages();
            }
        });
        return root;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }

    void refreshMessages(){
        Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                updateList();


                swipe.setRefreshing(false);
            }
        }, 10);
    }

    public void onResume(){
        super.onResume();
        updateList();
    }

    void updateList(){

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
                    int countStart = (int) dataSnapshot.getChildrenCount() - 15;
                    int countEnd = (int) dataSnapshot.getChildrenCount() + 1;
                    messages.clear();
                    for (int i = countStart; i < countEnd; i++) {
                        try {


                            strtext = dataSnapshot.child("" + i).child("post").getValue().toString();
                            int spirit = Integer.parseInt(dataSnapshot.child("" + i).child("spiritanimal").getValue().toString());
                            String username = dataSnapshot.child("" + i).child("user").getValue().toString();
                            String location = dataSnapshot.child("" + i).child("location").getValue().toString();
                            int likes = Integer.parseInt(dataSnapshot.child("" + i).child("likes").getValue().toString());

                            Message message = new Message(spirit, strtext, username, location, likes);

                            messages.add(message);
                            //messages.add(strtext);
                        } catch (NullPointerException e) {
                            System.out.println("i: " + i);
                            System.out.println("MessageSize  " + messages.size());

                            e.printStackTrace();
                            break;
                        }
                    }

                    for (int i = 0; i < messages.size() / 2; i++) {

                        Message temp = messages.get(i);
                        int backIndex = (messages.size() - 1) - i;

                        messages.set(i, messages.get(backIndex));
                        messages.set(backIndex, temp);
                    }
                    ArrayList<String> textMessages = new ArrayList<String>();
                    for(Message mes : messages){
                        textMessages.add(mes.getMessage());

                    }

                    ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.messages, R.id.message_text, textMessages.toArray()) {
                        @Override
                        public View getView(int position, View cachedView, ViewGroup parent) {
                            View view = super.getView(position, cachedView, parent);

                            TextView username = (TextView) view.findViewById(R.id.user_name);
                            username.setText( messages.get(position).getUsername());
                            ImageView billede = (ImageView) view.findViewById(R.id.message_image);


                            billede.setImageResource(getImage(messages.get(position).getSpiritanimal()));//R.drawable.smiley);

                            return view;
                        }
                    };

                    list.setAdapter(adapter);

                } catch (Exception e) {
                    System.out.println("ERROR:" + e.getMessage());
                    e.printStackTrace();
                }
                ref.removeEventListener(this);
            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private int getImage(int position) {
        try {
            switch (position) {
                case 1:
                    return R.drawable.bear;

                case 2:
                    return R.drawable.octopus;

                case 3:
                    return R.drawable.beaver;

                default:
                    return R.drawable.crab;


            }
        } catch (Exception e) {
            System.out.println("Der skete en fejl");
            e.printStackTrace();
            return R.drawable.cancel;
        }

    }


}
