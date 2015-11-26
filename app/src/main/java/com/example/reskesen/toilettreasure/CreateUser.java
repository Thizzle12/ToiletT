package com.example.reskesen.toilettreasure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reskesen.toilettreasure.R;import com.example.reskesen.toilettreasure.User;import com.firebase.client.Firebase;

/**
 * Created by Reskesen on 12-11-2015.
 */
public class CreateUser extends android.support.v4.app.Fragment implements View.OnClickListener {

    Button confirm;
    EditText username, password1, confirm_password1;
    Firebase firebase;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View root = i.inflate(R.layout.create_user, container, false);

        Firebase.setAndroidContext(getActivity());

        confirm = (Button) root.findViewById(R.id.confirm);
        username = (EditText) root.findViewById(R.id.username);
        password1 = (EditText) root.findViewById(R.id.password1);
        confirm_password1 = (EditText) root.findViewById(R.id.confirm_password1);


        confirm.setOnClickListener(this);
        firebase = new Firebase("https://vivid-inferno-5770.firebaseio.com/");

        return root;
    }

    @Override
    public void onClick(View v) {
        if(v == confirm){

            if(!username.getText().toString().equals("") && password1.getText().toString().equals(confirm_password1.getText().toString())){

                Firebase ref = firebase.child("Users").child(username.getText().toString());
                User user = new User(username.getText().toString(), password1.getText().toString());
                ref.setValue(user);

                getFragmentManager().beginTransaction()
                        .replace(R.id.hovedlayout, new Login())
                        .addToBackStack(null)
                        .commit();


            }else{
                password1.setText("");
                confirm_password1.setText("");
                Toast.makeText(getActivity(), "You have to choose a username! Or your passwords don't match!", Toast.LENGTH_SHORT).show();
            }



        }
    }
}
