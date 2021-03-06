### Thread

- 여러 처리를 비동기적으로 처리하기 위해 사용
- 비동기적 처리 외에 네트워크에 관련된 코드는 전부 쓰래드로 운영
- 안드로이드 8.0 이상 부터는 개발자가 만든 쓰래드에서 화면 처리가 가능



### ANR (Application Not Respond)

- Activity의 코드를 운영하기 위해 쓰래드를 생성
- 생성된 쓰래드는 Main Thread라 부르며 UI Tread라고도 불림
- Main Thread가 작업을 하지 않을 때만 화면 작업이 가능하며 Main Thread가 바쁠 때 화면 작업이나 터치가 발생하면 ANR이 발생



### Code

#### MainActivity

```java
package com.shindorim.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 뷰에 주소 값을 담을 참조 변수
    TextView text1;
    TextView text2;
    // 쓰래드 동작 여부를 컨트롤할 변수
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰의 주소 값음 받음
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);

        // 100ms 마다 현재 시간을 가쟈와서 출력
//        while (true) {
//            SystemClock.sleep(100);
//            long now = System.currentTimeMillis();
//            text2.setText("무한 루프 : " + now);
//        }

        // 오래 걸리는 작업 처리를 위해
        isRunning = true;
        ThreadClass thread = new ThreadClass();
        thread.start();
    }

    public void btnMethod(View view) {
        // 현재 시간 값을 ms로 가져옴
        long now = System.currentTimeMillis();
        // 출력
        text1.setText("버튼 클릭 : " + now);
    }

    // Thread 클래스
    class ThreadClass extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                SystemClock.sleep(100);
                long now = System.currentTimeMillis();
                Log.d("test", "쓰래드 : " + now);
                // 안드로이드 7 이하의 버전에서는 개발자가 발생기킨 쓰래드에서 화면에 관려된 작업을 하면 오류가 발생함
                text2.setText("쓰래드 : " + now);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 쓰래드 종료를 위해 false 값으로 변경
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

