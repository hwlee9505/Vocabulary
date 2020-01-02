package com.example.voca;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    public View vocalist;
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton vocaAdd = (ImageButton) findViewById(R.id.VocaAdd);
        ImageButton vocaList = (ImageButton) findViewById(R.id.VocaFind);
        ImageButton vocaTest = (ImageButton) findViewById(R.id.VocaTest);
        ImageButton vocaResult = (ImageButton) findViewById(R.id.TestResult);
//
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                VocaAddActivity.vocaNum = position;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        vocaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VocaAddActivity.class);
                startActivity(intent);
            }
        });


        vocaList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VocaListActivity.class);
                startActivity(intent);

            }
        });

        vocaTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VocaTestActivity.class);
                startActivity(intent);
            }
        });


        vocaResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        });

    }
}