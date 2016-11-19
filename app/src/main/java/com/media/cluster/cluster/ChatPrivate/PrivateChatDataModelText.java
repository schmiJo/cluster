package com.media.cluster.cluster.ChatPrivate;

import com.media.cluster.cluster.R;


 class PrivateChatDataModelText {
    String message;
    String sendingTime;
    int backgroundID;





     PrivateChatDataModelText(String sendingTime, String message, int socialMedias) {
        this.sendingTime = sendingTime;
        this.message = message;

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
