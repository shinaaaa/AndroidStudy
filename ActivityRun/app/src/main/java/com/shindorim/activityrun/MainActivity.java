package com.shindorim.activityrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1Method(View view) {
        // Intent 객체를 만들어 실행한 Activity의 정보를 세팅
        Intent intent = new Intent(this, SecondActivity.class);

        startActivity(intent);
    }
}
