package com.media.cluster.cluster.Twitter;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.media.cluster.cluster.R;

import java.util.List;

 class TwitterProfileMediaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> items;
    private static final int IMAGE = 0;
    private static final int GIF = 0;


     TwitterProfileMediaAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case IMAGE:
                View imageView = inflater.inflate(R.layout.twitter_profile_media_image, parent, false);
                viewHolder = new TwitterProfileMediaViewHolder(imageView);
                break;
            default:
                View defaultView = inflater.inflate(R.layout.twitter_profile_media_image, parent, false);
                viewHolder = new TwitterProfileMediaViewHolder(defaultView);
                break;

        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()){
            case IMAGE:
                TwitterProfileMediaViewHolder viewHolder = (TwitterProfileMediaViewHolder) holder;
                configureImage(viewHolder, position);

        }
    }

    private void configureImage(final TwitterProfileMediaViewHolder viewHolder, int position){
        final ImageView image = viewHolder.getImageView();
        final TwitterProfileMediaImageDataModel data = (TwitterProfileMediaImageDataModel) items.get(position);
        final ViewTreeObserver observer = image.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) image.getLayoutParams();
                params.height = image.getWidth() / 2;
                image.setLayoutParams(params);
                image.setImageBitmap(Bitmap.createScaledBitmap(data.image, image.getWidth(), image.getWidth() /2, true));


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    image.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    image.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
       image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position) instanceof TwitterProfileMediaImageDataModel ){
            return IMAGE;
        }else{
            return GIF;

        }
    }
}
