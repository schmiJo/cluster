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

public class TwitterProfileTweetsFragment extends Fragment {

    View layout;
    RecyclerView recyclerView;
    public TwitterProfileTweetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout =  inflater.inflate(R.layout.fragment_twitter_profile_tweets, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.twitter_profile_tweets_recycler_view);

        recyclerView.setAdapter(new TwitterCommentRecyclerViewAdapter(getTweetsArrayList(), getActivity().getTheme(), getResources()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return layout;

    }
    private ArrayList<Object> getTweetsArrayList(){
        ArrayList<Object> tweets = new ArrayList<>();
                            tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016","james",true,45,44,false,true));
        tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.woman_undidentified),"atHome","Just typiccal response from Maggy","@jamesMay","Maggy May","@MaggyMay","Nov 7th 2016","james",true,45,44,false,true));
                            tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016","james",true,45,44,false,true));
        tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.woman_undidentified),"atHome","Just typiccal response from Maggy","@jamesMay","Maggy May","@MaggyMay","Nov 7th 2016","james",true,45,44,false,true));
                            tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016","james",true,45,44,false,true));
        tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.woman_undidentified),"atHome","Just typiccal response from Maggy","@jamesMay","Maggy May","@MaggyMay","Nov 7th 2016","james",true,45,44,false,true));
                            tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016","james",true,45,44,false,true));
        tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.woman_undidentified),"atHome","Just typiccal response from Maggy","@jamesMay","Maggy May","@MaggyMay","Nov 7th 2016","james",true,45,44,false,true));
                            tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016","james",true,45,44,false,true));
        tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.woman_undidentified),"atHome","Just typiccal response from Maggy","@jamesMay","Maggy May","@MaggyMay","Nov 7th 2016","james",true,45,44,false,true));
                            tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016","james",true,45,44,false,true));
        tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.woman_undidentified),"atHome","Just typiccal response from Maggy","@jamesMay","Maggy May","@MaggyMay","Nov 7th 2016","james",true,45,44,false,true));
                            tweets.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016","james",true,45,44,false,true));
         return tweets;
    }
}
