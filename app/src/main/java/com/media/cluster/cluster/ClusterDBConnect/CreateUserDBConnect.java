package com.media.cluster.cluster.ClusterDBConnect;



import android.content.Context;
import android.content.res.Resources;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.media.cluster.cluster.Login.RegisterActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class CreateUserDBConnect {






    public static void createUser(final Context context, final String clusterName, final String password, final String firstName, final String surname, final  String birthday, final String gender){

        String URL;
        StringRequest stringRequest;
        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(context);
        URL = "http://social-cluster.com/user_register.php";
        stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try{
                           JSONObject jsonObject = new JSONObject(response);
                           RegisterActivity registerActivity = new RegisterActivity();
                           if(jsonObject.names().get(0).equals("success")){
                                RegisterActivity.userCreated(context);
                           }else if (jsonObject.names().get(0).equals("exists")){
                               registerActivity.makeToast("Your username already exists", context);
                           }else if(jsonObject.names().get(0).equals("birthdayEarly")){
                               registerActivity.makeToast("You have not entered a valid birthday", context);
                           }else if (jsonObject.names().get(0).equals("birthdayLate")){
                               registerActivity.makeToast("You have not entered a valid birthday", context);
                           }else{
                               registerActivity.makeToast("Error:" + response, context);
                           }


                       }catch (JSONException e){
                           e.printStackTrace();

                       }

                   }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new RegisterActivity().makeToast("Server error:  "+ error, context);
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("clustername",clusterName);
                hashMap.put("password",password);
                hashMap.put("firstname",firstName);
                hashMap.put("surname",surname);
                hashMap.put("birthday",birthday);
                hashMap.put("gender",gender);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);

    }




}
