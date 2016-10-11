package com.example.monisha.actionbar_monisha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.OvershootInterpolator;
import android.widget.PopupMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by monisha on 2/16/2016.
 */
public class DiffViewRecycler extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu.findItem(R.id.search) == null)
            inflater.inflate(R.menu.fragment_menu, menu);
        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        if (search != null) {
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    List<Map<String, ?>> Screen = movieData.getMoviesList();
                    for (int i = mRecyclerViewAdapter.getItemCount() - 1; i >= 0; i--) {

                        HashMap<String, ?> movie = (HashMap<String, ?>) movieData.getItem(i);
                        if (movie.get("name").toString().toLowerCase().contains(query)) {
                            if (i >= 0) {
                                mRecyclerView.scrollToPosition(i);
                                return true;

                            }
                        }
                    }
                    return false;

                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    public static DiffViewRecycler newInstance(int section_number)
    {
        DiffViewRecycler fragment = new DiffViewRecycler();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, section_number);
        fragment.setArguments(args);
        return fragment;
    }
    public DiffViewRecycler()
    {

    }
    public static DiffViewRecycler newInstance (HashMap<String, ?> movie)
    {
        //String ARG_MOVIE;
        DiffViewRecycler fragment = new DiffViewRecycler();
        Bundle args= new Bundle();
        args.putSerializable(ARG_SECTION_NUMBER, movie);
        fragment.setArguments(args);
        return fragment;
    }



    DiffView mRecyclerViewAdapter;
    MovieData movieData = new MovieData();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mlayoutmanager;

    public interface OnClickListenerInt{
        public void OnClickListenerInt( int position, HashMap<String,?> movie);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View SelectA;
        View ClearA;

        View Delete;
        final View rootView = inflater.inflate(R.layout.frag_rec,container, false);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);
        mlayoutmanager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mlayoutmanager);
        mRecyclerViewAdapter =  new DiffView(getActivity(),movieData.getMoviesList());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        itemAnimation();
        adapterAnimation();

        final OnClickListenerInt mListener;
        try {
            mListener = (OnClickListenerInt) getContext();
        }
        catch (ClassCastException e)
        {throw new ClassCastException ("The hosting activity of the fragment" + "forget to implement onFragmentInteractionListener");
        }


        /*Delete.setOnClickListener(new View.OnClickListener()
         {
            @Override
            public void onClick(View v)
            {
                List<Map<String,?>> Screen = movieData.getMoviesList();
                for (int i = mRecyclerViewAdapter.getItemCount() - 1; i >= 0; i--)
                {

                    HashMap<String, Boolean> movie = (HashMap<String, Boolean>) movieData.getItem(i);
                    if(movie.get("selection"))
                    {
                        Screen.remove(i);
                        mRecyclerViewAdapter.notifyItemRemoved(i);
                    }
                }
                }
        });

        SelectA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                for (int i = 0;i<mRecyclerViewAdapter.getItemCount();  i++) {

                    HashMap<String, Boolean> movie = (HashMap<String, Boolean>) movieData.getItem(i);
                    movie.put("selection", true);

                }
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

        ClearA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                for (int i = mRecyclerViewAdapter.getItemCount() - 1; i >= 0; i--) {

                    HashMap<String, Boolean> movie = (HashMap<String, Boolean>) movieData.getItem(i);
                    movie.put("selection", false);

                }
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        });

*/


        mRecyclerViewAdapter.setOnItemClickListener(new DiffView.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                HashMap<String, ?> movie = (HashMap<String, ?>) movieData.getItem(position);
                mListener.OnClickListenerInt(1, movie);

            }

            @Override
            public void onItemLongClick(View view, int position) {
                getActivity().startActionMode(new ActionBarCallback(position));
                /*List<Map<String,?>> Screen = movieData.getMoviesList();
                Screen.add(position, (HashMap) ((HashMap<String,?>) movieData.getItem(position)).clone());
                mRecyclerViewAdapter.notifyItemInserted(position+1);*/
            }

         /*   @Override
            public void onOverFlowMenuClick(View view, final int position) {
                final PopupMenu popup = new PopupMenu(getActivity(),view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        List<Map<String,?>> Screen = movieData.getMoviesList();
                        switch(item.getItemId())
                        {
                            case R.id.item_Duplicate:

                                Screen.add(position, (HashMap) ((HashMap<String,?>) movieData.getItem(position)).clone());
                                mRecyclerViewAdapter.notifyItemInserted(position+1);
                                return true;
                            case R.id.item_Delete:

                                Screen.remove(position);
                                mRecyclerViewAdapter.notifyItemRemoved(position);
                                return true;
                        }

                        return false;
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popup, popup.getMenu());
                popup.show();
            }*/

        });



        return rootView;
    }
    private void itemAnimation()
    {
        SlideInDownAnimator animator = new SlideInDownAnimator();
        animator.setInterpolator(new OvershootInterpolator());
        animator.setAddDuration(100);
        animator.setRemoveDuration(100);
        mRecyclerView.setItemAnimator(animator);

    }
    private void adapterAnimation()
    {
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mRecyclerViewAdapter);
        SlideInRightAnimationAdapter scaleAdapter = new SlideInRightAnimationAdapter(alphaAdapter);
        mRecyclerView.setAdapter(scaleAdapter);
    }

    private class ActionBarCallback implements ActionMode.Callback {
        int position;

        public ActionBarCallback(int position) {
            this.position = position;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.popup, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            HashMap hm = (HashMap) movieData.getItem(position);
            mode.setTitle((String) hm.get("name"));
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            List<Map<String,?>> Screen = movieData.getMoviesList();
            switch (id)
            {
                case R.id.item_Delete:
                    Screen.remove(position);
                    mRecyclerViewAdapter.notifyItemRemoved(position);
                    mode.finish();
                    break;
                case R.id.item_Duplicate:
                    Screen.add(position, (HashMap) ((HashMap<String, ?>) movieData.getItem(position)).clone());
                    mRecyclerViewAdapter.notifyItemInserted(position + 1);
                    mode.finish();
                    break;
                default: break;

            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }
}


