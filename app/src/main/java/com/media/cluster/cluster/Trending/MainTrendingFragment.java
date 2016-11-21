package com.media.cluster.cluster.Trending;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainTrendingFragment extends Fragment {

    View layout,noFeedError ;
    TextView textView;
    Button button;
    ImageView imageView;
    public MainTrendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_main_trending, container, false);
        textView = (TextView) layout.findViewById(R.id.no_feed_message);
        imageView = (ImageView) layout.findViewById(R.id.noFeedErrorImage);
        button = (Button) layout.findViewById(R.id.no_feed_button);
        noFeedError = layout.findViewById(R.id.no_trending_feed_error);
        checkForConnection();

        return layout;
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

    private void checkForConnection() {
        if (!haveNetworkConnection()) {
            Snackbar.make(layout, getResources().getString(R.string.noInternetConnection), Snackbar.LENGTH_LONG).show();
            imageView.setImageResource(R.drawable.no_connection);
            textView.setText(R.string.noConnection);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(onClickListener());
            noFeedError.setVisibility(View.VISIBLE);

        } else if (haveNetworkConnection() ) {
            imageView.setImageResource(R.drawable.no_feed);
            textView.setText(R.string.no_feed_message);
            button.setVisibility(View.GONE);
            noFeedError.setVisibility(View.VISIBLE);
        } else {
            noFeedError.setVisibility(View.GONE);
        }
    }

    private View.OnClickListener onClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkForConnection();

            }
        };
    }

}
