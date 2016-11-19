package com.media.cluster.cluster.ChatPrivate;

import android.graphics.Bitmap;

import com.media.cluster.cluster.R;


 class PrivateChatDataModelImage {
    Bitmap image;
    String sendingTime;
    int backgroundID;





     PrivateChatDataModelImage(String sendingTime, Bitmap image, int socialMedias) {
        this.sendingTime = sendingTime;
        this.image = image;

        switch (socialMedias){
            case PrivateChatActivity.FACEBOOK:
                backgroundID = R.drawable.chat_bubble_facebook;
                break;
            case  PrivateChatActivity.SKYPE:
                backgroundID = R.drawable.chat_bubble_skype;
                break;
            case  PrivateChatActivity.TWITTER:
                backgroundID = R.drawable.chat_bubble_twitter;
                break;
            case  PrivateChatActivity.TUMBLR:
                backgroundID = R.drawable.chat_bubble_tumblr;
                break;
        }
    }
}