package com.example.reskesen.toilettreasure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.Firebase;

/**
 * Created by Reskesen on 12-11-2015.
 */
public class CreateUser extends android.support.v4.app.Fragment implements View.OnClickListener {

    ImageButton confirm;
    EditText username, password, confirm_password;
    Firebase firebase;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View root = i.inflate(R.layout.create_user, container, false);

        Firebase.setAndroidContext(getActivity());

        confirm = (ImageButton) root.findViewById(R.id.confirm);
        username = (EditText) root.findViewById(R.id.username);
        password = (EditText) root.findViewById(R.id.password1);
        confirm_password = (EditText) root.findViewById(R.id.confirm_password1);


        confirm.setOnClickListener(this);
        firebase = new Firebase("https://vivid-inferno-5770.firebaseio.com/");

        return root;
    }

    @Override
    public void onClick(View v) {
        if(v == confirm){

            if(!username.getText().toString().equals("") && password.getText().toString().equals(confirm_password.getText().toString())){
            try {
                Firebase ref = firebase.child("Users").child(username.getText().toString());
                User user = new User(username.getText().toString(), password.getText().toString(), 1);
                ref.setValue(user);

                getFragmentManager().beginTransaction()
                        .replace(R.id.startlayout, new Login())
                        .addToBackStack(null)
                        .commit();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity(), "Your username is invalid", Toast.LENGTH_SHORT).show();
            }

            }else{
                password.setText("");
                confirm_password.setText("");
                Toast.makeText(getActivity(), "You have to choose a username! Or your passwords don't match!", Toast.LENGTH_SHORT).show();
            }



        }
    }
}
