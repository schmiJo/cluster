package com.media.cluster.cluster.Feed;


import android.app.ActivityOptions;
import android.content.Context;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.media.cluster.cluster.Main.MainActivity;
import com.media.cluster.cluster.R;
import com.media.cluster.cluster.Twitter.EmbeddedTweetActivity;
import com.media.cluster.cluster.Twitter.TwitterClient;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainMediaFeedFragment extends Fragment {

    View layout;
    RecyclerView recyclerView;
    View noFeedError;
    ImageView imageView;
    TextView textView;
    Button button;
    ArrayList<Object> dataList;
    public MainMediaFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_main_media_feed, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.main_feed_recycler_view);
        noFeedError = layout.findViewById(R.id.main_feed_no_feed_error);
         dataList = getDataArrayList();


        imageView = (ImageView) layout.findViewById(R.id.noFeedErrorImage);
        textView = (TextView) layout.findViewById(R.id.no_feed_message);
        button = (Button) layout.findViewById(R.id.no_feed_button);

        checkForConnection(dataList);

        return layout;
    }

    private ArrayList<Object> getDataArrayList() {
        ArrayList<Object> feedRows = new ArrayList<>();
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


    private ArrayList<Object> getTwitterHomeFeed() {
        final ArrayList<Object> feedRows = new ArrayList<>();
        TwitterClient client = new TwitterClient(getContext());
        client.getHomeTimeline(1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                // Response is automatically parsed into a JSONArray

                try {
                    for (int i = 0; i < json.length(); i++) {
                        long id = json.getJSONObject(i).getLong("id");
                        feedRows.add(new TweetData(getContext(), id));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return feedRows;

    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void checkForConnection(final List<Object> dataList) {
        if (!haveNetworkConnection()) {
            Snackbar.make(layout, getResources().getString(R.string.noInternetConnection), Snackbar.LENGTH_LONG).show();
            imageView.setImageResource(R.drawable.no_connection);
            textView.setText(R.string.noConnection);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(onClickListener());
            noFeedError.setVisibility(View.VISIBLE);

        } else if (haveNetworkConnection() && dataList.size() == 0) {
            imageView.setImageResource(R.drawable.no_feed);
            textView.setText(R.string.no_feed_message);
            button.setVisibility(View.GONE);
            noFeedError.setVisibility(View.VISIBLE);
        } else {
            noFeedError.setVisibility(View.GONE);
            recyclerView.setAdapter(new FeedRecyclerViewAdapter(dataList));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addOnItemTouchListener(new FeedRecyclerTouchListener(getContext(), new MainActivity.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    if (dataList.get(position) instanceof TweetData) {
                        Bundle startTwitterBundle = ActivityOptions.makeCustomAnimation(getContext(), R.anim.activity_slide_in_right, R.anim.activity_scale_out).toBundle();
                        Intent startTwitter = new Intent(getContext(), EmbeddedTweetActivity.class);
                        startActivity(startTwitter, startTwitterBundle);

                    }
                }
            }));
        }
    }

    private View.OnClickListener onClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkForConnection(dataList);

            }
        };
    }


}
