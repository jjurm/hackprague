package com.treecio.android.hackprague17.model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JJurM on 17/06/2017.
 */

public class Call {

    int id;

    String callerName;

    Uri photo;

    Date date;

    List<CallAction> callActions = new ArrayList<>();

    public Call(int id) {
        this.id = id;
    }

    public Call(int id, String name, Uri photo, Date date) {
        this.id = id;
        this.callerName = name;
        this.photo = photo;
        this.date = date;
    }

    public String getCallerName() {
        return callerName;
    }

    public Uri getPhoto() {
        return photo;
    }

    public Date getDate() {
        return date;
    }

    public List<CallAction> getCallActions() {
        return callActions;
    }

    public int getId() {
        return id;
    }
}
