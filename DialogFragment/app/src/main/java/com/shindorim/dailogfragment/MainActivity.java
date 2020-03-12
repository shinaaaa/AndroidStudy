package com.shindorim.dailogfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // View의 주소 값을 받을 참조 변수
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView);
    }

    public void btnMethod(View view) {
        // 다이얼로그를 띄울 객체 생성
        SubFragment sub = new SubFragment();
        FragmentManager manager = getSupportFragmentManager();

        // 프래그먼트 다이얼로그 띄움
        sub.show(manager, "tag");
    }
}
