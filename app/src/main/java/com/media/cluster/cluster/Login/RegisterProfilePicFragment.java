package com.media.cluster.cluster.Login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.media.cluster.cluster.R;
import com.media.cluster.cluster.SaveToExternalStorage.SaveBitmap;

import java.io.File;

public class RegisterProfilePicFragment extends Fragment {

    FloatingActionButton fab;
    ViewGroup layout;
    static ImageView profilePic;
    Context context;

    public RegisterProfilePicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = (ViewGroup) inflater.inflate(R.layout.fragment_register_profilepic, container, false);
        profilePic = (ImageView) layout.findViewById(R.id.register_round_profile_image);
        fab = (FloatingActionButton) layout.findViewById(R.id.fabRegisterProfilePic);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SelectProfilePicUpload.class));

        hideKeyboard(getContext(), layout);

            }
        });

        return layout;
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    static public void setDefaultProfilePic(RegisterDateFragment.Gender gender, Context context) {
        File file = new File("sdcard/.cluster/profile_pic.png");
        if(file.exists()){
            profilePic.setImageDrawable(Drawable.createFromPath("sdcard/.cluster/profile_pic.png"));
        }else {
            RegisterProfilePicFragment registerProfilePicFragment = new RegisterProfilePicFragment();
            if (gender.equals(RegisterDateFragment.Gender.MALE)) {
                profilePic.setImageResource(R.drawable.men_unidentified);
                registerProfilePicFragment.createDefaultProfilePic(R.drawable.men_unidentified, context);

            } else if (gender.equals(RegisterDateFragment.Gender.FEMALE)) {
                profilePic.setImageResource(R.drawable.woman_undidentified);
                registerProfilePicFragment.createDefaultProfilePic(R.drawable.woman_undidentified, context);
            } else if (gender.equals(RegisterDateFragment.Gender.OTHER)) {
                profilePic.setImageResource(R.drawable.alien_unidentified);
                registerProfilePicFragment.createDefaultProfilePic(R.drawable.alien_unidentified, context);
            }else if (gender.equals(RegisterDateFragment.Gender.ALIEN)){
                profilePic.setImageResource(R.drawable.alien_unidentified);
                registerProfilePicFragment.createDefaultProfilePic(R.drawable.alien_unidentified,context);
            }
        }
    }


    public void changeProfilePicture(Bitmap bitmap){
        SaveBitmap.saveFile("profile_pic.png", bitmap);
        profilePic.setImageBitmap(bitmap);
        RegisterActivity.setProfilePic(bitmap);

    }

    public void createDefaultProfilePic(int resourcepath, Context context){
        Bitmap defaultProfilePic = BitmapFactory.decodeResource(context.getResources(),
                resourcepath);
        RegisterActivity.setProfilePic(defaultProfilePic);
        SaveBitmap.saveFile("profile_pic.png", defaultProfilePic);

    }








}
