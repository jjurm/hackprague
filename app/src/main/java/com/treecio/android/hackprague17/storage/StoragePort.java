package com.treecio.android.hackprague17.storage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.CallLog;

import com.google.gson.Gson;
import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.storage.StoredData;

import java.util.List;

/**
 * Provides access to application's storage. Use this to load {@link StoredData}.
 */

public class StoragePort {

    private static String PREF_DATA = "data";
    private static String KEY_DATA = "stored_data";

    private Context mContext;
    private Gson mGson;

    public StoragePort(Context context) {
        this.mContext = context;
        this.mGson = new Gson();
    }

    private SharedPreferences getPrefs() {
        return mContext.getSharedPreferences(PREF_DATA, Context.MODE_PRIVATE);
    }

    public void storeData(StoredData data) {
        String json = mGson.toJson(data);
        getPrefs().edit().putString(KEY_DATA, json).apply();
    }

    public StoredData getData() {
        String json = getPrefs().getString(KEY_DATA, null);
        if (json != null) {
            return mGson.fromJson(json, StoredData.class);
        } else {
            return StoredData.newEmptyStoredData();
        }
    }

}
