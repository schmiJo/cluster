package com.media.cluster.cluster.Feed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.media.cluster.cluster.R;


 class TwitterRowViewHolder extends RecyclerView.ViewHolder {


    private FrameLayout twitterRowLayout;
     TwitterRowViewHolder(View itemView) {
        super(itemView);
        twitterRowLayout = (FrameLayout)itemView.findViewById(R.id.tweet_layout);
    }

     FrameLayout getTwitterRowLayout(){
        return twitterRowLayout;
    }



}
