package com.media.cluster.cluster.Chats;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.media.cluster.cluster.ChatPrivate.PrivateChatActivity;
import com.media.cluster.cluster.R;

import java.util.ArrayList;
import java.util.List;




public class MainChatListFragment extends Fragment {

    View chatLayout;
    public ChatsAdapter adapter;
    View selectedView;
    int selectedPosition;
    LinearLayoutManager linearLayoutManager;


    public MainChatListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        chatLayout = inflater.inflate(R.layout.fragment_main_chat_list, container, false);
         final RecyclerView recyclerView = (RecyclerView) chatLayout.findViewById(R.id.ChatList);
        adapter = new ChatsAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {

            //Click Event Handling
            @Override
            public void onClick(View view, int position) {
                // TODO: 9/18/2016 Connect each recycler item with the affiliated Chat


                if(adapter.selected >0 ){
                    adapter.changeLongClickIon(view);
                }else{
                    Intent openPrivateChat = new Intent(getContext(), PrivateChatActivity.class);
                    openPrivateChat.putExtra("Clustername",adapter.getCurrentClustername(view));
                    openPrivateChat.putExtra("Name",adapter.getCurrentName(view));
                    startActivity(openPrivateChat);

                }

            }

            @Override
            public void onLongClick(View view, int position) {
                adapter.changeLongClickIon(view);
                selectedPosition = position;
                selectedView = view;


            }
        }));

        //adding Lines between the rows
        recyclerView.addItemDecoration(new ChatsDividerItemDecoration(getActivity()));


        return chatLayout;
    }



    //create an Array list of Row information Objects
    public static List<ChatsRowDataModel> getData() {
        List<ChatsRowDataModel> data = new ArrayList<>();

        //-------------------------------------Information source----------------------------------
        int[] icons = {R.drawable.men_unidentified,
                R.drawable.woman_undidentified,
                R.drawable.men_unidentified,
                R.drawable.men_unidentified,R.drawable.woman_undidentified,
                R.drawable.men_unidentified,
                R.drawable.woman_undidentified,
                R.drawable.men_unidentified,R.drawable.woman_undidentified,
                R.drawable.woman_undidentified,
                R.drawable.men_unidentified,
                R.drawable.woman_undidentified,};

        String[] titles = {"Jonas", "Manuel Schmidt", "Bob", "Günther","Jonas", "Manuel Schmidt", "Bob", "Günther","Jonas", "Manuel Schmidt", "Bob", "Günther"};
        String[] clusternames = {"clusterjonas", "clustermanuel", "clusterbob15", "xXGüntherXx","clusterjonas", "clustermanuel", "clusterbob15", "xXGüntherXx","clusterjonas", "clustermanuel", "clusterbob15", "xXGüntherXx"};
        String[] lastMessage = {"Hallo, wann kommst du heute nach hause wir warten schon?", " Schaue dir Material Improvements auf YouTube an: www.youtube.com/asdf", "blablablablablba", "Hey","Hallo, wann kommst du heute nach hause wir warten schon?", " Schaue dir Material Improvements auf YouTube an: www.youtube.com/asdf", "blablablablablba", "Hey","Hallo, wann kommst du heute nach hause wir warten schon?", " Schaue dir Material Improvements auf YouTube an: www.youtube.com/asdf", "blablablablablba", "Hey"};
        String[] messageTime = {"9:10 PM", "7:34 PM", "5:12 PM", "10:54 AM","9:10 PM", "7:34 PM", "5:12 PM", "10:54 AM","9:10 PM", "7:34 PM", "5:12 PM", "10:54 AM"};
        int[] messageIcon = {ChatsRowDataModel.NULL, ChatsRowDataModel.RECEIVED, ChatsRowDataModel.SENDING, ChatsRowDataModel.SEND,ChatsRowDataModel.NULL, ChatsRowDataModel.RECEIVED, ChatsRowDataModel.SENDING, ChatsRowDataModel.SEND,ChatsRowDataModel.NULL, ChatsRowDataModel.RECEIVED, ChatsRowDataModel.SENDING, ChatsRowDataModel.SEND};
        int[] chatExtraIcons = {ChatsRowDataModel.NEW, ChatsRowDataModel.NULL, ChatsRowDataModel.NULL, ChatsRowDataModel.MUTE,ChatsRowDataModel.NEW, ChatsRowDataModel.NULL, ChatsRowDataModel.NULL, ChatsRowDataModel.MUTE,ChatsRowDataModel.NEW, ChatsRowDataModel.NULL, ChatsRowDataModel.NULL, ChatsRowDataModel.MUTE};
        int[] newMessageCounter = {2, 0, 0, 0,2, 0, 0, 0,2, 0, 0, 0};


        //-------------------------------------Information source end------------------------------
        //Add the items to Row Information current
        for (int i = 0; i < titles.length && i < icons.length; i++) {
            ChatsRowDataModel current = new ChatsRowDataModel();
            current.pictureID = icons[i];
            current.title = titles[i];
            current.clustername = clusternames[i];
            current.lastMessage = lastMessage[i];
            current.messageTime = messageTime[i];
            current.messageIcon = messageIcon[i];
            current.chatExtraIcon = chatExtraIcons[i];
            current.newMessageCounter = newMessageCounter[i];
            data.add(current);
        }
        return data;
    }





}

//--------------------------------- Handling Touch Events---------------------------------------------------
class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private ClickListener clickListener;

    RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {

        this.clickListener=clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child!=null && clickListener!=null)
                {
                    clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                }

            }
        });


    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());

        if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){

            clickListener.onClick(child, rv.getChildAdapterPosition(child));
        }

        return false;


    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

//Interface to indicates the View that was clicked and the Position that was clicked
interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}


//--------------------------------- Handling Touch Events end-----------------------------------------------

