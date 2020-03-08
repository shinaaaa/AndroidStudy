### Activity Action

- 기본 제공되는 어플리케이션 중 다른 어플리케이션에서 사용이 가능하도록  Activity가 제공되는 것들이 있음



### Code

MainActivity.java

```java
package com.shindorim.activityaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // 승인 받을 권한 목록
    String[] permission_list = {
            Manifest.permission.CALL_PHONE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
    }

    public void btnMethod(View view) {
        // 구글 지도를 통해 보여줄 위도와 경도를 Setting
        Uri uri = Uri.parse("geo:37.243243, 131.861601");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void btn2Method(View view) {
        // 브라우저를 통해 보여줄 페이지 주소를 Setting
        Uri uri = Uri.parse("http://developer.android.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void btn3Method(View view) {
        // 전화번호를 Setting
        Uri uri = Uri.parse("tel:000-0000-0000");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void btn4Method(View view) {
        Uri uri = Uri.parse("tel:000-0000-0000");
        Intent intent = new Intent(Intent.ACTION_CALL, uri);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            int chk = checkCallingOrSelfPermission(Manifest.permission.CALL_PHONE);
            if (chk == PackageManager.PERMISSION_DENIED) {
                return;
            }
        }
        startActivity(intent);
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        for (String permission : permission_list) {
            int chk = checkCallingOrSelfPermission(permission);
            if (chk == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permission_list, 0);
                break;
            }
        }
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
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btnMethod"
        android:text="지도 띄우기" />

    <Button
        android:id="@+id/button2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn2Method"
        android:text="브라우저 띄우기" />

    <Button
        android:id="@+id/button3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn3Method"
        android:text="다이얼 띄우기" />

    <Button
        android:id="@+id/button4"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btn4Method"
        android:text="전화걸기" />
</LinearLayout>
```



AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.shindorim.activityaction">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

    <!--    전화걸기 승인-->
    <uses-permission android:name="android.permission.CALL_PHONE" />
</manifest>
```

