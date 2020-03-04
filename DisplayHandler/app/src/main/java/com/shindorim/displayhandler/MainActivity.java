package com.shindorim.displayhandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 뷰의 주소 값을 담으 참조 변수
    TextView text1;
    TextView text2;

    // Thread의 무한 루프 작업을 종료하기 위한 변수
    boolean isRunning = false;

    // 일단 쓰래드에서 요청한 화면 처리용 Handler
    DisplayHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 뷰의 주소 값을 받음
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);

        handler = new DisplayHandler();

        // 오랜 시간 걸리는 작업
//        while (true) {
//            SystemClock.sleep(100);
//            long now = System.currentTimeMillis();
//            text1.setText("오래 걸리는 작업 : " + now);
//        }
        isRunning = true;
        ThreadClass thread = new ThreadClass();
        thread.start();
    }

    public void btnMethod(View view) {
        long now = System.currentTimeMillis();
        text1.setText("버튼 클릭 : " + now);
    }
    class ThreadClass extends Thread {
        @Override
        public void run() {
            int a1 = 10;
            int a2 = 20;
            while (isRunning) {
                SystemClock.sleep(100);
                long now = System.currentTimeMillis();
                Log.d("test","오래걸리는 작업" + now);
//                text2.setText("오래 걸리는 작업 : " + now);
                SystemClock.sleep(100);
                handler.sendEmptyMessage(0);
                SystemClock.sleep(100);
                handler.sendEmptyMessage(1);
                // Handler 요청을 할 때 화면 처리를 위한 데이터를 전달
                SystemClock.sleep(100);
                Message msg = new Message();
                msg.what = 2;
                // 정수 값
                msg.arg1 = ++ a1;
                msg.arg2 = ++ a2;
                msg.obj = now;

                handler.sendMessage(msg);
            }
        }
    }

    class DisplayHandler extends Handler {
        // 개발자가 만든 쓰래드를 화면에 관련된 처리를 하기 위해 작업을 요청하면
        // 자동으로 호출되는 메소드 (Main Thread)
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // what 값으로 분기
            switch (msg.what){
                case 0:
                    text2.setText("Handler 작업 1");
                    break;
                case 1:
                    text2.setText("Handler 작업 2");
                    break;
                case 2:
                    text2.setText(msg.arg1 + ", " + msg.arg2 + ", "+msg.obj);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
