package com.example.rick.rickbakker_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DataActivity extends AppCompatActivity {

    ListView lvItems;
    ArrayList<String> trackArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        lvItems = (ListView) findViewById(R.id.ListViewID);

        Bundle extras = getIntent().getExtras();
        List<String> trackArray2 = (ArrayList<String>) extras.getSerializable("data");
        String[] values = new String[trackArray2.size()];

        for (int i = 0; i < trackArray2.size(); i++) {
            values[i] = trackArray2.get(i);
        }

        ArrayAdapter< String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        lvItems.setAdapter(adapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String search = lvItems.getItemAtPosition(position).toString();
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                intent.putExtra("track", search);
                startActivity(intent);
            }
        });
    }


}
