package com.media.cluster.cluster.Login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.media.cluster.cluster.R;

public class     RegisterDetailFragment extends Fragment {

    private static EditText about_me;
    private View hometown, education, profession, relationship;
     private static TextView educationText, relationText, professionText;
    private static String selectedProfession = "No response";
    private static String jobDescription = " ";
    View layout;


    public RegisterDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout =  inflater.inflate(R.layout.fragment_register_detail, container, false);

        about_me = (EditText)layout.findViewById(R.id.register_detail_about_me);
        hometown = layout.findViewById(R.id.register_detail_hometown);
        education = layout.findViewById(R.id.register_detail_education);
        profession = layout.findViewById(R.id.register_detail_profession);
        relationship = layout.findViewById(R.id.register_detail_relationship);

        educationText = (TextView) layout.findViewById(R.id.register_detail_education_text_field);
        professionText = (TextView) layout.findViewById(R.id.register_detail_profession_text_field);
        relationText = (TextView) layout.findViewById(R.id.register_detail_relationship_text_field);
        hometown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),RegisterSelectDegreeActivity.class));
            }
        });

        relationship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RegisterSelectRelationActivity.class));
            }
        });

        profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),RegisterSelectProfessionActivity.class));
            }
        });

        return layout;
    }

    static public String getSelectedEducation(){
        return educationText.getText().toString().trim();
    }

    static public  String getSelectedRelation(){
        return relationText.getText().toString().trim();
    }

    static public String getSelectedProfession(){
        return selectedProfession;
    }

    static public String getJobDescription(){
        return jobDescription;
    }

    static public String getAboutMe(){
        return about_me.getText().toString().trim();
    }



    static public void setEducationField(String  text){

    }
//TODO: Make translateable
    static public void setRelationField(int position){
        switch (position){
            case 0:
                relationText.setText("No response");
                break;
            case 1:
                relationText.setText("In a relationship");
                break;
            case 2:
                relationText.setText("It is difficult");
                break;
            case 3:
                relationText.setText("Single");
                break;
            case 4:
                relationText.setText("Open for all");
                break;
            case 5:
                relationText.setText("Married");
                break;
            case 6:
                relationText.setText("Widowed");
                break;
            case 7:
                relationText.setText("Divorced");
                break;
            case 8:
                relationText.setText("Engaged");
                break;
            case 9:
                relationText.setText("In love");
                break;
        }
    }



    static public void setProfessionField(int position, String selected, String typed){

        switch (position){
            case 0 :
                selectedProfession = "No response";
                break;
            case 1:
                selectedProfession = "Unemployed";
                break;
            case 2:
                selectedProfession = "In an apprenticeship";
                break;
            case 3:
                selectedProfession = "Employee";
                break;
            case 4:
                selectedProfession = "Civil servant";
                break;
            case 5:
                selectedProfession = "Homemaker";
                break;
            case 6:
                selectedProfession = "Retired";
                break;
            case 7:
                selectedProfession = "Self employed";
                break;
            case 8:
                selectedProfession = "High School Student";
                break;
            case 9:
                selectedProfession = "College Student";
                break;
            default:
                selectedProfession = "No response";
        }

        if (!selected.trim().equals("")){
            if(!typed.trim().equals("")){
                professionText.setText(selected +", "+ typed);
                jobDescription = typed;


            }else{
                professionText.setText(selected);
            }

        }else{
            if(!typed.trim().equals("")){
                professionText.setText(typed);
                jobDescription = typed;
            }else{
                professionText.setText("No Response");
            }
        }



    }


}
