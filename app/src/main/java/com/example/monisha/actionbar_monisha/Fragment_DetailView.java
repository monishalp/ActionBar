package com.example.monisha.actionbar_monisha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by monisha on 2/10/2016.
 */
public class Fragment_DetailView extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    HashMap<String,?> movie;
    ShareActionProvider mshareActionProvider;
    public Fragment_DetailView()
    {

    }
    public static Fragment_DetailView newInstance (HashMap<String, ?> movie)
    {
        //String ARG_MOVIE;
        Fragment_DetailView fragment = new Fragment_DetailView();
        Bundle args= new Bundle();
        args.putSerializable(ARG_SECTION_NUMBER, movie);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfrag_actionprovider, menu);
        MenuItem shareItem =menu.findItem(R.id.action_share);
        mshareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent intentShare = new Intent(Intent.ACTION_SEND);
        intentShare.setType("text/plain");
        intentShare.putExtra(Intent.EXTRA_TEXT, (String) movie.get("name"));
        mshareActionProvider.setShareIntent(intentShare);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView Title;
        ImageView moviePic;
        TextView year;
        TextView actor;
        TextView Dir;
        TextView rating;
        TextView Desc;
        View rootView = null;
        final MovieData movieD = new MovieData();
        if(getArguments()!=null)
        {
            movie=(HashMap<String,?>) getArguments().getSerializable(ARG_SECTION_NUMBER);
        }
        rootView = inflater.inflate(R.layout.activity_moviedetail, container, false);
        Title = (TextView)rootView.findViewById(R.id.title);
        moviePic = (ImageView)rootView.findViewById(R.id.MoviePic);
        actor = (TextView) rootView.findViewById(R.id.actors);
        Dir = (TextView) rootView.findViewById(R.id.director);
        year = (TextView)rootView.findViewById(R.id.year);
        Desc = (TextView)rootView.findViewById(R.id.desp);
        moviePic.setImageResource((Integer) movie.get("image"));
        Title.setText((String) movie.get("name"));
        actor.setText((String)movie.get("stars"));
        Dir.setText((String)movie.get("director"));
        Desc.setText((String) movie.get("description"));
        RatingBar ratingBar = (RatingBar) rootView.findViewById(R.id.rate);
        Double ratingD = (Double)movie.get("rating");
        ratingBar.setRating(ratingD.floatValue()/2);
        year.setText((String) movie.get("year"));
        return rootView;
    }
}
