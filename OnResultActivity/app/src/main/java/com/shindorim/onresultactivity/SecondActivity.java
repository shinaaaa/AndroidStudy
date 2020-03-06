package com.shindorim.onresultactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void btnMethod(View view) {
        // 실행 결과를 Setting
        //setResult(RESULT_OK);
        // 현재 Activity 종료
        finish();
    }

    public void btn2Method(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void btn3Method(View view) {
        setResult(RESULT_FIRST_USER);
        finish();
    }

    public void btn4Method(View view) {
        setResult(RESULT_FIRST_USER + 1);
        finish();
    }
}
