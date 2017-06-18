package com.treecio.android.hackprague17;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.model.CallAction;
import com.treecio.android.hackprague17.storage.NotificationBuilder;
import com.treecio.android.hackprague17.storage.StoragePort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallRecorderService extends Service implements ServiceNotifiesListener {

    @Override
    public void listeningEnded() {
        if (onCall) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    voice.proceed();
                }
            });
        }
    }

    private static final String TAG = CallRecorderService.class.getName();

    HackyVoice voice;
    HackyListener listie;

    private static boolean onCall = false;

    public static final String EXTRA_SERVICE_ACTION = "extra_service_action";
    public static final String EXTRA_NUMBER = "extra_number";

    public static final int ACTION_PREPARE = 0;
    public static final int ACTION_START = 1;
    public static final int ACTION_STOP = 2; // may be called without start

    public ExecutorService executor = Executors.newSingleThreadExecutor();

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
                Log.i(TAG, "service prepared " + number);
                break;
            case ACTION_START:
                startRecording();
                Log.i(TAG, "service started");
                break;
            case ACTION_STOP:
                stopRecording();
                stopSelf();
                Log.i(TAG, "service stopped");
                break;
        }

        return ret;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        storagePort = new StoragePort(getApplicationContext());
        voice = new HackyVoice(getApplicationContext(), this);
        listie = new HackyListener();
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
        Log.i(TAG, "Start recording!");
        date = new Date();

        if (!onCall) {
            onCall = true;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    voice.listen();
                }
            });
        }
    }

    private void stopRecording() { // on call end
        // may be called just after prepareRecording, skipping startRecording
        if (onCall) {
            voice.stop();
            onCall = false;
            finalizeCall();
        }
    }

    private void finalizeCall() {

        Log.i(TAG, "Finalizing");
        Log.i(TAG, "Getting actions");

        List<CallAction> actions = new ArrayList<>();

        Call call = Call.createCall(getApplicationContext(), recordingId, number, date, actions);

        if (recordingId == 4) {
            // mocking

        } else {
            try {
                listie.process(voice.getData(), storagePort, call);
            } catch (ExecutionException e) {
                Log.e(TAG, e.getMessage(), e);
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }

        storagePort.getData().getCalls().put(call.getId(), call);
        storagePort.saveData();

        new NotificationBuilder(getApplicationContext()).createNotification(call);
    }

}
