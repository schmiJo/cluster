package com.media.cluster.cluster.ClusterCodeFragment;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;

import com.media.cluster.cluster.R;

import java.io.File;
import java.util.concurrent.TimeUnit;


 class SwitchCCCreateStateThread extends Thread {

    static LayerDrawable personalClusterCode;
    private Long decimalNumber;
    private ClusterCodeFragment.SaveOptions options;
    private File clusterCodePng = new File("sdcard/.cluster/cluster_code.png");
    private boolean show;
    private View view;

    void switchCCCreateStateThread(View view, final Resources resources,final int AccountID,final ClusterCodeFragment.SaveOptions options, final boolean show) {
    this.options = options;
        this.show = show;
        this.view = view;
        decimalNumber = 2000000000000L-AccountID;



        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Log.d("debug", "SwitchCCCreateStateThread called !!--- AccountID:  " + AccountID + "Decimalnumber: " + decimalNumber + ", SaveOptions: " + options + ", Show Cluster Code: " + show);
                if (options == ClusterCodeFragment.SaveOptions.LIBARY && clusterCodePng.exists()) {
                    Log.d("debug", "Create no new CC");
                    updatingUI();
                } else {
                    Log.d("debug", "Create Clustercode from scratch");
                    Log.d("debug", "----------------------Starting CCCreate Thread----------------------");
                    byte[] binaryStorage;
                    binaryStorage = new byte[42];
                    int BinaryStorageRow = 0;


                    while (decimalNumber > 0) {
                        byte binarynumber = (byte) (decimalNumber % 2);

                        binaryStorage[BinaryStorageRow++] = binarynumber;
                        decimalNumber = decimalNumber / 2;

                    }
                    int ones = 1;
                    for (int i = 0; i <= 41; i++) {
                        if (binaryStorage[i] == 1) {
                            ones++;
                        }

                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Drawable[] layers = new Drawable[ones];
                    layers[0] = (ResourcesCompat.getDrawable(resources, R.drawable.clustercode_ic_background2, null));
                    int layercount = 1;
                    for (int i = 40; i >= 0; i--) {
                        if (binaryStorage[i] == 1) {
                            switch (i + 1) {

                                case 1:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_01, null);
                                    Log.d("tag", "case 1");
                                    break;

                                case 2:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_02, null);
                                    Log.d("tag", "case 2");
                                    break;

                                case 3:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_03, null);
                                    Log.d("tag", "case 3");
                                    break;

                                case 4:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_04, null);
                                    Log.d("tag", "case 4");
                                    break;

                                case 5:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_05, null);
                                    Log.d("tag", "case 5");
                                    break;

                                case 6:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_06, null);
                                    Log.d("tag", "case 6");
                                    break;

                                case 7:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_07, null);
                                    Log.d("tag", "case 7");
                                    break;

                                case 8:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_08, null);
                                    Log.d("tag", "case 8");
                                    break;

                                case 9:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_09, null);
                                    Log.d("tag", "case 9");
                                    break;

                                case 10:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_10, null);
                                    Log.d("tag", "case 10");
                                    break;

                                case 11:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_11, null);
                                    Log.d("tag", "case 11");
                                    break;

                                case 12:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_12, null);
                                    Log.d("tag", "case 12");
                                    break;

                                case 13:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_13, null);
                                    Log.d("tag", "case 13");
                                    break;

                                case 14:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_14, null);
                                    Log.d("tag", "case 14");
                                    break;

                                case 15:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_15, null);
                                    Log.d("tag", "case 15");
                                    break;

                                case 16:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_16, null);
                                    Log.d("tag", "case 16");
                                    break;

                                case 17:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_17, null);
                                    Log.d("tag", "case 17");
                                    break;

                                case 18:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_18, null);
                                    Log.d("tag", "case 18");
                                    break;

                                case 19:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_19, null);
                                    Log.d("tag", "case 19");
                                    break;

                                case 20:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_20, null);
                                    Log.d("tag", "case 20");
                                    break;

                                case 21:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_21, null);
                                    Log.d("tag", "case 21");
                                    break;

                                case 22:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_22, null);
                                    Log.d("tag", "case 22");
                                    break;

                                case 23:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_23, null);
                                    Log.d("tag", "case 23");
                                    break;

                                case 24:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_24, null);
                                    Log.d("tag", "case 24");
                                    break;

                                case 25:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_25, null);
                                    Log.d("tag", "case 25");
                                    break;

                                case 26:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_26, null);
                                    Log.d("tag", "case 26");
                                    break;

                                case 27:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_27, null);
                                    Log.d("tag", "case 27");
                                    break;

                                case 28:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_28, null);
                                    Log.d("tag", "case 28");
                                    break;

                                case 29:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_29, null);
                                    Log.d("tag", "case 29");
                                    break;

                                case 30:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_30, null);
                                    Log.d("tag", "case 30");
                                    break;

                                case 31:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_31, null);
                                    Log.d("tag", "case 31");
                                    break;

                                case 32:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_32, null);
                                    Log.d("tag", "case 32");
                                    break;

                                case 33:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_33, null);
                                    Log.d("tag", "case 33");
                                    break;

                                case 34:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_34, null);
                                    Log.d("tag", "case 34");
                                    break;

                                case 35:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_35, null);
                                    Log.d("tag", "case 35");
                                    break;

                                case 36:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_36, null);
                                    Log.d("tag", "case 36");
                                    break;

                                case 37:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_37, null);
                                    Log.d("tag", "case 37");
                                    break;

                                case 38:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_38, null);
                                    Log.d("tag", "case 38");
                                    break;

                                case 39:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_39, null);
                                    Log.d("tag", "case 39");
                                    break;

                                case 40:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_40, null);
                                    Log.d("tag", "case 40");
                                    break;

                                case 41:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_41, null);
                                    Log.d("tag", "case 41");
                                    break;
                                case 42:
                                    layers[layercount] = ResourcesCompat.getDrawable(resources, R.drawable.clustercode_dot_42, null);
                                    Log.d("tag", "case 42");
                                    break;

                            }
                            layercount++;
                        } else {
                            Log.d("tag", "not Showed");

                        }
                    }


                    personalClusterCode = new LayerDrawable(layers);
                    Log.d("debug", "Finish creating Layerdrawable");
                    updatingUI();
                }

                Log.d("debug", "----------------------Ending CCCreate Thread----------------------");
            }

        };
        new Thread(runnable).start();

    }

    private void updatingUI() {

        Log.d("debug", "Called updatingUI");

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                final File clusterCodePng = new File("sdcard/.cluster/cluster_code.png");
                final ClusterCodeSave clusterCodeSave = new ClusterCodeSave();

                ClusterCodeFragment clusterCodeFragment = new ClusterCodeFragment();
                Log.d("debug", "Case:  " + options);
                switch (options) {

                    case CREATE:
                        //Take the image from the method
                        clusterCodeFragment.showClusterCode(clusterCodeSave.getSingleDrawable());
                        break;

                    case LIBARY:
                        if (clusterCodePng.exists()) {
                            //Take the image from the local storage
                            Log.d("debug", "exists:  " + clusterCodePng.exists());
                            clusterCodeFragment.showClusterCode(Drawable.createFromPath("sdcard/.cluster/cluster_code.png"));
                        } else {
                            //Save it to local storage
                            clusterCodeSave.saveClusterCode();
                            //Take the image from the method


                            clusterCodeFragment.showClusterCode(clusterCodeSave.getSingleDrawable());
                        }
                        break;

                    case OVERRIDE:
                        //Save it to local storage
                        clusterCodeSave.saveClusterCode();
                        if (show) {
                            //Take the image from the method
                            clusterCodeFragment.showClusterCode(clusterCodeSave.getSingleDrawable());
                        }
                        break;
                }

            }
        });
    }


}

