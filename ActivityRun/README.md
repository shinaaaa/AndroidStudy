### 안드로이드 4대 구성요소

- Activity
  - 화면을 그리는 실행 단위
- Service
  - 백그라운드에서 실행되는 실행단위
- Broad Cast Receiver
  - OS가 전달하는 메시지를 전달 받아 실행되는 실행단위
- Contents Provider
  - 저장된 데이터를 제공하기위한 실행단위



### Intent

- 개발자가 OS에게 실행 요청을 하는 과정에서  요청에 대한 정보를 담는 객체
- 4대 구성 요소 중 원하는 것을 실행할 수 있음



### startActivity ()

- 지정된 Intent에 담긴 정보를 토대로 Activity를 실행

### finish ()

- 현재 실행되어 있는 Activity를 종료



### Back Stack

- Activity에서 다른 Activity를 실행하면 이전 Activity는 Back Stack에 담겨 정지 상태가 되고 실행된 Activity가 활동
- 새로 실행된 Activity가 제거 되면 Back Stack에 있던 Activity가 다시 활동



### Code

#### MainActivity.java

```java
package com.shindorim.activityrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1Method(View view) {
        // Intent 객체를 만들어 실행한 Activity의 정보를 세팅
        Intent intent = new Intent(this, SecondActivity.class);

        startActivity(intent);
    }
}
```

#### secondActivity.java

```java
package com.shindorim.activityrun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void btn2Method(View view) {
        finish();
    }
}
```

#### active_main.xml

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
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn1Method"
        android:text="SecondActivity 실행" />
</LinearLayout>
```

#### active_second.xml

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
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="SecondActivity"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn2Method"
        android:text="종료하기" />
</LinearLayout>
```

