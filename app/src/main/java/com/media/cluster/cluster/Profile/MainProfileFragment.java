package com.media.cluster.cluster.Profile;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.media.cluster.cluster.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Fragment for showing a Profile.
 * If you wanna call the fragment you have to make a Bundle with the Key "CN"!
 *
 */
public class MainProfileFragment extends Fragment {


    public MainProfileFragment() {
        // Required empty public constructor
    }

    View layout;
    TabLayout tabLayout;
    ViewPager profileViewPager;
    View aboutSection;


    TabLayout.Tab ProfileInfo;
    TabLayout.Tab ProfileFeed;
    TabLayout.Tab ProfileTiles;

      ImageView profilePicV;
    private  TextView fullNameV;
      TextView clusternameV;

    Thread getDataThread;
    String firstname;
    String surname;
    int nameCounter;
    public static String clustername;

    public enum GetProfileData {FIRSTNAME, SURNAME, FACEBOOK, SKYPE, TWITTER, TUMBLR, ABOUTME, RELATIOINSHIP, HOMETOWN, BIRTHDAY, AGE, PROFESSION, JOBDESCRIPTION, PHONE, PHONECOUNTRY, PHONEVISIBILITY}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        clustername = bundle.getString("CN");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_main_profile, container, false);


        profilePicV = (ImageView) layout.findViewById(R.id.profile_profile_pic);
        clusternameV = (TextView) layout.findViewById(R.id.profile_clustername);
        clusternameV.setText(clustername);
        fullNameV = (TextView) layout.findViewById(R.id.profile_name_full);



        profileViewPager = (ViewPager) layout.findViewById(R.id.profile_view_pager);
        profileViewPager.setAdapter(new PagerAdapterProfile(getActivity(), getChildFragmentManager()));


        profilePicV.setImageBitmap(getRoundedBitmap.getRoundedCornerBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.men_unidentified), 100));

        aboutSection = layout.findViewById(R.id.profile_tab_info);
        setUpTabLayout();

        getData();


        profileViewPager.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE && profileViewPager != null) {
                    profileViewPager.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });



        return layout;
    }


    public void setUpTabLayout() {

        //Tab layout start
        tabLayout = (TabLayout) layout.findViewById(R.id.profile_tab_layout);
        ProfileInfo = tabLayout.newTab();
        ProfileFeed = tabLayout.newTab();
        ProfileTiles = tabLayout.newTab();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                switch (tab.getPosition()) {
                    case 0:
                        selectTabInfo();
                        break;
                    case 1:
                        selectTabFeed();
                        break;
                    case 2:
                        selectTabTiles();
                        break;
                }


            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.addTab(ProfileInfo, 0);
        tabLayout.addTab(ProfileFeed, 1);
        tabLayout.addTab(ProfileTiles, 2);

        //Tab layout end
    }

    public void selectTabInfo() {

        ProfileInfo.setIcon(R.drawable.profile_ic_info_clicked);
        ProfileFeed.setIcon(R.drawable.profile_ic_feed);
        ProfileTiles.setIcon(R.drawable.profile_ic_tiles);
        profileViewPager.setCurrentItem(0);
        //Log.d("debug", "selected Info Tab");
        //Log.d("debug", "ViewPager:  " + profileViewPager.getCurrentItem());


    }

    public void selectTabFeed() {

        ProfileInfo.setIcon(R.drawable.profile_ic_info);
        ProfileFeed.setIcon(R.drawable.profile_ic_feed_clicked);
        ProfileTiles.setIcon(R.drawable.profile_ic_tiles);
        profileViewPager.setCurrentItem(1);
        //Log.d("debug", "selected Feed Tab");
        //Log.d("debug", "ViewPager:  " + profileViewPager.getCurrentItem());
    }

    public void selectTabTiles() {

        ProfileInfo.setIcon(R.drawable.profile_ic_info);
        ProfileFeed.setIcon(R.drawable.profile_ic_feed);
        ProfileTiles.setIcon(R.drawable.profile_ic_tiles_clicked);
        profileViewPager.setCurrentItem(2);
        //Log.d("debug", "selected Tiles Tab");
        //Log.d("debug", "ViewPager:  " + profileViewPager.getCurrentItem());
    }
    private void getData(){

        //Get Firstname
        getDataThread = new Thread(new GetDataThread(getContext(), clustername, GetProfileData.FIRSTNAME));
        getDataThread.start();
        //Get Surname
        getDataThread = new Thread(new GetDataThread(getContext(), clustername, GetProfileData.SURNAME));
        getDataThread.start();


    }
    public void getFirstname(String result){
        Log.d("debug", "Firstname: " + result);
        firstname = result;
        nameCounter++;
        if (nameCounter == 2 ){
            setData(firstname + " " + surname);
            nameCounter = 0;
        }

    }
    public void getSurname(String result){
        Log.d("debug", "Surname: " + result);
        surname = result;
        nameCounter++;
        if (nameCounter == 2){
            setData(firstname + " " + surname);
            nameCounter = 0;
        }

    }

    private void setData(String fullname){
        fullNameV.setText(fullname);
    }

    private class GetDataThread implements Runnable {

        final Context context;
        final String clustername;
        final MainProfileFragment.GetProfileData getProfileData;

         GetDataThread(Context context,String clustername, GetProfileData getProfileData) {
            this.clustername = clustername;
            this.context = context;
            this.getProfileData = getProfileData;
        }


        @Override
        public void run() {
                String URL;
                StringRequest stringRequest;
                RequestQueue requestQueue;
                requestQueue = Volley.newRequestQueue(context);
                URL = "http://social-cluster.com/user_get_data.php";


                stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            switch (getProfileData) {
                                case FIRSTNAME:
                                    getFirstname(jsonObject.getString("firstname"));
                                    break;
                                case SURNAME:
                                    getSurname(jsonObject.getString("surname"));
                                    break;

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("debug", "JSONException !!!!!!!!!! at: " + getProfileData.toString() + " Exeption: " + e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("debug", "Exeption at GetMainClusterData:   " + getProfileData.toString() + "   " + error);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("clustername", clustername);
                        switch (getProfileData) {
                            case FIRSTNAME:
                                hashMap.put("type", "firstname");
                                break;
                            case SURNAME:
                                hashMap.put("type", "surname");
                                break;

                        }
                        return hashMap;
                    }


                };

                requestQueue.add(stringRequest);
            }
        }
    }

