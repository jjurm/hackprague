package com.treecio.android.hackprague17;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.treecio.android.hackprague17.storage.StoragePort;

public class CallRecorderService extends Service {

    private static final String TAG = CallRecorderService.class.getName();

    public static final String EXTRA_STOP_SERVICE = "extra_stop_service";

    StoragePort storagePort;
    int recordingId;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int ret = super.onStartCommand(intent, flags, startId);

        if (!intent.getBooleanExtra(EXTRA_STOP_SERVICE, false)) {
            // start service
            Log.d(TAG, "service started");
            startRecording();
        } else {
            // stop service
            Log.d(TAG, "service stopped");
            stopRecording();
            stopSelf();
        }

        return ret;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        storagePort = new StoragePort(getApplicationContext());
        recordingId = storagePort.getData().obtainNextCallIndex();
        storagePort.saveData();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        storagePort = null;
    }

    private void startRecording() {

        // use this.recordingId

    }

    private void stopRecording() {

    }
}
