package com.shindorim.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    // 뷰의 주소 값을 받을 참조 변수
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 뷰의 주소 값을 받음
        text1 = findViewById(R.id.textView);

        // Intent를 추출
        Intent intent = getIntent();

        // 객체를 추출
        // Class의 createFromParcel 메소드를 호출해 반환받는 메소드를 반환
        TestClass t1 = intent.getParcelableExtra("test1");

        text1.setText("t1.data10 : " + t1.data10 + "\n");
        text1.append("t1.data20 : " + t1.data20);
    }

    public  void btnMethod(View view ) {
        Intent intent = new Intent();

        TestClass t2 = new TestClass();
        t2.data10 = 200;
        t2.data20 = "문자열2";

        intent.putExtra("test2", t2);
        setResult(RESULT_OK, intent);
        finish();
    }
}
