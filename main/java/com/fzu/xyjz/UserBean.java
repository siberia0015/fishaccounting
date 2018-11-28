package com.fzu.xyjz;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 厦门科长 on 2018/11/28.
 */

public class UserBean extends AVUser {
    public void UserBean(){

    }
    public void combineRecordCloud(final ArrayList<Integer> book_id, final String newName){
        /*实现方法：把目标账簿中所有账目的账簿ID改成第一个账簿的账簿ID*/
        for(int i=1;i<book_id.size();i++){
            /*account账目表，book_id账目所属账簿id*/
            /*db.execSQL("update Account set book_id = " + book_id.indexOf(0) + "where book_id =" + book_id.indexOf(i));*/
            AVQuery<AVObject> query1=new AVQuery<>("Account");
            query1.whereEqualTo("bookid",book_id.get(i));
            query1.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    ArrayList<AVObject> todos = (ArrayList<AVObject>) list;
                    for (AVObject todo : list) {
                        todo.put("bookid",book_id.get(0) );
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
            query2.whereEqualTo("bookId",book_id.get(i));
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
        query3.whereEqualTo("bookId",book_id.get(0));
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
}
