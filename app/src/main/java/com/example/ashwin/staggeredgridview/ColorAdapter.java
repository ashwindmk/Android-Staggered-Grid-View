package com.example.ashwin.staggeredgridview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by ashwin on 31/1/17.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder>{
    private static String[] mDataSet;
    private static Context mContext;
    private Random mRandom = new Random();

    public ColorAdapter(Context context,String[] DataSet){
        mDataSet = DataSet;
        mContext = context;
    }

    public static class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView mTextView;
        public CardView mCardView;
        public int position;

        public ColorViewHolder(View v)
        {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mCardView.setOnClickListener(this);

            mTextView = (TextView)v.findViewById(R.id.tv);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "You selected : " + mDataSet[position], Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_view,parent,false);
        ColorViewHolder vh = new ColorViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position)
    {
        if (holder instanceof ColorViewHolder)
        {
            holder.position = position;

            holder.mTextView.setText(mDataSet[position]);
            // Set a random height for TextView
            holder.mTextView.getLayoutParams().height = getRandomIntInRange(250, 75);

            // Set dark gray color for TextView background
            holder.mTextView.setBackgroundColor(Color.DKGRAY);

            // Set a random color for TextView background
            //holder.mTextView.setBackgroundColor(getRandomHSVColor());
        }
    }

    @Override
    public int getItemCount(){
        return mDataSet.length;
    }

    // Custom method to get a random number between a range
    protected int getRandomIntInRange(int max, int min){
        return mRandom.nextInt((max-min)+min)+min;
    }

    // Custom method to generate random HSV color
    protected int getRandomHSVColor()
    {
        // Generate a random hue value between 0 to 360
        int hue = mRandom.nextInt(361);

        // We make the color depth full
        float saturation = 1.0f;

        // We make a full bright color
        float value = 1.0f;

        // We avoid color transparency
        int alpha = 255;

        // Finally, generate the color
        int color = Color.HSVToColor(alpha, new float[]{hue, saturation, value});

        // Return the color
        return color;
    }
}
