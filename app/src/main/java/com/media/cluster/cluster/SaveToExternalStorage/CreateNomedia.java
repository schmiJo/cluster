package com.media.cluster.cluster.SaveToExternalStorage;

import android.util.Log;

import java.io.File;
import java.io.IOException;


class CreateNomedia {
    static void CreateNomediaFile(File file){
        Log.d("debug", "Create nomedia file   #" + file.toString()+ "#");

        try {
            boolean fileCreated = file.createNewFile();
            if(fileCreated) {
                Log.d("debug", "    CreateNomediaFile: successful");
            }else{
                Log.d("debug", "    CreateNomediaFile: Error Occured");}
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}