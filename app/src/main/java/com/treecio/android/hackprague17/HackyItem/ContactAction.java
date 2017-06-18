package com.treecio.android.hackprague17.HackyItem;

import com.treecio.android.hackprague17.model.CallAction;

import ai.api.model.Result;

/**
 * Created by xcubae00 on 17.6.2017.
 */

public class ContactAction extends CallAction {

    public ContactAction(Result result) {
        super(result);
        type = CallActionType.Contact;

        title = contactname;

        //TODO
        description = "00421910420214";
    }

    //MOCK constructor
    public ContactAction() {
        type = CallActionType.Contact;
        title = "John Black";
        description = "john.black@gmail.com";
    }
}
