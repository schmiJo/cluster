package com.media.cluster.cluster.Profile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.media.cluster.cluster.Profile.ProfileTabs.FeedTab.ProfileFeedFragment;
import com.media.cluster.cluster.Profile.ProfileTabs.InfoTab.ProfileInfoFragment;
import com.media.cluster.cluster.Profile.ProfileTabs.TilesTap.ProfileTilesFragment;

class PagerAdapterProfile extends FragmentPagerAdapter {
    private Context context = null;

    PagerAdapterProfile(Context context, FragmentManager fm) {
       super(fm);
        this.context = context;
   }


   @Override
   public Fragment getItem(int position) {


       switch (position){
           case 0:
               Log.d("debug", "getItem 0");
               return new ProfileInfoFragment();
           case 1:
               Log.d("debug", "getItem 1");
               return new ProfileFeedFragment();
           case 2:
               Log.d("debug", "getItem 2");
               return new ProfileTilesFragment();


       }
       return null;
   }

   @Override
   public int getCount() {
       return 3;
   }

}
