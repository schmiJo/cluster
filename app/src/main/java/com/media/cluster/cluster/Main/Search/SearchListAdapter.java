package com.media.cluster.cluster.Main.Search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media.cluster.cluster.R;

import java.util.List;



 class SearchListAdapter extends RecyclerView.Adapter{

    private List<String> strings;
    public static boolean showGetter = true;

     SearchListAdapter(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_suggestion_row, parent, false);
        return  new SearchListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final SearchListViewHolder viewHolder = (SearchListViewHolder)holder;

        if(!showGetter) {
            viewHolder.getGetSuggestion().setVisibility(View.GONE);
        }
        viewHolder.getSuggestionText().setText(strings.get(position));


    }
}
