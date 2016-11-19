package com.media.cluster.cluster.Login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.media.cluster.cluster.ClusterDBConnect.CreateUserDBConnect;
import com.media.cluster.cluster.ClusterDBConnect.ImplementUserData;

import com.media.cluster.cluster.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.WHITE;

public class RegisterActivity extends AppCompatActivity {
    static ViewPager registerViewPager;
    static Button barNextButton, barPreviousButton;
    RegisterNameFragment registerNameFragment;
    RegisterDateFragment registerDateFragment;
    static final int ONE = 0;
    static final int TWO = 1;
    static final int THREE = 2;
    static final int NONE = 3;

    static public String firstName, lastName, ClusterName, password, gender;
    private View registerSwitchBar;
    private static boolean NumberSet = false;
    private static String URL = "http://social-cluster.com/user_check_clustername.php";
    private static StringRequest stringRequest;
    private static RequestQueue requestQueue;
    public static Bitmap profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerViewPager = (ViewPager) findViewById(R.id.register_view_pager);
        registerViewPager.setAdapter(new PagerAdapterRegister(getSupportFragmentManager()));
        registerDateFragment = new RegisterDateFragment();
        registerNameFragment = new RegisterNameFragment();
        setTitle("Create a Cluster Account");
        final Resources r = getResources();
        barNextButton = (Button) findViewById(R.id.register_bar_next);
        barPreviousButton = (Button) findViewById(R.id.register_bar_previous);
        registerSwitchBar = findViewById(R.id.register_switch_bar);
        registerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    barPreviousButton.setVisibility(View.GONE);

