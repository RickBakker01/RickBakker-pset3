package com.example.rick.rickbakker_pset3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().findItem(R.id.navigation_search).setChecked(true);
        findViewById(R.id.bSearch).setOnClickListener(new myListener());

        search = (EditText) findViewById(R.id.search);
        assert search != null;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        Intent intent;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_watchlist:
                    intent = new Intent(getApplicationContext(), Watch_ListActivity.class);
                    startActivity(intent);
                    break;
            }
            return false;
        }
    };

    public class myListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.bSearch:
                        trackSearch();
                        break;
                }
        }
    }

    public void trackSearch(){
        String trackSearch = search.getText().toString();
        TrackAsyncTask asyncTask = new TrackAsyncTask(this);
        asyncTask.execute(trackSearch);
        search.getText().clear();
    }

    public void trackStartIntent(ArrayList<String> trackData) {
        Intent dataIntent = new Intent(this, DataActivity.class);
        dataIntent.putExtra("data", trackData);
        this.startActivity(dataIntent);
    }
}