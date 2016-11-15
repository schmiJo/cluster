package com.media.cluster.cluster.Twitter;

import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.media.cluster.cluster.General.ImageRounder;
import com.media.cluster.cluster.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PostTwitterActivity extends AppCompatActivity {

    EditText messageEditText;
    TextView countTextView;
    ImageButton imageButton;
    ImageView profilePictureImageView;
    Button submitButton;
    int charLeft = 140;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getSupportActionBar().hide();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        setContentView(R.layout.activity_post_twitter);

        imageButton = (ImageButton) findViewById(R.id.twitter_post_camera_button);
        messageEditText = (EditText) findViewById(R.id.twitter_post_tweet_edit_text);
        countTextView = (TextView) findViewById(R.id.twitter_post_count_text);
        submitButton = (Button) findViewById(R.id.twitter_post_tweet_button);
        profilePictureImageView = (ImageView)findViewById(R.id.twitter_post_profile_pic);

        profilePictureImageView.setImageBitmap(ImageRounder.getRoundedImage(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),180));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postTweet(messageEditText.getText().toString().trim());
                messageEditText.setText(null);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageEditText.setHint(R.string.twitterUploadAddAComment);
            }
        });

        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charactersCountStatus(charSequence.toString())) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private boolean charactersCountStatus(String text) {
        int urlsCount = 0;
        int urlsCharCount = 0;

        String regex = "\\(?\\b(http://|https://|www[.])[-A-Za-z0-9+&@#/?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
        Pattern urlPattern = Pattern.compile(regex);
        Matcher urlMatcher = urlPattern.matcher(text);
        while (urlMatcher.find()) {
            urlsCount++;
            urlsCharCount += urlMatcher.group().length();
        }

        int tweetLength = text.length() - urlsCharCount + urlsCount * 23;
        charLeft = 140 - tweetLength;
        countTextView.setText(charLeft + "");

        return tweetLength > 0 && tweetLength <= 140;
    }

    private void postTweet(String text) {
        StatusesService statusesService = Twitter.getApiClient().getStatusesService();
        statusesService.update(text, null, false, null, null, null, false, false, null, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.twitterUploadSuccess), Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void failure(TwitterException e) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.twitterUploadError), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }


    public void closeActivty(View view) {
        finish();
    }
}
