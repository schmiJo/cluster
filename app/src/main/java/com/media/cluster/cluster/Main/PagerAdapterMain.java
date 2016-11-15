package com.media.cluster.cluster.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.media.cluster.cluster.Chats.MainChatListFragment;
import com.media.cluster.cluster.Feed.MainMediaFeedFragment;
import com.media.cluster.cluster.Profile.MainProfileFragment;
import com.media.cluster.cluster.Trending.MainTrendingFragment;

//Pager Adapter switches between the Main Feed Fragment and the Main Chat List Fragment
 class PagerAdapterMain extends FragmentPagerAdapter {

     PagerAdapterMain(FragmentManager fm) {


        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new MainMediaFeedFragment();
            case 2:
                return new MainChatListFragment();
            case 0:
                return new MainTrendingFragment();
            case 3:
                Bundle bundle = new Bundle();
                MainProfileFragment mainProfileFragment = new MainProfileFragment();
                bundle.putString("CN", MainActivity.CurrentClustername);
                mainProfileFragment.setArguments(bundle);
                return mainProfileFragment;

        }
        return null;
    }


    @Override
    public int getCount() {
        return 4;
    }

}
