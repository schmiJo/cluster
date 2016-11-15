package com.media.cluster.cluster.Twitter;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.media.cluster.cluster.R;

import java.util.List;

class TwitterCommentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final private static int TEXT = 0;
    final private static int IMAGE = 1;
    private List<Object> items;
    private Resources.Theme theme;
    private Resources resources;

    TwitterCommentRecyclerViewAdapter(List<Object> items, Resources.Theme theme, Resources resources) {
        this.items = items;
        this.theme = theme;
        this.resources = resources;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TEXT:
                View commentText = inflater.inflate(R.layout.twitter_comment_text, parent, false);
                viewHolder = new TwitterCommentTextViewHolder(commentText);
                break;
            case IMAGE:
                View commentImage = inflater.inflate(R.layout.twitter_comment_image, parent, false);
                viewHolder = new TwitterCommentImageViewHolder(commentImage);
                break;
            default:
                View commentDefaultText = inflater.inflate(R.layout.twitter_comment_text, parent, false);
                viewHolder = new TwitterCommentTextViewHolder(commentDefaultText);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case TEXT:
                TwitterCommentTextViewHolder twitterCommentTextViewHolder = (TwitterCommentTextViewHolder) holder;
                configureCommentTextViewHolder(twitterCommentTextViewHolder, position, resources, theme);
                break;
            case IMAGE:
                TwitterCommentImageViewHolder twitterCommentImageTextViewHolder = (TwitterCommentImageViewHolder) holder;
                configureCommentImageViewHolder(twitterCommentImageTextViewHolder,position,resources,theme);
                break;
        }

    }


    //-------------------------------------------------------------------configure Tweet Text------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------------------------------


    private void configureCommentTextViewHolder(final TwitterCommentTextViewHolder viewHolder, int position, Resources resources, Resources.Theme theme) {
        final TwitterCommentTextDataModel dataModel = (TwitterCommentTextDataModel) items.get(position);
        viewHolder.getName().setText(dataModel.twitterName);
        viewHolder.getTwitterName().setText(dataModel.twitterUserName);
        viewHolder.getDate().setText(dataModel.date);
        if (!dataModel.location.equals("")) {
            viewHolder.getLocationLayout().setVisibility(View.VISIBLE);
            viewHolder.getLocationText().setText(dataModel.date);
        } else {
            viewHolder.getLocationLayout().setVisibility(View.GONE);
        }

        Drawable[] layers = new Drawable[2];

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layers[0] = resources.getDrawable(R.drawable.men_unidentified, theme);
            layers[1] = resources.getDrawable(R.drawable.twitter_image_frame, theme);
        } else {
            layers[0] = resources.getDrawable(R.drawable.men_unidentified);
            layers[1] = resources.getDrawable(R.drawable.twitter_image_frame);
        }
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        viewHolder.getProfilePicture().setImageDrawable(layerDrawable);
        viewHolder.getTweetMessage().setText(dataModel.message);
        if (!dataModel.respondedPerson.equals("")) {
            viewHolder.getResponsedPersonUsername().setText(dataModel.respondedPerson);
        } else {
            viewHolder.getResponsedPersonUsername().setVisibility(View.GONE);
        }

        if (dataModel.promoted) {
            viewHolder.getPromotedText().setVisibility(View.VISIBLE);
        }

        if (!dataModel.retweet.equals("")) {
            viewHolder.getRetweetedText().setVisibility(View.VISIBLE);
            viewHolder.getRetweetedText().setText(dataModel.retweet + " " + "retweeted");
        }

        viewHolder.getRetweetCount().setText(dataModel.retweets + "");
        viewHolder.getLikeCount().setText(dataModel.likes + "");
        if (dataModel.retweeted) {
            viewHolder.getRetweetCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_retweet_ic_secondary_selected,0,0,0);
            viewHolder.getRetweetCount().setTextColor(Color.parseColor("#ff0004"));
        }

        if (dataModel.liked) {
            viewHolder.getLikeCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_tweet_like_ic_secondary_selected,0,0,0);
            viewHolder.getLikeCount().setTextColor(Color.parseColor("#ff0004"));
        }

        viewHolder.getRetweetCount().setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (dataModel.retweeted) {
                dataModel.setRetweeted(false);
                viewHolder.getRetweetCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_retweet_ic_secondary,0,0,0);
                viewHolder.getRetweetCount().setText(dataModel.retweets + "");
                viewHolder.getRetweetCount().setTextColor(Color.parseColor("#c3ccd1"));
            } else {
                dataModel.setRetweeted(true);
                viewHolder.getRetweetCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_retweet_ic_secondary_selected,0,0,0);
                int retweetNumberNew = dataModel.retweets + 1;
                viewHolder.getRetweetCount().setText(retweetNumberNew + "");
                viewHolder.getRetweetCount().setTextColor(Color.parseColor("#ff0004"));
            }
        }
                                                         }
        );

        viewHolder.getLikeCount().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataModel.liked) {
                    dataModel.setLiked(false);
                    viewHolder.getLikeCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_tweet_like_ic_secondary,0,0,0);
                    viewHolder.getLikeCount().setText(dataModel.likes + "");
                    viewHolder.getLikeCount().setTextColor(Color.parseColor("#c3ccd1"));
                } else {
                    dataModel.setLiked(true);
                    int likeNumberNew = dataModel.likes + 1;
                    viewHolder.getLikeCount().setText(likeNumberNew + "");
                    viewHolder.getLikeCount().setTextColor(Color.parseColor("#ff0004"));
                    viewHolder.getLikeCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_tweet_like_ic_secondary_selected,0,0,0);

                }
            }
        });

    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------configure Tweet Text------------------------------------------------------------


    //-------------------------------------------------------------------configure Tweet Image-----------------------------------------------------------
    //---------------------------------------------------------------------------- Start ----------------------------------------------------------------
    private void configureCommentImageViewHolder(final TwitterCommentImageViewHolder viewHolder, int position, Resources resources, Resources.Theme theme) {
        final TwitterCommentImageDataModel dataModel = (TwitterCommentImageDataModel) items.get(position);
        viewHolder.getName().setText(dataModel.twitterName);
        viewHolder.getTwitterName().setText(dataModel.twitterUserName);
        viewHolder.getDate().setText(dataModel.date);
        if (!dataModel.location.equals("")) {
            viewHolder.getLocationLayout().setVisibility(View.VISIBLE);
            viewHolder.getLocationText().setText(dataModel.date);
        } else {
            viewHolder.getLocationLayout().setVisibility(View.GONE);
        }

        Drawable[] messageLayers = new Drawable[2];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            messageLayers[0] = resources.getDrawable(R.drawable.alien_unidentified, theme);
            messageLayers[1] = resources.getDrawable(R.drawable.twitter_image_frame, theme);
        } else {
            messageLayers[0] = resources.getDrawable(R.drawable.alien_unidentified);
            messageLayers[1] = resources.getDrawable(R.drawable.twitter_image_frame);
        }
        LayerDrawable messageLayerDrawable = new LayerDrawable(messageLayers);
        viewHolder.getMessagePicture().setImageDrawable(messageLayerDrawable);


        Drawable[] layers = new Drawable[2];

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layers[0] = resources.getDrawable(R.drawable.men_unidentified, theme);
            layers[1] = resources.getDrawable(R.drawable.twitter_image_frame, theme);
        } else {
            layers[0] = resources.getDrawable(R.drawable.men_unidentified);
            layers[1] = resources.getDrawable(R.drawable.twitter_image_frame);
        }
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        viewHolder.getProfilePicture().setImageDrawable(layerDrawable);
        viewHolder.getTweetMessage().setText(dataModel.message);
        if (!dataModel.respondedPerson.equals("")) {
            viewHolder.getResponsedPersonUsername().setText(dataModel.respondedPerson);
        } else {
            viewHolder.getResponsedPersonUsername().setVisibility(View.GONE);
        }

        if (dataModel.promoted) {
            viewHolder.getPromotedText().setVisibility(View.VISIBLE);
        }

        if (!dataModel.retweet.equals("")) {
            viewHolder.getRetweetedText().setVisibility(View.VISIBLE);
            viewHolder.getRetweetedText().setText(dataModel.retweet + " " + "retweeted");
        }

        viewHolder.getRetweetCount().setText(dataModel.retweets + "");
        viewHolder.getLikeCount().setText(dataModel.likes + "");
        if (dataModel.retweeted) {
            viewHolder.getRetweetCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_retweet_ic_secondary_selected,0,0,0);
            viewHolder.getRetweetCount().setTextColor(Color.parseColor("#ff0004"));
        }

        if (dataModel.liked) {
            viewHolder.getLikeCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_tweet_like_ic_secondary_selected,0,0,0);
            viewHolder.getLikeCount().setTextColor(Color.parseColor("#ff0004"));
        }

        viewHolder.getRetweetCount().setOnClickListener(new View.OnClickListener() {

       @Override
       public void onClick(View view) {
           if (dataModel.retweeted) {
               dataModel.setRetweeted(false);
               viewHolder.getRetweetCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_retweet_ic_secondary,0,0,0);
               viewHolder.getRetweetCount().setText(dataModel.retweets + "");
               viewHolder.getRetweetCount().setTextColor(Color.parseColor("#c3ccd1"));
           } else {
               dataModel.setRetweeted(true);
               viewHolder.getRetweetCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_retweet_ic_secondary_selected,0,0,0);
               int retweetNumberNew = dataModel.retweets + 1;
               viewHolder.getRetweetCount().setText(retweetNumberNew + "");
               viewHolder.getRetweetCount().setTextColor(Color.parseColor("#ff0004"));
           }
       }
                                                         }
        );

        viewHolder.getLikeCount().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dataModel.liked) {
                    dataModel.setLiked(false);
                    viewHolder.getLikeCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_tweet_like_ic_secondary,0,0,0);
                    viewHolder.getLikeCount().setText(dataModel.likes + "");
                    viewHolder.getLikeCount().setTextColor(Color.parseColor("#c3ccd1"));
                } else {
                    dataModel.setLiked(true);
                    int likeNumberNew = dataModel.likes + 1;
                    viewHolder.getLikeCount().setText(likeNumberNew + "");
                    viewHolder.getLikeCount().setTextColor(Color.parseColor("#ff0004"));
                    viewHolder.getLikeCount().setCompoundDrawablesWithIntrinsicBounds(R.drawable.twitter_tweet_like_ic_secondary_selected,0,0,0);

                }
            }
        });

        viewHolder.getMessagePicture().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                viewHolder.getMessagePicture().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = viewHolder.getMessagePicture().getWidth();
                viewHolder.getMessagePicture().setLayoutParams(new LinearLayout.LayoutParams(width,(int)Math.round(viewHolder.getMessagePicture().getWidth() * 0.5)));
            }
        });

    }


    //----------------------------------------------------------------------------- End -----------------------------------------------------------------
    //-------------------------------------------------------------------configure Tweet Image-----------------------------------------------------------

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof TwitterCommentTextDataModel) {
            return TEXT;
        } else if (items.get(position) instanceof TwitterCommentImageDataModel) {
            return IMAGE;
        } else {
            return -1;
        }
    }
}
