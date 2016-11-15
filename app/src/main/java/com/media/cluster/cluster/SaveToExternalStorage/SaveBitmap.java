package com.media.cluster.cluster.SaveToExternalStorage;

import android.graphics.Bitmap;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class SaveBitmap {

    private static Boolean success = false;

    public static void saveFile(String Filename, Bitmap bitmap) {
        File DefaultDir = new File("sdcard/.cluster");
        saveFile(DefaultDir, Filename, true, bitmap);
    }


    public static void saveFile(final File Directory,final String Filename,final Boolean Nomedia,final Bitmap bitmap) {
        Log.d("debug", "----------------------------------START SAVING Thread--------------------------------");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                boolean doSave = true;

                if (!Directory.exists()) {
                    doSave = Directory.mkdir();
                    Log.d("debug", "Created new Directory: " + Directory.toString());
                }

                if (doSave) {
                    saveBitmapToFile(Directory, Filename, bitmap, Bitmap.CompressFormat.PNG, 100);
                    if (success) {
                        Log.d("debug", "Saving Successful, Filename: " + Filename + "  Directory: " + Directory.toString());
                    }
                } else {
                    Log.e("debug", "----------!!Couldn't create target directory or File!!---------- Filename: "+Filename);
                }

                if (Nomedia) {
                    File nomedia = new File(Directory + "/.nomedia");
                    if (!nomedia.exists()) {

                        CreateNomedia.CreateNomediaFile(nomedia);

                    }
                }


                Log.d("debug", "----------------------------------END SAVING Thread--------------------------------");
            }
        };
        new Thread(runnable).start();

    }
    private static boolean saveBitmapToFile(File dir, String fileName, Bitmap bm,
                                    Bitmap.CompressFormat format, int quality) {

        File imageFile = new File(dir, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);

            bm.compress(format, quality, fos);

            fos.close();
            success = true;
            return true;
        } catch (IOException e) {
            Log.e("app", e.getMessage());
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }
}
