###   Parcelable

- Intent를 통해 객체를 전달 할 때는 Parcelable 인터페이스를 수현한 객체만 가능
- Parcelable 인터페이스는 전달 받은 쪽에서 객체를 복원할때 필요한 정보를 가진 부분을 의미



### Code

mainActivity.java

```java
package com.shindorim.parcelable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 뷰의 주소 값을 담을 참조변수
    TextView text2;

    final int SECOND_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text2 = findViewById(R.id.textView2);
    }

    public void btnMethod(View view) {
        Intent intent = new Intent(this, SecondActivity.class);

        TestClass t1 = new TestClass();
        t1.data10 = 100;
        t1.data20 = "문자열 1";

        // 객체가 가지고 있는 WriteToParcle 메소드를 호출
        intent.putExtra("test1", t1);

//        startActivity(intent);
        startActivityForResult(intent, SECOND_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                // 객체를 추출
                TestClass t2 = data.getParcelableExtra("test2");

                text2.setText("t2.data10 : " + t2.data10 + "\n");
                text2.append("t2.data20 : " + t2.data20);
            }
        }
    }
}
```



SecondActivity.java

```java
package com.shindorim.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    // 뷰의 주소 값을 받을 참조 변수
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 뷰의 주소 값을 받음
        text1 = findViewById(R.id.textView);

        // Intent를 추출
        Intent intent = getIntent();

        // 객체를 추출
        // Class의 createFromParcel 메소드를 호출해 반환받는 메소드를 반환
        TestClass t1 = intent.getParcelableExtra("test1");

        text1.setText("t1.data10 : " + t1.data10 + "\n");
        text1.append("t1.data20 : " + t1.data20);
    }

    public  void btnMethod(View view ) {
        Intent intent = new Intent();

        TestClass t2 = new TestClass();
        t2.data10 = 200;
        t2.data20 = "문자열2";

        intent.putExtra("test2", t2);
        setResult(RESULT_OK, intent);
        finish();
    }
}
```



TestClass.java

```java
package com.shindorim.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class TestClass implements Parcelable {
    int data10;
    String data20;

    public static final Creator<TestClass> CREATOR = new Creator<TestClass>() {
        // 객체 복원
        @Override
        public TestClass createFromParcel(Parcel source) {
            TestClass t1 = new TestClass();
            t1.data10 = source.readInt();
            t1.data20 = source.readString();
            return t1;
        }

        @Override
        public TestClass[] newArray(int size) {
            return new TestClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    // 객체흫 Intent에 담을때 자동으로 호출되는 메소드
    // 첫번째 매개 변수로 들어오는 Parcel 객체에 객체 복원을 위해 필요한 정보를 담음
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(data10);
        dest.writeString(data20);
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
        android:text="TextView"
        android:textAppearance="@style/TextAppearance.AppCompat.Large" />

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="btnMethod"
        android:text="Second Activity 실행" />
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

