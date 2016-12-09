package com.media.cluster.cluster.Profile.ProfileTabs.InfoTab;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.media.cluster.cluster.Profile.FacebookProfileActivity;
import com.media.cluster.cluster.Profile.MainProfileFragment;
import com.media.cluster.cluster.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ProfileInfoFragment extends Fragment {


    public ProfileInfoFragment() {
        // Required empty public constructor
    }

    View layout;
    int accountState = 0;
    int moreState = 0;
    FrameLayout accountPlaceholder;
    FrameLayout facebookF;
    FrameLayout skypeF;
    FrameLayout twitterF;
    FrameLayout tumblrF;
    FrameLayout birthdayF;
    LinearLayout aboutmeF;
    FrameLayout hometownF;
    FrameLayout workF;
    FrameLayout phoneF;
    View accountExtendF;
    ImageView accountExtendIconF;
    ViewGroup rootLinearLayoutF;
    View moreExtendF;
    ImageView moreExtendIconF;

    private TextView followersV;
    private TextView uploadV;
    private TextView likesV;
    private TextView facebooknameV;
    private TextView skypenameV;
    private TextView twitternameV;
    private TextView tumblrnameV;
    private TextView aboutmeV;
    private TextView relationshipV;
    private TextView hometownV;
    private TextView birthdayV;
    private TextView workV;
    private TextView phoneV;

    private String clustername;
    private String profession;
    private String phonenumber;
    private String phonecountry;
    private String birthday;
    private String age;
    private String jobDescrition;
    private int facebook;
    private int skype;
    private int twitter;
    private int tumblr;
    private int socialMediaCounter;
    private int phonevisibility;
    private int phonecounter;
    private int jobCounter;
    private int birthdaycounter;

    Thread getDataThread;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clustername = MainProfileFragment.clustername;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_profile_info, container, false);
        Log.d("debug", "-----------------" + clustername + "----------------------");
        followersV = (TextView) layout.findViewById(R.id.profile_info_followers);
        uploadV = (TextView) layout.findViewById(R.id.profile_info_uploads);
        likesV = (TextView) layout.findViewById(R.id.profile_info_likes);
        facebooknameV = (TextView) layout.findViewById(R.id.profile_info_facebook_username);
        skypenameV = (TextView) layout.findViewById(R.id.profile_info_skype_username);
        twitternameV = (TextView) layout.findViewById(R.id.profile_info_twitter_username);
        tumblrnameV = (TextView) layout.findViewById(R.id.profile_info_tumblr_username);
        aboutmeV = (TextView) layout.findViewById(R.id.profile_info_aboutme_text);
        relationshipV = (TextView) layout.findViewById(R.id.profile_info_relationship_status);
        hometownV = (TextView) layout.findViewById(R.id.profile_info_hometown_text);
        birthdayV = (TextView) layout.findViewById(R.id.profile_info_birthday);
        workV = (TextView) layout.findViewById(R.id.profile_info_work);
        phoneV = (TextView) layout.findViewById(R.id.profile_info_phone);


        facebookF = (FrameLayout) layout.findViewById(R.id.profile_tab_info_accounts_facebook);
        skypeF = (FrameLayout) layout.findViewById(R.id.profile_tab_info_accounts_skype);
        twitterF = (FrameLayout) layout.findViewById(R.id.profile_tab_info_accounts_twitter);
        tumblrF = (FrameLayout) layout.findViewById(R.id.profile_tab_info_accounts_tumblr);
        aboutmeF = (LinearLayout) layout.findViewById(R.id.profile_info_aboutme);
        hometownF = (FrameLayout) layout.findViewById(R.id.profile_info_hometown);
        birthdayF = (FrameLayout) layout.findViewById(R.id.profile_tab_info_more_birthday);
        workF = (FrameLayout) layout.findViewById(R.id.profile_info_more_work);
        phoneF = (FrameLayout) layout.findViewById(R.id.profile_info_more_phone);
        rootLinearLayoutF = (ViewGroup) layout.findViewById(R.id.profile_tab_info);
        accountExtendIconF = (ImageView) layout.findViewById(R.id.profile_info_account_extend_icon);
        moreExtendIconF = (ImageView) layout.findViewById(R.id.profile_info_more_extend_icon);
        accountPlaceholder = (FrameLayout) layout.findViewById(R.id.profile_tab_info_placeholder);


        accountExtendF = layout.findViewById(R.id.profile_tab_info_accounts_extend);
        moreExtendF = layout.findViewById(R.id.profile_tab_info_more_extend);

        socialMediaCounter = 0;
        phonevisibility = 0;
        phonecounter = 0;
        birthdaycounter = 0;
        phonecounter = 0;
        jobCounter = 0;
        getData();


        profileInfosOnClick();

        TextView placeholder = (TextView) layout.findViewById(R.id.profile_tab_info_accounts_placeholder);
        placeholder.setText(getResources().getString(R.string.accounts_placeholder) + " " + "\uD83D\uDE28");

        return layout;
    }


    public void profileInfosOnClick() {

        accountExtendF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (accountState == 0) {

                    Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotation_180);
                    accountExtendIconF.startAnimation(rotate);
                    accountExtendIconF.setRotation(180);
                    TransitionManager.beginDelayedTransition(rootLinearLayoutF);
                    if (facebook != 0) {
                        facebookF.setVisibility(View.VISIBLE);
                    }
                    if (skype != 0) {
                        skypeF.setVisibility(View.VISIBLE);
                    }
                    if (twitter != 0) {
                        twitterF.setVisibility(View.VISIBLE);
                    }
                    if (tumblr != 0) {
                        tumblrF.setVisibility(View.VISIBLE);
                    }
                    accountState++;


                } else {
                    Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotation_180_reverse);
                    accountExtendIconF.startAnimation(rotate);
                    accountExtendIconF.setRotation(0);
                    TransitionManager.beginDelayedTransition(rootLinearLayoutF);
                    if (facebook != 0) {
                        facebookF.setVisibility(View.GONE);
                    }
                    if (skype != 0) {
                        skypeF.setVisibility(View.GONE);
                    }
                    if (twitter != 0) {
                        twitterF.setVisibility(View.GONE);
                    }
                    if (tumblr != 0) {
                        tumblrF.setVisibility(View.GONE);
                    }
                    accountState--;

                }


            }
        });

        moreExtendF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (moreState == 0) {

                    Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotation_180);
                    moreExtendIconF.startAnimation(rotate);
                    moreExtendIconF.setRotation(180);
                    TransitionManager.beginDelayedTransition(rootLinearLayoutF);
                    birthdayF.setVisibility(View.VISIBLE);
                    workF.setVisibility(View.VISIBLE);
                    if (phonevisibility == 1) {
                        phoneF.setVisibility(View.VISIBLE);
                    }
                    moreState++;


                } else {
                    Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotation_180_reverse);
                    moreExtendIconF.startAnimation(rotate);
                    moreExtendIconF.setRotation(0);
                    TransitionManager.beginDelayedTransition(rootLinearLayoutF);
                    birthdayF.setVisibility(View.GONE);
                    workF.setVisibility(View.GONE);
                    phoneF.setVisibility(View.GONE);
                    moreState--;

                }


            }
        });
        facebookF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FacebookProfileActivity.class));
            }
        });
        skypeF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        twitterF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tumblrF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        birthdayF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        workF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        phoneF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    public void getData() {
        //Get Facebook email
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.FACEBOOK));
        getDataThread.start();

        //Get Skype username
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.SKYPE));
        getDataThread.start();

        //Get Twitter username
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.TWITTER));
        getDataThread.start();

        //Get Tumblr username
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.TUMBLR));
        getDataThread.start();

        //Get About me
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.ABOUTME));
        getDataThread.start();

        //Get Relationship status
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.RELATIOINSHIP));
        getDataThread.start();

        //Get Hometown
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.HOMETOWN));
        getDataThread.start();

        //Get Birthday
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.BIRTHDAY));
        getDataThread.start();

        //Get Age
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.AGE));
        getDataThread.start();

        //Get Profession
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.PROFESSION));
        getDataThread.start();

        //Get Job description
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.JOBDESCRIPTION));
        getDataThread.start();

        //Get Phone number
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.PHONE));
        getDataThread.start();

        //Get Phonecountry
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.PHONECOUNTRY));
        getDataThread.start();

        //Get Phonevisibility
        getDataThread = new Thread(new GetInfoDataThread(getContext(), clustername, MainProfileFragment.GetProfileData.PHONEVISIBILITY));
        getDataThread.start();

    }

    void getFacebookEmail(String result) {
        Log.d("debug", "Facebook: " + result);
        facebooknameV.setText(result);
        facebook = result.length();
        if (facebook != 0) {
            socialMediaCounter++;
        }
        updatePlaceholder();
    }

    void getSkypeUsername(String result) {
        Log.d("debug", "Skype: " + result);
        skypenameV.setText(result);
        skype = result.length();
        if (skype != 0) {
            socialMediaCounter++;
        }
        updatePlaceholder();
    }

    void getTwitterUsername(String result) {
        Log.d("debug", "Twitter: " + result);
        twitternameV.setText(result);
        twitter = result.length();
        if (twitter != 0) {
            socialMediaCounter++;
        }
        updatePlaceholder();
    }

    void getTumblrUsername(String result) {
        Log.d("debug", "Tumblr: " + result);
        tumblrnameV.setText(result);
        tumblr = result.length();
        if (tumblr != 0) {
            socialMediaCounter++;
        }
        updatePlaceholder();

    }

    void updatePlaceholder() {
        if (socialMediaCounter == 0) {
            accountPlaceholder.setVisibility(View.VISIBLE);
        } else {
            accountPlaceholder.setVisibility(View.GONE);
        }
    }

    void getAboutMe(String result) {
        Log.d("debug", "About: " + result);
        aboutmeV.setText(result);
        if (result.equals("null") || result.equals("")) {
            aboutmeF.setVisibility(View.GONE);
        }
    }

    void getRelationshipStatus(String result) {
        Log.d("debug", "Relationship: " + result);
        relationshipV.setText(result);
    }

    void getHometown(String result) {
        Log.d("debug", "Hometown: " + result);
        hometownV.setText(result);
        if (result.equals("null")) {
            hometownF.setVisibility(View.GONE);
        }
    }

    void getBirthday(String result) {
        Log.d("debug", "Birthday: " + result);
        birthday = result;
        birthdaycounter++;
            setBirthday();
    }

    void getAge(String result) {
        Log.d("debug", "Age: " + result);
        age = result;
        birthdaycounter++;
            setBirthday();
    }

    void setBirthday() {
        if(birthdaycounter == 2) {
            birthdayV.setText(birthday + ", (Age: " + age + ")");
        }
    }

    void getProfession(String result) {
        profession = result;
        jobCounter++;
        Log.d("debug", jobCounter + "  Profession: " + result);
        if(jobCounter == 2){
            setWork();
        }

    }

    void getJobDescription(String result) {
        jobCounter++;
        jobDescrition = result;
        Log.d("debug", jobCounter + "  Jobdescription: " + result);
        if(jobCounter == 2){
            setWork();
        }
    }

    void setWork(){

        if (profession.equals("No response")) {
        workV.setText(profession);
        } else {
        workV.setText(profession + ", " + jobDescrition);
        }
    }

    void getPhone(String result) {
        Log.d("debug", "Phone: " + result);
        phonenumber = result;
        phonecounter++;
        if (phonecounter == 2){
            phoneV.setText(phonecountry + " " + phonenumber);
        }
    }

    void getPhoneCountry(String result) {
        Log.d("debug", "PhoneCountry: " + result);
        phonecountry = result;
        phonecounter++;
        if(phonecounter == 2){
            phoneV.setText(phonecountry + " " + phonenumber);
        }
    }

    void getPhoneVisibility(String result) {
        Log.d("debug", "Visibility: " + result);
        if (result.trim().equals("1")) {
            phonevisibility = 1;
        }
    }




    private class GetInfoDataThread implements Runnable {

        final Context context;
        final String clustername;
        final MainProfileFragment.GetProfileData getProfileData;

         GetInfoDataThread(Context context, String clustername, MainProfileFragment.GetProfileData getProfileData) {
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

                            case FACEBOOK:
                                getFacebookEmail(jsonObject.getString("facebook_email"));
                                break;
                            case SKYPE:
                                getSkypeUsername(jsonObject.getString("skype_username"));
                                break;
                            case TWITTER:
                                getTwitterUsername(jsonObject.getString("twitter_username"));
                                break;
                            case TUMBLR:
                                getTumblrUsername(jsonObject.getString("tumblr_username"));
                                break;
                            case ABOUTME:
                                getAboutMe(jsonObject.getString("aboutme"));
                                break;
                            case RELATIOINSHIP:
                                getRelationshipStatus(jsonObject.getString("relationshipstatus"));
                                break;
                            case HOMETOWN:
                                getHometown(jsonObject.getString("hometown"));
                                break;
                            case BIRTHDAY:
                                getBirthday(jsonObject.getString("birthday"));
                                break;
                            case AGE:
                                getAge(jsonObject.getString("age"));
                            case PROFESSION:
                                getProfession(jsonObject.getString("profession"));
                                break;
                            case JOBDESCRIPTION:
                                getJobDescription(jsonObject.getString("job_description"));
                                break;
                            case PHONE:
                                getPhone(jsonObject.getString("phonenumber"));
                                break;

                            case PHONECOUNTRY:
                                getPhoneCountry(jsonObject.getString("phonecountry"));
                                break;

                            case PHONEVISIBILITY:
                                getPhoneVisibility(jsonObject.getString("phonevisibility"));
                                break;

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("debug", "JSONException !!!!!! at: " + getProfileData.toString() + " Exeption: " + e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("debug", "Exeption at GetClusterInfoData:   " + getProfileData + "  " + error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("clustername", clustername);
                    switch (getProfileData) {

                        case FACEBOOK:
                            hashMap.put("type", "facebook_email");
                            break;
                        case SKYPE:
                            hashMap.put("type", "skype_username");
                            break;
                        case TWITTER:
                            hashMap.put("type", "twitter_username");
                            break;
                        case TUMBLR:
                            hashMap.put("type", "tumblr_username");
                            break;
                        case ABOUTME:
                            hashMap.put("type", "aboutme");
                            break;
                        case RELATIOINSHIP:
                            hashMap.put("type", "relationshipstatus");
                            break;
                        case HOMETOWN:
                            hashMap.put("type", "hometown");
                            break;
                        case BIRTHDAY:
                            hashMap.put("type", "birthday");
                            break;
                        case AGE:
                            hashMap.put("type", "age");
                            break;
                        case PROFESSION:
                            hashMap.put("type", "profession");
                            break;
                        case JOBDESCRIPTION:
                            hashMap.put("type", "job_description");
                            break;
                        case PHONE:
                            hashMap.put("type", "phonenumber");
                            break;
                        case PHONECOUNTRY:
                            hashMap.put("type", "phonecountry");
                            break;
                        case PHONEVISIBILITY:
                            hashMap.put("type", "phonevisibility");
                            break;
                    }
                    return hashMap;
                }


            };

            requestQueue.add(stringRequest);
        }
    }
}

