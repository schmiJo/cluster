package com.media.cluster.cluster.Twitter;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.media.cluster.cluster.Main.DrawerLayoutManager;
import com.media.cluster.cluster.R;

import java.util.ArrayList;


public class EmbeddedTweetActivity extends AppCompatActivity {

    private  Toolbar toolbar;
    LinearLayout EmbeddedTweet;
    ImageView profileImage;
    ImageButton replyButton , retweetButton, likeButton, messageButton, shareButton;
    Button followButton;
    View locationLayout;
    RecyclerView commentRecyclerView;
    TextView locationText ,timeText, messageText, nameText, usernameText, retweetText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_embedded_tweet);
        initializeViews();
        setSupportActionBar(toolbar);

        Drawable[] layers = new Drawable[2];


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layers[0] = getResources().getDrawable(R.drawable.men_unidentified,getTheme());
            layers[1] = getResources().getDrawable(R.drawable.twitter_image_frame,getTheme());
        }else{
            layers[0] = getResources().getDrawable(R.drawable.men_unidentified);
            layers[1] = getResources().getDrawable(R.drawable.twitter_image_frame);
        }
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        profileImage.setImageDrawable(layerDrawable);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.setStatusBarColor(getResources().getColor(R.color.twitterStatusBarColor, getTheme()));
                }



        } catch (NullPointerException e) {
            e.printStackTrace();
        }



        final ArrayList<Object> commentList = getCommentArrayList();
        if(commentList.size() == 0){
            commentRecyclerView.setVisibility(View.GONE);
        }
        commentRecyclerView.setAdapter(new TwitterCommentRecyclerViewAdapter(commentList, getTheme(),getResources()));
        commentRecyclerView.setLayoutManager(new DrawerLayoutManager(getApplicationContext()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tweet_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.twitter_action_search:
                Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), TwitterProfileViewActivity.class));
                break;
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.activity_scale_in, R.anim.activity_slide_out_right);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_scale_in, R.anim.activity_slide_out_right);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        finish();
        overridePendingTransition(R.anim.activity_scale_in, R.anim.activity_slide_out_right);
        return super.onKeyDown(keyCode, event);

    }

    private void initializeViews() {
        profileImage = (ImageView) findViewById(R.id.twitter_tweet_profile_image);
        EmbeddedTweet = (LinearLayout) findViewById(R.id.activity_embedded_tweet);
        toolbar = (Toolbar) findViewById(R.id.tweet_toolbar);
        replyButton = (ImageButton) findViewById(R.id.twitter_tweet_reply_button);
        retweetButton = (ImageButton) findViewById(R.id.twitter_tweet_retweet_button);
        likeButton = (ImageButton) findViewById(R.id.twitter_tweet_like_button);
        messageButton = (ImageButton) findViewById(R.id.twitter_tweet_message_button);
        shareButton = (ImageButton) findViewById(R.id.twitter_tweet_share_button);
        locationLayout = findViewById(R.id.twitter_tweet_location_layout);
        locationText = (TextView) findViewById(R.id.twitter_tweet_location_text);
        messageText = (TextView) findViewById(R.id.twitter_tweet_message_text);
        timeText = (TextView) findViewById(R.id.twitter_tweet_time_text);
        followButton = (Button) findViewById(R.id.twitter_tweet_follow_button);
        nameText = (TextView) findViewById(R.id.twitter_tweet_profile_name);
        usernameText = (TextView) findViewById(R.id.twitter_tweet_profile_twitter_name);
        retweetText = (TextView)findViewById(R.id.twitter_tweet_retweet_text_view);
        commentRecyclerView = (RecyclerView)findViewById(R.id.twitter_tweet_comment_recycler_view);
    }

    private ArrayList<Object> getCommentArrayList(){
        ArrayList<Object> comments = new ArrayList<>();
        comments.add(new TwitterCommentTextDataModel(BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016", "Stern", true,45,78,true, false));
        comments.add(new TwitterCommentImageDataModel(BitmapFactory.decodeResource(getResources(),R.drawable.alien_unidentified),BitmapFactory.decodeResource(getResources(), R.drawable.men_unidentified),"","Just typiccal response from Bob","","James May","@jamesMay","Nov 6th 2016", "Stern", true,45,78,true, false));


        return comments;
    }


}
