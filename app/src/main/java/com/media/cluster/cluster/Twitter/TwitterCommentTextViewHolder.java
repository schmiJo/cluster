package com.media.cluster.cluster.Twitter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;


class TwitterCommentTextViewHolder extends RecyclerView.ViewHolder {

    private ImageView profilePicture;
    private TextView twitterName, name, date, tweetMessage, locationText, respondedPersonUsername, likeCount, retweetCount, promotedText, retweetedText;
    private ImageButton replyButton, messageButton, shareButton;
    private View locationLayout;

    TwitterCommentTextViewHolder(View itemView) {
        super(itemView);

        profilePicture = (ImageView) itemView.findViewById(R.id.twitter_comment_profile_picture);
        twitterName = (TextView) itemView.findViewById(R.id.twitter_comment_user_name);
        name = (TextView) itemView.findViewById(R.id.twitter_comment_name);
        date = (TextView) itemView.findViewById(R.id.twitter_comment_date);
        tweetMessage = (TextView) itemView.findViewById(R.id.twitter_comment_message);
        locationText = (TextView) itemView.findViewById(R.id.twitter_comment_location_text);
        replyButton = (ImageButton) itemView.findViewById(R.id.twitter_comment_reply_button);
        messageButton = (ImageButton) itemView.findViewById(R.id.twitter_comment_message_button);
        shareButton = (ImageButton) itemView.findViewById(R.id.twitter_comment_share_button);
        locationLayout = itemView.findViewById(R.id.twitter_response_location_layout);
        respondedPersonUsername = (TextView) itemView.findViewById(R.id.twitter_comment_responded);
        retweetCount = (TextView) itemView.findViewById(R.id.twitter_comment_retweet_text_indicator);
        likeCount = (TextView) itemView.findViewById(R.id.twitter_comment_like_text);
        promotedText = (TextView) itemView.findViewById(R.id.twitter_comment_promoted);
        retweetedText = (TextView) itemView.findViewById(R.id.twitter_comment_retweet_text);
    }

    TextView getResponsedPersonUsername() {
        return respondedPersonUsername;
    }

     TextView getLikeCount() {
        return likeCount;
    }

     TextView getRetweetCount() {
        return retweetCount;
    }



    TextView getDate() {
        return date;
    }

    public TextView getRespondedPersonUsername() {
        return respondedPersonUsername;
    }


    View getLocationLayout() {
        return locationLayout;
    }

    TextView getLocationText() {
        return locationText;
    }

    public ImageButton getMessageButton() {
        return messageButton;
    }

    public TextView getName() {
        return name;
    }

    ImageView getProfilePicture() {
        return profilePicture;
    }

    public ImageButton getReplyButton() {
        return replyButton;
    }


    public ImageButton getShareButton() {
        return shareButton;
    }

    TextView getTweetMessage() {
        return tweetMessage;
    }

    TextView getTwitterName() {
        return twitterName;
    }

    public TextView getPromotedText() {
        return promotedText;
    }

    public TextView getRetweetedText() {
        return retweetedText;
    }
}
