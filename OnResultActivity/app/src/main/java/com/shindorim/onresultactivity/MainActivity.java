package com.shindorim.onresultactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Activity를 구분하기 위한 값
    final int SECOND_ACTIVITY = 1;
    final int THRID_ACTIVITY = 2;

    // 뷰의 주소 값을 담을 참조 변수
    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰의 주소 값을 담음
        text2 = findViewById(R.id.textView2);
    }

    public void Btn1Method(View view) {
        // SecondActivity 실행을 요청
        Intent intent = new Intent(this, SecondActivity.class);
        //startActivity(intent);

        startActivityForResult(intent, SECOND_ACTIVITY);
    }

    public void Btn2Method(View view) {
        // ThridActivity 실행을 요청
        Intent intent = new Intent(this, ThridActivity.class);
        //startActivity(intent);

        startActivityForResult(intent, THRID_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // requestCode로 분기
        // resultCode Defualt => RESULT_CANCELED
        switch (requestCode) {
            case SECOND_ACTIVITY:
                text2.setText("Second Activity 실행 후 돌아옴\n");
                switch (resultCode) {
                    case RESULT_OK:
                        text2.append("RESULT_OK");
                        break;
                    case RESULT_CANCELED:
                        text2.append("RESULT_CANCELED");
                        break;
                    case RESULT_FIRST_USER:
                        text2.append("RESULT_FIRST_USER");
                        break;
                    case RESULT_FIRST_USER + 1:
                        text2.append("RESULT_FIRST_USER + 1");
                        break;
                }
                break;
            case THRID_ACTIVITY:
                text2.setText("Thrid Activity 실행 후 돌아옴");
                break;
        }
//        text2.setText("다른 Activity 실행 후 돌아옴");
    }
}
