package com.media.cluster.cluster.Trending;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.media.cluster.cluster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainTrendingFragment extends Fragment {

    View layout;
    TextView noFeedError;
    public MainTrendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_main_trending, container, false);
        noFeedError = (TextView) layout.findViewById(R.id.no_feed_message);


        return layout;
    }

}
