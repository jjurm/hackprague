package com.treecio.android.hackprague17.model;

import android.util.Log;

import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

import ai.api.model.Result;


/**
 * Created by Pali on 17.06.2017.
 */

public class CallAction {

    final String TAG = "CalAction";

    protected CallAction.CallActionType type;

    protected String description;

    protected String query;

    protected HashMap<String, String> parameters;

    public CallActionType getType() { return type; }

    public String getDescription() { return description; }

    public HashMap<String, String> getParameters() { return parameters; }

    public static enum CallActionType {
        Remind,
        Meet,
        Address,
        Log
    }

    protected CallAction(Result result) {
        query = result.getResolvedQuery();
        Log.i(TAG, "Resolved query: " + query);

        //get parameters
        final HashMap<String, JsonElement> params = result.getParameters();
        if (params != null && !params.isEmpty()) {
            Log.i(TAG, "Parameters: ");

            for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();

                Log.i(TAG, String.format("%s: %s", key, value));

                parameters.put(key, value);
            }
        }

    }
}
