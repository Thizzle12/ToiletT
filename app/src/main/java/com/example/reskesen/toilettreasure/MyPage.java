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
        proName = i.getExtras().getString("username");
        int spiAnim = i.getExtras().getInt("spiritanimal");
        profilName.setText(proName);



        try {
            switch (spiAnim) {
                case 0:
                    spiritAnim.setImageResource(R.drawable.bird);
                    break;
                case 1:
                    spiritAnim.setImageResource(R.drawable.bear);
                    break;
                case 2:
                    spiritAnim.setImageResource(R.drawable.beaver);
                    break;
                case 3:
                    spiritAnim.setImageResource(R.drawable.penguin);
                    break;
                case 4:
                    spiritAnim.setImageResource(R.drawable.clown_fish);
                    break;
                case 5:
                    spiritAnim.setImageResource(R.drawable.wolf);
                    break;
                case 6:
                    spiritAnim.setImageResource(R.drawable.turtle);
                    break;
                case 7:
                    spiritAnim.setImageResource(R.drawable.octopus);
                    break;
                case 8:
                    spiritAnim.setImageResource(R.drawable.panda);
                    break;
                case 9:
                    spiritAnim.setImageResource(R.drawable.falcon);
                    break;
                case 10:
                    spiritAnim.setImageResource(R.drawable.snail);
                    break;

                default:
                    spiritAnim.setImageResource(R.drawable.wolf);
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
        if(v == spiritAnim){
            Intent in = new Intent(getActivity(),SelectSpiritAnimal.class);
            in.putExtra("profilename", proName);
            startActivity(in);
        }



    }
}