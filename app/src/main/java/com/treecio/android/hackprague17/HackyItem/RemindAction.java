package com.treecio.android.hackprague17.HackyItem;

import com.treecio.android.hackprague17.model.CallAction;

import ai.api.model.Result;

/**
 * Created by xcubae00 on 17.6.2017.
 */

public class RemindAction extends CallAction {

    public RemindAction (Result result) {
        super(result);

        type = CallActionType.Remind;

        description = "Remind description";
    }

    public RemindAction() {
        type = CallActionType.Remind;
        title = "Reminder from James Franco";
        description = "Call your mom!";
    }
}
