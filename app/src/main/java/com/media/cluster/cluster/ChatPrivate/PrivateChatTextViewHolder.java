package com.media.cluster.cluster.ChatPrivate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.media.cluster.cluster.R;


public class PrivateChatTextViewHolder extends RecyclerView.ViewHolder{

    private TextView message;
    private TextView time;
    private LinearLayout root;
    private ImageView media;

    public PrivateChatTextViewHolder(View itemView) {
        super(itemView);
         message = (TextView) itemView.findViewById(R.id.private_message);
        time = (TextView) itemView.findViewById(R.id.private_message_time);
        root = (LinearLayout) itemView.findViewById(R.id.private_message_root);
        media = (ImageView) itemView.findViewById(R.id.private_message_media);
    }

     TextView getTime() {
        return time;
    }

     TextView getMessage() {
        return message;
    }

    LinearLayout getBackground(){
        return root;
    }
    ImageView getMedia(){
        return media;
    }


}
