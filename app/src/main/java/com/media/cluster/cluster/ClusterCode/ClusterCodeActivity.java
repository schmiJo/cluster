package com.media.cluster.cluster.ClusterCode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.media.cluster.cluster.ClusterCodeFragment.ClusterCodeFragment;
import com.media.cluster.cluster.R;



public class ClusterCodeActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences loginPref = getSharedPreferences("userLoginInfo",MODE_PRIVATE);
        final int id = loginPref.getInt("id",0);

        setContentView(R.layout.activity_clustercode);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.ClusterCodeFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: 10/13/2016 Adding Scanner functionality


            }
        });

        ClusterCodeFragment.switchCCCCreateState(getApplicationContext(), id, ClusterCodeFragment.SaveOptions.LIBARY);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.clustercode_ab_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void saveCodeOnClick(MenuItem item) {
        SharedPreferences loginPref = getSharedPreferences("userLoginInfo",MODE_PRIVATE);
        final int id = loginPref.getInt("id",0);
        ClusterCodeSaveToStorage clusterCodeSaveToStorage = new ClusterCodeSaveToStorage(this);
        clusterCodeSaveToStorage.saveCodeToStorage(id);

        //Make sure that the gallery detects the picture
        MediaScannerConnection.scanFile(this,
                new String[]{"sdcard/pictures/Cluster/Clustercode.jpg"}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.d("debug", "Gallery Scan successful" + "Path: " +  path + " --- Uri: " + uri);



                    }
                }
        );
        Toast.makeText(ClusterCodeActivity.this, "Saved to Gallery", Toast.LENGTH_SHORT).show();
    }


    public void helpOnClick(MenuItem item){
        startActivity(new Intent(getApplicationContext(), ClusterCodeHelp.class));

    }


    //Handle the click on Back Button (parent Activity)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (android.os.Build.VERSION.SDK_INT > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {

        // TODO: 31.10.2016 Close activity animation
        //Intent startMainActivity = new Intent(getApplicationContext(), MainActivity.class);
        /*Bundle ClusterCodeBackTransition =
        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.activity_slide_in_left, R.anim.activity_slide_out_right).toBundle();
        startActivity(startMainActivity, ClusterCodeBackTransition);
        */
        finish();
    }
}
