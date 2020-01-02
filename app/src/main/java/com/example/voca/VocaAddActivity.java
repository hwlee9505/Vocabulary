package com.example.voca;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VocaAddActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocaadd);

        Button backbtn = (Button) findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
