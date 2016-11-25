package com.media.cluster.cluster.ClusterDBConnect;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.media.cluster.cluster.Login.LoginActivity;
import com.media.cluster.cluster.Login.RegisterFinishedFragment;
import com.media.cluster.cluster.Login.AddServicesActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class GetUserData {
    private static  String URL;
    private static StringRequest stringRequest;
    private static RequestQueue requestQueue;

    public GetUserData() {
    }


    public static final  int Register  =0 ;
    public static final  int  Login = 1;
    public static final  int SearchFilter = 2;
    public static final  int  FACEBOOK =1 ;
    public static final  int  SKYPE = 2;
    public static final  int  TWITTER =3  ;
    public static final  int  TUMBLR =4 ;




    public static int[] mediaToChatPrivate(){

        return new int[]{FACEBOOK, SKYPE, TWITTER, TUMBLR };
    }

    public static void getProfileID(final Context context, final String Clustername, final int usage){

        requestQueue = Volley.newRequestQueue(context);
        URL = "http://social-cluster.com/user_get_id.php";
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                switch (usage){
                    case Register:
                        RegisterFinishedFragment.setClusterCode(context,Integer.parseInt(response.trim()));
                        break;


                }

                SharedPreferences login = context.getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor loginEdit = login.edit();
                loginEdit.putInt("id",Integer.parseInt(response.trim()));
                loginEdit.apply();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("clustername",Clustername);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    public static void getAddedServices(final Context context, final String Clustername, final boolean other){
        requestQueue = Volley.newRequestQueue(context);
        URL = "http://social-cluster.com/user_get_data.php";
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                        if(!response.equals("error") && !response.equals("this is no collum!!") && !response.equals("no input")){
                            SharedPreferences login = context.getSharedPreferences("userLoginInfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor loginEdit = login.edit();
                            loginEdit.putString("facebook",jsonObject.getString("facebook"));
                            loginEdit.putString("twitter",jsonObject.getString("twitter"));
                            loginEdit.putString("tumblr",jsonObject.getString("tumblr"));
                            loginEdit.putString("skype",jsonObject.getString("skype"));
                            loginEdit.putBoolean("mediaSet", true);
                            loginEdit.apply();
                            if(other) {
                                AddServicesActivity.used(context, jsonObject.getString("facebook"), jsonObject.getString("tumblr"), jsonObject.getString("twitter"), jsonObject.getString("skype"));
                            }
                        }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("clustername",Clustername);
                hashMap.put("type","services");
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

    public static void getFirstname(final Context context, final String Clustername){
        requestQueue = Volley.newRequestQueue(context);
        URL = "http://social-cluster.com/user_get_data.php";
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    new LoginActivity().makeSucessToast(jsonObject.getString("firstname"));

                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("clustername",Clustername);
                hashMap.put("type","firstname");
                return hashMap;
            }
        };
    }

}
