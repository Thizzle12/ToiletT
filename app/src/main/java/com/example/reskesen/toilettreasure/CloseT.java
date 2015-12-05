package com.example.reskesen.toilettreasure;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.reskesen.toilettreasure.R;import com.example.reskesen.toilettreasure.R;

/**
 * Created by Henrik on 19/11/2015.
 */
public class CloseT extends android.support.v4.app.Fragment implements View.OnClickListener{


        String userName;
        int spiritanimal;
        ImageButton postMessage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.forside_fragmenter, container, false);
        //   TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //   textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        postMessage = (ImageButton) root.findViewById(R.id.posting);
        postMessage.setOnClickListener(this);
        Intent i = getActivity().getIntent();
        userName = i.getExtras().getString("username");
        spiritanimal = i.getExtras().getInt("spiritanimal");


        return root;
    }



    public void onClick(View v) {
        if(v == postMessage){
            Intent i = new Intent(getActivity(), PostMessage.class);
            i.putExtra("username",userName);
            i.putExtra("spiritanimal",spiritanimal);
            startActivity(i);
        }
    }

}