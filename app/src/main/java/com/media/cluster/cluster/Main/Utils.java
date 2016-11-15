package com.media.cluster.cluster.Main;

import android.content.Context;
import android.content.res.TypedArray;

import com.media.cluster.cluster.R;

 class Utils {

     static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

}
