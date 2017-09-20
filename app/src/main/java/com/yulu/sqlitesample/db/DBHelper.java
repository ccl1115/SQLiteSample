package com.yulu.sqlitesample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Simon Yu
 */

public class DBHelper {

    public static SQLiteDatabase open(Context context) {
        return new DBDatabase(context).getWritableDatabase();
    }

    private static class DBDatabase extends  SQLiteOpenHelper {

        DBDatabase(final Context context) {
            super(context, "sample", null, 1);
        }

        @Override
        public void onCreate(final SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS sample (echo TEXT)"
            );
        }

        @Override
        public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int i, final int i1) {
            sqLiteDatabase.execSQL("DROP TABLE sample");
            onCreate(sqLiteDatabase);
        }
    }
}
