package com.media.cluster.cluster.Twitter;

import android.graphics.Bitmap;

 class TwitterCommentImageDataModel {
    Bitmap imageView, sendImage;
   boolean promoted,liked, retweeted;
    String twitterUserName,twitterName, message, respondedPerson, location, date, retweet;
    int retweets, likes;




    TwitterCommentImageDataModel(Bitmap sendImage,Bitmap imageView, String location, String message, String respondedPerson, String twitterName, String twitterUserName, String date, String retweet, boolean promoted, int retweets, int likes, boolean liked, boolean retweeted) {
        this.liked = liked;
        this.retweeted = retweeted;
       this.imageView = imageView;
       this.location = location;
       this.message = message;
       this.respondedPerson = respondedPerson;
       this.twitterName = twitterName;
       this.twitterUserName = twitterUserName;
       this.date = date;
       this.retweet = retweet;
       this.promoted = promoted;
        this.likes = likes;
        this.retweets = retweets;
        this.sendImage = sendImage;
   }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

     void setLiked(boolean liked) {
        this.liked = liked;
    }
}
