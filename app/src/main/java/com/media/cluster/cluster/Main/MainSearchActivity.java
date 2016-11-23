package com.media.cluster.cluster.Main;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.media.cluster.cluster.R;

public class MainSearchActivity extends AppCompatActivity {

    TextWatcher textWatcher;
    EditText searchText;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        searchText = (EditText) findViewById(R.id.search_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!searchText.getText().toString().equals("")){
                    menu.findItem(R.id.action_clear).setVisible(true);

                }else {
                    menu.findItem(R.id.action_clear).setVisible(false);
                }
            }
        };
        ActionBar supportActionBar = getSupportActionBar();

        searchText.addTextChangedListener(textWatcher);
        try {
            assert supportActionBar != null;
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_dark);
            supportActionBar.setTitle("");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_search, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_clear:
                searchText.setText("");
                break;
            case R.id.action_filter:
                item.setIcon(R.drawable.action_filter_light_ic);

                break;
            case android.R.id.home:
                finish();
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
