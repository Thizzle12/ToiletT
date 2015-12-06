package com.example.reskesen.toilettreasure;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.lang.Override;import java.lang.String;import java.lang.System;
import java.util.ArrayList;

/**
 * Created by Theis on 12-10-2015.
 */
public class MessageListFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{

    int postCount = 15;
    Firebase firebase;
   // ArrayList<String> message = new ArrayList<String>();
    ListView list;
    SwipeRefreshLayout swipe;
    ArrayList<Message> messages = new ArrayList<>();
    ToiletTreasure app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.message_list, container, false);
        swipe = (SwipeRefreshLayout) root.findViewById(R.id.refresh);

        list = (ListView) root.findViewById(R.id.list);
        list.setOnItemClickListener(this);
        list.setDivider(new ColorDrawable(Color.rgb(0, 197, 0)));
        list.setDividerHeight(5);
        app = (ToiletTreasure) getActivity().getApplication();



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
        //return inflater.inflate(R.layout.message, container, false);
        final Firebase ref = firebase.child("Posts");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //dataSnapshot.getChildren();

                try {
                    final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
                    postCount = Integer.parseInt(prefs.getString("postCount", "15"));
                    int countStart = (int) dataSnapshot.getChildrenCount() - (postCount-1);
                    int countEnd = (int) dataSnapshot.getChildrenCount() + 1;
                    messages.clear();
                    for (int i = countStart; i < countEnd; i++) {
                        try {
                            String text = dataSnapshot.child("" + i).child("post").getValue().toString();
                            int spirit = Integer.parseInt(dataSnapshot.child("" + i).child("spiritanimal").getValue().toString());
                            String username = dataSnapshot.child("" + i).child("user").getValue().toString();
                            String location = dataSnapshot.child("" + i).child("location").getValue().toString();
                            int likes = Integer.parseInt(dataSnapshot.child("" + i).child("likes").getValue().toString());

                            Message message = new Message(spirit,  text, username, location, likes);

                            messages.add(message);
                            //message.add(strtext);
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

                    ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.message, R.id.message_text, textMessages.toArray()) {
                        @Override
                        public View getView(int position, View cachedView, ViewGroup parent) {
                            View view = super.getView(position, cachedView, parent);

                            TextView username = (TextView) view.findViewById(R.id.user_name);
                            username.setText(messages.get(position).getUsername());
                            ImageView billede = (ImageView) view.findViewById(R.id.message_image);

                            TextView text = (TextView) view.findViewById(R.id.message_text);
                            text.setText(messages.get(position).getMessage());

                            TextView location = (TextView) view.findViewById(R.id.location);
                            location.setText(messages.get(position).getLocation());



                            billede.setImageResource(app.getImage(messages.get(position).getSpiritanimal()));//R.drawable.smiley);

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






}
