package com.media.cluster.cluster.Twitter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.media.cluster.cluster.R;

 class TwitterProfileMediaViewHolder extends RecyclerView.ViewHolder{

    private ImageView imageView;

     TwitterProfileMediaViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.twitter_profile_media_image);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
