package com.shindorim.ipc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.io.FileDescriptor;

public class ServiceClass extends Service {

    int value = 0;

    // 서비스에 접속 처리를 하기위한 바인더 객체
    // 접속하는 쪽에서 객체를 이용해 서비스 객체를 받을 수 있음
    IBinder binder = new LocalBinder();

    public ServiceClass() {
    }

    // 서비스에 접속하면 호출되는 메소드
    // 반환하는 바인더 객체를 접속하는 객체쪽에 반환
    // 반환하는 바인더를 이용해 현재 서비스 객체를 반환받을 수 있음
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("test1", "Service", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            manager.createNotificationChannel(channel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "test1");

            builder.setSmallIcon(android.R.drawable.ic_menu_search);
            builder.setContentTitle("서비스 가동");
            builder.setContentText("서비스가 가동 중입니다.");
            builder.setAutoCancel(true);
            Notification notification = builder.build();

            startForeground(10, notification);
        }
        ThreadClass thread = new ThreadClass();
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    class ThreadClass extends Thread {
        @Override
        public void run() {
            while (true) {
                SystemClock.sleep(1000);
                Log.d("test", "value : " + value);
                value++;
            }
        }
    }

    // 변수의 값을 반환하는 메소드
    public int getNumber() {
        return value;
    }

    // 접속하는 Activity에서 서비스를 추출을 위해 사용하는 클래스
    class LocalBinder  extends Binder {

        public ServiceClass getService(){
            return ServiceClass.this;
        }
    }
}
