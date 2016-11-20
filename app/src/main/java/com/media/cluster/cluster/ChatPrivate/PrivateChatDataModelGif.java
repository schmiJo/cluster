package com.media.cluster.cluster.ChatPrivate;


import com.media.cluster.cluster.R;

import pl.droidsonroids.gif.GifDrawable;


class PrivateChatDataModelGif {
    GifDrawable gif;
    String sendingTime;
    int backgroundID;





    PrivateChatDataModelGif(String sendingTime, GifDrawable gif, int socialMedias) {
       this.sendingTime = sendingTime;
       this.gif = gif;

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