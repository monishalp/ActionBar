package com.example.monisha.actionbar_monisha;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  Toolbar toolbar;
    Fragment mcontent;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id)
        {
            case R.id.item1:
         {
             mcontent=  FragAboutMe.newInstance(R.id.fragabt);
             getSupportFragmentManager().beginTransaction().replace(R.id.container, mcontent).addToBackStack(null).commit();
             break;
         }
            case R.id.item2:
         {
             intent = new Intent(this, Activity_recycler.class);
             startActivity(intent);
             break;
         }
            case R.id.item3:
            {
                intent = new Intent(this, Activity_Toolbar2.class);
                startActivity(intent);
               break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open,R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
            if(savedInstanceState == null)
            {
                getSupportFragmentManager().beginTransaction().add(R.id.container, FragmentCover.newInstance(0)).commit();
            }
    }


}
