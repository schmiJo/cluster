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

    private static String URL;
    private static StringRequest stringRequest;
    private static RequestQueue requestQueue;

   public  enum Attribute {FIRSTNAME, SURNAME, CLUSTERNAME, PASSWORD, PHONECOUNTRY,PHONENUMBER,PHONEVISIBILITY , ABOUTME, HOMETOWN, GENDER, BIRTHDAY, PROFESSION, RELATIONSHIP, EDUCATION, JOBDESCRIPTION, FACEBOOK_EMAIL, TWITTER_USERNAME, TUMBLR_USERNAME, SKYPE_USERNAME, PROFILE_PIC}

    public static void implementUser(final Context context, final Attribute attribute, final String value, final String clustername, final String password){
        requestQueue = Volley.newRequestQueue(context);
        URL = "http://social-cluster.com/user_implementation.php";
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
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
