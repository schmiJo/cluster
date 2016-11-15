package com.media.cluster.cluster.Feed;

import android.content.Context;

 class TweetData {
    long id;
    Context context;

     TweetData(Context context, long id) {
        this.context = context;
        this.id = id;
    }
}
