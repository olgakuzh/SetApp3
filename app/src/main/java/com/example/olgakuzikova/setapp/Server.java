package com.example.olgakuzikova.setapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Server extends AsyncTask<String, Void, JSONObject> {

    private String set_server_url;
    URL url;
    HttpURLConnection urlConnection;

    public Server() throws IOException, JSONException {
        this.set_server_url = "http://194.176.114.21:8050/";
    }

    public JSONObject post(String data) throws IOException, JSONException {
        url = new URL(set_server_url);
        // creating connection
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true); // setting POST method// creating stream for writing request

        // creating stream for writing request
        OutputStream out = urlConnection.getOutputStream();
        out.write(data.getBytes());

        Scanner in = new Scanner(urlConnection.getInputStream());
        String response = in.nextLine();
        JSONObject resp = new JSONObject(response);
        return resp;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            return post(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}