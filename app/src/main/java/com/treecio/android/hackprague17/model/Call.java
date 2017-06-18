package com.treecio.android.hackprague17.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.treecio.android.hackprague17.storage.StoragePort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Call {

    private static final String TAG = Call.class.getName();

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

    public static Call createCall(Context context, int recordingId, String number, Date date, List<CallAction> actions) {

        ContentResolver cr = context.getContentResolver();

        Call call = new Call(recordingId);
        call.setDate(date);

        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        try (Cursor cur = cr.query(uri,
                new String[]{
                        ContactsContract.PhoneLookup.LOOKUP_KEY,
                        ContactsContract.PhoneLookup.DISPLAY_NAME,
                        ContactsContract.PhoneLookup.NORMALIZED_NUMBER,
                        ContactsContract.PhoneLookup.PHOTO_URI,
                }, null, null, null)) {

            if (cur.moveToNext()) { // take first contact match
                String contactLookupKey = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.LOOKUP_KEY));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                Log.d(TAG, name);
                call.setCallerName(name);
                Log.d(TAG, cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.NORMALIZED_NUMBER)));
                String photoUri = cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.PHOTO_URI));
                if (photoUri != null) {
                    Log.d(TAG, photoUri);
                    call.setPhoto(Uri.parse(photoUri));
                }

                try (Cursor emailCur = cr.query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        new String[]{
                                ContactsContract.CommonDataKinds.Email.ADDRESS,
                        },
                        ContactsContract.CommonDataKinds.Email.LOOKUP_KEY + "=?",
                        new String[]{contactLookupKey}, null)) {
                    if (emailCur.moveToNext()) { // take first email match
                        String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                        Log.d(TAG, email);
                        call.setEmail(email);
                    }
                }

            }
        }

        call.setCallActions(actions);

        return call;

    }

}
