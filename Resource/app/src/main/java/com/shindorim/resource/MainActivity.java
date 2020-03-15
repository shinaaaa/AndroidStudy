package com.shindorim.resource;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView);
    }

    public void btnMethod(View view) {

        // FM 방법
//        Resources res = getResources();
//        String str1 = res.getString(R.string.str2);
//        text1.setText(str1);

        // 문자열만 이렇게 사용 가능
        text1.setText(R.string.str2);
    }

    public void btn2Method(View view) {
        Resources res = getResources();
        String[] array = res.getStringArray(R.array.data_array);

        text1.setText("");

        for (String str1 : array) {
            text1.append(str1 + "\n");
        }
    }

    public void btn3Method(View view) {
//        text1.setTextColor(Color.YELLOW);
        // 색상
//        int color = Color.rgb(26, 106, 129);상
        // 투명도
//        int color = Color.argb(50,26, 106, 129);

        int color = ContextCompat.getColor(this, R.color.color4);
        text1.setTextColor(color);
    }

    public void btn4Method(View view) {
        Resources res = getResources();

        float dp = res.getDimension(R.dimen.dp);
        float sp = res.getDimension(R.dimen.sp);
        float mm = res.getDimension(R.dimen.mm);
        float pt = res.getDimension(R.dimen.pt);
        float px = res.getDimension(R.dimen.px);
        float inch = res.getDimension(R.dimen.inch);

        text1.setText("px : " + px + "\n");
        text1.append("pt : " + pt + "\n");
        text1.append("dp : " + dp + "\n");
        text1.append("sp : " + sp + "\n");
        text1.append("mm : " + mm + "\n");
        text1.append("inch : " + inch + "\n");

        text1.setTextSize(dp * 20);

    }
}
