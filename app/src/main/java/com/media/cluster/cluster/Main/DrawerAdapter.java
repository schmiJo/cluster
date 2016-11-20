package com.media.cluster.cluster.Main;


import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.R;

import java.util.Collections;
import java.util.List;


 class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {
    private View view;
    private LayoutInflater inflater;
    private List<DrawerRowDataModel> data = Collections.emptyList();

     DrawerAdapter(Context context, List<DrawerRowDataModel> data) {
        inflater =LayoutInflater.from(context);
        this.data = data;
     }


//onCreateViewHolder
    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.drawer_row_layout, parent, false);
       return   new DrawerViewHolder(view);}

//onBindViewHolder
    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        DrawerRowDataModel current = data.get(position);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        holder.drawerText.setText(current.title);
        holder.drawerIcon.setImageResource(current.iconId);}


//getItemCount
    @Override
    public int getItemCount() {return data.size();}



    //View Holder Class
    class DrawerViewHolder extends RecyclerView.ViewHolder {
        ImageView drawerIcon;
        TextView drawerText;


         DrawerViewHolder(View itemView)  {
            super(itemView);
            drawerIcon = (ImageView) itemView.findViewById(R.id.drawer_row_image);
            drawerText = (TextView) itemView.findViewById(R.id.drawer_row_text);

        }

        }

    }


