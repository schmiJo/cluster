package com.media.cluster.cluster.ChatPrivate.Typefields;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.media.cluster.cluster.R;


public class ChatTypefieldTumblr extends Fragment {


    public ChatTypefieldTumblr() {
        // Required empty public constructor
    }

    private EditText typefield;
    private ImageButton sendButton;
    MessageListenerTu messageListener;

    public interface MessageListenerTu{
         void messageTumblr(String message);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            messageListener = (MessageListenerTu) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout;
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_chat_typefield_tumblr, container, false);
        sendButton = (ImageButton) layout.findViewById(R.id.typefield_tumblr_send);


        typefield = (EditText) layout.findViewById(R.id.typefield_tumblr_input);
        typefield.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = typefield.getText().toString().trim();

                if (input.length() == 0 || input.equals("")) {
                    sendButton.setImageResource(R.drawable.typefield_ic_tumblr_3_send);

                } else {
                    sendButton.setImageResource(R.drawable.typefield_ic_tumblr_3_send_clicked);
                    /*if (typefield.getLineCount() < 2) {
                        PrivateChatActivity.setRecyclerViewMargin(cardview.getHeight());
                    } else if (typefield.getLineCount() == 2) {
                        PrivateChatActivity.setRecyclerViewMargin(cardview.getHeight());
                    } else if (typefield.getLineCount() > 2) {
                        PrivateChatActivity.setRecyclerViewMargin(cardview.getHeight());
                    }*/
                }


            }

        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendButtonCllick();
            }
        });

        typefield.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    handleSendButtonCllick();
                }
                return false;
            }
        });

        return layout;
    }
    private void handleSendButtonCllick(){
        if (!typefield.getText().toString().trim().equals("")) {
            messageListener.messageTumblr(typefield.getText().toString());
            MediaPlayer messageSound = MediaPlayer.create(getContext(),R.raw.facebook_pop);
            messageSound.start();
            typefield.setText("");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                   //PrivateChatActivity.setRecyclerViewMargin(cardview.getHeight());
                }
            },50  );
        }
    }



}
