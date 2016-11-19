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
import android.widget.TextView;

import com.media.cluster.cluster.ChatPrivate.PrivateChatActivity;
import com.media.cluster.cluster.R;


public class ChatTypefieldSkype extends Fragment {


    private ImageButton keyboardButton, smileysButton, stickerButton, contactButton, documentsButton, imageButton, cameraButton, videoButton, locationButton, sendButton;
    private EditText typefield;
    private CardView cardview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View layout;
        layout = inflater.inflate(R.layout.fragment_chat_typefield_skype, container, false);

        keyboardButton = (ImageButton) layout.findViewById(R.id.typefield_skype_keyboard);
        smileysButton = (ImageButton) layout.findViewById(R.id.typefield_skype_smileys);
        stickerButton = (ImageButton) layout.findViewById(R.id.typefield_skype_sticker);
        contactButton = (ImageButton) layout.findViewById(R.id.typefield_skype_contact);
        documentsButton = (ImageButton) layout.findViewById(R.id.typefield_skype_documents);
        imageButton = (ImageButton) layout.findViewById(R.id.typefield_skype_images);
        cameraButton = (ImageButton) layout.findViewById(R.id.typefield_skype_camera);
        videoButton = (ImageButton) layout.findViewById(R.id.typefield_skype_video);
        locationButton = (ImageButton) layout.findViewById(R.id.typefield_skype_location);
        sendButton = (ImageButton) layout.findViewById(R.id.typefield_skype_send);

        cardview = (CardView) layout.findViewById(R.id.typefield_skype_card);


        keyboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(keyboardButton);
            }
        });

        smileysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(smileysButton);
            }
        });

        stickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(stickerButton);
            }
        });
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(contactButton);
            }
        });

        documentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(documentsButton);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(imageButton);
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(cameraButton);
            }
        });
        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(videoButton);
            }
        });
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(locationButton);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendButtonCllick();
            }
        });





        //--------------------------------------------Edit Button for TextInput--------------------------------------------//
        typefield = (EditText) layout.findViewById(R.id.typefield_skype_input);
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
                switchIconState(keyboardButton);
                if (input.length() == 0 || input.equals("")) {
                    sendButton.setImageResource(R.drawable.chat_ic_skype_send);

                }else{
                    sendButton.setImageResource(R.drawable.chat_ic_skype_send_clicked);
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

        typefield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchIconState(keyboardButton);
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
    private void handleSendButtonCllick() {
        switchIconState(sendButton);
        if (!typefield.getText().toString().trim().equals("")) {
            PrivateChatActivity.sendChatMessage(typefield.getText().toString(), PrivateChatActivity.SKYPE);
            MediaPlayer messageSound = MediaPlayer.create(getContext(), R.raw.skype_pop);
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


    private void switchIconState(ImageButton button) {
        if (button == keyboardButton) {
            keyboardButton.setImageResource(R.drawable.typefield_ic_skype_1_keyboard_clicked);
        } else {
            keyboardButton.setImageResource(R.drawable.typefield_ic_skype_1_keyboard);
        }
        if (button == smileysButton) {
            smileysButton.setImageResource(R.drawable.typefield_ic_skype_2_smiley_clicked);
        } else {
            smileysButton.setImageResource(R.drawable.typefield_ic_skype_2_smiley);
        }
        if (button == stickerButton) {
            stickerButton.setImageResource(R.drawable.typefield_ic_skype_3_sticker_clicked);
        } else {
            stickerButton.setImageResource(R.drawable.typefield_ic_skype_3_sticker);
        }
        if (button == contactButton) {
            contactButton.setImageResource(R.drawable.typefield_ic_skype_4_contact_clicked);
        } else {
            contactButton.setImageResource(R.drawable.typefield_ic_skype_4_contact);
        }
        if (button == documentsButton) {
            documentsButton.setImageResource(R.drawable.typefield_ic_skype_5_documents_clicked);
        } else {
            documentsButton.setImageResource(R.drawable.typefield_ic_skype_5_documents);
        }
        if (button == imageButton) {
            imageButton.setImageResource(R.drawable.typefield_ic_skype_6_images_clicked);
        } else {
            imageButton.setImageResource(R.drawable.typefield_ic_skype_6_images);
        }
        if (button == cameraButton) {
            cameraButton.setImageResource(R.drawable.typefield_ic_skype_7_camera_clicked);
        } else {
            cameraButton.setImageResource(R.drawable.typefield_ic_skype_7_camera);
        }
        if (button == videoButton) {
            videoButton.setImageResource(R.drawable.typefield_ic_skype_8_video_clicked);
        } else {
            videoButton.setImageResource(R.drawable.typefield_ic_skype_8_video);
        }
        if (button == locationButton) {
            locationButton.setImageResource(R.drawable.typefield_ic_skype_9_location_clicked);
        } else {
            locationButton.setImageResource(R.drawable.typefield_ic_skype_9_location);
        }


    }
}
