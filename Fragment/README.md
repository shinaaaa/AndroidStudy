### Fragment

- Activity 내의 화면을 여러 영역을으로 나누어 관리하는 목적

- 다중 화면 Application => 다중 Activity
- Activity는 독립된 실행단위로 많은 메모리를 소모
- 독립된 실행이 아닌 화면만 필요한 경우 Fragment가 더 효율적

- 안드로이드 5.0에서 추가 되었지만 하위 버전에서도 사용가능



### AddToBackStack

- Fragment는 Activity가 아니므로 Back Button으로 제거 불가능
- AddToBackStack 메소드를 이용하여 Back Stack에 포함할 경우 Back Button으로 제거 가능



### Code

MainActivity.java

```java
package com.shindorim.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Fragment 객체 생성
    FirstFragment first = new FirstFragment();
    SecondFragment second = new SecondFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1Method(View view) {
        // Fragment 관리하는 객체 추출
        FragmentManager manager = getSupportFragmentManager();

        // Fragment 변경을 관리하는 객체 추출
        FragmentTransaction transaction = manager.beginTransaction();

        // Fragment 추가
        //transaction.add(R.id.container,first);

        // Fragment 교체
        transaction.replace(R.id.container, first);

        // Fragment 변경사항 Back Stack에 포함
        transaction.addToBackStack(null);

        // Fragment 적용
        transaction.commit();
    }

    public void btn2Method(View view) {
        // Fragment 관리하는 객체 추출
        FragmentManager manager = getSupportFragmentManager();

        // Fragment 변경을 관리하는 객체 추출
        FragmentTransaction transaction = manager.beginTransaction();

        // Fragment 추가
        // transaction.add(R.id.container,second);

        // Fragment 교체
        transaction.replace(R.id.container, second);

        // Fragment 변경사항 Back Stack에 포함
        transaction.addToBackStack(null);

        // Fragment 적용
        transaction.commit();
    }
}
```



FirstFragment.java

```java
package com.shindorim.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}
```

 SecodFragment.java

```java
package com.shindorim.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
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

    <Button
        android:id="@+id/button5"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn1Method"
        android:text="Button" />

    <Button
        android:id="@+id/button6"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn2Method"
        android:text="Button" />

    <FrameLayout
        android:id="@+id/container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></FrameLayout>
</LinearLayout>
```



fragment_first.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".FirstFragment">

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button" />

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button" />

    <Button
        android:id="@+id/button3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button" />

    <Button
        android:id="@+id/button4"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button" />
</LinearLayout>
```



fragment_second.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".SecondFragment">

    <ProgressBar
        android:id="@+id/progressBar"
        style="?android:attr/progressBarStyle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <ProgressBar
        android:id="@+id/progressBar2"
        style="?android:attr/progressBarStyle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <ProgressBar
        android:id="@+id/progressBar3"
        style="?android:attr/progressBarStyle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <ProgressBar
        android:id="@+id/progressBar4"
        style="?android:attr/progressBarStyle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
</LinearLayout>
```

