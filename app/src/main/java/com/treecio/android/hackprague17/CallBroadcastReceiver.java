package com.treecio.android.hackprague17;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = CallBroadcastReceiver.class.getName();

    private static final String ACTION_OUT = "android.intent.action.NEW_OUTGOING_CALL";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(CallBroadcastReceiver.class.getName(), "onReceive!");
        Bundle bundle = intent.getExtras();
        if (intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            if (bundle != null) {
                String state = bundle.getString(TelephonyManager.EXTRA_STATE);
                if (state == null) return;
                Log.d(TAG, "state: " + state);
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
        }
    }

}
