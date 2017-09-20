package com.yulu.sqlitesample;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yulu.sqlitesample.db.DBHelper;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class InsertSmallServer extends IntentService {
    public InsertSmallServer() {
        super("InsertSmallServer");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SQLiteDatabase db = DBHelper.open(this);
        db.beginTransaction();
        ContentValues cv = new ContentValues();
        cv.put("echo", String.valueOf(System.currentTimeMillis()));
        db.insert("sample", null, cv);
    }

}
