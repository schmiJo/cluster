package com.media.cluster.cluster.Twitter;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media.cluster.cluster.Main.DrawerLayoutManager;
import com.media.cluster.cluster.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwitterProfileLikesFragment extends Fragment {

    RecyclerView recyclerView;
    View layout;

    public TwitterProfileLikesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout =  inflater.inflate(R.layout.fragment_twitter_profile_likes, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.twitter_profile_likes_recycler_view);
        recyclerView.setAdapter(new TwitterCommentRecyclerViewAdapter(getLikesArrayList(),getActivity().getTheme(),getResources()));
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        return layout;
    }
    private ArrayList<Object> getLikesArrayList(){
        ArrayList<Object> likes = new ArrayList<>();
                            likes.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016","maggy",true,34,34,true,false));
        likes.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.woman_undidentified),"atHome","Just typiccal response from Maggy","@jamesMay","Maggy May","@MaggyMay","Nov 7th 2016","maggy",true,56,22,false, true));



        return likes;
    }
}
