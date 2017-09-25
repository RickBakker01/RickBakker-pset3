package com.example.rick.rickbakker_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        makeAsyncTask();
    }

    public void makeAsyncTask(){
        Intent intent = getIntent();
        String track = intent.getStringExtra("track");
        ExtraAsyncTask asyncTask = new ExtraAsyncTask(this);
        asyncTask.execute(track);
    }

    public void infoStartIntent(ArrayList<String> trackData) {
        Intent dataIntent = new Intent(this, infoDataActivity.class);
        dataIntent.putExtra("data", trackData);
        this.startActivity(dataIntent);
    }
}
