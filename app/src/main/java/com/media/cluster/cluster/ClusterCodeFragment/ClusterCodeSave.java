package com.media.cluster.cluster.ClusterCodeFragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.media.cluster.cluster.SaveToExternalStorage.SaveBitmap;

public class ClusterCodeSave {


    public Bitmap getSingleDrawable( ) {
        Log.d("debug", "Called get Single Drawable");
        Bitmap bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        SwitchCCCreateStateThread.personalClusterCode.setBounds(0, 0, 1000, 1000);
        SwitchCCCreateStateThread.personalClusterCode.draw(new Canvas(bitmap));

        return bitmap;
    }

     void saveClusterCode() {
        SaveBitmap.saveFile("cluster_code.png", getSingleDrawable());

    }







}


