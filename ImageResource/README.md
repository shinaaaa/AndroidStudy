### 이미지 리소스

- drawable 폴더에 이미지를 넣어서 이미지를 사용
- xml을 통해서 이미지를 새롭게 구성하여 사용 가능
- 여러 이미지를 겹쳐 하나의 이미지를 만드는 경우
- 패턴 이미지를 만드는 경우
- 상태에 따른 이미지를 만드는 경우



### Code

#### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/tile"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:srcCompat="@drawable/layer" />

    <Button
        android:id="@+id/button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@drawable/btn_image"
        android:text="Button" />
</LinearLayout>
```



#### layer.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item>
        <bitmap
            android:gravity="center"
            android:src="@drawable/img1" />
    </item>
    <item
        android:left="10dp"
        android:top="10dp">
        <bitmap
            android:gravity="center"
            android:src="@drawable/img2" />
    </item>
    <item
        android:left="20dp"
        android:top="20dp">
        <bitmap
            android:gravity="center"
            android:src="@drawable/img3" />
    </item>
</layer-list>
```



#### btn_image.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/btn1" android:state_pressed="true" />
    <item android:drawable="@drawable/btn2" />
</selector>
```



#### tile.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<bitmap xmlns:android="http://schemas.android.com/apk/res/android"
    android:src="@drawable/p"
    android:tileMode="repeat" />
```

