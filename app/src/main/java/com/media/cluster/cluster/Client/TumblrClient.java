package com.media.cluster.cluster.Client;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;


public class TumblrClient extends OAuthBaseClient {



    private static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    private static final String TWITTER_REST_URL = "https://api.tumblr.com";
    private static final String TWITTER_KEY = "d6K1i8KPDhnyxhWPVjfca6XdibcSBWxUwQpVdfORLe79vvbTRi";
    private static final String TWITTER_SECRET = "URoc5WNqwLRPQX4bgjRY3hno7g0yLcR8QpiFsfEiQ5JeLrolNR";
    private static final String TWITTER_REST_CALLBACK_URL = "oauth://com.media.cluster.cluster";

    public TumblrClient(Context context) {
        super(context,REST_API_CLASS, TWITTER_REST_URL,
                TWITTER_KEY,TWITTER_SECRET, TWITTER_REST_CALLBACK_URL);

    }
}
