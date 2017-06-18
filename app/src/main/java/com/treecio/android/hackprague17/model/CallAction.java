package com.treecio.android.hackprague17.model;

import android.util.Log;

import com.google.gson.JsonElement;

import java.util.Date;
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

    protected String title;

    protected Date date;

    protected HashMap<String, String> parameters;

    protected String title;

    protected String location;

    protected String contactname;

    protected Date date;

    public Date getDate() {
        return date;
    }

    public String getTitle() { return title; }

    public String getContactName() { return contactname; }

    public CallActionType getType() { return type; }

    public String getDescription() { return description; }

    public HashMap<String, String> getParameters() { return parameters; }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public static enum CallActionType {
        Remind,
        Meet,
        Address,
        Contact,
        Tip
    }

    //mock constructor
    protected CallAction() {
        query = "mock";
        date = new Date();
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

        parseLocation();
        parseDate();
        parseContact();
    }

    protected void parseLocation() {
        //TODO
        location = parameters.get("Location");
        if (location == null) {
            location = "Unknown";
        }
    }

    protected void parseDate() {
        //TODO
        date = new Date();
    }

    protected void parseContact() {
        //TODO
        contactname = "Joe Black";
    }
}
