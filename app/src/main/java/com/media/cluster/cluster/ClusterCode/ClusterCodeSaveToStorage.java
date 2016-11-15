package com.media.cluster.cluster.ClusterCode;

import android.app.Activity;
import android.content.Context;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.media.cluster.cluster.ClusterCodeFragment.ClusterCodeFragment;
import com.media.cluster.cluster.ClusterCodeFragment.ClusterCodeSave;
import com.media.cluster.cluster.SaveToExternalStorage.SaveBitmap;

import java.io.File;




class ClusterCodeSaveToStorage {


    private Context context;


     ClusterCodeSaveToStorage(Context context) {
        this.context = context;

    }

     void saveCodeToStorage(int id) {


        ClusterCodeFragment.switchCCCreateState(context, id, ClusterCodeFragment.SaveOptions.OVERRIDE, false);


        File dir = new File("sdcard/pictures/Cluster");
        try {
            Drawable clustercode = Drawable.createFromPath("sdcard/.cluster/cluster_code.png");
            Bitmap bm = ((BitmapDrawable) clustercode).getBitmap();
            SaveBitmap.saveFile(dir, "Clustercode.jpg", false, bm);
        } catch (NullPointerException e) {
            ClusterCodeSave clusterCodeSave = new ClusterCodeSave();
            SaveBitmap.saveFile(dir, "Clustercode.jpg", false, clusterCodeSave.getSingleDrawable());
        }

    }
}




