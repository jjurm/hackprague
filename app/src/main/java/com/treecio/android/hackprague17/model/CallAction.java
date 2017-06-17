package com.treecio.android.hackprague17.model;

import java.util.HashMap;

/**
 * Created by Pali on 17.06.2017.
 */

public class CallAction {

    CallAction.CallActionType type;

    String description;

    HashMap<String, String> parameters;

    enum CallActionType {
        Remind,
        Meet,
        Address,
        Log
    }
}
