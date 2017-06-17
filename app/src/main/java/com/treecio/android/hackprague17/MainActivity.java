package com.treecio.android.hackprague17;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button startbutton;

    private String LOG_TAG = "MainActivity";

    private HackyVoice rec;

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startbutton = (Button) findViewById(R.id.button);
        rec = new HackyVoice(this);

        startbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (startbutton.getText().equals("Start")) {
                    startbutton.setText("Stop");
                    rec.listen();
                } else {
                    rec.stop();
                    startbutton.setText("Start");
                }

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
