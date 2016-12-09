package com.media.cluster.cluster.Login.AddServices;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;

import com.media.cluster.cluster.General.Utils;
import com.media.cluster.cluster.R;


public class SelectServicesView extends SurfaceView implements Runnable, SurfaceHolder.Callback {


    Thread thread = null;
    Context appContext;
    Context context;
    Canvas canvas;
    SurfaceHolder surfaceHolder;
    Bitmap facebookB;
    Bitmap skypeB;
    Bitmap twitterB;
    Bitmap tumblrB;
    Bitmap background;

    private int serviceCounter = 0;
    private int xFacebook = 0;
    private int yFacebook = 0;
    private int xSkype = 0;
    private int ySkype = 0;
    private int xTwitter = 0;
    private int yTwitter = 0;
    private int xTumblr = 0;
    private int yTumblr = 0;

    boolean canDraw = false;
    boolean facebook = false;
    boolean skype = false;
    boolean twitter = false;
    boolean tumblr = false;

    private int width;
    private int height;
    private int radius;
    private int drawableRadius;
    private float targetRadius;
    private float radiusDecrement;

    Bitmap resizedBackground;
    private float xActionDown = 0;
    private float yActionDown = 0;
    private VelocityTracker mVelocityTracker = VelocityTracker.obtain();

    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(int itemId);
    }


    public SelectServicesView(Context context) {
        super(context);
    }

    public SelectServicesView(Context context, Context applicationContext, Boolean facebook, Boolean skype, Boolean twitter, Boolean tumblr) {
        super(context);
        this.appContext = applicationContext;
        this.context = context;
        getHolder().addCallback(this);
        surfaceHolder = getHolder();
        this.facebook = facebook;
        this.skype = skype;
        this.twitter = twitter;
        this.tumblr = tumblr;
        init();

    }


    private void init() {
        if (facebook) {
            serviceCounter++;
            facebookB = Utils.drawableToBitmap(appContext, R.drawable.ic_facebook_round_dark);
            drawableRadius = facebookB.getWidth() / 2;
        }
        if (skype) {
            serviceCounter++;
            skypeB = Utils.drawableToBitmap(appContext, R.drawable.ic_skype_round_dark);
            drawableRadius = skypeB.getWidth() / 2;
        }
        if (twitter) {
            serviceCounter++;
            twitterB = Utils.drawableToBitmap(appContext, R.drawable.ic_twitter_round_dark);
            drawableRadius = twitterB.getWidth() / 2;
        }
        if (tumblr) {
            serviceCounter++;
            tumblrB = Utils.drawableToBitmap(appContext, R.drawable.ic_tumblr_round_dark);
            drawableRadius = tumblrB.getWidth() / 2;
        }


        this.width = Utils.getDisplayDimensions(appContext, true);
        this.height = Utils.getDisplayDimensions(appContext, false);
        radius = width;
        radiusDecrement = Utils.convertDpToPixel(15, appContext);
        targetRadius = Utils.convertDpToPixel(80, appContext);


//Scaling Background
        background = Utils.drawableToBitmap(appContext, R.drawable.space_background, width, height);
        int width = background.getWidth();
        int height = background.getHeight();
        float scaleWidth = ((float) this.width) / width;
        float scaleHeight = ((float) this.height) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        resizedBackground = Bitmap.createBitmap(background, 0, 0, width, height, matrix, false);
//


        try {
            Activity activity = (Activity) context;
            onItemClickListener = (OnItemClickListener) activity;
            Log.d("debug", "OnAttach");
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int action = event.getActionMasked();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                xActionDown = event.getX();
                yActionDown = event.getY();
                mVelocityTracker.clear();
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;

            case MotionEvent.ACTION_UP:

                if ((Math.abs(xActionDown - event.getX()) < 6 && Math.abs(yActionDown - event.getY()) < 6)) {

                    if (facebook) {
                        if (xActionDown > (xFacebook - drawableRadius) && yActionDown > (yFacebook - drawableRadius) && xActionDown < (xFacebook + drawableRadius) && yActionDown < (yFacebook + drawableRadius)) {
                            onItemClickListener.onItemClick(1);
                        }
                    }
                    if (skype) {
                        if (xActionDown > (xSkype - drawableRadius) && yActionDown > (ySkype - drawableRadius) && xActionDown < (xSkype + drawableRadius) && yActionDown < (ySkype + drawableRadius)) {
                            onItemClickListener.onItemClick(2);
                        }
                    }
                    if (twitter) {
                        if (xActionDown > (xTwitter - drawableRadius) && yActionDown > (yTwitter - drawableRadius) && xActionDown < (xTwitter + drawableRadius) && yActionDown < (yTwitter + drawableRadius)) {
                            onItemClickListener.onItemClick(3);
                        }
                    }
                    if (tumblr) {
                        if (xActionDown > (xTumblr - drawableRadius) && yActionDown > (yTumblr - drawableRadius) && xActionDown < (xTumblr + drawableRadius) && yActionDown < (yTumblr + drawableRadius)) {
                            onItemClickListener.onItemClick(4);
                        }
                    }

                }

                //Reset variables
                xActionDown = 0;
                yActionDown = 0;
                break;

            case MotionEvent.ACTION_MOVE:
                break;

        }
        return true;

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {
        while (canDraw) {


            if (!surfaceHolder.getSurface().isValid()) {
                //if surfaceHolder is not valid, the while loop will continued, if it is the while loop will be skipped
                continue;
            }


            canvas = surfaceHolder.lockCanvas();

            canvas.drawBitmap(resizedBackground, 0, 0, null);

            int servicePosition = 0;
            if (facebook) {
                servicePosition++;
                xFacebook = calculateCoordinates(true, servicePosition);
                yFacebook = calculateCoordinates(false, servicePosition);
                canvas.drawBitmap(facebookB, xFacebook - drawableRadius, yFacebook - drawableRadius, null);
            }
            if (skype) {
                servicePosition++;
                xSkype = calculateCoordinates(true, servicePosition);
                ySkype = calculateCoordinates(false, servicePosition);
                canvas.drawBitmap(skypeB, xSkype - drawableRadius, ySkype - drawableRadius, null);
            }
            if (twitter) {
                servicePosition++;
                xTwitter = calculateCoordinates(true, servicePosition);
                yTwitter = calculateCoordinates(false, servicePosition);
                canvas.drawBitmap(twitterB, xTwitter - drawableRadius, yTwitter - drawableRadius, null);
            }
            if (tumblr) {
                servicePosition++;
                xTumblr = calculateCoordinates(true, servicePosition);
                yTumblr = calculateCoordinates(false, servicePosition);
                canvas.drawBitmap(tumblrB, xTumblr - drawableRadius, yTumblr - drawableRadius, null);
            }
            radius = radius - (int)radiusDecrement;


            if (radius <= targetRadius) {
                canDraw = false;
            }

            surfaceHolder.unlockCanvasAndPost(canvas);

        }

    }


    public void pause() {
        //join blocks the Thread until the receiver finishes its execution and dies
        canDraw = false;
        while (true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;


    }

    //The resume Method is called when the class has been called
    public void resume() {
        canDraw = true;
        //Thread(this) is appointed to the run method above
        thread = new Thread(this);
        thread.start();
    }


    private int calculateCoordinates(Boolean xValue, int position) {
        double shiftFactor = 0;
        switch (serviceCounter) {
            case 1:
                radius = 0;
                break;
            case 3:
                shiftFactor = Math.PI / 2;
                break;
        }

        if (xValue) {
            return (int) (width / 2 + (radius * Math.cos(shiftFactor + position * (2 * Math.PI) / serviceCounter)));
        } else {
            //calculate y variables and move it a bit above
            return (int) (height / 2 + (radius * Math.sin(shiftFactor + position * (2 * Math.PI) / serviceCounter)) - targetRadius);
        }
    }



}
