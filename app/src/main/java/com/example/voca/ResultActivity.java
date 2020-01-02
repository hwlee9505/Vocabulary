package com.example.voca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class ResultActivity extends Activity {

    ArrayList<Result> resultArr = new ArrayList<>();
    private Spinner spinner2;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    TextView tvResult;
    boolean show_first = false;
    Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vocaresult);


        tvResult = (TextView) findViewById(R.id.tvResult);
        homeBtn = (Button) findViewById(R.id.homeBtn);

        SimpleDateFormat format = new SimpleDateFormat("yyyy년MM월dd일/HH시mm분ss초");
        Calendar time = Calendar.getInstance();
        String format_time = format.format(time.getTime());

        Result result = new Result();
        result.correctNum = VocaTestActivity.correctCnt;
        result.date = format_time;
        resultArr.add(result);

        // 문제푼 내용을 담는다 그러고 나서 1) 시험이 끝났다는 플래그를 false 와 맞춘갯수를 0으로 초기화 시킨다.
        if (FifthTestActivity.test_end == true) {
            save();
            FifthTestActivity.test_end = false;
            VocaTestActivity.correctCnt = 0;
        }


        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, load());

        spinner2 = (Spinner) findViewById(R.id.spinner);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (show_first == false) {
                    Toast.makeText(getApplicationContext(), arrayList.get(i) + "가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    tvResult.setText("최근에 본 시험점수\n");
                    tvResult.append("맞춘갯수 : " + resultArr.get(resultArr.size() - 1).correctNum + "\n");
                    tvResult.append("날짜  : " + resultArr.get(resultArr.size() - 1).date + "\n");
                    show_first = true;
                } else {
                    Toast.makeText(getApplicationContext(), arrayList.get(i) + "가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                    tvResult.setText("맞춘갯수 : " + resultArr.get(i + 1).correctNum + "\n");
                    tvResult.append("날짜  : " + resultArr.get(i + 1).date + "\n");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public ArrayList load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFilesDir() + "/result.txt"));
            arrayList = new ArrayList<>();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] tempArr = line.split(",");
                Result result = new Result();
                result.correctNum = Integer.parseInt(tempArr[0]);
                result.date = tempArr[1];
                arrayList.add(tempArr[1]);
                resultArr.add(result);
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "파일 없음", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return null;
        }
        return arrayList;
    }

    public void save() {
        try {
            FileWriter fw = new FileWriter(getFilesDir() + "/result.txt", true);

            for (int i = 0; i < resultArr.size(); i++) {
                Result temp = resultArr.get(i);
                fw.write(temp.correctNum + "," + temp.date + "\n");
            }
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
//
//    private void showVoca() {
//        for (int i = 0; i < resultArr.size(); i++) {
//            tvResult.append((i + 1) + ". " + resultArr.get(i).correctNum + "  : " + resultArr.get(i).date + "\n");
//        }
//    }
}

