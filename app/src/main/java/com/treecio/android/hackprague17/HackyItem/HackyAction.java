package com.treecio.android.hackprague17.HackyItem;

/**
 * Created by xcubae00 on 17.6.2017.
 */

public abstract class HackyAction {
    private String type;

    protected HackyAction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public HackyAction() {}
}
