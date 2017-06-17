package com.treecio.android.hackprague17.model;

import android.net.Uri;

import java.util.Date;
import java.util.List;

/**
 * Created by JJurM on 17/06/2017.
 */

public class Call {

    int id;

    public Call(String name, Uri photo, Date date) {
        this.callerName = name;
        this.photo = photo;
        this.date = date;
    }

    String callerName;

    Uri photo;

    Date date;

    List<CallAction> callActions;

    public String getCallerName() {
        return callerName;
    }

    public Uri getPhoto() {
        return photo;
    }

    public Date getDate() {
        return date;
    }

    public List<CallAction> getCallActions() { return callActions; }

    public int getId() {
        return id;
    }
}
