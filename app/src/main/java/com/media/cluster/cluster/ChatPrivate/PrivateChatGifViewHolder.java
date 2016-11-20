package com.media.cluster.cluster.ChatPrivate;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;

class PrivateChatGifViewHolder extends RecyclerView.ViewHolder{

   private TextView time;
   private pl.droidsonroids.gif.GifImageView gif;
    private ImageView media;
   private View root;

    PrivateChatGifViewHolder(View itemView) {
       super(itemView);

       time = (TextView) itemView.findViewById(R.id.private_gif_time);
       gif = (pl.droidsonroids.gif.GifImageView)  itemView.findViewById(R.id.private_gif);
       media = (ImageView) itemView.findViewById(R.id.private_gif_media);
       root = itemView.findViewById(R.id.private_gif_root);
   }


   public pl.droidsonroids.gif.GifImageView getImage() {
       return gif;
   }

   public ImageView getMedia() {
       return media;
   }

    View getRoot() {
       return root;
   }

   public TextView getTime() {
       return time;
   }
}
