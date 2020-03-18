package com.shindorim.socketclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.*;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView);
    }

    public void btnMethod(View view) {
        NetworkThread thread = new NetworkThread();
        thread.start();
    }

    // 네트워크 처리 Thread
    class NetworkThread extends Thread {
        @Override
        public void run() {
            try {
                // 서버에 접속
                final Socket socket = new Socket("192.168.35.205", 55555);

                // 입출력 스트림을 추출한다.
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                DataInputStream dis = new DataInputStream(is);
                DataOutputStream dos = new DataOutputStream(os);

                final int data1 = dis.readInt();
                final double data2 = dis.readDouble();
                final boolean data3 = dis.readBoolean();
                final String data4 = dis.readUTF();

                dos.writeInt(200);
                dos.writeDouble(22.22);
                dos.writeBoolean(false);
                dos.writeUTF("클라이언트가 보낸 문자열");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text1.setText("data1 : " + data1 + "\n");
                        text1.append("data2 : " + data2 + "\n");
                        text1.append("data3 : " + data3 + "\n");
                        text1.append("data4 : " + data4);
                    }
                });

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
