package com.media.cluster.cluster.Main.Search;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.media.cluster.cluster.ClusterDBConnect.AddSearchHistory;
import com.media.cluster.cluster.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView suggestionRecyclerView;
    private String clustername , password;
    private Context dialogContext;
    private RecyclerView.OnItemTouchListener searchTouchListener;
    private View noHistoryLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        SearchListAdapter.showGetter = false;
        suggestionRecyclerView = (RecyclerView) findViewById(R.id.searchSuggestionRecyclerView);
        noHistoryLayout = findViewById(R.id.noHistoryLayout);
        SharedPreferences loginPref = getSharedPreferences("userLoginInfo", MODE_PRIVATE);
        clustername = loginPref.getString("clustername", "");
         password = loginPref.getString("password", "");
        getSuggestions();

        searchTouchListener = new RecyclerTouchListener(getApplicationContext(), suggestionRecyclerView, new MainSearchActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getApplicationContext(), MainSearchActivity.class);
                i.putExtra("search", (new SearchListViewHolder(view)).getSuggestionText().getText().toString().trim() );
                startActivity(i);
                finish();

            }

            @Override
            public void onLongClick(final View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext);
                builder.setPositiveButton(getResources().getString(R.string.remove), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AddSearchHistory.removeSearchHistory(clustername, password, (new SearchListViewHolder(view)).getSuggestionText().getText().toString().trim(), getApplicationContext());
                        getSuggestions();

                    }
                });
                DialogInterface.OnClickListener negativeDialogButton = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                };
                builder.setNegativeButton(getResources().getString(R.string.cancel), negativeDialogButton);

                builder.setTitle((new SearchListViewHolder(view)).getSuggestionText().getText().toString().trim());
                builder.setMessage(getResources().getString(R.string.removeConfirm));
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        suggestionRecyclerView.addOnItemTouchListener(searchTouchListener);

        ActionBar supportActionBar = getSupportActionBar();
        dialogContext = this;
        try {
            assert supportActionBar != null;
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
            supportActionBar.setTitle(getString(R.string.history));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private  void getSuggestions(){


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String URL = "http://social-cluster.com/get_search.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.trim().equals("null") ) {
                    suggestionRecyclerView.setVisibility(View.GONE);
                }else {

                    try {

                        List<String> suggestions = new ArrayList<>();
                        JSONArray jr = new JSONArray(response);


                        for (int i = 0; i < jr.length(); i++) {

                            String row = jr.getString(i);
                            suggestions.add(row);
                        }
                        if(suggestionRecyclerView.getVisibility() == View.GONE){
                            suggestionRecyclerView.setVisibility(View.VISIBLE);
                        }

                        if(suggestions.size() == 0){
                            noHistoryLayout.setVisibility(View.VISIBLE);
                        }else {
                            suggestionRecyclerView.setAdapter(new SearchListAdapter(suggestions));
                            suggestionRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            noHistoryLayout.setVisibility(View.GONE);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }

                }
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
                hashMap.put("searchRequest", "");
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){


            case android.R.id.home:
                finish();
                break;

            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext);
                builder.setPositiveButton(getResources().getString(R.string.remove), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       clearSearchHistory(clustername, password, getApplicationContext());

                    }
                });
                DialogInterface.OnClickListener negativeDialogButton = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                };
                builder.setNegativeButton(getResources().getString(R.string.cancel), negativeDialogButton);

                builder.setTitle(getResources().getString(R.string.removeHistory));
                builder.setMessage(getResources().getString(R.string.removeEntireHistory));
                AlertDialog dialog = builder.create();
                dialog.show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }


    public  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainSearchActivity.ClickListener clickListener;

        RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainSearchActivity.ClickListener clickListener) {

            this.clickListener=clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if(child!=null && clickListener!=null)
                    {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }

                }
            });


        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());

            if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){

                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;


        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        suggestionRecyclerView.addOnItemTouchListener(searchTouchListener);
    }

    private   void clearSearchHistory(final String clustername, final String password, Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String URL = "http://social-cluster.com/remove_search_user.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                noHistoryLayout.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_slow));
                noHistoryLayout.setVisibility(View.VISIBLE);
                suggestionRecyclerView.setVisibility(View.GONE);
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
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }
}
