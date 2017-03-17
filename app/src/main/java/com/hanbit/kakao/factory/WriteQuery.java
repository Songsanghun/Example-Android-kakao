package com.hanbit.kakao.factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hb2002 on 2017-03-15.
 */

public abstract class WriteQuery extends QueryFactory{
    SQLiteOpenHelper helper;

    public WriteQuery(Context context) {
        super(context);
        helper = new DatabaseHelper(context);
    }


    @Override
    public SQLiteDatabase getDatabase() {
        return helper.getWritableDatabase();
    }

    public abstract void update(String sql);
}