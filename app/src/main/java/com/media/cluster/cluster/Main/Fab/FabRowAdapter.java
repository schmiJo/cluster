package com.media.cluster.cluster.Main.Fab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;

import java.util.Collections;
import java.util.List;

 class FabRowAdapter extends RecyclerView.Adapter<FabRowAdapter.FabViewHolder>{
    private LayoutInflater layoutInflater;
    private List<FabRowModel> data = Collections.emptyList();

     FabRowAdapter(Context context,List<FabRowModel> data) {
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

      void setIcon(int resource, View view){
         FabViewHolder fabViewHolder = new FabViewHolder(view);
         fabViewHolder.fabIcon.setImageResource(resource);
     }

     String setServiceIcon(View view){
         FabViewHolder fabViewHolder = new FabViewHolder(view);
         switch (fabViewHolder.fabText.getText().toString().trim()){
             case "Facebook":
                 fabViewHolder.fabIcon.setImageResource(R.drawable.fab_ic_facebook_selected);
                 return "facebook";
             case "Twitter":
                 fabViewHolder.fabIcon.setImageResource(R.drawable.fab_ic_twitter_selected);
                 return "twitter";
             case "Tumblr":
                 fabViewHolder.fabIcon.setImageResource(R.drawable.fab_ic_tumblr_selected);
                 return "tumblr";
             default:
                 return "null";
         }

     }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public FabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.fab_row_layout,parent,false);
        return new FabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FabViewHolder holder, int position) {
        FabRowModel current = data.get(position);
        holder.fabText.setText(current.title);
        holder.fabIcon.setImageResource(current.iconId);
    }
    class FabViewHolder extends RecyclerView.ViewHolder {
        ImageView fabIcon;
        TextView fabText;


        FabViewHolder(View itemView)  {
            super(itemView);
            fabIcon = (ImageView) itemView.findViewById(R.id.fab_row_image);
            fabText = (TextView) itemView.findViewById(R.id.fab_row_text);

        }

    }

}
