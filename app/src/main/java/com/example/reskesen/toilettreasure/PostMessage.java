package com.example.reskesen.toilettreasure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.reskesen.toilettreasure.R;import java.lang.Override;import java.lang.String;import java.lang.System;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Reskesen on 05-11-2015.
 */
public class PostMessage extends Activity implements View.OnClickListener {

    Button post;
    EditText message;
    Firebase firebase;

    long count;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_message);
        Firebase.setAndroidContext(this);

            post = (Button) findViewById(R.id.post);
            message = (EditText) findViewById(R.id.message);

        firebase = new Firebase("https://vivid-inferno-5770.firebaseio.com/");

        post.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == post){
            final String text = message.getText().toString();

            final Firebase ref = firebase.child("PostCount");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //dataSnapshot.getChildren();

                    try {
                        count = (long) dataSnapshot.getValue();

                        System.out.println(count);
                        count++;
                        Firebase ref2 = firebase.child("Posts").child("" + count);

                        Post post = new Post(text, "", "Theis", 0);
                        ref2.setValue(post);

                        ref.setValue(count);

                    } catch (Exception e) {
                        System.out.println("ERROR");
                        System.out.println(e.getMessage());
                    }
                }



                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }
    }
}
