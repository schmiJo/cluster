package com.media.cluster.cluster.ClusterDBConnect;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class AddSearchHistory {




    public static void addSearchHistory(final String clustername, final String password, final String search, Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String URL = "http://social-cluster.com/add_search.php";
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
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("clustername",clustername);
                hashMap.put("password", password);
                hashMap.put("search", search);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }


    public static void removeSearchHistory(final String clustername, final String password, final String search , Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String URL = "http://social-cluster.com/remove_search.php";
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
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("clustername",clustername);
                hashMap.put("password", password);
                hashMap.put("searchRow", search);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }






}
