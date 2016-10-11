package com.example.monisha.actionbar_monisha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by monisha on 2/16/2016.
 */
public class FragAboutMe extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public static FragAboutMe newInstance(int section_number)
    {
        FragAboutMe fragment_abt = new FragAboutMe();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, section_number);
        fragment_abt.setArguments(args);
        return fragment_abt;
    }
    public FragAboutMe()
    {

    }
    public interface OnClickListenerInt{
        public void OnClickListenerInt( int position, HashMap<String,?> movie);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        int option = getArguments().getInt(ARG_SECTION_NUMBER);

        rootView = inflater.inflate((R.layout.frag_aboutme), container, false);
       /* final OnClickListenerInt mListener;
        try {
            mListener = (OnClickListenerInt) getContext();
        }
        catch (ClassCastException e)
        {throw new ClassCastException ("The hosting activity of the fragment" + "forget to implement onFragmentInteractionListener");
        }*/

        return rootView;
    }
}
