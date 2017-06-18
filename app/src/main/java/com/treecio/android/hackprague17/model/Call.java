package com.treecio.android.hackprague17.model;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Call {

    int id;

    String callerName;

    String number;

    String email;

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

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public void setCallActions(List<CallAction> actions) {
        callActions = actions;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
