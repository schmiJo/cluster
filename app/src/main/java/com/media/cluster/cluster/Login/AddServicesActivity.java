package com.media.cluster.cluster.Login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.cluster.cluster.ClusterDBConnect.ImplementUserData;
import com.media.cluster.cluster.ClusterDBConnect.GetUserData;
import com.media.cluster.cluster.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class AddServicesActivity extends AppCompatActivity {

    private static ImageView icFacebook, icTumblr, icTwitter, icSkype;
    private static View IconContainer, noSelectedInclude;
    private static ViewGroup IconContainerGroup;
    private static ViewGroup ContainerGroup;
    private TwitterSession twitterSession;

    //Row views
    private static View rowFacebook, rowTwitter, rowTumblr, rowSkype;
    private static TextView textFacebook, textTwitter, textTumblr, textSkype;

    enum Service {FACEBOOK, TWITTER, TUMBLR, SKYPE}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services);

        twitterSession= Twitter.getSessionManager().getActiveSession();
        textSkype = (TextView) findViewById(R.id.add_service_skype_row_username);
        textFacebook = (TextView) findViewById(R.id.add_service_facebook_row_username);
        textTumblr = (TextView) findViewById(R.id.add_service_tumblr_row_username);
        textTwitter = (TextView) findViewById(R.id.add_service_twitter_row_username);

        rowFacebook = findViewById(R.id.add_service_facebook_row_container);
        rowTumblr = findViewById(R.id.add_service_tumblr_row_container);
        rowTwitter = findViewById(R.id.add_service_twitter_row_container);
        rowSkype = findViewById(R.id.add_service_skype_row_container);

        icFacebook = (ImageView) findViewById(R.id.add_service_icon_facebook);
        icTumblr = (ImageView) findViewById(R.id.add_service_icon_tumblr);
        icTwitter = (ImageView) findViewById(R.id.add_service_icon_twitter);
        icSkype = (ImageView) findViewById(R.id.add_service_icon_skype);
        IconContainer = findViewById(R.id.add_service_icon_container);
        IconContainerGroup = (ViewGroup) IconContainer;
        ContainerGroup = (ViewGroup) findViewById(R.id.add_service_container);
        noSelectedInclude = findViewById(R.id.add_service_no_selected_include);
        checkIfSelected(getApplicationContext());

        icFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation FadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                icFacebook.setAnimation(FadeOut);
                icFacebook.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(IconContainerGroup);
                checkIfSelected(getApplicationContext());
                Intent facebook = new Intent(getApplicationContext(), AddServiceDataActivity.class);
                facebook.putExtra("service", "facebook");
                startActivity(facebook);
                checkIfEmpty();

            }
        });

        icSkype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation FadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                icSkype.setAnimation(FadeOut);
                icSkype.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(IconContainerGroup);
                checkIfSelected(getApplicationContext());
                Intent skype = new Intent(getApplicationContext(), AddServiceDataActivity.class);
                skype.putExtra("service", "skype");
                startActivity(skype);
                checkIfEmpty();

            }
        });

        icTumblr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation FadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                icTumblr.setAnimation(FadeOut);
                icTumblr.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(IconContainerGroup);
                checkIfSelected(getApplicationContext());
                Intent tumblr = new Intent(getApplicationContext(), AddServiceDataActivity.class);
                tumblr.putExtra("service", "tumblr");
                startActivity(tumblr);
                checkIfEmpty();

            }
        });

        icTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation FadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                icTwitter.setAnimation(FadeOut);
                icTwitter.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(IconContainerGroup);
                checkIfSelected(getApplicationContext());
                Intent twitter = new Intent(getApplicationContext(), TwitterLoginActivity.class);
                startActivity(twitter);
                checkIfEmpty();

            }
        });
        SharedPreferences loginPref = getSharedPreferences("userLoginInfo",MODE_PRIVATE);
        String clustername = loginPref.getString("clustername","");
        GetUserData.getAddedServices(getApplicationContext(),clustername, true);


    }





    public static void checkIfEmpty() {
        if (icTwitter.getVisibility() == View.GONE && icSkype.getVisibility() == View.GONE && icFacebook.getVisibility() == View.GONE && icTumblr.getVisibility() == View.GONE) {
            IconContainer.setVisibility(View.GONE);
            TransitionManager.beginDelayedTransition(ContainerGroup);
        }else{
            IconContainer.setVisibility(View.VISIBLE);
            TransitionManager.beginDelayedTransition(IconContainerGroup);
        }
    }


    private static void checkIfSelected(Context context) {
        Animation FadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        Animation FadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        if (rowTwitter.getVisibility() == View.GONE && rowSkype.getVisibility() == View.GONE && rowFacebook.getVisibility() == View.GONE && rowTumblr.getVisibility() == View.GONE) {
            noSelectedInclude.setAnimation(FadeIn);
            noSelectedInclude.setVisibility(View.VISIBLE);
        } else {
            if (noSelectedInclude.getVisibility() != View.GONE) {
                noSelectedInclude.setVisibility(View.GONE);
                noSelectedInclude.setAnimation(FadeOut);
            }
        }
    }

    public static void setIconVisible(String service, Context context, boolean anim) {
        final Animation FadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        switch (service) {
            case "facebook":
                icFacebook.setAnimation(FadeIn);
                icFacebook.setVisibility(View.VISIBLE);
                if(anim) {
                    TransitionManager.beginDelayedTransition(IconContainerGroup);
                }
                checkIfSelected(context);
                break;
            case "twitter":
                icTwitter.setAnimation(FadeIn);
                icTwitter.setVisibility(View.VISIBLE);
                if(anim) {
                    TransitionManager.beginDelayedTransition(IconContainerGroup);
                }
                checkIfSelected(context);
                break;
            case "skype":

                icSkype.setAnimation(FadeIn);
                icSkype.setVisibility(View.VISIBLE);
                if(anim) {
                    TransitionManager.beginDelayedTransition(IconContainerGroup);
                }
                checkIfSelected(context);
                break;
            case "tumblr":

                icTumblr.setAnimation(FadeIn);
                icTumblr.setVisibility(View.VISIBLE);
                if(anim) {
                    TransitionManager.beginDelayedTransition(IconContainerGroup);
                }
                checkIfSelected(context);
                break;
        }

    }

    public void deleteFacebookRow(View view) {
        deleteRow(Service.FACEBOOK);
    }

    public void deleteTumblrRow(View view) {
        deleteRow(Service.TUMBLR);
    }

    public void deleteTwitterRow(View view) {
        deleteRow(Service.TWITTER);
    }

    public void deleteSkypeRow(View view) {
        deleteRow(Service.SKYPE);
    }

    private void deleteRow(final Service service) {
        String serviceString ;
        switch (service){
            case FACEBOOK:
                serviceString = getResources().getString(R.string.facebook);
                break;
            case TUMBLR:
                serviceString = getResources().getString(R.string.tumblr);
                break;
            case TWITTER:
                serviceString = getResources().getString(R.string.twitter);
                break;
            case SKYPE:
                serviceString = getResources().getString(R.string.skype);
                break;
            default:
                serviceString = getResources().getString(R.string.facebook);
                break;
        }
        //--------------
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                final Animation FadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                SharedPreferences loginPref = getSharedPreferences("userLoginInfo",MODE_PRIVATE);
                final String clustername = loginPref.getString("clustername","");
                final String password = loginPref.getString("password","");
                switch (service) {
                    case FACEBOOK:
                        icFacebook.setAnimation(FadeIn);
                        icFacebook.setVisibility(View.VISIBLE);
                        Animation SlideOutFB = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.row_slide_out);
                        rowFacebook.setAnimation(SlideOutFB);
                        rowFacebook.setVisibility(View.GONE);

                        TransitionManager.beginDelayedTransition(ContainerGroup);
                        ImplementUserData.implementUser(getApplicationContext(), ImplementUserData.Attribute.FACEBOOK_EMAIL,"",clustername,password);

                        break;
                    case TUMBLR:
                        icTumblr.setAnimation(FadeIn);
                        icTumblr.setVisibility(View.VISIBLE);
                        Animation SlideOutTB = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.row_slide_out);
                        rowTumblr.setAnimation(SlideOutTB);
                        rowTumblr.setVisibility(View.GONE);
                        setIconVisible("tumblr", getApplicationContext(),false);
                        TransitionManager.beginDelayedTransition(ContainerGroup);
                        ImplementUserData.implementUser(getApplicationContext(), ImplementUserData.Attribute.TUMBLR_USERNAME,"",clustername,password);

                        break;
                    case TWITTER:
                        icTwitter.setAnimation(FadeIn);
                        icTwitter.setVisibility(View.VISIBLE);
                        Animation SlideOutTW = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.row_slide_out);
                        rowTwitter.setAnimation(SlideOutTW);
                        rowTwitter.setVisibility(View.GONE);
                        setIconVisible("twitter", getApplicationContext(),false);
                        TransitionManager.beginDelayedTransition(ContainerGroup);
                        ImplementUserData.implementUser(getApplicationContext(), ImplementUserData.Attribute.TWITTER_USERNAME,"",clustername,password);
                        Twitter.logOut();

                        break;
                    case SKYPE:
                        icSkype.setAnimation(FadeIn);
                        icSkype.setVisibility(View.VISIBLE);
                        Animation SlideOutSK = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.row_slide_out);
                        rowSkype.setAnimation(SlideOutSK);
                        rowSkype.setVisibility(View.GONE);
                        setIconVisible("skype", getApplicationContext(),false);
                        TransitionManager.beginDelayedTransition(ContainerGroup);
                        ImplementUserData.implementUser(getApplicationContext(), ImplementUserData.Attribute.SKYPE_USERNAME,"",clustername,password);

                        break;
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkIfEmpty();
                    }
                },200);
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setTitle(getResources().getString(R.string.addServiceDeleteTitle1) +" "+ serviceString +" "+ getResources().getString( R.string.addServiceDeleteTitle2));
        builder.setMessage(R.string.addServiceDeleteMessage);

        AlertDialog dialog = builder.create();
        dialog.show();


        //---------------





    }

    public static void addRow(Service service, String username, Context context, boolean animation) {
        switch (service) {
            case FACEBOOK:
                textFacebook.setText(username);
                if(animation) {
                    Animation SlideInFB = AnimationUtils.loadAnimation(context, R.anim.row_slide_in);
                    rowFacebook.setAnimation(SlideInFB);
                    rowFacebook.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(ContainerGroup);
                }else{
                    rowFacebook.setVisibility(View.VISIBLE);
                }
                break;
            case TUMBLR:
                textTumblr.setText(username);
                if(animation) {
                    Animation SlideInTB = AnimationUtils.loadAnimation(context, R.anim.row_slide_in);
                    rowTumblr.setAnimation(SlideInTB);
                    rowTumblr.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(ContainerGroup);
                }else{
                    rowTumblr.setVisibility(View.VISIBLE);
                }


                break;
            case TWITTER:

                textTwitter.setText(username);
                if(animation) {
                    Animation SlideInTW = AnimationUtils.loadAnimation(context, R.anim.row_slide_in);
                    rowTwitter.setAnimation(SlideInTW);
                    rowTwitter.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(ContainerGroup);
                }else{
                    rowTwitter.setVisibility(View.VISIBLE);
                }

                break;
            case SKYPE:
                textSkype.setText(username);
                if(animation) {
                    Animation SlideInSK = AnimationUtils.loadAnimation(context, R.anim.row_slide_in);
                    rowSkype.setAnimation(SlideInSK);
                    rowSkype.setVisibility(View.VISIBLE);
                    TransitionManager.beginDelayedTransition(ContainerGroup);
                }else{
                    rowSkype.setVisibility(View.VISIBLE);
                }
        }
        checkIfSelected(context);
    }

    public static void used(Context context, String facebook_email,String tumblr_username,String twitter_username, String skype_username){
        if(!facebook_email.trim().equals("null") && !facebook_email.trim().equals("") ){
            addRow(Service.FACEBOOK,facebook_email,context, false);
            icFacebook.setVisibility(View.GONE);

        }

        if(!tumblr_username.trim().equals("null") && !tumblr_username.trim().equals("")){
            addRow(Service.TUMBLR,tumblr_username,context, false);
            icTumblr.setVisibility(View.GONE);

        }

        if(!twitter_username.trim().equals("null") && !twitter_username.trim().equals("")){
            addRow(Service.TWITTER,twitter_username, context, false);
            icTwitter.setVisibility(View.GONE);

        }

        if(!skype_username.trim().equals("null")&& !skype_username.trim().equals("")){
            addRow(Service.SKYPE,skype_username,context, false);
            icSkype.setVisibility(View.GONE);

        }
        checkIfSelected(context);
        if (icTwitter.getVisibility() == View.GONE && icSkype.getVisibility() == View.GONE && icFacebook.getVisibility() == View.GONE && icTumblr.getVisibility() == View.GONE) {
            IconContainer.setVisibility(View.GONE);}
    }


}
