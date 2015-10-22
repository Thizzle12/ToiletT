package com.example.theis.toilettreasure;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Theis on 03-10-2015.
 */
public class Login extends android.support.v4.app.Fragment implements View.OnClickListener{
    ImageView img;
    EditText email, password;
    Button login;


    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View root = i.inflate(R.layout.login_layout, container, false);
        email = (EditText) root.findViewById(R.id.e_mail);
        password = (EditText) root.findViewById(R.id.password);
        login = (Button) root.findViewById(R.id.login);
        login.setOnClickListener(this);



        return root;
    }

    @Override
    public void onClick(View v) {
        Bundle arg = getArguments();
        android.support.v4.app.Fragment mainmenu = new MainMenu();
        mainmenu.setArguments(arg);
        if(v == login){
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.hovedlayout, mainmenu)
                    .addToBackStack(null)
                    .commit();

        }
    }




}
