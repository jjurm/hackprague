package com.treecio.android.hackprague17;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;

import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.storage.StoragePort;

import java.util.Date;

public class CallRecorderService extends Service {

    private static final String TAG = CallRecorderService.class.getName();

    public static final String EXTRA_SERVICE_ACTION = "extra_service_action";
    public static final String EXTRA_NUMBER = "extra_number";

    public static final int ACTION_PREPARE = 0;
    public static final int ACTION_START = 1;
    public static final int ACTION_STOP = 2; // may be called without start

    StoragePort storagePort;
    int recordingId;
    String number;
    Date date;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int ret = super.onStartCommand(intent, flags, startId);

        switch (intent.getIntExtra(EXTRA_SERVICE_ACTION, -1)) {
            case ACTION_PREPARE:
                String number = intent.getStringExtra(EXTRA_NUMBER);
                prepareRecording(number);
                Log.d(TAG, "service prepared " + number);
                break;
            case ACTION_START:
                startRecording();
                Log.d(TAG, "service started");
                break;
            case ACTION_STOP:
                stopRecording();
                stopSelf();
                Log.d(TAG, "service stopped");
                break;
        }

        return ret;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        storagePort = new StoragePort(getApplicationContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        storagePort = null;
    }

    private void prepareRecording(String number) { // when dialing number
        recordingId = storagePort.getData().obtainNextCallIndex();
        storagePort.saveData();

        this.number = number;
    }

    private void startRecording() { // when the call has been answered
        // use this.recordingId
        date = new Date();
        date.setHours(0);

    }

    private void stopRecording() { // on call end
        // may be called just after prepareRecording, skipping startRecording

        finalizeCall();
    }

    private void finalizeCall() {

        Call call = new Call(recordingId);
        call.setDate(date);

        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));
        try (Cursor cur = getContentResolver().query(uri,
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

                try (Cursor emailCur = getContentResolver().query(
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

    }

}
