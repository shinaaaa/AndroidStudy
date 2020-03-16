package com.shindorim.sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // 뷰의 주소 값을 담을 참조변수
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView);
    }

    public void btn1Method(View view) {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = sdf.format(new Date());

        // 컬럼에 저장할 데이터를 관리할 객체
        ContentValues cv1 = new ContentValues();
        cv1.put("textData", "문자열1");
        cv1.put("intData", 100);
        cv1.put("floatData", 11.11);
        cv1.put("dateData", date);

        db.insert("TestTable", null, cv1);

        ContentValues cv2 = new ContentValues();
        cv2.put("textData", "문자열2");
        cv2.put("intData", 200);
        cv2.put("floatData", 22.22);
        cv2.put("dateData", date);

        db.insert("TestTable", null, cv2);

        db.close();
        text1.setText("저장완료");
    }

    public void btn2Method(View view) {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        // 첫 번째 : 데이터를 가져올 테이블의 이름
        // 두 번째 : 가져올 컬럼의 이름이 담긴 문자열 배열. null은 *(모든 컬럼)
        // 세 번째 : 조건절.
        // 네 번째 : 조건절의 ?에 바인딩 될 값의 배열
        // 다섯 번째 : Group by절 기준 컬럼
        // 여섯 번째 : having절에 들어갈 조건절
        // 일곱 번째 : 정렬 기준
        Cursor c = db.query("TestTable", null, null, null, null, null, null);

        text1.setText("");

        while (c.moveToNext()) {
            int idx_pos = c.getColumnIndex("idx");
            int textData_pos = c.getColumnIndex("textData");
            int intData_pos = c.getColumnIndex("intData");
            int floatData_pos = c.getColumnIndex("floatData");
            int dateData_pos = c.getColumnIndex("dateData");

            int idx = c.getInt(idx_pos);
            String textData = c.getString(textData_pos);
            int intData = c.getInt(intData_pos);
            double floatData = c.getDouble(floatData_pos);
            String dateData = c.getString(dateData_pos);

            text1.append("idx : " + idx + "\n");
            text1.append("textData : " + textData + "\n");
            text1.append("intData : " + intData + "\n");
            text1.append("floatData : " + floatData + "\n");
            text1.append("dateData : " + dateData + "\n\n");
        }
        db.close();
    }

    public void btn3Method(View view) {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("textData", "문자열3");

        String where = "idx=?";
        String[] args = {"1"};

        db.update("TestTable", cv, where, args);
        db.close();

        text1.setText("수정완료");
    }

    public void btn4Method(View view) {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = "idx=?";
        String[] args = {"1"};

        db.delete("TestTable", where, args);
        db.close();

        text1.setText("삭제 완료");
    }
}
