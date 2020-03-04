### Handler

- Main Thread에서 처리하는 코드 중 일정 작업을 계속 반복처리해야하는 경우
- 안드로이드 OS에게 작업 수행을 요청하는 역할
- 작업을 요청하면 안드로이드 OS는 작업을 하지 않을 때 개발자가 요청한 작업을 처리
- Main Thread에서 작업을 처리하며 5초이상 걸리는 작업은 불가능
- 화면 처리 가능
- 특정 코드를 Main Tread에게 반복적으로 요청할 수 있음



### Code

#### MainActivity.java

```java
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
A
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