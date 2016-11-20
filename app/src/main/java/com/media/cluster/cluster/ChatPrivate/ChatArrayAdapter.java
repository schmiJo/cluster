package com.media.cluster.cluster.ChatPrivate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.media.cluster.cluster.General.DetailGifActivity;
import com.media.cluster.cluster.General.DetailPictureActivity;
import com.media.cluster.cluster.R;

import java.util.List;

import pl.droidsonroids.gif.GifDrawable;


class ChatArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;
    private Context context;
    private Resources resources;
    final static private int TEXT = 0;
    final static private int IMAGE = 1;
     final static private int GIF = 2;




     ChatArrayAdapter(List<Object> items, Context context, Resources resources) {
        this.items = items;
         this.context = context;
         this.resources = resources;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TEXT:
                View textView = inflater.inflate(R.layout.chat_row_text_layout, parent, false);
                viewHolder = new PrivateChatTextViewHolder(textView);
                break;
            case IMAGE:
                View imageView = inflater.inflate(R.layout.chat_row_image_layout, parent, false);
                viewHolder = new PrivateChatImageViewHolder(imageView);
                break;
            case GIF:
                View gifView = inflater.inflate(R.layout.chat_row_gif_layout, parent, false);
                viewHolder = new PrivateChatGifViewHolder(gifView);
                break;
            default:
                View defaultTextView = inflater.inflate(R.layout.chat_row_text_layout, parent, false);
                viewHolder = new PrivateChatTextViewHolder(defaultTextView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TEXT:
                PrivateChatTextViewHolder textVH = (PrivateChatTextViewHolder) holder;
                configureTextViewHolder(textVH, position);
                break;
            case IMAGE:
                PrivateChatImageViewHolder imageVH = (PrivateChatImageViewHolder) holder;
                configureImageViewHoder(imageVH, position);
                break;
            case GIF:
                PrivateChatGifViewHolder gifVH = (PrivateChatGifViewHolder) holder;
                configureGifViewHoder(gifVH, position, resources);

        }


    }

    private void configureTextViewHolder(final PrivateChatTextViewHolder textVH, int position) {
        final PrivateChatDataModelText dataModel = (PrivateChatDataModelText) items.get(position);


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                textVH.getTime().setText(dataModel.sendingTime);
                textVH.getMessage().setText(dataModel.message);
                textVH.getBackground().setBackgroundResource(dataModel.backgroundID);
                switch (dataModel.backgroundID){
                    case R.drawable.chat_bubble_facebook:
                        textVH.getMedia().setImageResource(R.drawable.round_service_ic_facebook);
                        break;
                    case R.drawable.chat_bubble_skype:
                        textVH.getMedia().setImageResource(R.drawable.round_service_ic_skype);
                        break;
                    case R.drawable.chat_bubble_twitter:
                        textVH.getMedia().setImageResource(R.drawable.round_service_ic_twitter);
                        break;
                    case R.drawable.chat_bubble_tumblr:
                        textVH.getMedia().setImageResource(R.drawable.round_service_ic_tumblr);
                        break;
                }
            }
        });



    }

    private void configureImageViewHoder(final PrivateChatImageViewHolder imageVH, int position){
        final PrivateChatDataModelImage dataModel = (PrivateChatDataModelImage) items.get(position);


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                imageVH.getTime().setText(dataModel.sendingTime);
                imageVH.getRoot().setBackgroundResource(dataModel.backgroundID);
                switch (dataModel.backgroundID){
                    case R.drawable.chat_bubble_facebook:
                        imageVH.getMedia().setImageResource(R.drawable.round_service_ic_facebook);
                        break;
                    case R.drawable.chat_bubble_skype:
                        imageVH.getMedia().setImageResource(R.drawable.round_service_ic_skype);
                        break;
                    case R.drawable.chat_bubble_twitter:
                        imageVH.getMedia().setImageResource(R.drawable.round_service_ic_twitter);
                        break;
                    case R.drawable.chat_bubble_tumblr:
                        imageVH.getMedia().setImageResource(R.drawable.round_service_ic_tumblr);
                        break;
                }
                imageVH.getImage().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        ViewGroup.LayoutParams params = imageVH.getImage().getLayoutParams();
                        params.height = (dataModel.image.getHeight() / dataModel.image.getWidth()) * imageVH.getImage().getWidth();
                        imageVH.getImage().setLayoutParams(params);



                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            imageVH.getImage().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            imageVH.getImage().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }

                    }
                });
                imageVH.getImage().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(context, DetailPictureActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                        DetailPictureActivity.setDrawable(imageVH.getImage().getDrawable());
                    }
                });
                Bitmap compressed  = Bitmap.createScaledBitmap(dataModel.image,300, (dataModel.image.getHeight() / dataModel.image.getWidth())*300 ,true);
                imageVH.getImage().setImageBitmap(compressed);
            }
        });


    }

     private void configureGifViewHoder(final  PrivateChatGifViewHolder gifVH, int position,final  Resources res){
         final PrivateChatDataModelGif dataModel = (PrivateChatDataModelGif) items.get(position);


         new Handler().post(new Runnable() {
             @Override
             public void run() {
                 gifVH.getTime().setText(dataModel.sendingTime);
                 gifVH.getRoot().setBackgroundResource(dataModel.backgroundID);
                 switch (dataModel.backgroundID){
                     case R.drawable.chat_bubble_facebook:
                         gifVH.getMedia().setImageResource(R.drawable.round_service_ic_facebook);
                         break;
                     case R.drawable.chat_bubble_skype:
                         gifVH.getMedia().setImageResource(R.drawable.round_service_ic_skype);
                         break;
                     case R.drawable.chat_bubble_twitter:
                         gifVH.getMedia().setImageResource(R.drawable.round_service_ic_twitter);
                         break;
                     case R.drawable.chat_bubble_tumblr:
                         gifVH.getMedia().setImageResource(R.drawable.round_service_ic_tumblr);
                         break;
                 }

                 gifVH.getImage().setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent i = new Intent(context, DetailGifActivity.class);
                         i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         context.startActivity(i);
                         DetailGifActivity.setDrawable(GifDrawable.createFromResource(res,R.drawable.aaa_test_bear_gif));


                     }
                 });
                 gifVH.getImage().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                     @Override
                     public void onGlobalLayout() {
                         /*ViewGroup.LayoutParams params = gifVH.getImage().getLayoutParams();
                         Bitmap bitmap = Utils.drawableToBitmap(GifDrawable.createFromResource(res,R.drawable.aaa_test_bear_gif));
                         params.height = ((bitmap.getHeight()+1) / (bitmap.getWidth()+1)) * 300;
                         gifVH.getImage().setLayoutParams(params);
                         */



                         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                             gifVH.getImage().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                         } else {
                             gifVH.getImage().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                         }

                     }
                 });
                 gifVH.getImage().setImageDrawable(GifDrawable.createFromResource(res,R.drawable.aaa_test_bear_gif));

             }});}







    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof PrivateChatDataModelText) {
            return TEXT;
        } else if(items.get(position)instanceof  PrivateChatDataModelImage) {
            return IMAGE;
        }else if(items.get(position)instanceof  PrivateChatDataModelGif) {
            return  GIF;
        }else{
            return -1;
        }
    }

 }
