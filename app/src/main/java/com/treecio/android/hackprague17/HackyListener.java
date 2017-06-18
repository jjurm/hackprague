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

import com.treecio.android.hackprague17.HackyItem.HackyFactory;
import com.treecio.android.hackprague17.model.CallAction;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class HackyListener implements AIListener {

    final String TOKEN = "a57e003f19e5402fa73751df094d7d37";

    final String TAG = "HackyListener";

    final AIConfiguration config = new AIConfiguration(TOKEN,
            AIConfiguration.SupportedLanguages.English,
            AIConfiguration.RecognitionEngine.System);

    final AIRequest aiRequest = new AIRequest();

    final AIDataService aiDataService = new AIDataService(config);

    final HackyFactory factory = new HackyFactory();

    List<CallAction> actions = new ArrayList<>();

    protected void processSentence(String t) throws ExecutionException, InterruptedException {

        aiRequest.setQuery(t);
        Log.i(TAG, "ASKING: " + t);

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
        }.execute(aiRequest).get();
    }


    public List<CallAction> process(List<String> data) throws ExecutionException, InterruptedException {

        actions.clear();

        Log.i(TAG, "Processing data");

        for (String t: data) {
            processSentence(t);
        }

        Log.i(TAG, "Data processed");

        return actions;
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

        actions.add(factory.create(result));
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
