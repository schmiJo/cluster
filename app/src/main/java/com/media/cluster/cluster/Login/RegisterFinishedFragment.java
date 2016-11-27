package com.media.cluster.cluster.Login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.media.cluster.cluster.ClusterCodeFragment.ClusterCodeFragment;
import com.media.cluster.cluster.ClusterDBConnect.GetUserData;
import com.media.cluster.cluster.Login.AddServices.AddServicesActivity;
import com.media.cluster.cluster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFinishedFragment extends Fragment {


    public RegisterFinishedFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_register_finished, container, false);
        TextView welcomeMessage = (TextView) layout.findViewById(R.id.register_finished_welcome_message);
        FloatingActionButton addServices = (FloatingActionButton)layout.findViewById(R.id.register_finished_add_service_fab);
        addServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),AddServicesActivity.class));
            }
        });
        welcomeMessage.setText(getResources().getString(R.string.welcomeClustername) + " " + RegisterActivity.getFirstName());

        return layout;
    }

    public static void setClusterCode(Context context, int id) {
        ClusterCodeFragment.switchCCCreateState(context, id, ClusterCodeFragment.OVERRIDE, true);
    }

    public static void setCodeInfo(Context context) {
        GetUserData.getProfileID(context, RegisterActivity.getClustername(), GetUserData.Register);
    }

}
