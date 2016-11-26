package com.media.cluster.cluster.General;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.media.cluster.cluster.R;

 public class Utils {

     public static Bitmap drawableToBitmap(Drawable drawable) {
         if (drawable instanceof BitmapDrawable) {
             return ((BitmapDrawable)drawable).getBitmap();
         }

         int width = drawable.getIntrinsicWidth();
         width = width > 0 ? width : 1;
         int height = drawable.getIntrinsicHeight();
         height = height > 0 ? height : 1;

         Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
         Canvas canvas = new Canvas(bitmap);
         drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
         drawable.draw(canvas);

         return bitmap;
     }

     public static int getToolbarHeight(Context context) {
         final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                 new int[]{R.attr.actionBarSize});
         int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
         styledAttributes.recycle();

         return toolbarHeight;
     }




     public static float convertDpToPixel(float dp, Context context){
         Resources resources = context.getResources();
         DisplayMetrics metrics = resources.getDisplayMetrics();
         return dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
     }


     public static float convertPixelsToDp(float px, Context context){
         Resources resources = context.getResources();
         DisplayMetrics metrics = resources.getDisplayMetrics();
         return px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
     }


     public static int[] getDisplayDimensions(Context context){
         WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
         Display display = wm.getDefaultDisplay();
         Point size = new Point();
         display.getSize(size);
         int[] dim = new int[2];
         dim[1] = size.x;
         dim[2] = size.y;
         return dim;
     }


 }
