package com.treecio.android.hackprague17.model;

import java.util.HashMap;

/**
 * Created by Pali on 17.06.2017.
 */

public class CallAction {

    CallAction.CallActionType type;

    String description;

    HashMap<String, String> parameters;

    public CallActionType getType() { return type; }

    public String getDescription() { return description; }

    public HashMap<String, String> getParameters() { return parameters; }

    public static enum CallActionType {
        Remind,
        Meet,
        Address,
        Log
    }
}
