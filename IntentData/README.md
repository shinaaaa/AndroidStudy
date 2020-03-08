### Intent에 데이터 Setting

- Activity를 실행하기 위해 사용하는 Intent 객체에 putExtra 메소드를 이용하여 데이터를 세팅
- putExtra 메소드는 자료형 별로 메소드가 제공, 타입을 가리지않음 



### Code

MainActivity.java

```java
package com.shindorim.intentdata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 뷰의 주소 값을 담을 참조 변수
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 뷰을 주소 값을 담음
        text1 = findViewById(R.id.textView);
    }

    public void btnMethod(View view) {
        // Intent 생성
        Intent intent = new Intent(this, SecondActivity.class);
        // SecondActivity로 전달한 데이터 Setting
        intent.putExtra("data1", 100);
        intent.putExtra("data2", 11.11);
        intent.putExtra("data3", true);
        intent.putExtra("data4", "문자열 1");
        // Activity 실행
        // startActivity(intent);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                int value1 = data.getIntExtra("value1", 0);
                double value2 = data.getDoubleExtra("value2", 0.0);
                boolean value3 = data.getBooleanExtra("value3", false);
                String value4 = data.getStringExtra("value4");

                text1.setText("value1 : " + value1 + "\n");
                text1.append("value2 : " + value2 + "\n");
                text1.append("value3 : " + value3 + "\n");
                text1.append("value4 : " + value4 + "\n");
                break;
        }
    }
}
```



SecondActivity.java

```java
package com.shindorim.intentdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    // 뷰의 주소 값을 담을 참조 변수
    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // 뷰의 주소 값을 담음
        text2 = findViewById(R.id.textView2);

        // 현재 Activity를 실행하기 위해 사용된 Intent 객체를 추출
        Intent intent = getIntent();

        // 저장한 데이터를 가져옴
        int data1 = intent.getIntExtra("data1", 0);
        double data2 = intent.getDoubleExtra("data2", 0.0);
        boolean data3 = intent.getBooleanExtra("data3", false);
        String data4 = intent.getStringExtra("data4");

        text2.setText("data1 : " + data1 + "\n");
        text2.append("data2 : " + data2 + "\n");
        text2.append("data3 : " + data3 + "\n");
        text2.append("data4 : " + data4 + "\n");
    }

    public void btnMethod(View view) {
        // 돌아갈 때 전달할 데이터를 Setting
        Intent intent = new Intent();
        // 데이터를 세팅
        intent.putExtra("value1", 200);
        intent.putExtra("value2", 22.2);
        intent.putExtra("value3", true);
        intent.putExtra("value4", "문자열 2");
        // 결과 정보에 intent 객체를 Setting
        setResult(RESULT_OK, intent);
        finish();
    }
}
```



activity_main.xml

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
        android:text="MainActivity"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btnMethod"
        android:text="SecondActivity 실행" />
</LinearLayout>
```



activity_second.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".SecondActivity">

    <TextView
        android:id="@+id/textView2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btnMethod"
        android:text="종료하기" />
</LinearLayout>
```

