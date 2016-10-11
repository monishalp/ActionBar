package com.example.monisha.actionbar_monisha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by monisha on 2/25/2016.
 */




import java.util.HashMap;

public class Activity_Toolbar2 extends AppCompatActivity implements  recyclerView.OnClickListenerInt, DiffViewRecycler.OnClickListenerInt,NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    Toolbar mtoolbar;
    Fragment mcontent;
    ActionBar mActionBar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ImageView img;
    MovieData movieData = new MovieData();
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id)
        {
            case R.id.item1:
            {intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.item2:
            {
                intent = new Intent(this, recyclerView.class);
                startActivity(intent);
                break;
            }
            case R.id.item3:
            {
                break;
            }


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*`        mActionBar = getSupportActionBar();
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
       mActionBar.setLogo(R.drawable.frozen);*/

        mtoolbar = (Toolbar) findViewById(R.id.toolbar2);
        mtoolbar.inflateMenu(R.menu.bottom_toolbar);
        setupToolbarItemsSelected();
        toolbar.setNavigationIcon(R.drawable.delete);
        ///ICON SMALL

        //getSupportFragmentManager().beginTransaction().add(R.id.container,recyclerView.newInstance(3)).commit();
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        img = (ImageView) findViewById(R.id.img1);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtoolbar.setVisibility(View.VISIBLE);

            }
        });
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
            getSupportFragmentManager().beginTransaction().add(R.id.container, recyclerView.newInstance(0)).commit();
        }
    }
void setupToolbarItemsSelected() {

    mtoolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId();
            HashMap<String, ?> movie = (HashMap<String, ?> )movieData.getItem(1);

            switch (id) {
                case R.id.rate_t:
                    Toast.makeText(Activity_Toolbar2.this,"Stand Alone Toolbar demo.",Toast.LENGTH_LONG).show();
                    break;
                case R.id.rate_v:
                    mcontent=  DiffViewRecycler.newInstance(movie);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, mcontent).addToBackStack(null).commit();
                    break;

            }
            return true;
        }
    });
    mtoolbar.setNavigationIcon(R.drawable.bottompic);
    ///ICON SMALL
    mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mtoolbar.setVisibility(View.GONE);

        }
    });
/*    toolbar.setNavigationIcon(R.drawable.delete);
    ///ICON SMALL
    toolbar.setNavigationOnClickListener(new View.OnClickListener()
    {

        @Override
        public void onClick(View v) {
            mtoolbar.setVisibility(View.VISIBLE);

        }
    });*/
}
    @Override
    public void OnClickListenerInt(int position, HashMap<String, ?> movie) {
        mcontent=  Fragment_DetailView.newInstance(movie);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mcontent).addToBackStack(null).commit();

    }
}
