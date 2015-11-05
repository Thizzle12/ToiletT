package com.example.theis.toilettreasure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Reskesen on 05-11-2015.
 */
public class PostMessage extends android.support.v4.app.Fragment implements View.OnClickListener {

    Button post;
    EditText message;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.post_message,container, false);

            post = (Button) root.findViewById(R.id.post);
            message = (EditText) root.findViewById(R.id.message);


        post.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        if(v == post){
            getFragmentManager().beginTransaction()
                    .replace(R.id.hovedlayout, new MainMenu())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
