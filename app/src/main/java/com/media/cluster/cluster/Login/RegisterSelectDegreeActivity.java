package com.media.cluster.cluster.Login;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.media.cluster.cluster.R;

import java.util.ArrayList;
import java.util.List;


public class RegisterSelectDegreeActivity extends AppCompatActivity {


    private RegisterNumberAdapter adapter;
    private String  setText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select_degree);
        View layout, cardView;
        RecyclerView recyclerView;
        layout = findViewById(R.id.activity_register_select_degree);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cardView = findViewById(R.id.register_select_degree_card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.register_select_degree_recycler_view);
        adapter = new RegisterNumberAdapter(getApplicationContext(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),  new RegisterSelectCountryActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                adapter.setDetailSelected(view);
                switch (position){
                    case 0:
                        setText = getResources().getString(R.string.degreesNoResponse);
                        break;
                    case 1:
                        setText = getResources().getString(R.string.degreesNotFinished);
                        break;
                    case 2:
                        setText = getResources().getString(R.string.degreesApprenticeship);
                        break;
                    case 3:
                        setText = getResources().getString(R.string.degreesHighSchool);
                        break;
                    case 4:
                        setText = getResources().getString(R.string.degreesHighSchoolDiploma);
                        break;
                    case 5:
                        setText = getResources().getString(R.string.degreesSomeUniversity);
                        break;
                    case 6:
                        setText = getResources().getString(R.string.degreesPostgraduateDegree);
                        break;
                }

                RegisterDetailFragment.setEducationField(setText);
                new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        },300);
            }
        }));


    }

    public  List<SelectNumberDataModel> getData(){
        List<SelectNumberDataModel> data = new ArrayList<>();

        String[] degrees= {getResources().getString(R.string.degreesNoResponse), getResources().getString(R.string.degreesNotFinished),getResources().getString(R.string.degreesApprenticeship), getResources().getString(R.string.degreesHighSchool),getResources().getString(R.string.degreesHighSchoolDiploma),getResources().getString(R.string.degreesSomeUniversity), getResources().getString(R.string.degreesPostgraduateDegree)};

        for(int i = 0; i < degrees.length; i++){
            SelectNumberDataModel current = new SelectNumberDataModel();
            current.countryName = degrees[i];
            data.add(current);
        }

        return data;
    }
}

class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private RegisterSelectCountryActivity.ClickListener clickListener;

    RecyclerTouchListener(Context context,  final RegisterSelectCountryActivity.ClickListener clickListener) {

        this.clickListener=clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
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

