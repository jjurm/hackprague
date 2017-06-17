package com.treecio.android.hackprague17.storage;

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

    private HashMap<Integer, Call> calls = new HashMap<>();
    private AtomicInteger nextInt = new AtomicInteger(1);

    private StoredData() {

    }

    public HashMap<Integer, Call> getCalls() {
        return calls;
    }

    public Call getCall(int id) {
        return calls.get(id);
    }

    public static StoredData newEmptyStoredData() {
        return new StoredData();
    }

}
