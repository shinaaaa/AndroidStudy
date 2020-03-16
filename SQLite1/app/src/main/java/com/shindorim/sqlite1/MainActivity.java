package com.shindorim.sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.textView);
    }

    public void btnMethod(View view) {
        // 데이터베이스 오픈
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "insert into TestTable (textData, intData, floatData, dateData) values (?, ?, ?, ?)";

        // 데이터 준비
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = sdf.format(new Date());

        // ?에 바인딩될 값 배열
        String[] arg1 = {"문자열1", "100", "11.11", date};
        String[] arg2 = {"문자열2", "200", "22.22", date};

        // 저장
        db.execSQL(sql, arg1);
        db.execSQL(sql, arg2);
        db.close();

        text1.setText("저장 완료");
    }

    public void btn2Method(View view) {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "select * from TestTable";

        // 쿼리 실행
        Cursor c = db.rawQuery(sql, null);
        text1.setText("");

        // 선택된 row를 가져옴
        while (c.moveToNext()) {
            // 가져올 컬럼의 인덱스 번호를 추출
            int idx_pos = c.getColumnIndex("idx");
            int textData_pos = c.getColumnIndex("textData");
            int intData_pos = c.getColumnIndex("intData");
            int floatData_pos = c.getColumnIndex("floatData");
            int dateData_pos = c.getColumnIndex("dateData");

            // 컬럼 인덱스 번호를 통해 데이터를 가져옴
            int idx = c.getInt(idx_pos);
            String textData = c.getString(textData_pos);
            int intData = c.getInt(intData_pos);
            double floatData = c.getDouble(floatData_pos);
            String dateDate = c.getString(dateData_pos);

            text1.append("idx : " + idx + "\n");
            text1.append("textData : " + textData + "\n");
            text1.append("intData : " + intData + "\n");
            text1.append("floatData : " + floatData + "\n");
            text1.append("dateDate : " + dateDate + "\n\n");

        }
    }

    public void btn3Method(View view) {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "update TestTable set textData = ? where idx = ?";
        String[] args = {"문자열3", "1"};

        db.execSQL(sql, args);
        db.close();

        text1.setText("수정완료");
    }

    public void btn4Method(View view) {
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "delete from TestTable where idx = ?";
        String[] args = {"1"};

        db.execSQL(sql, args);
        db.close();

        text1.setText("삭제 완료");
    }
}
