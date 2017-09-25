package com.example.rick.rickbakker_pset3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class TrackAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    SearchActivity searchAct;

    public TrackAsyncTask(SearchActivity search) {
        this.searchAct = search;
        this.context = this.searchAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute(){
        Toast.makeText(context, "Searching for tracks...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params)  {
        try {
            return HttpRequestHelper.downloadFromServer(params);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } return null;
    }

    @Override
    protected void onPostExecute(String result){
        ArrayList<String> Data = new ArrayList<>();
        super.onPostExecute(result);
        try {
            JSONObject Mainobj = new JSONObject(result);

            JSONObject Resultsobj = Mainobj.getJSONObject("results");
            JSONObject Matchobj = Resultsobj.getJSONObject("trackmatches");
            JSONArray Trackarr = Matchobj.getJSONArray("track");

            for (int i = 0; i < Trackarr.length(); i++) {
                JSONObject Trackobj = Trackarr.getJSONObject(i);
                String name = Trackobj.get("name").toString();
                String artist = Trackobj.get("artist").toString();
                Data.add(name + " / "+ artist);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
       this.searchAct.trackStartIntent(Data);
    }


}
