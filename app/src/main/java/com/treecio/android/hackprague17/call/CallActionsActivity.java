package com.treecio.android.hackprague17.call;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.treecio.android.hackprague17.R;
import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.model.CardsAdapter;
import com.treecio.android.hackprague17.storage.StoragePort;

public class CallActionsActivity extends Activity {

    private StoragePort storagePort;
    private Call call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_actions);

        RecyclerView rv = (RecyclerView) findViewById(R.id.callActionsList);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        //get intent
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");

        //retrieve call by id from intent
        storagePort = new StoragePort(this);
        call = storagePort.getData().getCall(id);

        //add adapter
        CallActionsAdapter adapter = new CallActionsAdapter(call);
        rv.setAdapter(adapter);

    }
}
