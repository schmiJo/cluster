package com.media.cluster.cluster.General;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.media.cluster.cluster.R;

public class DetailGifActivity extends AppCompatActivity {

    private  static  pl.droidsonroids.gif.GifDrawable drawable;
    private pl.droidsonroids.gif.GifImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gif);

        gifImageView = (pl.droidsonroids.gif.GifImageView) findViewById(R.id.cluster_detail_gif);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_gif_toolbar);
        setSupportActionBar(toolbar);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (getSupportActionBar() != null){
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle("");
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            FrameLayout frameLayout = (FrameLayout)findViewById(R.id.detail_gif_status);
            frameLayout.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().setStatusBarColor(Color.parseColor("#000000"));
            }
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(drawable != null){
                        gifImageView.setImageDrawable(drawable);
                        break;
                    }
                }
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_pic,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.detail_pic_save:
                break;
            case R.id.detail_pic_share:
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void setDrawable(pl.droidsonroids.gif.GifDrawable gifNew){
        drawable = gifNew;
    }
}
