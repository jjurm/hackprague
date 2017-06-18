package com.treecio.android.hackprague17.call;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.treecio.android.hackprague17.R;
import com.treecio.android.hackprague17.model.Call;
import com.treecio.android.hackprague17.model.CardsAdapter;
import com.treecio.android.hackprague17.storage.StoragePort;

import java.text.SimpleDateFormat;

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

        //add fields
        TextView callerName = (TextView) findViewById(R.id.callerName);
        callerName.setText(call.getCallerName());

        TextView date = (TextView) findViewById(R.id.date);
        date.setText(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(call.getDate()));

        ImageView photo = (ImageView) findViewById(R.id.photo);
        photo.setImageURI(call.getPhoto());

        //add adapter
        CallActionsAdapter adapter = new CallActionsAdapter(call);
        rv.setAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
