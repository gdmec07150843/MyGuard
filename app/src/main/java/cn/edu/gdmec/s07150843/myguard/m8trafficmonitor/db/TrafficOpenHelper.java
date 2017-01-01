package cn.edu.gdmec.s07150843.myguard.m8trafficmonitor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hasee on 2016/12/22.
 */
public class TrafficOpenHelper  extends SQLiteOpenHelper {
    private	static final	String	DB_NAME = "traffic.	db";
    private	static final	String	TABLE_NAME = "traffic";

    private	final static	String	GPRS = "gprs";
    private	final static	String	TIME = "date";

    public TrafficOpenHelper(Context context) {
        super (context, DB_NAME, null, 1);
        }

    public void onCreate(SQLiteDatabase db) {
        db. execSQL("create table "+ TABLE_NAME
                + "(id integer primary key autoincrement, " + GPRS +" varchar(255),"+ TIME +" datetime)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

	}

