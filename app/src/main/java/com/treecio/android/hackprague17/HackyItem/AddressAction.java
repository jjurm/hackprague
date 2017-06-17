package com.treecio.android.hackprague17.HackyItem;

import com.treecio.android.hackprague17.model.CallAction;

import ai.api.model.Result;

/**
 * Created by xcubae00 on 17.6.2017.
 */

public class AddressAction extends CallAction {

    public AddressAction (Result result) {
        super(result);
        type = CallActionType.Address;

        description = "Address description";
    }
}
