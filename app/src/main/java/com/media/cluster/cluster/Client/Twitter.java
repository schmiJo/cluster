package com.media.cluster.cluster.Client;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;



public class Twitter extends OAuthBaseClient {

    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1/";// Change this, base API URL
    public static final String REST_CONSUMER_KEY = "xWYS45mQYzwXtFzwwiRxk6LQh";       // Change this
    public static final String REST_CONSUMER_SECRET = "RV82K47864J91BKDISTPE9i0g4VPPYPYhHAIyyiDquWSHCuT5P"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://cluster"; // Change this (here and in manifest)

    public Twitter(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

}
