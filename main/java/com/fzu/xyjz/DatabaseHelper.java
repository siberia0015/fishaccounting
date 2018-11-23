package com.fzu.xyjz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CloudQueryCallback;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.ArrayList;
import java.util.List;

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
    public void combineRecordLocal(ArrayList<String> book_id,String newName){
        SQLiteDatabase db=this.getWritableDatabase();
        /*实现方法：把目标账簿中所有账目的账簿ID改成第一个账簿的账簿ID*/
        for(int i=1;i<book_id.size();i++){
            /*account账目表，book_id账目所属账簿id*/
            db.execSQL("update account set book_id = " + book_id.indexOf(0) + "where book_id =" + book_id.indexOf(i));
            db.execSQL("delete from book where book_id = " + book_id.indexOf(i));
        }
        db.execSQL("update book set bookName = "+ newName + "where book_id = "+book_id.indexOf(0));
    }
    public void combineRecordCloud(final ArrayList<String> book_id, final String newName){
        /*实现方法：把目标账簿中所有账目的账簿ID改成第一个账簿的账簿ID*/
        for(int i=1;i<book_id.size();i++){
            /*account账目表，book_id账目所属账簿id*/
            /*db.execSQL("update Account set book_id = " + book_id.indexOf(0) + "where book_id =" + book_id.indexOf(i));*/
            AVQuery<AVObject> query1=new AVQuery<>("Account");
            query1.whereEqualTo("bookId",book_id.indexOf(i));
            query1.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    ArrayList<AVObject> todos = (ArrayList<AVObject>) list;
                    for (AVObject todo : list) {
                        todo.put("bookId",book_id.indexOf(0) );
                    }
                    AVObject.saveAllInBackground(todos, new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e != null) {
                                // 出现错误
                            } else {
                                // 保存成功
                            }
                        }
                    });
                }
            });
            /*db.execSQL("delete from book where book_id = " + book_id.indexOf(i));*/
            AVQuery<AVObject> query2=new AVQuery<>("Book");
            query2.whereEqualTo("bookId",book_id.indexOf(i));
            query2.deleteAllInBackground(new DeleteCallback() {
                @Override
                public void done(AVException e) {
                    if (e != null) {
                        // 出现错误
                    } else {
                        // 保存成功
                    }
                }
            });
        }
        /*db.execSQL("update book set bookName = "+ newName + "where book_id = "+book_id.indexOf(0));*/
        AVQuery<AVObject> query3=new AVQuery<>("Book");
        query3.whereEqualTo("bookId",book_id.indexOf(0));
        query3.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                ArrayList<AVObject> todos = (ArrayList<AVObject>) list;
                for (AVObject todo : list) {
                    todo.put("bookName",newName );
                }
                AVObject.saveAllInBackground(todos, new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e != null) {
                            // 出现错误
                        } else {
                            // 保存成功
                        }
                    }
                });
            }
        });
    }
    public void synchronizeUserData(AVUser user){

    }
    public void synchronizeBookData(){

    }
    public void synchronizeAccountData(){

    }
}
