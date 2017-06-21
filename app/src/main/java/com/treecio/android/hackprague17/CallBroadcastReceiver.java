package com.treecio.android.hackprague17;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = CallBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(CallBroadcastReceiver.class.getName(), "onReceive! " + intent.getAction());
        Bundle bundle = intent.getExtras();
        if (intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            if (bundle != null) {
                String state = bundle.getString(TelephonyManager.EXTRA_STATE);
                if (state == null) return;
                Log.i(TAG, "state: " + state);
                Intent service = new Intent(context, CallRecorderService.class);
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    // send number
                    service.putExtra(CallRecorderService.EXTRA_SERVICE_ACTION, CallRecorderService.ACTION_PREPARE);
                    String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    service.putExtra(CallRecorderService.EXTRA_NUMBER, number);
                } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                    // start service
                    service.putExtra(CallRecorderService.EXTRA_SERVICE_ACTION, CallRecorderService.ACTION_START);
                } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                    // stop service
                    service.putExtra(CallRecorderService.EXTRA_SERVICE_ACTION, CallRecorderService.ACTION_STOP);
                }
                context.startService(service);
            }
        } else if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            Log.i(TAG, "HEREHERE");
            if (bundle != null) {
                Intent service = new Intent(context, CallRecorderService.class);
                service.putExtra(CallRecorderService.EXTRA_SERVICE_ACTION, CallRecorderService.ACTION_PREPARE);
                String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                service.putExtra(CallRecorderService.EXTRA_NUMBER, number);
                context.startService(service);
            }
        }
    }

}
