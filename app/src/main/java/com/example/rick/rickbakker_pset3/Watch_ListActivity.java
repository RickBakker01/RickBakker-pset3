package com.example.rick.rickbakker_pset3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Watch_ListActivity extends AppCompatActivity {

    Context context;
    ArrayList<String> tasks;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch__list);
        ListView listlvItems = (ListView) findViewById(R.id.listListViewID);
        listlvItems.setOnItemClickListener(new listener());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().findItem(R.id.navigation_watchlist).setChecked(true);

        tasks = new ArrayList<>();

        Load();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        if (name != null) {
            if (tasks.contains(name)){
                Toast.makeText(this, "Already in list", Toast.LENGTH_SHORT).show();
            } else {
                tasks.add(name);
            }

        }

            context = this;
            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, tasks);
            listlvItems.setAdapter(adapter);

        if (tasks != null) {
            Save();
        }
    }
    private class listener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            tasks.remove(position);
            adapter.notifyDataSetChanged();
            Save();
        }
    }


    public void Save(){
        SharedPreferences prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(tasks);
        editor.putStringSet("key", set);
        editor.apply();
    }


    public void Load(){
        SharedPreferences prefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = prefs.getStringSet("key", null);
        if (set != null) {
            tasks.addAll(set);
            Log.d("qqdsdasd", tasks.toString());
        }
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
                case R.id.navigation_search:
                    intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
                    break;
            }
            return false;
        }

    };

}
