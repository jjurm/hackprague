package com.treecio.android.hackprague17.HackyItem;

import com.treecio.android.hackprague17.model.CallAction;

import ai.api.model.Result;

/**
 * Created by xcubae00 on 17.6.2017.
 */

public class LogAction extends CallAction {

    public LogAction (Result result) {
        super(result);
        type = CallActionType.Log;

        description = "Log description";

    }
}
