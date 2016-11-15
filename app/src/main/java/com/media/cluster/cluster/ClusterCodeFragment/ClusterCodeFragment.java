package com.media.cluster.cluster.ClusterCodeFragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.media.cluster.cluster.R;


public class ClusterCodeFragment extends Fragment {


    View layout;
    static ImageView clusterCodeHolder;
    public enum SaveOptions {CREATE, LIBARY, OVERRIDE}
    View advancedView;
    static ProgressBar progressBar;



    public ClusterCodeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_cluster_code, container, false);
        clusterCodeHolder = (ImageView) layout.findViewById(R.id.clustercode_holder);
        progressBar = (ProgressBar) layout.findViewById(R.id.clustercode_progressbar);

        return layout;
    }


    public static void switchCCCCreateState(Context context, int AccountID, SaveOptions options) {

        switchCCCreateState(context, AccountID, options, true);
    }

    public static void switchCCCreateState(Context context, int AccountId, SaveOptions options, boolean show) {
        SwitchCCCreateStateThread switchCCCreateStateThread = new SwitchCCCreateStateThread();
        ClusterCodeFragment clusterCodeFragment = new ClusterCodeFragment();
        switchCCCreateStateThread.switchCCCreateStateThread(clusterCodeFragment.getAdvancedView(context), context.getResources(), AccountId, options, show);
    }

    public View getAdvancedView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        advancedView = inflater.inflate(R.layout.fragment_cluster_code, null);
        return advancedView;
    }


    public void showClusterCode(Bitmap bitmap) {
        clusterCodeHolder.setImageBitmap(bitmap);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showClusterCode(Drawable drawable) {
        clusterCodeHolder.setImageDrawable(drawable);
        progressBar.setVisibility(View.INVISIBLE);

    }
}





