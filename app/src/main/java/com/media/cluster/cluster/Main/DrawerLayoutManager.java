package com.media.cluster.cluster.Main;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;


public class DrawerLayoutManager extends LinearLayoutManager {
    public DrawerLayoutManager(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
