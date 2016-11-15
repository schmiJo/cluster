package com.media.cluster.cluster.Login;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.media.cluster.cluster.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterClusterNameFragment extends Fragment {

    EditText clusterNameEditText;
    static TextView clusterNameError;
    View layout;

    //TODO: replace string with get resources

    public RegisterClusterNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout =  inflater.inflate(R.layout.fragment_register_clustername, container, false);
        clusterNameEditText = (EditText)layout.findViewById(R.id.register_cluster_name_input);
        clusterNameError = (TextView)layout.findViewById(R.id.cluster_name_error);
        clusterNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
           @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                RegisterActivity.ClusterNameSetButtonOnClickListener(getContext(),clusterNameEditText.getText().toString().trim());
            }

        });

        return layout;
        }
    //Error methods

    public static void clusterErrorEmpty(){
        clusterNameError.setText("Please submit some input");
        clusterNameError.setTextColor(Color.parseColor("#Dd0000"));
    }

    public static void clusterErrorExist(){
        clusterNameError.setText("Your Username already exists");
        clusterNameError.setTextColor(Color.parseColor("#Dd0000"));
    }

    public static void clusterErrorChar(){
        clusterNameError.setText("You can only use A-Z , a-z and '.'");
        clusterNameError.setTextColor(Color.parseColor("#Dd0000"));

    }

    public static void clusterNoError(){
        clusterNameError.setText("You can only use A-Z , a-z and '.'");
        clusterNameError.setTextColor(Color.parseColor("#757575"));
    }

    public static void clusterError(){
        clusterNameError.setText("Error occurred Please try later again");
        clusterNameError.setTextColor(Color.parseColor("#Dd0000"));
    }





}
