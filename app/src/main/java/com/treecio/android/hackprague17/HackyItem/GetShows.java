package com.treecio.android.hackprague17.HackyItem;

/**
 * Created by xcubae00 on 18.6.2017.
 */

import android.util.Log;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import ai.api.util.IOUtils;

public class GetShows {
    private String address = "https://api.londontheatredirect.com/rest/v2/Venues/1/Events";

    public String get() throws Exception {
        URL url = new URL(address);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.setRequestProperty( "Content-Type", "application/json");
        conn.setRequestProperty( "Api-Key", "jp7rddm9fmnm7w3batt9vmqe");
        conn.setRequestProperty( "X-Originating-Ip", "95.46.32.147");

        int responseCode = conn.getResponseCode();
        Log.i("Response", Integer.toString(responseCode));
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            JSONObject obj = new JSONObject(IOUtils.readAll(conn.getInputStream()));
            JSONObject o =  obj.getJSONArray("Events").getJSONObject(0);
            return o.getString("Name") + "\n" + "Link: " + o.getString("EventDetailUrl");
        }

        return "No shows available :(";
    }

}
