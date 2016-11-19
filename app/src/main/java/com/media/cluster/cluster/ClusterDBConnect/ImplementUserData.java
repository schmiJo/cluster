package com.media.cluster.cluster.ClusterDBConnect;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ImplementUserData {


    private static final  int  FIRSTNAME=0 ;
    private static final  int  SURNAME= 1;
    private static final  int CLUSTERNAME = 2;
    private static final  int PASSWORD = 3;
    public static final  int  PHONECOUNTRY= 4;
    public static final  int PHONENUMBER = 5;
    public static final  int PHONEVISIBILITY = 6;
    public static final  int ABOUTME = 7;
    private static final  int HOMETOWN = 8;
    private static final  int GENDER = 9;
    private static final  int BIRTHDAY = 10;
    public static final  int  PROFESSION= 11 ;
    public static final  int RELATIONSHIP = 12;
    public static final  int EDUCATION = 13;
    public static final  int JOBDESCRIPTION =14 ;
    public static final  int FACEBOOK_EMAIL = 15 ;
    public static final  int TWITTER_USERNAME = 16;
    public static final  int TUMBLR_USERNAME = 17;
    public static final  int  SKYPE_USERNAME= 18;
    public static final  int PROFILE_PIC = 19 ;

    public static void implementUser(final Context context, final int attribute, final String value, final String clustername, final String password){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String URL = "http://social-cluster.com/user_implementation.php";
         StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("clustername", clustername);
                hashMap.put("password",password);
                SharedPreferences login = context.getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor loginEdit = login.edit();


                loginEdit.putBoolean("mediaSet", true);

                switch (attribute){
                    case FIRSTNAME:
                        hashMap.put("firstname",value);
                        break;
                    case SURNAME:
                        hashMap.put("surname",value);
                        break;
                    case CLUSTERNAME:
                        hashMap.put("clustername_new",value);
                        break;
                    case PHONECOUNTRY:
                        hashMap.put("phonecountry", value);
                        break;
                    case PHONENUMBER:
                        hashMap.put("phonenumber",value);
                        break;
                    case PHONEVISIBILITY:
                        hashMap.put("phonevisibility",value);
                        break;
                    case ABOUTME:
                        hashMap.put("about_me",value);
                        break;
                    case HOMETOWN:
                        hashMap.put("hometown",value);
                        break;
                    case GENDER:
                        hashMap.put("gender",value);
                        break;
                    case BIRTHDAY:
                        hashMap.put("birthday",value);
                        break;
                    case PROFESSION:
                        hashMap.put("profession",value);
                        break;
                    case RELATIONSHIP:
                        hashMap.put("relationship", value);
                        break;
                    case EDUCATION:
                        hashMap.put("education", value);
                        break;
                    case PASSWORD:
                        hashMap.put("new_password",value);
                        break;
                    case JOBDESCRIPTION:
                        hashMap.put("job_description",value);
                        break;
                    case FACEBOOK_EMAIL:
                        hashMap.put("facebook_email",value);
                        loginEdit.putString("facebook",value);
                        break;
                    case TUMBLR_USERNAME:
                        hashMap.put("tumblr_name",value);
                        loginEdit.putString("tumblr",value);
                        break;
                    case TWITTER_USERNAME:
                        hashMap.put("twitter_name",value);
                        loginEdit.putString("twitter",value);
                        break;
                    case SKYPE_USERNAME:
                        hashMap.put("skype_name",value);
                        loginEdit.putString("skype",value);
                        break;
                    case PROFILE_PIC:
                        hashMap.put("profilePic",value);
                        break;


                }
                loginEdit.apply();
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

    public static String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);

    }



}
