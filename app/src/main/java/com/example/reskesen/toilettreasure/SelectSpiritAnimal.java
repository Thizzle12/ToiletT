package com.example.reskesen.toilettreasure;

/**
 * Created by Theis on 04-12-2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


public class SelectSpiritAnimal extends Activity implements AdapterView.OnItemClickListener {
    ListView list;
    ArrayList<Integer> spiritAnimal = new ArrayList<>();
    int spiritAnimalCount = 10;
    Firebase firebase;
    ToiletTreasure app;


    int spirit = 1;
    String proName;

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_spirit_animal);
        Intent in = getIntent();
        proName = in.getExtras().getString("profilename");
        app = (ToiletTreasure) getApplication();

        list = (ListView) findViewById(R.id.spiritanimal_list);
        list.setOnItemClickListener(this);
        for (int i = 0; i < spiritAnimalCount; i++) {
            spiritAnimal.add(i);
        }
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://vivid-inferno-5770.firebaseio.com/");


        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spirit_animal_list_item, R.id.spirit_number, spiritAnimal.toArray()) {
            @Override
            public View getView(int position, View cachedView, ViewGroup parent) {
                View view = super.getView(position, cachedView, parent);


                ImageView billede = (ImageView) view.findViewById(R.id.spirit_list_image);


                billede.setImageResource(app.getImage(spiritAnimal.get(position)));//R.drawable.smiley);

                return view;
            }
        };

        list.setAdapter(adapter);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        switch (position) {
            case 0:
                spirit = 0;
                break;
            case 1:
               spirit = 1;
                break;
            case 2:
                spirit = 2;
                break;
            case 3:
                spirit = 3;
                break;
            case 4:
                spirit = 4;
                break;
            case 5:
                spirit = 5;
                break;
            case 6:
                spirit = 6;
                break;
            case 7:
                spirit = 7;
                break;
            case 8:
                spirit = 8;
                break;
            case 9:
                spirit = 9;
                break;
            case 10:
                spirit = 10;
                break;

            default:
               spirit = 10;
                break;
        }

            final Firebase ref = firebase.child("Users").child(proName);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //dataSnapshot.getChildren();

                    try {


                        String password = dataSnapshot.child("password").getValue().toString();


                        User user = new User(proName, password,spirit);
                        ref.setValue(user);

                    } catch (Exception e) {
                        System.out.println("ERROR");
                        System.out.println();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

    finish();

    }



}