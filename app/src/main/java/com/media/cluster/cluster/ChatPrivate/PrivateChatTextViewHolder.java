package com.media.cluster.cluster.ChatPrivate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.media.cluster.cluster.R;


 class PrivateChatTextViewHolder extends RecyclerView.ViewHolder{

     private TextView message;
     private TextView time;
     private LinearLayout bubble;
     private ImageView media;
     private ImageView status;
     private View root;

     PrivateChatTextViewHolder(View itemView) {
        super(itemView);
         message = (TextView) itemView.findViewById(R.id.private_message);
         time = (TextView) itemView.findViewById(R.id.private_message_time);
         bubble = (LinearLayout) itemView.findViewById(R.id.private_message_bubble);
         media = (ImageView) itemView.findViewById(R.id.private_message_media);
         status = (ImageView) itemView.findViewById(R.id.private_message_status);
         root = itemView.findViewById(R.id.private_message_root);
    }

     TextView getTime() {
        return time;
    }

     TextView getMessage() {
        return message;
    }

    LinearLayout getBackground(){
        return bubble;
    }
    ImageView getMedia(){
        return media;
    }
     ImageView getStatus(){
         return status;
     }
     View getRoot(){
         return root;
     }


}
