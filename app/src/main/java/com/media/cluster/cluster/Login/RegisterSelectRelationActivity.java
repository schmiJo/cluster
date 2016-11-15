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

public class RegisterSelectRelationActivity extends AppCompatActivity {

    private RegisterNumberAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select_relation);
        View layout, cardView;
        layout = findViewById(R.id.activity_register_select_relation);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RecyclerView recyclerView;
        cardView = findViewById(R.id.register_select_relation_card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.register_select_relation_recycler_view);
        adapter = new RegisterNumberAdapter(getApplicationContext(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RegisterSelectCountryActivity.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                adapter.setDetailSelected(view);
                RegisterDetailFragment.setRelationField(position);
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
        String[] states = {getResources().getString(R.string.relationNoResponse),getResources().getString(R.string.relationInARelationship), getResources().getString(R.string.relationItIsDifficult),getResources().getString(R.string.relationSingle),getResources().getString(R.string.relationOpenForAll),getResources().getString(R.string.relationMarried),getResources().getString(R.string.relationWidowed),getResources().getString(R.string.relationDivorced),getResources().getString(R.string.relationEngaged),getResources().getString(R.string.relationInLove)};
        for(int i = 0; i < states.length; i++){
            SelectNumberDataModel current = new SelectNumberDataModel();
            current.countryName = states[i];
            data.add(current);
        }
        return data;
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private RegisterSelectCountryActivity.ClickListener clickListener;

        RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RegisterSelectCountryActivity.ClickListener clickListener) {

            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }


            });


        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());

            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {

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

}