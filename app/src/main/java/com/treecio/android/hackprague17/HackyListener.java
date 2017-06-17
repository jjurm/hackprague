package com.treecio.android.hackprague17;

/**
 * Created by xcubae00 on 17.6.2017.
 */

import android.os.AsyncTask;
import android.util.Log;

import ai.api.AIDataService;
import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import ai.api.model.Status;

import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

public class HackyListener implements AIListener {

    private final String TOKEN = "a57e003f19e5402fa73751df094d7d37";

    final String TAG = "HackyListener";

    final AIConfiguration config = new AIConfiguration(TOKEN,
            AIConfiguration.SupportedLanguages.English,
            AIConfiguration.RecognitionEngine.System);

    final AIRequest aiRequest = new AIRequest();

    final AIDataService aiDataService = new AIDataService(config);

    public void process() {

        String text = "Could you please call your dad?";

        aiRequest.setQuery(text);

        Log.i(TAG, "ASKING: " + text);

        new AsyncTask<AIRequest, Void, AIResponse>() {
            @Override
            protected AIResponse doInBackground(AIRequest... requests) {
                try {
                    final AIResponse response = aiDataService.request(aiRequest);
                    return response;
                } catch (AIServiceException e) {
                }
                return null;
            }
            @Override
            protected void onPostExecute(AIResponse aiResponse) {
                Log.i(TAG, "Post execute");
                if (aiResponse != null) {
                    onResult(aiResponse);
                }
            }
        }.execute(aiRequest);

        Log.i(TAG, "Process exit");
    }

    /**
     * Use the response object to get all the results
     * @param response
     */
    public void onResult(final AIResponse response) {

        //get status
        final Status status = response.getStatus();
        Log.i(TAG, "Status code: " + status.getCode());
        Log.i(TAG, "Status type: " + status.getErrorType());

        //get resolved query
        final Result result = response.getResult();

        String query = result.getResolvedQuery();
        Log.i(TAG, "Resolved query: " + query);

        //get action
        String action = result.getAction();
        Log.i(TAG, "Action: " + action);

        //get parameters
        final HashMap<String, JsonElement> params = result.getParameters();
        if (params != null && !params.isEmpty()) {
            Log.i(TAG, "Parameters: ");
            for (final Map.Entry<String, JsonElement> entry : params.entrySet()) {
                Log.i(TAG, String.format("%s: %s", entry.getKey(), entry.getValue().toString()));
            }
        }
    }

    @Override
    public void onListeningStarted() {}

    @Override
    public void onListeningCanceled() {}

    @Override
    public void onListeningFinished() {}

    @Override
    public void onAudioLevel(final float level) {}

    @Override
    public void onError(final AIError error) {
        //do stg
    }
}
