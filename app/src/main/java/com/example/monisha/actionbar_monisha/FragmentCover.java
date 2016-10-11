package com.example.monisha.actionbar_monisha;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by monisha on 2/16/2016.
 */
public class FragmentCover extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static FragmentCover newInstance(int section_number)
    {
        FragmentCover fragment = new FragmentCover();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, section_number);
        fragment.setArguments(args);
        return fragment;
    }
    public FragmentCover()
    {

    }
    public static FragmentCover newInstance (HashMap<String, ?> movie)
    {
        //String ARG_MOVIE;
        FragmentCover fragment = new FragmentCover();
        Bundle args= new Bundle();
        args.putSerializable(ARG_SECTION_NUMBER, movie);
        fragment.setArguments(args);
        return fragment;
    }
    public interface OnClickListenerInt{
        public void OnClickListenerInt( int position, HashMap<String,?> movie);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View About_me;
        View Task;

        View rootView = null;
        final MovieData movieD = new MovieData();
        int option = getArguments().getInt(ARG_SECTION_NUMBER);
        rootView = inflater.inflate(R.layout.fragment_cover, container, false);



       /* final OnClickListenerInt mListener;
        try {
            mListener = (OnClickListenerInt) getContext();
        }
        catch (ClassCastException e)
        {throw new ClassCastException ("The hosting activity of the fragment" + "forget to implement onFragmentInteractionListener");
        }*/

       /* About_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                HashMap<String, ?> movie = (HashMap<String, ?>) movieD.getItem(10);
                mListener.OnClickListenerInt(1,movie);

            }
        });
        Task.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(),Task1.class);
                startActivity(intent);
            }
        });*/

        return rootView;
    }

}
