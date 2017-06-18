package com.treecio.android.hackprague17.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Provides access to application's storage. Use this to load {@link StoredData}.
 */

public class StoragePort {

    private static String PREF_DATA = "data";
    private static String KEY_DATA = "stored_data";

    private Context mContext;

    private StoredData storedData;

    public StoragePort(Context context) {
        this.mContext = context;
    }

    public void loadData() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Uri.class, new UriDeserializer())
                .registerTypeAdapter(Date.class, ser)
                .registerTypeAdapter(Date.class, deser)
                .create();
        String json = getPrefs().getString(KEY_DATA, null);
        if (json != null) {
            storedData = gson.fromJson(json, StoredData.class);
        } else {
            storedData = StoredData.newEmptyStoredData();
        }
    }

    public void saveData() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Uri.class, new UriSerializer())
                .registerTypeAdapter(Date.class, ser)
                .registerTypeAdapter(Date.class, deser)
                .create();
        String json = gson.toJson(storedData);
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

    public class UriSerializer implements JsonSerializer<Uri> {
        public JsonElement serialize(Uri src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

    public class UriDeserializer implements JsonDeserializer<Uri> {
        @Override
        public Uri deserialize(final JsonElement src, final Type srcType,
                               final JsonDeserializationContext context) throws JsonParseException {
            return Uri.parse(src.getAsString());
        }
    }

    JsonSerializer<Date> ser = new JsonSerializer<Date>() {
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext
                context) {
            return src == null ? null : new JsonPrimitive(src.getTime());
        }
    };

    JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : new Date(json.getAsLong());
        }
    };

}
