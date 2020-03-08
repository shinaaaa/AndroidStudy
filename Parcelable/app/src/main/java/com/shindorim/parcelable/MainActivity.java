package com.shindorim.parcelable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 뷰의 주소 값을 담을 참조변수
    TextView text2;

    final int SECOND_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text2 = findViewById(R.id.textView2);
    }

    public void btnMethod(View view) {
        Intent intent = new Intent(this, SecondActivity.class);

        TestClass t1 = new TestClass();
        t1.data10 = 100;
        t1.data20 = "문자열 1";

        // 객체가 가지고 있는 WriteToParcle 메소드를 호출
        intent.putExtra("test1", t1);

//        startActivity(intent);
        startActivityForResult(intent, SECOND_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                // 객체를 추출
                TestClass t2 = data.getParcelableExtra("test2");

                text2.setText("t2.data10 : " + t2.data10 + "\n");
                text2.append("t2.data20 : " + t2.data20);
            }
        }
    }
}
