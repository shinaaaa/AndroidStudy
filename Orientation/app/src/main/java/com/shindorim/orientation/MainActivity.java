package com.shindorim.orientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edit1;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.editText);
        text1 = findViewById(R.id.textView);

        // 화면 회전이 발생했을 경우에만 처리
        if(savedInstanceState !=null) {
            String str2 = savedInstanceState.getString("data1");
            text1.setText(str2 );
        }
    }

    public void btnMethod(View view) {
        String str = edit1.getText().toString();
        text1.setText(str);
    }

    // 화면 회전 발생 시 호출되는 메소드

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // 화면 복원에 필요한 데이터 복원
        outState.putString("data1", text1.getText().toString());
    }
}
