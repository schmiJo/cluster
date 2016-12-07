package com.media.cluster.cluster.Main.Search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;


 class SearchListViewHolder extends RecyclerView.ViewHolder{

    private ImageView getSuggestion;
    private TextView  suggestionText;

     SearchListViewHolder(View itemView) {
         super(itemView);
        getSuggestion = (ImageView) itemView.findViewById(R.id.search_take_image);
        suggestionText = (TextView) itemView.findViewById(R.id.search_text);
    }

    public ImageView getGetSuggestion() {
        return getSuggestion;
    }

    public TextView getSuggestionText() {
        return suggestionText;
    }
}
