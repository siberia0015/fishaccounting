package com.fzu.xyjz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by 厦门科长 on 2018/11/22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String TAG = "DatabaseHelper";
    public static String DB_NAME="Record";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句 并 执行

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void combineRecord(ArrayList<String> book_id,String newName){
        SQLiteDatabase db=this.getWritableDatabase();
        /*实现方法：把目标账簿中所有账目的账簿ID改成第一个账簿的账簿ID*/
        for(int i=1;i<book_id.size();i++){
            /*account账目表，book_id账目所属账簿id*/
            db.execSQL("update account set book_id = " + book_id.indexOf(0) + "where book_id =" + book_id.indexOf(i));
            db.execSQL("delete from book where book_id = " + book_id.indexOf(i));
        }
        db.execSQL("update book set bookName = "+ newName + "where book_id = "+book_id.indexOf(0));
    }
}
