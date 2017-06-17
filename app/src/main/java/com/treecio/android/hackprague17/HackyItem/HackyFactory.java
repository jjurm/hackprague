package com.treecio.android.hackprague17.HackyItem;

import android.util.Log;

import com.treecio.android.hackprague17.model.CallAction;;

import ai.api.model.Result;

/**
 * Created by xcubae00 on 17.6.2017.
 */

public class HackyFactory {

    private String TAG = "HackyFactory";

    public CallAction create(Result result) {

        //get action
        String action = result.getAction();
        Log.i(TAG, "Action: " + action);

        switch (action) {
            case "Meet":
                return new MeetAction(result);
            case "Remind":
                return new RemindAction(result);
            case "Address":
                return new AddressAction(result);
            case "Log":
                return new LogAction(result);
        }

        return null;
    }
}
