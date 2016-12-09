package com.media.cluster.cluster.Main.Search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;


 class SearchListViewHolder extends RecyclerView.ViewHolder{

    private ImageButton getSuggestion;
    private TextView  suggestionText;

     SearchListViewHolder(View itemView) {
         super(itemView);
        getSuggestion = (ImageButton) itemView.findViewById(R.id.search_take_image);
        suggestionText = (TextView) itemView.findViewById(R.id.search_text);
    }

     ImageView getGetSuggestion() {
        return getSuggestion;
    }

     TextView getSuggestionText() {
        return suggestionText;
    }
}
