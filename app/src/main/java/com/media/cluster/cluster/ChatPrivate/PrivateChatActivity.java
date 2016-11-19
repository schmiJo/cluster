package com.media.cluster.cluster.ChatPrivate;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.media.cluster.cluster.Account.AccountActivity;
import com.media.cluster.cluster.ClusterDBConnect.GetUserData;
import com.media.cluster.cluster.R;
import com.media.cluster.cluster.SaveToExternalStorage.SaveBitmap;

import java.util.ArrayList;


public class PrivateChatActivity extends AppCompatActivity {
    private String clustername;
    static ViewPager chatViewPager;
    View facebookTypefield;
    View facebookCardview;
    private static ImageView background;
    static RecyclerView privateChatrecyclerView;
    static ArrayList<Object> messages = new ArrayList<>();

    static public final int FACEBOOK =  0 ;
    static public final int SKYPE = 1;
    static public final int TWITTER =2 ;
    static public final int TUMBLR = 3;


    private static ChatArrayAdapter chatArrayAdapter;
    public static int heightFacebook;
    public static int heightTwitter;
    public static int marginFacebook;
    public static int marginTwitter;
    public static int recyclerPagerMargin;

    SharedPreferences viewPagerItem;

    public PrivateChatActivity() {
        //do nothing
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat);

        viewPagerItem = this.getSharedPreferences(
                "privateChatPagerItem", Context.MODE_PRIVATE);


        chatArrayAdapter = new ChatArrayAdapter(getPrivateChatData(), getApplicationContext());

        privateChatrecyclerView = (RecyclerView) findViewById(R.id.private_chat_recyclerview);

        privateChatrecyclerView.setAdapter(chatArrayAdapter);
        privateChatrecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        View viewPagerLayout = findViewById(R.id.private_chats_pager);
        facebookTypefield = findViewById(R.id.typefield_facebook_full);
        facebookCardview = findViewById(R.id.typefield_facebook_cardview);
        background = (ImageView) findViewById(R.id.private_chat_background);
        background.setImageDrawable(Drawable.createFromPath("sdcard/.cluster/wallpaper.png"));


        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        privateChatrecyclerView.setItemAnimator(itemAnimator);


        chatViewPager = (ViewPager) viewPagerLayout;
        chatViewPager.setAdapter(new PagerAdapterPrivateChat(getSupportFragmentManager()));
        chatViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        heightFacebook = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 135, getResources().getDisplayMetrics());
        heightTwitter = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());

        marginFacebook = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());
        marginTwitter = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

        recyclerPagerMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());




        //--------- Getting the name of the clicked chat-----------------------------
        Intent basicInfo = getIntent();
        setTitle(basicInfo.getStringExtra("Name"));
        clustername = basicInfo.getStringExtra("Clustername");


        chatViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchHeight(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        int savedPagerItem = viewPagerItem.getInt("privateChatPagerItem", Context.MODE_PRIVATE);
        chatViewPager.setCurrentItem(savedPagerItem);
        switchHeight(savedPagerItem);
        scrollToLastPosition();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        viewPagerItem.edit().putInt("privateChatPagerItem", chatViewPager.getCurrentItem()).apply();
        super.onSaveInstanceState(savedInstanceState);
    }

    public static void sendChatMessage(String message, int socialMedias) {


        long milliseconds = System.currentTimeMillis();

        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        String time = hours + ":" + minutes;

        messages.add(new PrivateChatDataModelText(time, message, socialMedias));
        Log.d("debug", "MESSAGES ARRAY:" + messages.toString());
        chatArrayAdapter.notifyDataSetChanged();
        privateChatrecyclerView.scrollToPosition(messages.size() - 1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.private_chat_ab_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.private_chat_mute:
                Toast.makeText(getApplication(), "Mute", Toast.LENGTH_SHORT).show();
                viewPagerItem.edit().putInt("privateChatPagerItem", chatViewPager.getCurrentItem()).apply();

                break;
            case R.id.private_chat_block:
                Toast.makeText(getApplication(), "Block", Toast.LENGTH_SHORT).show();
                break;
            case R.id.private_chat_contact:
                Intent openAccount = new Intent(getApplicationContext(), AccountActivity.class);
                openAccount.putExtra("Clustername", clustername);
                startActivity(openAccount);
                break;
            case R.id.private_chat_wallpaper:
                startActivity(new Intent(getApplicationContext(), SelectWallpaperSource.class));
                break;
            case android.R.id.home:
                viewPagerItem.edit().putInt("privateChatPagerItem", chatViewPager.getCurrentItem()).apply();
                PrivateChatActivity.this.finish();

        }
        return super.onOptionsItemSelected(item);
    }


    public void changeChatWallpaper(Bitmap bitmap) {
        background.setImageBitmap(bitmap);
        SaveBitmap.saveFile("wallpaper.png", bitmap);

    }


    public void commitSizeToPageViewer(final int height) {


        Log.d("debug", "PageViewerHeight getItem" + height);

        ViewGroup.LayoutParams sizePageViewer = chatViewPager.getLayoutParams();
        sizePageViewer.height = height;
        chatViewPager.setLayoutParams(sizePageViewer);

    }

    public static void scrollToLastPosition() {
        privateChatrecyclerView.scrollToPosition(messages.size() - 1);
    }

    public void switchHeight(int position) {

        switch (GetUserData.mediaToChatPrivate()[position]) {

            case GetUserData.FACEBOOK:
                Log.d("debug", "Facebook View Pager Height " + heightFacebook);
                commitSizeToPageViewer(heightFacebook);
                setRecyclerViewMargin(0);
                break;

            case GetUserData.SKYPE:
                Log.d("debug", "Skype View Pager Height " + heightFacebook);
                commitSizeToPageViewer(heightFacebook);
                setRecyclerViewMargin(1);
                break;

            case GetUserData.TUMBLR:
                Log.d("debug", "Tumblr View Pager Height " + heightTwitter);
                commitSizeToPageViewer(heightTwitter);
                setRecyclerViewMargin(2);
                break;
            case GetUserData.TWITTER:
                Log.d("debug", "Twitter View Pager Height " + heightTwitter);
                commitSizeToPageViewer(heightTwitter);
                setRecyclerViewMargin(3);
                break;
        }


    }


    public static void setRecyclerViewMargin(int marginBottom) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) privateChatrecyclerView.getLayoutParams();
        Log.d("debug", "setListViewMArgin: " + marginBottom);
        if(marginBottom == 0 || marginBottom == 1){
            marginBottom = marginFacebook;
            params.setMargins(0, 0, 0, marginBottom );
            scrollToLastPosition();
        }else if(marginBottom == 2 || marginBottom == 3){
            marginBottom = marginTwitter;
            params.setMargins(0, 0, 0, marginBottom );
            scrollToLastPosition();
        }

        params.setMargins(0, 0, 0, marginBottom + recyclerPagerMargin); //substitute parameters for left, top, right, bottom
        privateChatrecyclerView.setLayoutParams(params);


    }


    private ArrayList<Object> getPrivateChatData() {
        messages.add(new PrivateChatDataModelText("10:15", "bla bla bla", FACEBOOK));
        messages.add(new PrivateChatDataModelImage("11:07", BitmapFactory.decodeResource(getResources(),R.drawable.detail_hometown_button),TWITTER));
        return messages;
    }

    @Override
    public void onBackPressed() {
        viewPagerItem.edit().putInt("privateChatPagerItem", chatViewPager.getCurrentItem()).apply();
        Log.d("debug", "onBackPressed");
        PrivateChatActivity.this.finish();
    }
}

