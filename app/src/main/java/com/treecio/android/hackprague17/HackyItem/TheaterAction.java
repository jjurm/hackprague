package com.treecio.android.hackprague17.HackyItem;

import android.util.Log;

import com.treecio.android.hackprague17.model.CallAction;


import ai.api.model.Result;

/**
 * Created by xcubae00 on 18.6.2017.
 */

public class TheaterAction extends CallAction {

    final String TAGGG = "TheaterAction";


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
        description = "Love in Idleness\nApollo Theatre\n";
        description += "https://www.londontheatredirect.com/play/2681/love-in-idleness-tickets.aspx";

        new Thread(new Runnable() {
            @Override
            public void run() {
                GetShows s = new GetShows();
                try {
                    description = s.get();
                    Log.i(TAGGG, description);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
