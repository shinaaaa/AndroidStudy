#### startActivityForResult

- Activity에서 다른 Activity를 실행하고 다시 돌아왔을 때 어떤 처리가 필요하다면
- startActivity가 아닌 startActivityForResult 메소드를 사용



#### onActivityResult

- startAcitivityForResult 메소드를 이용해 Activity를 실행
- 자동을 onActivityResult 메소드가 호출



#### Code

MainActivity

```java
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
```



SecondActivity

```java
package com.shindorim.onresultactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void btnMethod(View view) {
        // 실행 결과를 Setting
        //setResult(RESULT_OK);
        // 현재 Activity 종료
        finish();
    }

    public void btn2Method(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void btn3Method(View view) {
        setResult(RESULT_FIRST_USER);
        finish();
    }

    public void btn4Method(View view) {
        setResult(RESULT_FIRST_USER + 1);
        finish();
    }
}
```



ThridActivity

```java
package com.shindorim.onresultactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ThridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
    }

    public void btnMethod(View view) {
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
        android:id="@+id/textView2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="MainActivity"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="Btn1Method"
        android:text="SecondActivity 실행" />

    <Button
        android:id="@+id/button4"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="Btn2Method"
        android:text="ThridActivity 실행" />
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
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="SecondActivity"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btnMethod"
        android:text="종료하기 - OK" />

    <Button
        android:id="@+id/button5"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn2Method"
        android:text="종료하기 - Cancel" />

    <Button
        android:id="@+id/button8"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn3Method"
        android:text="종료하기 - First" />

    <Button
        android:id="@+id/button9"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn4Method"
        android:text="종료하기 - First+1" />
</LinearLayout>
```



activity_thrid

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".ThridActivity">

    <TextView
        android:id="@+id/textView3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Thrid Activity"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <Button
        android:id="@+id/button3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btnMethod"
        android:text="종료하기" />
</LinearLayout>
```

