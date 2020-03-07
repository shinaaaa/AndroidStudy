package com.shindorim.intentdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    // 뷰의 주소 값을 담을 참조 변수
    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // 뷰의 주소 값을 담음
        text2 = findViewById(R.id.textView2);

        // 현재 Activity를 실행하기 위해 사용된 Intent 객체를 추출
        Intent intent = getIntent();

        // 저장한 데이터를 가져옴
        int data1 = intent.getIntExtra("data1", 0);
        double data2 = intent.getDoubleExtra("data2", 0.0);
        boolean data3 = intent.getBooleanExtra("data3", false);
        String data4 = intent.getStringExtra("data4");

        text2.setText("data1 : " + data1 + "\n");
        text2.append("data2 : " + data2 + "\n");
        text2.append("data3 : " + data3 + "\n");
        text2.append("data4 : " + data4 + "\n");
    }

    public void btnMethod(View view) {
        // 돌아갈 때 전달할 데이터를 Setting
        Intent intent = new Intent();
        // 데이터를 세팅
        intent.putExtra("value1", 200);
        intent.putExtra("value2", 22.2);
        intent.putExtra("value3", true);
        intent.putExtra("value4", "문자열 2");
        // 결과 정보에 intent 객체를 Setting
        setResult(RESULT_OK, intent);
        finish();
    }
}
