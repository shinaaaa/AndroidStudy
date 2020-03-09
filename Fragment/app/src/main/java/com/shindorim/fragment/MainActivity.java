package com.shindorim.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Fragment 객체 생성
    FirstFragment first = new FirstFragment();
    SecondFragment second = new SecondFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1Method(View view) {
        // Fragment 관리하는 객체 추출
        FragmentManager manager = getSupportFragmentManager();

        // Fragment 변경을 관리하는 객체 추출
        FragmentTransaction transaction = manager.beginTransaction();

        // Fragment 추가
        //transaction.add(R.id.container,first);

        // Fragment 교체
        transaction.replace(R.id.container, first);

        // Fragment 변경사항 Back Stack에 포함
        transaction.addToBackStack(null);

        // Fragment 적용
        transaction.commit();
    }

    public void btn2Method(View view) {
        // Fragment 관리하는 객체 추출
        FragmentManager manager = getSupportFragmentManager();

        // Fragment 변경을 관리하는 객체 추출
        FragmentTransaction transaction = manager.beginTransaction();

        // Fragment 추가
        // transaction.add(R.id.container,second);

        // Fragment 교체
        transaction.replace(R.id.container, second);

        // Fragment 변경사항 Back Stack에 포함
        transaction.addToBackStack(null);

        // Fragment 적용
        transaction.commit();
    }
}
