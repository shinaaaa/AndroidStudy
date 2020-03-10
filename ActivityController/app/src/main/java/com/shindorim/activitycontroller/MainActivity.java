package com.shindorim.activitycontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // 관리할 Fragment 객체
    InputFragment input = new InputFragment();
    ResultFragment result = new ResultFragment();

    // Fragment들이 공통적으로 사용할 값을 저장할 변수
    String value1;
    String value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inputFragment를 배치
        setFragment("input");
    }

    // 보여줄 Fragment를 관리하는 Method
    public void setFragment(String name) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        // Fragment 이름으로 분기
        switch (name) {
            case "input" :
                transaction.replace(R.id.container, input);
                transaction.addToBackStack(null);
                break;
            case "result" :
                transaction.replace(R.id.container, result);
                transaction.addToBackStack(null);
                break;
        }

        transaction.commit();
    }
}
