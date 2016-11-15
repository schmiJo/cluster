package com.media.cluster.cluster.ChatPrivate.Typefields;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.media.cluster.cluster.ChatPrivate.PrivateChatActivity;
import com.media.cluster.cluster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatTypefieldTwitter extends Fragment {


    public ChatTypefieldTwitter() {
        // Required empty public constructor
    }

    private EditText typefield;
    private ImageButton sendButton;
    private LinearLayout rootLayout;
    private CardView cardview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout;
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_chat_typefield_twitter, container, false);
        sendButton = (ImageButton) layout.findViewById(R.id.typefield_twitter_send);

        rootLayout = (LinearLayout) layout.findViewById(R.id.typefield_twitter_root);
        cardview = (CardView) layout.findViewById(R.id.typefield_twitter_card);

        typefield = (EditText) layout.findViewById(R.id.typefield_twitter_input);
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
                    sendButton.setImageResource(R.drawable.typefield_ic_twitter_3a_smiley);

                } else {
                    sendButton.setImageResource(R.drawable.typefield_ic_twitter_3b_send);
                    if (typefield.getLineCount() < 2) {
                        PrivateChatActivity.setRecyclerViewMargin(cardview.getHeight());
                    } else if (typefield.getLineCount() == 2) {
                        PrivateChatActivity.setRecyclerViewMargin(cardview.getHeight());
                    } else if (typefield.getLineCount() > 2) {
                        PrivateChatActivity.setRecyclerViewMargin(cardview.getHeight());
                    }
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
            PrivateChatActivity.sendChatMessage(typefield.getText().toString(), PrivateChatActivity.SocialMedias.TWITTER);
            MediaPlayer messageSound = MediaPlayer.create(getContext(), R.raw.twitter_pop);
            messageSound.start();
            typefield.setText("");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    PrivateChatActivity.setRecyclerViewMargin(cardview.getHeight());
                }
            },50  );
        }
    }
}


