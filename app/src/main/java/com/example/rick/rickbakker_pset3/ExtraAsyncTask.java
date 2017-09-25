package com.example.rick.rickbakker_pset3;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class ExtraAsyncTask extends AsyncTask<String, Integer, String> {

    Context context;
    InfoActivity infoAct;

    public ExtraAsyncTask(InfoActivity search) {
        this.infoAct = search;
        this.context = this.infoAct.getApplicationContext();

    }

    @Override
    protected void onPreExecute(){
        //Toast.makeText(context, "Searching for info...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params)  {
        try {
            return InfoHttpRequestHelper.downloadFromServer(params);
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

            JSONObject Resultsobj = Mainobj.getJSONObject("track");
            JSONObject Artist = Resultsobj.getJSONObject("artist");
            JSONObject Album = Resultsobj.getJSONObject("album");


            String Trackname = Resultsobj.get("name").toString();
            String Playcount = Resultsobj.get("playcount").toString();
            String Artistname = Artist.get("name").toString();
            String title = Album.get("title").toString();
            Data.add("Trackname: " + Trackname);
            Data.add("Artist: " + Artistname);
            Data.add("Album: " + title);
            Data.add("Playcount: " + Playcount);


        } catch (JSONException e) {
            e.printStackTrace();
        }
       this.infoAct.infoStartIntent(Data);
    }
}
