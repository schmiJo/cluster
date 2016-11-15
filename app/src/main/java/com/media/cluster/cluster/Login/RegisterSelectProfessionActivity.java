package com.media.cluster.cluster.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.media.cluster.cluster.Main.DrawerLayoutManager;
import com.media.cluster.cluster.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterSelectProfessionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RegisterNumberAdapter adapter;
    DrawerLayoutManager drawerLayoutManager;
    private String selected = "";
    private EditText typed;
    private int selectedPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select_profession);
        typed = (EditText)findViewById(R.id.register_detail_job_description);

        typed.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        RegisterDetailFragment.setProfessionField(selectedPosition,selected,typed.getText().toString().trim());
                        finish();
                        return true;
                    }
             return false;
            }});
        findViewById(R.id.activity_register_select_profession).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterDetailFragment.setProfessionField(selectedPosition,selected, typed.getText().toString().trim());
                finish();
            }
        });

        findViewById(R.id.register_select_profession_card_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        drawerLayoutManager= new DrawerLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.register_select_profession_recycler_view);
        adapter = new RegisterNumberAdapter(getApplicationContext(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(drawerLayoutManager);

        recyclerView.addOnItemTouchListener(new com.media.cluster.cluster.Login.RecyclerTouchListener(getApplicationContext(),  new RegisterSelectCountryActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                selected = adapter.setProfessionSelected(position,drawerLayoutManager);
                selectedPosition  = position;
            }
        }));
    }

    public  List<SelectNumberDataModel> getData(){
        List<SelectNumberDataModel> data = new ArrayList<>();
        String[] professions = {getResources().getString(R.string.professionNoResponse),getResources().getString(R.string.professionUnemployed),getResources().getString(R.string.professionApprenticeship),getResources().getString(R.string.professionEmployee),getResources().getString(R.string.professionCivilServant),getResources().getString(R.string.professionHomemaker),getResources().getString(R.string.professionRetired),getResources().getString(R.string.professionSelfEmployed),getResources().getString(R.string.professionHighSchoolStudent),getResources().getString(R.string.professionCollegeStudent)};

        for(int i = 0; i<professions.length;i++){
            SelectNumberDataModel current = new SelectNumberDataModel();
            current.countryName = professions[i];
            data.add(current);
        }
        return data;
    }


}
