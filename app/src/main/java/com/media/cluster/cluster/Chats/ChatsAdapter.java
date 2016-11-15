package com.media.cluster.cluster.Chats;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.media.cluster.cluster.Main.MainActivity;
import com.media.cluster.cluster.R;
import java.util.Collections;
import java.util.List;


 class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private Resources r = Resources.getSystem();
    private View view;
    private Context context;
     int selected = 0;
    private ViewGroup chatRowLayoutGroup;

    //Creating an Array List with the Datatyp,es from the ChatsDataModel Class
    //emptyList() takes care that we did not get a NullPointer Exception
    private  List<ChatsRowDataModel> data = Collections.emptyList();

    public ChatsAdapter(Context context, List<ChatsRowDataModel> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    //Calls every time a new row is showed
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        //Getting the reference to the layout file (view stands for the root layout(Linear Layout))
        view = inflater.inflate(R.layout.chats_custom_row, parent, false);


        //Pass the view to the MyViewHolder class

        return new MyViewHolder(view);
    }

    //is called when long clicked and changes the status of the row to selected in user feedback and code
    public void changeLongClickIon(int position , View clickedItemView) {
        MyViewHolder selectedViewHolder = new MyViewHolder(clickedItemView);
        chatRowLayoutGroup = (ViewGroup)selectedViewHolder.chatRowLayout;

        if(selectedViewHolder.selectedChatRow.getVisibility() == View.VISIBLE ){
            Animation fadeOut = AnimationUtils.loadAnimation(context,R.anim.fade_out_slow);
            selectedViewHolder.selectedChatRow.startAnimation(fadeOut);
            selectedViewHolder.selectedChatRow.setVisibility(View.GONE);
            selectedViewHolder.chatRowLayout.setBackgroundColor(0);
            selected--;

        }else{
            Animation fadeIn = AnimationUtils.loadAnimation(context,R.anim.fade_in_slow);
            selectedViewHolder.selectedChatRow.startAnimation(fadeIn);
            selectedViewHolder.selectedChatRow.setVisibility(View.VISIBLE);
            selectedViewHolder.chatRowLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.selectedRow));
            selected++;
        }
    }

    public String getCurrentClustername (View clickedItemView){
        MyViewHolder clickedViewHolder = new MyViewHolder(clickedItemView);
        return clickedViewHolder.clustername.getText().toString();
    }

    public String getCurrentName(View clickedItemView){
        MyViewHolder clickedViewHolder = new MyViewHolder(clickedItemView);
        return clickedViewHolder.title.getText().toString();
    }




    //From here, the Adapter gets its Input and fills the widgets
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        //Getting the current DataModel Object
        ChatsRowDataModel current = data.get(position);

        holder.title.setText(current.title);
        holder.profilePicture.setImageResource(current.pictureID);
        holder.clustername.setText("@" + current.clustername);
        holder.lastMessage.setText(current.lastMessage);
        holder.messageTime.setText(current.messageTime);





        //New Message Handling
        if (current.newMessageCounter == 0) {
            holder.extraIcon.setText("");
        } else {
            holder.extraIcon.setText(Integer.toString(current.newMessageCounter));
            holder.lastMessage.setTextColor(Color.parseColor("#757575"));
            holder.lastMessage.setTypeface(holder.lastMessage.getTypeface(), Typeface.BOLD_ITALIC);
        }


        //Set the individual Message Status Icon for each row
        switch (current.messageIcon) {

            case NULL:
                ViewGroup.LayoutParams sizeRulesMessageStatusIcon = holder.messageIconView.getLayoutParams();
                sizeRulesMessageStatusIcon.width = 0;
                holder.messageIcon.setLayoutParams(sizeRulesMessageStatusIcon);
                break;

            case SENDING:
                holder.messageIcon.setImageResource(R.drawable.chats_ic_sending);
                break;

            case SEND:
                holder.messageIcon.setImageResource(R.drawable.chats_ic_send);
                break;

            case RECEIVED:
                holder.messageIcon.setImageResource(R.drawable.chats_ic_recieved);
                break;

            case READ:
                holder.messageIcon.setImageResource(R.drawable.chats_ic_read);
                break;
        }


        //Set the individual Extra Icon for each row
        switch (current.chatExtraIcon) {

            case NULL:
                ViewGroup.LayoutParams sizeRulesExtraIcon = holder.messageIconView.getLayoutParams();
                sizeRulesExtraIcon.width = 0;
                holder.extraIcon.setLayoutParams(sizeRulesExtraIcon);
                break;

            case NEW:
                holder.extraIcon.setBackgroundResource(R.drawable.chats_ic_extra_new);
                //holder.lastMessage.setTextColor(getResources().getColor(R.color.highlightedText));
                LinearLayout.LayoutParams sizeLastMessage = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int dip50 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
                sizeLastMessage.setMarginEnd(dip50);
                holder.lastMessage.setLayoutParams(sizeLastMessage);

                break;

            case MUTE:
                holder.extraIcon.setBackgroundResource(R.drawable.chats_ic_extra_mute);

                LinearLayout.LayoutParams sizeLastMessageMute = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dip50 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
                sizeLastMessageMute.setMarginEnd(dip50);
                holder.lastMessage.setLayoutParams(sizeLastMessageMute);

                break;

        }


    }


    @Override
    public int getItemCount() {
        //returns the size of the Recycler View items
        return data.size();
    }

    //The ViewHolder describes an item view and metadata about its place within the RecyclerView
    class MyViewHolder extends RecyclerView.ViewHolder {

        //Define the widgets
        TextView title, lastMessage, clustername;
        TextView messageTime;
        ImageView profilePicture, messageIcon, selectedChatRow;
        Button extraIcon;
        View messageIconView, extraIconView, lastMessageView , chatRowLayout;



        public MyViewHolder(View itemView) {
            super(itemView);

            //getting references to the widgets
            title = (TextView) itemView.findViewById(R.id.main_chat_row_name);
            profilePicture = (ImageView) itemView.findViewById(R.id.main_chat_row_profile_round_image);

            lastMessageView = itemView.findViewById(R.id.main_chat_row_last_message);
            lastMessage = (TextView) lastMessageView;

            clustername = (TextView) itemView.findViewById(R.id.main_chat_row_cluster_name);
            messageTime = (TextView) itemView.findViewById(R.id.message_time);

            messageIconView = itemView.findViewById(R.id.main_chat_row_message_icon);
            messageIcon = (ImageView) messageIconView;

            extraIconView = itemView.findViewById(R.id.main_chat_row_extra);
            extraIcon = (Button) extraIconView;

            selectedChatRow = (ImageView) itemView.findViewById(R.id.chat_row_selected);

            chatRowLayout = itemView.findViewById(R.id.chat_row_layout);
        }

    }

}
