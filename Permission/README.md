### 권한

- 안드로이드는 개인 정보, 센서, 카메라, 저장소 등 개인 정보와 관련된 기능을 사용하기 위해서는 권한을 등록해야함
- 등록된 권한는 어플리케이션 다운 전이나 후에 확인 가능
- 사용자에게 어플리케이션이 어떠한 기능릏 사용하는 지 알려주는 목적
- 권한 등록이 필요한 기능을 사용할떄는 권한 등록을 해야하며 하지 않았을 경우 오류 발생
- 마시멜로우 버전이후 개인 정보와 관련한 정보를 어플리케이션 내부에서 고지하고 사용허가를 맡도록 변경
- 권한 사용시 반드시 사용자에게 고지하고 승인 받는 작업이 필요
- https://developer.android.com/training/permissions/requesting



### Code

#### AndroidManifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.shindorim.permission">

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
<!--    네트워크 사용-->
    <uses-permission android:name="android.permission.INTERNET"/>
<!--    위치 관련-->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<!--    연락처 읽기-->
    <uses-permission android:name="android.permission.READ_CONTACTS"/>
<!--    연락처 쓰기-->
    <uses-permission android:name="android.permission.WRITE_CONTACTS"/>
<!--    문자 발신-->
    <uses-permission android:name="android.permission.SEND_SMS"/>
<!--    문자 수신-->
    <uses-permission android:name="android.permission.RECEIVE_SMS"/>
</manifest>
```



#### MainActivity.java

```java
package com.shindorim.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //    뷰의 주소값을 담을 참조 변수
    TextView text1;

    //체크할 권한 목록
    String[] permission_list = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        뷰의 주소 값을 받아옴
        text1 = findViewById(R.id.textView);

        checkPermission();
    }

    // 권한 체크 메소드
    public void checkPermission() {
        // 현재 안드로이드 버전이 6.0 미만이면 메소드를 종료
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        // 각 권한의 허용 여부를 확인
        for (String permission : permission_list) {
            // 권한 허용 여부를 확인
            int chk = checkCallingOrSelfPermission(permission);
            // 거부 상태라면
            if (chk == PackageManager.PERMISSION_DENIED) {
                // 사용자에게 권한 허용여부를 확인하는 창을 띄움, requestCode 분기하기 위한 값
                requestPermissions(permission_list, 0);
            }
        }
    }

    // 권한 허용여부 창 완료 후 호출, requestCode에 따라서 분기 가능
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        text1.setText("");

        // 사용자가 권한 허용 여부를 확인
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                text1.append(permissions[i] + "허용\n");
            } else {
                text1.append(permissions[i] + "거부\n");
            }
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
</LinearLayout>
```

