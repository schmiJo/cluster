package com.media.cluster.cluster.ChatPrivate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media.cluster.cluster.R;

import java.util.List;


public class ChatArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflator;
    private List<Object> items;

    final static private int TEXT = 0;
    final static private int IMAGE = 1;
    final static private int LOCATION = 2;
    final static private int GIF = 3;




    public ChatArrayAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TEXT:
                View textView = inflater.inflate(R.layout.private_chat_row_layout, parent, false);
                viewHolder = new PrivateChatTextViewHolder(textView);
                break;

            default:
                View defaultTextView = inflater.inflate(R.layout.private_chat_row_layout, parent, false);
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



    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof PrivateChatDataModelText) {
            return TEXT;
        } else {
            return -1;
        }
    }


}
