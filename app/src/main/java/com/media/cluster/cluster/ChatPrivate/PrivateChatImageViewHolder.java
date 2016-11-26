package com.media.cluster.cluster.ChatPrivate;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;

 class PrivateChatImageViewHolder extends RecyclerView.ViewHolder{

     private TextView time;
     private ImageView image, media;
     private View root;
     private ImageView status;

     public PrivateChatImageViewHolder(View itemView) {
         super(itemView);

         time = (TextView) itemView.findViewById(R.id.private_image_time);
         image = (ImageView) itemView.findViewById(R.id.private_image);
         media = (ImageView) itemView.findViewById(R.id.private_image_media);
         root = itemView.findViewById(R.id.private_image_root);
         status = (ImageView) itemView.findViewById(R.id.private_image_status);
     }


     public ImageView getImage() {
         return image;
     }

     public ImageView getMedia() {
         return media;
     }

     public View getRoot() {
         return root;
     }

     public TextView getTime() {
         return time;
     }
     public ImageView getStatus(){
         return status;
     }
}
