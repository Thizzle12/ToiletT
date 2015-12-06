package com.example.reskesen.toilettreasure;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.lang.Exception;import java.lang.Override;import java.lang.String;import java.lang.System;

/**
 * Created by Theis on 03-10-2015.
 */
public class Login extends android.support.v4.app.Fragment implements View.OnClickListener{

    EditText username, password;
    Button login, createUser;
    Firebase firebase;
    String userNameData;
    int spiritanimal;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View root = i.inflate(R.layout.login_layout, container, false);

        Firebase.setAndroidContext(getActivity());

        username = (EditText) root.findViewById(R.id.e_mail);
        password = (EditText) root.findViewById(R.id.password);
        login = (Button) root.findViewById(R.id.login);
        createUser = (Button) root.findViewById(R.id.create_user);
        login.setOnClickListener(this);
        createUser.setOnClickListener(this);

        firebase = new Firebase("https://vivid-inferno-5770.firebaseio.com/");

        return root;
    }

    @Override
    public void onClick(View v) {

        if(v == login){

            Firebase ref = firebase.child("Users");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    try {
                        String response = dataSnapshot.child(username.getText().toString()).child("password").getValue().toString();
                        System.out.println("username: " + username.getText().toString() + " password: " + response);

                        if (response.equals(password.getText().toString())) {
                            userNameData = username.getText().toString();
                            spiritanimal = Integer.parseInt(dataSnapshot.child(userNameData).child("spiritanimal").getValue().toString());
                            startHoved();

                        } else {
                            throw new Exception("invalid password");
                        }


                    } catch (Exception e) {
                        password.setText("");
                        Toast.makeText(getActivity(), "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });


        } else if(v == createUser){
            getFragmentManager().beginTransaction()
                    .replace(R.id.startlayout, new CreateUser())
                    .addToBackStack(null)
                    .commit();

        }
    }


    private void startHoved() {

        Intent i = new Intent(getActivity(),MainActivity.class);
        i.putExtra("username", userNameData);
        i.putExtra("spiritanimal", spiritanimal);

        startActivity(i); }
}

