package com.example.rick.rickbakker_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class infoDataActivity extends AppCompatActivity {

    ListView infolvItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_data);


        infolvItems = (ListView) findViewById(R.id.infoListViewID);
        infolvItems.setOnItemClickListener(new listener());

        Bundle extras = getIntent().getExtras();
        List<String> trackArray2 = (ArrayList<String>) extras.getSerializable("data");
        String[] values = new String[trackArray2.size()];

        for (int i = 0; i < trackArray2.size(); i++) {
            values[i] = trackArray2.get(i);
        }

        ArrayAdapter<String> adapterinfo = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        infolvItems.setAdapter(adapterinfo);
    }


    private class listener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String search = infolvItems.getItemAtPosition(position).toString();
            if (search.startsWith("Trackname: ")) {
                Intent intent = new Intent(infoDataActivity.this, Watch_ListActivity.class);
                intent.putExtra("name", search);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), search, Toast.LENGTH_SHORT).show();}
        }
    }

}
