package com.media.cluster.cluster.Twitter;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media.cluster.cluster.R;

import java.util.ArrayList;
import java.util.List;


public class TwitterProfileMediaFragment extends Fragment {

    RecyclerView recyclerView;
    View layout;
    public TwitterProfileMediaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_twitter_profile_media, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.twitter_profile_media_recycler);
        recyclerView.setAdapter(new TwitterProfileMediaAdapter(getMedia()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return layout;
    }


    private List<Object> getMedia(){
        List<Object> media = new ArrayList<>();
        media.add(new TwitterProfileMediaImageDataModel(BitmapFactory.decodeResource(getResources(),R.drawable.alien_unidentified)));
        media.add(new TwitterProfileMediaImageDataModel(BitmapFactory.decodeResource(getResources(),R.drawable.men_unidentified)));
        media.add(new TwitterProfileMediaImageDataModel(BitmapFactory.decodeResource(getResources(),R.drawable.woman_undidentified)));
        media.add(new TwitterProfileMediaImageDataModel(BitmapFactory.decodeResource(getResources(),R.drawable.aaa_chat_back_ground_light)));
        media.add(new TwitterProfileMediaImageDataModel(BitmapFactory.decodeResource(getResources(),R.drawable.alien_unidentified)));
        return media;
    }

}
