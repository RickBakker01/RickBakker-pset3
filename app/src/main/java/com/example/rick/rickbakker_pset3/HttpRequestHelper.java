package com.example.rick.rickbakker_pset3;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpRequestHelper {


    protected static synchronized String downloadFromServer(String... params) throws MalformedURLException {
        String result = "";
        String track = params[0];


        URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=track.search&track=" + track + "&api_key=c7bdb1d5847001a286e0f17349a59ad6&format=json");
        HttpURLConnection connect;

        if (url != null) {
            try {
                connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("GET");

                Integer responseCode = connect.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;
                    while ((line = bReader.readLine()) != null) {
                        result += line;
                    }
                }


        } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
