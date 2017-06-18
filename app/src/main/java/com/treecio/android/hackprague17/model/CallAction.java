package com.treecio.android.hackprague17.model;

import android.util.Log;

import com.google.gson.JsonElement;

import java.util.Calendar;
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

    protected HashMap<String, String> parameters;

    protected String title;

    protected String location;

    protected String contactname;

    protected String contactinfo;

    protected Date date;

    public Date getDate() {
        return date;
    }

    public String getTitle() { return title; }

    public String getContactName() { return contactname; }

    public String getContactinfo() { return contactinfo; }

    public CallActionType getType() { return type; }

    public String getDescription() { return description; }

    public HashMap<String, String> getParameters() { return parameters; }


    public enum CallActionType {
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
        location = parameters.get("Location");
        if (location == null) {
            location = "Prague";
        }
    }

    protected void parseDate() {
        String d = parameters.get("TimeDate");
        if (d == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();

        String[] sa = d.split(" ");
        for (String s: sa) {
            switch (s) {
                case "today":
                    break;
                case "tomorrow":
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    break;
                case "tonight":
                    cal.set(Calendar.HOUR_OF_DAY, 8);
                    cal.set(Calendar.AM_PM, Calendar.PM);
                case "morning":
                    cal.set(Calendar.HOUR_OF_DAY, 8);
                    cal.set(Calendar.AM_PM, Calendar.AM);
                case "lunch":
                    cal.set(Calendar.HOUR_OF_DAY, 12);
                    cal.set(Calendar.AM_PM, Calendar.PM);
                case "evening":
                    cal.set(Calendar.HOUR_OF_DAY, 7);
                    cal.set(Calendar.AM_PM, Calendar.PM);
                case "afternoon":
                    cal.set(Calendar.HOUR_OF_DAY, 4);
                    cal.set(Calendar.AM_PM, Calendar.PM);
                default:
                   if (s.contains(":")) {
                       String[] ta = d.split(":");
                       if (ta.length == 2) {
                           try {
                               cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ta[0]));
                               cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ta[1]));
                           } catch (Exception e) {

                           }
                       }
                   }
            }
        }
        date = cal.getTime();

    }

    protected void parseContact() {
        contactname = parameters.get("given-name");
        if (contactname == null) {
            contactname = "";
        } else {
            contactname += " ";
        }

        String lastname = parameters.get("last-name");
        if (lastname != null) {
            contactname += lastname;
        } else {
            if (contactname == "") {
                contactname = "Anna Summ";
            }
        }

        contactinfo = "";
        String s = parameters.get("email");
        if (s != null) {
            contactinfo = s;
        }

        s = parameters.get("phone-number");
        if (s != null) {
            contactinfo += "\n" + s;
        }

        s = parameters.get("url");
        if (s != null) {
            contactinfo += "\n" + s;
        }
    }
}
