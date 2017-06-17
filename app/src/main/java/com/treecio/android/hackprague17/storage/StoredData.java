package com.treecio.android.hackprague17.storage;

import com.treecio.android.hackprague17.model.Call;

import java.util.ArrayList;
import java.util.List;

/**
 * Object holding a bundle of data including call history.
 */

public class StoredData {

    private List<Call> calls = new ArrayList<>();

    private StoredData() {

    }

    public List<Call> getCalls() {
        return calls;
    }

    public static StoredData newEmptyStoredData() {
        return new StoredData();
    }

}
