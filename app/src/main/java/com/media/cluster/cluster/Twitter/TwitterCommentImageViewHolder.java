package com.media.cluster.cluster.Twitter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;


class TwitterCommentImageViewHolder extends RecyclerView.ViewHolder {

    private ImageView profilePicture, messagePicture;
    private TextView twitterName, name, date, tweetMessage, locationText, respondedPersonUsername, likeCount, retweetCount, promotedText, retweetedText;
    private ImageButton replyButton, messageButton, shareButton;
    private View locationLayout, layout;

    TwitterCommentImageViewHolder(View itemView) {
        super(itemView);

        layout = itemView.findViewById(R.id.twitter_comment_image);
        profilePicture = (ImageView) itemView.findViewById(R.id.twitter_comment_image_profile_picture);
        twitterName = (TextView) itemView.findViewById(R.id.twitter_comment_image_user_name);
        name = (TextView) itemView.findViewById(R.id.twitter_comment_image_name);
        date = (TextView) itemView.findViewById(R.id.twitter_comment_image_date);
        tweetMessage = (TextView) itemView.findViewById(R.id.twitter_comment_image_message);
        locationText = (TextView) itemView.findViewById(R.id.twitter_comment_image_location_text);
        replyButton = (ImageButton) itemView.findViewById(R.id.twitter_comment_image_reply_button);
        messageButton = (ImageButton) itemView.findViewById(R.id.twitter_comment_image_message_button);
        shareButton = (ImageButton) itemView.findViewById(R.id.twitter_comment_image_share_button);
        locationLayout = itemView.findViewById(R.id.twitter_response_image_location_layout);
        respondedPersonUsername = (TextView) itemView.findViewById(R.id.twitter_comment_image_responded);
        retweetCount = (TextView) itemView.findViewById(R.id.twitter_comment_image_retweet_text_indicator);
        likeCount = (TextView) itemView.findViewById(R.id.twitter_comment_image_like_text);
        promotedText = (TextView) itemView.findViewById(R.id.twitter_comment_image_promoted);
        retweetedText = (TextView) itemView.findViewById(R.id.twitter_comment_image_retweet_text);
        messagePicture = (ImageView) itemView.findViewById(R.id.twitter_comment_image_image);
    }

    TextView getResponsedPersonUsername() {
        return respondedPersonUsername;
    }

    public TextView getLikeCount() {
        return likeCount;
    }

    public TextView getRetweetCount() {
        return retweetCount;
    }

    public ImageView getMessagePicture() {
        return messagePicture;
    }

    public View getLayout() {
        return layout;
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
