package com.example.reskesen.toilettreasure;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    String userName;
    int spiritanimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent i = getIntent();
        userName = i.getExtras().getString("username");
        spiritanimal = i.getExtras().getInt("spiritanimal");

        Bundle bundle=new Bundle();
        bundle.putString("username", userName);
        bundle.putInt("spiritanimal", spiritanimal);


//        ProfilePage fragobj=new ProfilePage();
//        fragobj.setArguments(bundle);


        setSupportActionBar(toolbar);

        setVerion();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().add(
                    R.id.mainactivity_container, new ContainerClass()).commit();
        }

        viewPager = (ViewPager) findViewById(R.id.VP);
        PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pageAdapter);

        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {

            startSettings();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.my_t) {
            Toast.makeText(this, "Under construction", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.new_t) {
            Toast.makeText(this, "Under construction", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.inst) {
            startSettings();

        } else if (id == R.id.close_t) {
            Toast.makeText(this, "Under construction", Toast.LENGTH_SHORT).show();

        }else if(id == R.id.top_t) {
            Toast.makeText(this, "Under construction", Toast.LENGTH_SHORT).show();
        }else if(id == R.id.logout){
            Intent i = new Intent(this, StartActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void startSettings() { startActivity(new Intent(this, Settings.class)); }

    private void startPost() {
        Intent in = new Intent(this,PostMessage.class);
        in.putExtra("username",userName);
        in.putExtra("spiritanimal",spiritanimal);
        startActivity(in); }


public void setVerion(){

    if (android.os.Build.VERSION.SDK_INT >= 15) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPost();
            }
        });
   }
}




}