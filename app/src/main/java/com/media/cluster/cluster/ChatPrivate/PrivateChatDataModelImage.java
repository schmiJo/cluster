package com.media.cluster.cluster.ChatPrivate;

import android.graphics.Bitmap;

import com.media.cluster.cluster.R;


public class PrivateChatDataModelImage {
    Bitmap image;
    String sendingTime;
    int backgroundID;





    public PrivateChatDataModelImage(String sendingTime, Bitmap image, PrivateChatActivity.SocialMedias socialMedias) {
        this.sendingTime = sendingTime;
        this.image = image;

        switch (socialMedias){
            case FACEBOOK:
                backgroundID = R.drawable.chat_bubble_facebook;
                break;
            case SKYPE:
                backgroundID = R.drawable.chat_bubble_skype;
                break;
            case TWITTER:
                backgroundID = R.drawable.chat_bubble_twitter;
                break;
            case TUMBLR:
                backgroundID = R.drawable.chat_bubble_tumblr;
                break;
        }
    }
}