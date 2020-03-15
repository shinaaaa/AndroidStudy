package com.shindorim.ipc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 접속한 서비스 객체
    ServiceClass ipc_service = null;

    // 서비스 접속을 관리하는 객체
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 전달 받은 바인더 클래스를 이용해 서비스 객체를 추
            ServiceClass.LocalBinder binder = (ServiceClass.LocalBinder) service;
            ipc_service = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ipc_service = null;
        }
    };

    // 뷰의 주소 값을 담을 참조 변수
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰의 주소 값을 받음
        text1 = findViewById(R.id.textView);

        Intent intent = new Intent(this, ServiceClass.class);
        // 현재 서비스가 가동중인지 확인
        boolean chk = isServiceRunning("com.shindorim.ipc.ServiceClass");

        if (!chk) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        }

        // 서비스에 접속
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void btnMethod(View view) {
        int value = ipc_service.getNumber();
        text1.setText("value : " + value);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 접속중인 서비스 해제
        unbindService(connection);
    }

    // 현재 서비스가 가동중인지 확인
    public boolean isServiceRunning(String name) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        // 현재 실행중인 서비스의 이름을 가져옴
        List<ActivityManager.RunningServiceInfo> list = manager.getRunningServices(Integer.MAX_VALUE);

        for (ActivityManager.RunningServiceInfo info : list) {
            if (info.service.getClassName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
