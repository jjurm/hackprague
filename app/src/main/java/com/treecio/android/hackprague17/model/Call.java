package com.treecio.android.hackprague17.model;

import android.net.Uri;

import java.util.Date;

/**
 * Created by JJurM on 17/06/2017.
 */

public class Call {

    String callerName;

    Uri photo;

    Date date;

    public String getCallerName() {
        return callerName;
    }

    public Uri getPhoto() {
        return photo;
    }

    public Date getDate() {
        return date;
    }
}
