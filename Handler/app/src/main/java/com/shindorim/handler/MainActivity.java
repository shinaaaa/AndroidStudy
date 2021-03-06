package com.shindorim.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 뷰의 주소 값을 담을 참조 변수
    TextView text1;
    TextView text2;

    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰의 주소 값을 담음
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);

        handler = new Handler();

        // Handler를 통해 요청할 작업
        ThreadCalss thread = new ThreadCalss();
        // Handler를 통해 작업을 요청함 (Main Thread)
        //handler.post(thread);
        handler.postDelayed(thread, 100);
    }

    public void btnMethod(View view) {
        long now = System.currentTimeMillis();
        text1.setText("버튼 클릭 : " + now);
    }

    class ThreadCalss extends Thread {
        @Override
        public void run() {
            long now = System.currentTimeMillis();
            text2.setText("Handler : " + now);
            // 현재 작업을 다시 요청함
            //handler.post(this);
            handler.postDelayed(this, 100);
        }
    }
}
