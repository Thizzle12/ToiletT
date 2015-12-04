package com.example.reskesen.toilettreasure;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Henrik on 03/12/2015.
 */
public class MyPage extends android.support.v4.app.Fragment implements View.OnClickListener{

    int spiAnim;
    String proName;
    String username;

    TextView profilName;
    ImageButton spiritAnim;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.my_page, container, false);

        spiritAnim = (ImageButton) root.findViewById(R.id.spiritAnim);

       spiritAnim.setOnClickListener(this);

        profilName = (TextView) root.findViewById(R.id.profilName);

      // String proName = getArguments().getString("username");
     //   int spiAnim = getArguments().getInt("spiritanimal");

        Intent i= getActivity().getIntent();
        String proName = i.getExtras().getString("username");
        int spiAnim = i.getExtras().getInt("spiritanimal");
        profilName.setText(proName);

        try {
            switch (spiAnim) {
                case 1:
                    spiritAnim.setImageResource(R.drawable.sheep);
                    break;
                case 2:
                    spiritAnim.setImageResource(R.drawable.bear);
                    break;

                case 3:
                    spiritAnim.setImageResource(R.drawable.bear);
                    break;

                default:
                    spiritAnim.setImageResource(R.drawable.bear);
                    break;


            }
        } catch (Exception e) {
            System.out.println("Der skete en fejl");
            e.printStackTrace();

        }


        return root;
    }



    @Override
    public void onClick(View v) {

    }
}