package com.yulu.sqlitesample;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yulu.sqlitesample.db.DBHelper;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mClosedDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mClosedDb = DBHelper.open(MainActivity.this);

        findViewById(R.id.btn_large).setOnClickListener(view -> startService(new Intent(MainActivity.this, InsertLargeServer.class)));
        findViewById(R.id.btn_small).setOnClickListener(view -> startService(new Intent(MainActivity.this, InsertSmallServer.class)));

        findViewById(R.id.btn_thread_insert).setOnClickListener(view -> {
            final SQLiteDatabase db = DBHelper.open(MainActivity.this);
            final ContentValues cv = new ContentValues();
            for (int i = 0; i < 4; i++) {
                new Thread(() -> {
                    db.beginTransaction();
                    for (int j = 0; j < 1000; j++) {
                        cv.put("echo", String.valueOf(System.currentTimeMillis()));
                        db.insert("sample", null, cv);
                    }
                    db.endTransaction();
                }).start();
            }
        });

        findViewById(R.id.btn_close).setOnClickListener(view -> {
            mClosedDb.close();
        });

        findViewById(R.id.btn_query).setOnClickListener(view -> {
            if (mClosedDb == null) {
                return;
            }
            Cursor cursor = mClosedDb.query("sample", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                while(cursor.moveToNext()) {
                    cursor.getString(cursor.getColumnIndex("echo"));
                }
            }

            cursor.close();
        });

    }
}
