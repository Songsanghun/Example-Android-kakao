package com.hanbit.kakao.factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hb2002 on 2017-03-15.
 */

public abstract class DetailQuery extends QueryFactory{
    SQLiteOpenHelper helper;
    public DetailQuery(Context context) {
        super(context);
        helper=new DatabaseHelper(context);
    }

    @Override
    public SQLiteDatabase getDatabase() {
        return helper.getReadableDatabase();
    }

    public abstract Object detail(String sql);
}