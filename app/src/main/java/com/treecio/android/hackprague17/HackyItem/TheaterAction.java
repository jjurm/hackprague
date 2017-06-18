package com.treecio.android.hackprague17.HackyItem;

import com.treecio.android.hackprague17.model.CallAction;

import ai.api.model.Result;

/**
 * Created by xcubae00 on 18.6.2017.
 */

public class TheaterAction extends CallAction {

    public TheaterAction (Result result) {
        super(result);
        type = CallActionType.Tip;

        title = "Tip for today";
        getTipForToday();
    }

    public TheaterAction() {
        type = CallActionType.Contact;
        title = "Tip for today";
        description = "Romeo and Juliet in Theater";
    }

    protected void getTipForToday() {
        description = "Very cool recommendation";
    }
}
