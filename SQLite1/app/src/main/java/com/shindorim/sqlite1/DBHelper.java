package com.shindorim.sqlite1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {

        // 두 번째 매개변수 : 사용할 데이터베이스의 이름
        super(context, "Test.db", null, 1);
    }

    // 사용할 데이터베이스가 없을 경우 데이터베이스 파일을 새로 만듬
    // 테이블 생성 및 기타 작업
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("test", "데이터베이스가 생성됨");

        String sql = "create table TestTable("
                + "idx integer primary key autoincrement, "
                + "textData text not null, "
                + "intData integer not null, "
                + "floatData real not null, "
                + "dateData date not null"
                + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                // 버전1에서 버전2 형태로 테이블 구조를 변경시키는 작업
            case 2:
                // 버전2에서 버전3 형태로 테이블 구조를 변경시키는 작업
            case 3:
                // 버전3에서 버전4 형태로 테이블 구조를 변경시키는 작업
        }
        Log.d("test", "old : " + oldVersion);
        Log.d("test", "new : " + newVersion);
    }
}
