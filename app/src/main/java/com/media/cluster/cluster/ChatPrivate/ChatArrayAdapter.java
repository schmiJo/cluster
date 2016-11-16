package com.media.cluster.cluster.ChatPrivate;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.media.cluster.cluster.R;

import java.util.List;


 class ChatArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> items;

    final static private int TEXT = 0;
    final static private int IMAGE = 1;
    final static private int LOCATION = 2;
    final static private int GIF = 3;




     ChatArrayAdapter(List<Object> items) {
        this.items = items;
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


        }


    }

    private void configureTextViewHolder(PrivateChatTextViewHolder textVH, int position) {
        PrivateChatDataModelText dataModel = (PrivateChatDataModelText) items.get(position);

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

    private void configureImageViewHoder(final PrivateChatImageViewHolder imageVH, int position){
        final PrivateChatDataModelImage dataModel = (PrivateChatDataModelImage) items.get(position);

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

            }
        });

        imageVH.getImage().setImageBitmap(dataModel.image);

    }



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
        }else{
            return -1;
        }
    }


}
