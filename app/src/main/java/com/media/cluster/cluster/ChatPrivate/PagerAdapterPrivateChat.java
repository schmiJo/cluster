package com.media.cluster.cluster.ChatPrivate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.media.cluster.cluster.ChatPrivate.Typefields.ChatTypefieldFacebook;
import com.media.cluster.cluster.ChatPrivate.Typefields.ChatTypefieldSkype;
import com.media.cluster.cluster.ChatPrivate.Typefields.ChatTypefieldTumblr;
import com.media.cluster.cluster.ChatPrivate.Typefields.ChatTypefieldTwitter;
import com.media.cluster.cluster.ClusterDBConnect.GetUserData;


class PagerAdapterPrivateChat extends FragmentPagerAdapter {

    PagerAdapterPrivateChat(FragmentManager fragmentManager) {

        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                switch (GetUserData.mediaToChatPrivate()[0]) {

                    case GetUserData.FACEBOOK:
                        return new ChatTypefieldFacebook();
                    case GetUserData.SKYPE:
                        return new ChatTypefieldSkype();
                    case GetUserData.TUMBLR:
                        return new ChatTypefieldTumblr();
                    case GetUserData.TWITTER:
                        return new ChatTypefieldTwitter();
                }
            case 1:
                switch (GetUserData.mediaToChatPrivate()[1]) {

                    case GetUserData.FACEBOOK:
                        return new ChatTypefieldFacebook();
                    case GetUserData.SKYPE:
                        return new ChatTypefieldSkype();
                    case GetUserData.TUMBLR:
                        return new ChatTypefieldTumblr();
                    case GetUserData.TWITTER:
                        return new ChatTypefieldTwitter();
                }
            case 2:
                switch (GetUserData.mediaToChatPrivate()[2]) {

                    case GetUserData.FACEBOOK:
                        return new ChatTypefieldFacebook();
                    case GetUserData.SKYPE:
                        return new ChatTypefieldSkype();
                    case GetUserData.TUMBLR:
                        return new ChatTypefieldTumblr();
                    case GetUserData.TWITTER:
                        return new ChatTypefieldTwitter();
                }
            case 3:
                switch (GetUserData.mediaToChatPrivate()[3]) {

                    case GetUserData.FACEBOOK:
                        return new ChatTypefieldFacebook();
                    case GetUserData.SKYPE:
                        return new ChatTypefieldSkype();
                    case GetUserData.TUMBLR:
                        return new ChatTypefieldTumblr();
                    case GetUserData.TWITTER:
                        return new ChatTypefieldTwitter();
                }


        }

        return null;
    }


    @Override
    public int getCount() {
        return GetUserData.mediaToChatPrivate().length;
    }


}

