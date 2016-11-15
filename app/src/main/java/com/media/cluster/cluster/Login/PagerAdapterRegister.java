package com.media.cluster.cluster.Login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

 class PagerAdapterRegister extends FragmentPagerAdapter{

     PagerAdapterRegister(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new RegisterNameFragment();
            case 1:
                return new RegisterDateFragment();
            case 2:
                return new RegisterClusterNameFragment();
            case 3:
                return new RegisterPasswordFragment();
            case 4:
                return new RegisterProfilePicFragment();
            case 5:
                return new RegisterNumberFragment();
            case 6:
                return new RegisterDetailFragment();
            case 7:
                return new RegisterFinishedFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 8;
    }
}
