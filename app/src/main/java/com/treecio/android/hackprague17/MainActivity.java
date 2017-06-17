package com.treecio.android.hackprague17;

import android.app.Activity;
import android.os.Bundle;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HackyListener listie = new HackyListener();

        try {
            listie.process();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
