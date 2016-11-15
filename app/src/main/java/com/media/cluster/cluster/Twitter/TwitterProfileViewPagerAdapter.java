package com.media.cluster.cluster.Twitter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


 class TwitterProfileViewPagerAdapter extends FragmentPagerAdapter {

     TwitterProfileViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new TwitterProfileTweetsFragment();

            case 1:
                return new TwitterProfileMediaFragment();

            case 2:
                return new TwitterProfileLikesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
