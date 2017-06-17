package com.treecio.android.hackprague17.storage;

import android.annotation.SuppressLint;

import com.treecio.android.hackprague17.model.Call;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Object holding a bundle of data including call history.
 */

public class StoredData {

    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Call> calls = new HashMap<>();
    private int nextInt = 1;

    private StoredData() {

    }

    public HashMap<Integer, Call> getCalls() {
        return calls;
    }

    public Call getCall(int id) {
        return calls.get(id);
    }

    public int obtainNextCallIndex() {
        return nextInt++;
    }

    static StoredData newEmptyStoredData() {
        return new StoredData();
    }

}
