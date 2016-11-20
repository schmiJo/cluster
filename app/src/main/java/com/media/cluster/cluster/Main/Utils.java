package com.media.cluster.cluster.Main;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.media.cluster.cluster.R;

 public class Utils {

     static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }



     public static Bitmap drawableToBitmap (Drawable drawable) {
         Bitmap bitmap = null;

         if (drawable instanceof BitmapDrawable) {
             BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
             if(bitmapDrawable.getBitmap() != null) {
                 return bitmapDrawable.getBitmap();
             }
         }

         if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
             bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
         } else {
             bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
         }

         Canvas canvas = new Canvas(bitmap);
         drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
         drawable.draw(canvas);
         return bitmap;
     }



 }
