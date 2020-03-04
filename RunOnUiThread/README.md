### RunOnUiTread

- 개발자가 만든 일반 Thread에서 코드 일부를 Main Thread가 처리 하도록 하는 메소드
- 일반 Thread 운영 중 화면 처리를 가능하게 함





### Code

#### MainActivity.java

```java
package com.shindorim.runonuithread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 븅의 주소 값을 받을 참조 변수
    TextView text1;
    TextView text2;

    // Thread 중단을 위한 변수
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰의 주소 값을 받음
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);

        isRunning = true;
        ThreadCalss thread = new ThreadCalss();
        thread.start();
    }

    public void btnMethod(View view) {
        long now = System.currentTimeMillis();
        text1.setText("버튼 클릭 : " + now);
    }

    class ThreadCalss extends Thread {
        @Override
        public void run() {
            SystemClock.sleep(100);

             final long time = System.currentTimeMillis();
            Log.d("test", "Thread : " + time);

            // 일반 Thread에서 화면 처리가 필요한 경우 runOnUiThread를 사용
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    text2.setText("Thread : " + time);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
```



#### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btnMethod"
        android:text="현재 시간 가져오기" />
</LinearLayout>
```

