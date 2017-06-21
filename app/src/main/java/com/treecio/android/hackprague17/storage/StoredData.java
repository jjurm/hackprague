package com.treecio.android.hackprague17.storage;

import android.annotation.SuppressLint;

import com.treecio.android.hackprague17.model.Call;

import java.util.HashMap;

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

    /**
     * Will not increase it's index. Don't use for creating new Calls.
     *
     * @return
     */
    public int getLastUsedIndex() {
        return nextInt - 1;
    }

    /**
     * Only use for mocking data
     * @param index
     */
    public void setNextIndex(int index) {
        this.nextInt = index;
    }

    static StoredData newEmptyStoredData() {
        return new StoredData();
    }

}
