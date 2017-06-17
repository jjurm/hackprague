package com.treecio.android.hackprague17.HackyItem;

import android.util.Log;

import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

import ai.api.model.Result;

/**
 * Created by xcubae00 on 17.6.2017.
 */

public class HackyFactory {

    private String TAG = "HackyFactory";

    public HackyAction create(Result result) {

        String query = result.getResolvedQuery();
        Log.i(TAG, "Resolved query: " + query);

        //get action
        String action = result.getAction();
        Log.i(TAG, "Action: " + action);

        //get parameters
        final HashMap<String, JsonElement> params = result.getParameters();
        if (params != null && !params.isEmpty()) {
            Log.i(TAG, "Parameters: ");
            for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                Log.i(TAG, String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
            }
        }

        switch (action) {
            case "Meet":
                break;
            case "Remind":
                break;
            case "Address":
                break;
            case "Log":
                break;
        }

        return null;
    }
}
