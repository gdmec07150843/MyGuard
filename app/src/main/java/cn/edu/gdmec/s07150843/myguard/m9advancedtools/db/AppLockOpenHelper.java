package cn.edu.gdmec.s07150843.myguard.m9advancedtools.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chen on 2016/12/20.
 */
public class AppLockOpenHelper extends SQLiteOpenHelper {
    public AppLockOpenHelper(Context context) {
        super(context, "applock.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table applock (id integer primary key autoincrement, packagename varchar(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

