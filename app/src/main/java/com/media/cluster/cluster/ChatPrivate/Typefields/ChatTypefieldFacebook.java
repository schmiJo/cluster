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


public class ChatTypefieldFacebook extends Fragment {


    private ImageButton keyboardButton, cameraButton, imageButton, smileyButton, gifButton, micButton, moreButton, smileysButton, sendLikeButton;
    private EditText typefield;
    TextWatcher textWatcher;
    MessageListenerFB messagelistener;
    TextView.OnEditorActionListener actionListener;


    public interface MessageListenerFB{
     void messageFacebook(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        try {
            messagelistener = (MessageListenerFB) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View layout;
        layout = inflater.inflate(R.layout.fragment_chat_typefield_facebook, container, false);

        keyboardButton = (ImageButton) layout.findViewById(R.id.typefield_facebook_keyboard);
        cameraButton = (ImageButton) layout.findViewById(R.id.typefield_facebook_camera);
        imageButton = (ImageButton) layout.findViewById(R.id.typefield_facebook_image);
        smileyButton = (ImageButton) layout.findViewById(R.id.typefield_facebook_smiley);
        gifButton = (ImageButton) layout.findViewById(R.id.typefield_facebook_gif);
        micButton = (ImageButton) layout.findViewById(R.id.typefield_facebook_mic);
        moreButton = (ImageButton) layout.findViewById(R.id.typefield_facebook_more);
        smileysButton = (ImageButton) layout.findViewById(R.id.typefield_facebook_smileys);
        sendLikeButton = (ImageButton) layout.findViewById(R.id.typefield_facebook_send);



        keyboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(keyboardButton);
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(cameraButton);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(imageButton);
            }
        });
        smileyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(smileyButton);
            }
        });
        gifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(gifButton);
            }
        });
        micButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(micButton);
            }
        });
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(moreButton);
            }
        });
        smileysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(smileysButton);
            }
        });
        sendLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendButtonClick();
            }
        });

//--------------------------------------------Edit Button for TextInput--------------------------------------------//
        typefield = (EditText) layout.findViewById(R.id.typefield_facebook_input);
        textWatcher = new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = typefield.getText().toString().trim();
                switchIconState(keyboardButton);

                if (input.length() == 0 || input.equals("")) {
                    sendLikeButton.setImageResource(R.drawable.typefield_ic_facebook_9a_like);

                } else {
                    sendLikeButton.setImageResource(R.drawable.typefield_ic_facebook_9b_save);
                    /*if (typefield.getLineCount() < 2) {
                        PrivateChatActivity.setRecyclerViewMargin(cardView.getHeight());
                    } else if (typefield.getLineCount() == 2) {
                        PrivateChatActivity.setRecyclerViewMargin(cardView.getHeight());
                    } else if (typefield.getLineCount() > 2) {
                        PrivateChatActivity.setRecyclerViewMargin(cardView.getHeight());
                    }*/
                }




            }

        };
        typefield.addTextChangedListener(textWatcher);
        typefield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(keyboardButton);
            }
        });
        actionListener = new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    handleSendButtonClick();
                }
                return false;
            }
        };
        typefield.setOnEditorActionListener(actionListener);



        return layout;
    }


    private void handleSendButtonClick() {
        switchIconState(sendLikeButton);
        if (!typefield.getText().toString().trim().equals("")) {
            messagelistener.messageFacebook(typefield.getText().toString());
            MediaPlayer messageSound = MediaPlayer.create(getContext(), R.raw.facebook_pop);
            messageSound.start();
            typefield.setText("");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //PrivateChatActivity.setRecyclerViewMargin(cardView.getHeight());
                }
            },50  );
        }
    }


    private void switchIconState(ImageButton button) {
        if (button == keyboardButton) {
            keyboardButton.setImageResource(R.drawable.typefield_ic_facebook_1_keyboard_clicked);
        } else {
            keyboardButton.setImageResource(R.drawable.typefield_ic_facebook_1_keyboard);
        }
        if (button == cameraButton) {
            cameraButton.setImageResource(R.drawable.typefield_ic_facebook_2_camera_clicked);
        } else {
            cameraButton.setImageResource(R.drawable.typefield_ic_facebook_2_camera);
        }
        if (button == imageButton) {
            imageButton.setImageResource(R.drawable.typefield_ic_facebook_3_images_clicked);
        } else {
            imageButton.setImageResource(R.drawable.typefield_ic_facebook_3_images);
        }
        if (button == smileyButton) {
            smileyButton.setImageResource(R.drawable.typefield_ic_facebook_4_sticker_clicked);
        } else {
            smileyButton.setImageResource(R.drawable.typefield_ic_facebook_4_sticker);
        }
        if (button == gifButton) {
            gifButton.setImageResource(R.drawable.typefield_ic_facebook_5_gif_clicked);
        } else {
            gifButton.setImageResource(R.drawable.typefield_ic_facebook_5_gif);
        }
        if (button == micButton) {
            micButton.setImageResource(R.drawable.typefield_ic_facebook_6_mic_clicked);
        } else {
            micButton.setImageResource(R.drawable.typefield_ic_facebook_6_mic);
        }
        if (button == moreButton) {
            moreButton.setImageResource(R.drawable.typefield_ic_facebook_7_more_clicked);
        } else {
            moreButton.setImageResource(R.drawable.typefield_ic_facebook_7_more);
        }
        if (button == smileysButton) {
            smileysButton.setImageResource(R.drawable.typefield_ic_facebook_8_smileys_clicked);
        } else {
            smileysButton.setImageResource(R.drawable.typefield_ic_facebook_8_smileys);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        typefield.removeTextChangedListener(textWatcher);
    }
}
