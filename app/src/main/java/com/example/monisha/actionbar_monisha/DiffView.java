package com.example.monisha.actionbar_monisha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by monisha on 2/16/2016.
 */
public class DiffView extends RecyclerView.Adapter<DiffView.ViewHolder>
{

    private List<Map<String, ?>> mDataset;
    private Context mContext;
    OnItemClickListener mItemClickListener;
    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
        public void onItemLongClick(View view , int position);

    }

    @Override
    public int getItemViewType(int position) {
        Map<String, ?> item = mDataset.get(position);
        Double ratingD = (Double)item.get("rating");
        return ratingD.intValue();
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener)
    {
        this.mItemClickListener = mItemClickListener;
    }
    public DiffView(Context myContext, List<Map<String, ?>> myDataset)
    {
        mContext = myContext;
        mDataset = myDataset;
    }



    @Override
    public DiffView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;
        if(viewType >= 8)

        {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        }
        else

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview1, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public int getItemCount()
    {
        return mDataset.size();
    }
    @Override
    public void onBindViewHolder(DiffView.ViewHolder holder, int position) {
        Map<String, ?>movie = mDataset.get(position);
        holder.bindMovieData(movie);
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView vIcon ;
        public TextView vTitle;
        public RatingBar ratingBar;
        public TextView vDescription;
       // public CheckBox vCheckBox;
        MovieData movieData = new MovieData();
        public ViewHolder (View v)
        {

            super(v);

            ratingBar = (RatingBar) v.findViewById(R.id.rate);
            vIcon = (ImageView)v.findViewById(R.id.icon);
            vTitle = (TextView)v.findViewById(R.id.Title);
            vDescription = (TextView)v.findViewById(R.id.description);
           // vCheckBox = (CheckBox)v.findViewById(R.id.selection);
            v.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(mItemClickListener != null)
                    {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemLongClick(v, getPosition());
                    }
                    return true;
                }
            });

          /*  vCheckBox.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    HashMap movie = (HashMap) mDataset.get(getPosition());
                    //if(movie.get("selection"))
                    movie.put("selection",vCheckBox.isChecked());
                    //else movie.put("selection",true);
                }
            });*/

        }

        public void bindMovieData(Map<String, ?> movie) {
            vTitle.setText((String) movie.get("name"));
            vDescription.setText((String) movie.get("description"));
            vIcon.setImageResource((Integer) movie.get("image"));
           // vCheckBox.setChecked((Boolean) movie.get("selection"));

            Double ratingD = (Double)movie.get("rating");
            ratingBar.setRating(ratingD.floatValue() / 2);
        }
    }
}
