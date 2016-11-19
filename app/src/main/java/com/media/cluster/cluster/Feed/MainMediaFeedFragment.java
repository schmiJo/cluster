package com.media.cluster.cluster.Feed;


import android.app.ActivityOptions;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.media.cluster.cluster.Main.MainActivity;
import com.media.cluster.cluster.R;
import com.media.cluster.cluster.Twitter.EmbeddedTweetActivity;
import com.media.cluster.cluster.Twitter.TwitterClient;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainMediaFeedFragment extends Fragment{

    View layout;
    RecyclerView recyclerView;
    View noFeedError;

    public MainMediaFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_main_media_feed, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.main_feed_recycler_view);
        noFeedError = layout.findViewById(R.id.main_feed_no_feed_error);
        final ArrayList<Object> dataList = getDataArrayList();
        if (dataList.size() > 0) {
            noFeedError.setVisibility(View.GONE);
        } else {
            noFeedError.setVisibility(View.VISIBLE);
        }
        recyclerView.setAdapter(new FeedRecyclerViewAdapter(dataList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addOnItemTouchListener(new FeedRecyclerTouchListener(getContext(), new MainActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (dataList.get(position) instanceof TweetData) {
                    Bundle startTwitterBundle = ActivityOptions.makeCustomAnimation(getContext(),R.anim.activity_slide_in_right,R.anim.activity_scale_out).toBundle();
                    Intent startTwitter = new Intent(getContext(), EmbeddedTweetActivity.class);
                    startActivity(startTwitter,startTwitterBundle);

                }
            }
        }));
        return layout;
    }

    private ArrayList<Object> getDataArrayList() {
        ArrayList<Object> feedRows = new ArrayList<>();
        feedRows.add(new TweetData(getContext(), 794170457679392768L));
        feedRows.add(new TweetData(getContext(), 794170785015468032L));
        feedRows.add(new TweetData(getContext(), 794065597583753216L));
        feedRows.add(new TweetData(getContext(), 794164998251552769L));
        feedRows.add(new TweetData(getContext(), 793924304412999680L));
        feedRows.add(new TweetData(getContext(), 793922942019272704L));
        feedRows.add(new TweetData(getContext(), 794170518387593220L));
        feedRows.add(new TweetData(getContext(), 794169925157978116L));
        feedRows.add(new TweetData(getContext(), 794169139015413760L));
        feedRows.add(new TweetData(getContext(), 794169101404999680L));
        feedRows.add(new TweetData(getContext(), 794168691365740544L));
        feedRows.add(new TweetData(getContext(), 794237559106715649L));
        feedRows.add(new TweetData(getContext(), 794236200454328320L));
        feedRows.add(new TweetData(getContext(), 794247569832611840L));
        return feedRows;

    }


    class FeedRecyclerTouchListener implements RecyclerView.OnItemTouchListener {


        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        //Constructor
        private FeedRecyclerTouchListener(Context context, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            //Gesture Detector
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            //

        }
        //


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }


    private ArrayList<Object> getTwitterHomeFeed(){
        final ArrayList<Object> feedRows = new ArrayList<>();
        TwitterClient client = new TwitterClient(getContext());
        client.getHomeTimeline(1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                // Response is automatically parsed into a JSONArray

                try {
                for (int i = 0; i< json.length(); i++) {
                    long id = json.getJSONObject(i).getLong("id");
                    feedRows.add(new TweetData(getContext(), id));
                }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

        return feedRows;

    }



}
