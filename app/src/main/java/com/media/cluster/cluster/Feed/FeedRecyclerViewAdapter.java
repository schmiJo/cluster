package com.media.cluster.cluster.Feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.media.cluster.cluster.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetUtils;

import java.util.List;



 class FeedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    final static private int TWITTER = 0;

     FeedRecyclerViewAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case TWITTER:
                View twitterView = inflater.inflate(R.layout.twitter_tweet_layout, parent, false);
                viewHolder = new TwitterRowViewHolder(twitterView);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                viewHolder = new TwitterRowViewHolder(v);
                break;
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case TWITTER:
                TwitterRowViewHolder twitterVH = (TwitterRowViewHolder) holder;
                configureTwitterViewHolder(twitterVH, position);
                break;
        }


    }

    private void configureTwitterViewHolder(final TwitterRowViewHolder twitterVH, int position){
        final TweetData tweetData = (TweetData) items.get(position);

    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof TweetData) {
            return TWITTER;
        }else {
            return -1;
        }

    }
}
