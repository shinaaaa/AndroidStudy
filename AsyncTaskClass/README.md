### AsyncTask

- 비동기 처리를 위해 제공되는 클래스
- 개발자가 만든 Thread와 Handler의 조합으로 Thread 운영 중 화면 처리가 가능했던 구조를 클래스로 제공하는 것



#### onPreExecute ()

- doInBackground가 호출되기 전에 호출되는 메소드
- Main Thread가 처리



#### doInBackground ()

- 일반 Thread에서 처리
- 오래 걸리는 작업 처리 가능
- 화면 처리 작업 X



#### onProgressUpdate ()

- doInBackground에서  publishProgress를 호출하면 Main Tread가 처리하는 메소드
- doInBackground내에서 화면 처리가 필요할 때 사용



#### onPostExecute ()

- doInBackground 수행 완료 후 호출
- Main Tread가 처리



### Code

#### MainActivity.java

```java
package com.shindorim.asynctaskclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 뷰의 주소 값을 받는 변수
    TextView text1;
    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰의 주소 값을 받음
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);

        AsyncTaskClass async = new AsyncTaskClass();
        // AsyncTask를 가동
        // 매개 변수의 값은 doInBackgrounnd로 전달
        async.execute(10, 20);
    }

    public void btnMethod(View view) {
        long now = System.currentTimeMillis();
        text1.setText("버튼 클릭 : " + now);
    }

    // 첫 번째 제네릭 : execute 메소드의 매개 변수, doInBackground 메서드의 매개 변수 타입
    // 두 번째 제네릭 : publishProgress 메소드의 매개 변수, onProgressUpdate 메소드의 매개 변수 타입
    // 세 번째 제네릭 : doInBackground 메소드의 반환 타입, onPostExecute 메소드의 매개 변수 타입
    class AsyncTaskClass extends AsyncTask<Integer, Long, String> {
        @Override
        // doInBackground 메소드가 호출되기 전에 호출되는 메소드
        // 일단 Thread로 처리할 작업이 동작하기 전에 필요한 사전 작업을 실행
        // Main Thread가 처리, 오래 걸리는 작업 X
        protected void onPreExecute() {
            super.onPreExecute();
            text2.setText("AsyncTask 가동");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int a1 = integers[0];
            int a2 = integers[1];

            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(1000);
                a1++;
                a2++;
                Log.d("test", i + ", " + a1 + ", " + a2);

                // 화면 처리
                long now = System.currentTimeMillis();
                publishProgress(now);
            }
            return "작업 종료";
        }

        // doInBackground 메소드에서 화면 처리를 요청하면
        // Main Tread가 처리, 오래 걸리는 작업 X
        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            text2.setText("async : "+ values[0]);
        }

        // doInBackground의 작업이 종료되면 Main Thread가 호출하는 메소드
        // doInBackground가 반환하는 값을 매개 변수로 받음
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            text2.setText(s);
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
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
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