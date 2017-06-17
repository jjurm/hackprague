package com.treecio.android.hackprague17.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Provides access to application's storage. Use this to load {@link StoredData}.
 */

public class StoragePort {

    private static String PREF_DATA = "data";
    private static String KEY_DATA = "stored_data";

    private Context mContext;
    private Gson mGson;

    private StoredData storedData;

    public StoragePort(Context context) {
        this.mContext = context;
        this.mGson = new Gson();
    }

    public void loadData() {
        String json = getPrefs().getString(KEY_DATA, null);
        if (json != null) {
            storedData = mGson.fromJson(json, StoredData.class);
        } else {
            storedData = StoredData.newEmptyStoredData();
        }
    }

    public void saveData() {
        String json = mGson.toJson(storedData);
        getPrefs().edit().putString(KEY_DATA, json).apply();
    }

    private SharedPreferences getPrefs() {
        return mContext.getSharedPreferences(PREF_DATA, Context.MODE_PRIVATE);
    }

    public StoredData getData() {
        if (storedData == null) {
            loadData();
        }
        return storedData;
    }

}
