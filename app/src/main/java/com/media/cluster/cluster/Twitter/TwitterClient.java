package com.media.cluster.cluster.Twitter;


import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;


public class TwitterClient extends OAuthBaseClient {



    private static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;
    private static final String TWITTER_REST_URL = "https://api.twitter.com/1.1";
    private static final String TWITTER_KEY = "8W1vhfZ4ujqq8LOXxronKkuDe ";
    private static final String TWITTER_SECRET = "pcAbYnMhkxkxS7JNVNFvgsUbBb0H3xLOM1phWL3wZL8LtfVkJ4";
    private static final String TWITTER_REST_CALLBACK_URL = "oauth://com.media.cluster.cluster";

    public TwitterClient(Context context) {
        super(context,REST_API_CLASS, TWITTER_REST_URL,
                TWITTER_KEY,TWITTER_SECRET, TWITTER_REST_CALLBACK_URL);

    }
    public void postTweet(String body, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", body);
        getClient().post(apiUrl, params, handler);
    }

     public void getHomeTimeline(int page, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        RequestParams params = new RequestParams();
        params.put("page", String.valueOf(page));
        getClient().get(apiUrl, params, handler);
    }



}