                    setTitle(getResources().getString(R.string.registerViewPager0));
                } else if (position == 7) {
                    barNextButton.setVisibility(View.GONE);
                    barPreviousButton.setVisibility(View.GONE);
                    registerSwitchBar.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.register_switch_bar_slide_out));
                    registerSwitchBar.setVisibility(View.GONE);
                    ViewGroup.MarginLayoutParams PagerMarginParams = (ViewGroup.MarginLayoutParams) registerViewPager.getLayoutParams();
                    PagerMarginParams.bottomMargin -= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
                    setTitle(getResources().getString(R.string.registerViewPager7));
                } else {
                    barPreviousButton.setVisibility(View.VISIBLE);
                    barNextButton.setVisibility(View.VISIBLE);
                    switch (position) {
                        case 1:
                            setTitle(getResources().getString(R.string.registerViewPager1));
                            break;
                        case 2:
                            setTitle(getResources().getString(R.string.registerViewPager2));
                            break;
                        case 3:
                            setTitle(getResources().getString(R.string.registerViewPager3));
                            break;
                        case 4:
                            setTitle(getResources().getString(R.string.registerViewPager4));
                            new RegisterProfilePicFragment().setDefaultProfilePic(RegisterDateFragment.getGender(), getApplicationContext());
                            gender = RegisterDateFragment.getGender().toString();
                            barNextButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    registerViewPager.setCurrentItem(5);
                                }
                            });
                            break;
                        case 5:
                            setTitle(getResources().getString(R.string.registerViewPager5));
                            barNextButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    checkNuberFragment();
                                }
                            });
                            break;
                        case 6:
                            setTitle(getResources().getString(R.string.registerViewPager6));
                            finishRegistration(getApplicationContext());
                            break;

                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        barPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerViewPager.setCurrentItem(registerViewPager.getCurrentItem() - 1, true);

                barNextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        registerViewPager.setCurrentItem(registerViewPager.getCurrentItem() + 1);
                        barNextButton.setTextColor(WHITE);
                    }
                });


            }
        });


    }


    public void setName(String NameFirst, String NameLast) {

        if (registerViewPager.getCurrentItem() == 0) {
            firstName = NameFirst;
            lastName = NameLast;
            barNextButton.setTextColor(Color.parseColor("#FFFFFF"));
            barNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    registerViewPager.setCurrentItem(1, true);
                }
            });
        }


    }

    public void goToNext() {
        registerViewPager.setCurrentItem(getCurrentPosition() + 1, true);
    }

    public void notSetName() {
        if (registerViewPager.getCurrentItem() == 0) {
            barNextButton.setTextColor(Color.parseColor("#cbccdc"));
            barNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public static int getCurrentPosition() {
        return registerViewPager.getCurrentItem();

    }

    public static void DateSetNextButtonOnClickListener() {
        if (registerViewPager.getCurrentItem() == 1) {
            barNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (RegisterDateFragment.checkDate()) {
                        RegisterDateFragment.setDateError(false);
                        if (RegisterDateFragment.checkGender()) {
                            RegisterDateFragment.setGenderError(true);
                        } else {
                            RegisterDateFragment.setGenderError(false);
                            registerViewPager.setCurrentItem(2, true);
                        }
                    } else {
                        RegisterDateFragment.setDateError(true);
                    }
                }
            });
        }
    }

    public static void setButtonWhite() {
        barNextButton.setTextColor(Color.parseColor("#FFFFFF"));
    }

    public static void setButtonGrey() {
        barNextButton.setTextColor(Color.parseColor("#cbccdc"));
    }

    public static void ClusterNameSetButtonOnClickListener(Context context, final String clusterName) {
        requestQueue = Volley.newRequestQueue(context);

        if (!clusterName.matches("^[a-zA-Z0-9_.]*$")) {
            RegisterClusterNameFragment.clusterErrorChar();
            setButtonGrey();
            barNextButtonSetOnBlankListener();
        } else {
            if (clusterName.equals("")) {
                RegisterClusterNameFragment.clusterErrorEmpty();
                setButtonGrey();
                barNextButtonSetOnBlankListener();
            } else {
                stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("notExist")) {
                                RegisterClusterNameFragment.clusterNoError();
                                setButtonWhite();
                                ClusterName = clusterName;
                                barNextButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        registerViewPager.setCurrentItem(3, true);
                                    }
                                });
                            } else if (jsonObject.names().get(0).equals("exist")) {
                                RegisterClusterNameFragment.clusterErrorExist();
                                setButtonGrey();
                                barNextButtonSetOnBlankListener();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        RegisterClusterNameFragment.clusterError();
                        setButtonGrey();
                        barNextButtonSetOnBlankListener();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("clustername", clusterName);
                        return hashMap;
                    }

                };
                requestQueue.add(stringRequest);

            }
        }
    }

    static private void barNextButtonSetOnBlankListener() {
        barNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public static void barNextButtonSetOnPasswordListener(final int passwordErrors, final String Password) {
        if (registerViewPager.getCurrentItem() == 3) {
            barNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (passwordErrors == ONE) {
                        RegisterPasswordFragment.passwordErrorCaseOne();
                    } else if (passwordErrors==TWO) {
                        RegisterPasswordFragment.passwordErrorCaseTwo();
                    } else if (passwordErrors==THREE) {
                        RegisterPasswordFragment.passwordErrorCaseTree();
                    } else if (passwordErrors==NONE) {
                        RegisterPasswordFragment.passwordErrorCaseNone();
                        password = Password;
                        registerViewPager.setCurrentItem(4, true);
                    }
                }

            });
        }
    }

    public static void skipPage() {
        registerViewPager.setCurrentItem(getCurrentPosition() + 1);
    }

    public static void finishRegistration(final Context context) {
        if (registerViewPager.getCurrentItem() == 6) {
            barNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Drawable profilePic = Drawable.createFromPath("sdcard/.cluster/profile_pic.png");

                    CreateUserDBConnect.createUser(context, ClusterName, password, firstName, lastName, RegisterDateFragment.Date(), gender );


                }


            });
        }

    }

    public static void  userCreated(final Context context){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RegisterFinishedFragment.setCodeInfo(context);

            }
        }, 300);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (NumberSet) {
                    ImplementUserData.implementUser(context, ImplementUserData.PHONENUMBER, RegisterNumberFragment.getNumber(), ClusterName, password);
                    ImplementUserData.implementUser(context, ImplementUserData.PHONECOUNTRY, RegisterNumberFragment.getCountry(), ClusterName, password);
                    String phoneVisibility;
                    if (RegisterNumberFragment.getNumberVisibilty()) {
                        phoneVisibility = "1";
                    } else {
                        phoneVisibility = "0";
                    }
                    ImplementUserData.implementUser(context, ImplementUserData.PHONEVISIBILITY, phoneVisibility, ClusterName, password);
                }

                if (!RegisterDetailFragment.getSelectedEducation().equals("No Response")) {
                    ImplementUserData.implementUser(context, ImplementUserData.EDUCATION, RegisterDetailFragment.getSelectedEducation(), ClusterName, password);
                }
                if (!RegisterDetailFragment.getSelectedProfession().equals("No Response")) {
                    ImplementUserData.implementUser(context, ImplementUserData.PROFESSION, RegisterDetailFragment.getSelectedProfession(), ClusterName, password);
                }
                if (!RegisterDetailFragment.getSelectedRelation().equals("No Response")) {
                    ImplementUserData.implementUser(context, ImplementUserData.RELATIONSHIP, RegisterDetailFragment.getSelectedRelation(), ClusterName, password);
                }
                if (!RegisterDetailFragment.getJobDescription().equals("")) {
                    ImplementUserData.implementUser(context, ImplementUserData.JOBDESCRIPTION, RegisterDetailFragment.getJobDescription(), ClusterName, password);
                }
                if (!RegisterDetailFragment.getAboutMe().equals("")) {
                    ImplementUserData.implementUser(context, ImplementUserData.ABOUTME, RegisterDetailFragment.getAboutMe(), ClusterName, password);
                }


                String bmToBase64 = ImplementUserData.getStringImage(profilePic);
                ImplementUserData.implementUser(context, ImplementUserData.PROFILE_PIC, bmToBase64, ClusterName, password);

                goToWelcome();

            }
        }, 500);


    }


    public void makeToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void checkNuberFragment() {
        if (RegisterNumberFragment.getNumber().equals("") || RegisterNumberFragment.getCountry().equals("")) {
            RegisterNumberFragment.setErrorMessage(false);
        } else {
            RegisterNumberFragment.setErrorMessage(true);
            NumberSet = true;
            registerViewPager.setCurrentItem(6);
        }

    }

    public static void goToWelcome() {
        registerViewPager.setCurrentItem(7);
    }

    public static String getClustername() {
        return ClusterName;
    }

    public static String getFirstName() {
        return firstName;
    }









    private void createCloseDialog(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(getResources().getString(R.string.confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(context, LoginActivity.class));
                finish();

            }
        });

        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setTitle(getResources().getString(R.string.registerCloseDialogTitle));
        builder.setMessage(getResources().getString(R.string.registerCloseDialogMessage));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void setProfilePic(Bitmap profilePicNew){
        profilePic = profilePicNew;
    }

}

