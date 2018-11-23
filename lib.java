package com.fzu.xyjz;

import android.content.Intent;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SaveCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by 厦门科长 on 2018/11/28.
 */

public class lib {
    static public void lib(){

    }
    //登陆
    static public void login(final String phone,final String password){
        AVUser.loginByMobilePhoneNumberInBackground(phone, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {

                } else {

                }
            }
        });

    }
    //注册
    static public void register(final String phone,final String verificationCode,final String password){
        AVUser.signUpOrLoginByMobilePhoneInBackground(phone, verificationCode, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                // 如果 e 为空就可以表示登录成功了，并且 user 是一个全新的用户
                avUser.setPassword(password);
                avUser.saveInBackground();
            }
        });
    }
    //获取验证码（注册）
    static public void getVerificationCode(final String phone){
            AVOSCloud.requestSMSCodeInBackground(phone, new RequestMobileCodeCallback() {
                @Override
                public void done(AVException e) {
                    // 发送失败可以查看 e 里面提供的信息
                    if(e==null) {

                    }else{

                    }
                }
            });
    }
    //更改信息
    static public void changeInfo(final AVUser user, final String nickname, final String sex, final String city, final String email, final String birthday){
        user.put("nickname",nickname);
        user.put("sex",sex);
        user.put("city",city);
        user.put("email",email);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            user.put("birthday",simpleDateFormat.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.saveInBackground();
    }
    static public void changeInfo(final AVUser user, final String nickname, final String sex, final String city, final String email, final String birthday, final AVFile u_pic){
        user.put("nickname",nickname);
        user.put("sex",sex);
        user.put("city",city);
        user.put("email",email);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            user.put("birthday",simpleDateFormat.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.put("u_pic",u_pic);
        user.saveInBackground();
    }
    //包月会员
    static public void buyMonth(final AVUser user){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = null;
        if(user.getBoolean("isVIP")==false) {
            date = new Date();//取时间
        }else{
            date = user.getDate("VIP_EndDate");
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);//把日期往后增加一天.正数往后推,负数往前推
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        user.put("VIP_EndDate", date);
        user.put("isVIP",true);
        user.saveInBackground();
    }
    //包季会员
    static public void buySeason(final AVUser user){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = null;
        if(user.getBoolean("isVIP")==false) {
            date = new Date();//取时间
        }else{
            date = user.getDate("VIP_EndDate");
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 3);//把日期往后增加一天.正数往后推,负数往前推
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        user.put("VIP_EndDate", date);
        user.put("isVIP",true);
        user.saveInBackground();
    }
    //永久会员
    static public void buyForever(final AVUser user){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = null;
        if(user.getBoolean("isVIP")==false) {
            date = new Date();//取时间
        }else{
            date = user.getDate("VIP_EndDate");
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 100);//把日期往后增加一天.正数往后推,负数往前推
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        user.put("VIP_EndDate", date);
        user.put("isVIP",true);
        user.saveInBackground();
    }
    //设置提醒
    static public void setRemindCloud(final AVUser user,final String remindTime){
        user.put("remind",true);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            user.put("remindTime",simpleDateFormat.parse(remindTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.saveInBackground();
    }
    //取消提醒
    static public void unsetRemindCloud(final AVUser user){
        user.put("remind",false);
        user.saveInBackground();
    }

    //创建账簿
    static public void createBookCloud(final String userID, final String bookName,final double budget ) {
        /*实现方法：创建新账簿*/
        AVObject todo = new AVObject("Book");
        todo.put("bookName", bookName);
        todo.put("budget", budget);
        todo.put("owner", userID);
        todo.saveInBackground();// 保存到服务端
    }
    static public void createBookCloud(final String userID, final String bookName,final double budget,final Date begin,final Date end ) {
        /*实现方法：创建新账簿*/
        AVObject todo = new AVObject("Book");
        todo.put("bookName", bookName);
        todo.put("budget", budget);
        todo.put("owner", userID);
        todo.put("begin",begin);
        todo.put("end",end);
        todo.saveInBackground();// 保存到服务端
    }
    //修改账簿
    static public void updateBookCloud(final int bookID, final String bookName,final double budget ) {
        /*实现方法：修改账簿信息*/
        AVQuery<AVObject> query = new AVQuery<>("Book");// 构建对象
        query.whereEqualTo("bookid",bookID);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for (AVObject todo : list) {
                    todo.put("bookName", bookName);
                    todo.put("budget", budget);
                    todo.saveInBackground();// 保存到服务端
                }
            }
        });
    }
    static public void updateBookCloud(final int bookID, final String bookName, final double budget , final Date begin, final Date end) {
        /*实现方法：修改账簿信息*/
        AVQuery<AVObject> query = new AVQuery<>("Book");// 构建对象
        query.whereEqualTo("bookid",bookID);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for (AVObject todo : list) {
                    todo.put("bookName", bookName);
                    todo.put("budget", budget);
                    todo.put("begin",begin);
                    todo.put("end",end);
                    todo.saveInBackground();// 保存到服务端
                }
            }
        });
    }
    //删除账簿
    static public void deleteBookCloud(final ArrayList<Integer> book_id){
        for(int i=0;i<book_id.size();i++) {
            /*account账目表，book_id账目所属账簿id*/
            AVQuery<AVObject> query1 = new AVQuery<>("Account");
            query1.whereEqualTo("bookid", book_id.get(i));
            query1.deleteAllInBackground(new DeleteCallback() {
                @Override
                public void done(AVException e) {
                    if (e != null) {
                        // 出现错误
                    } else {
                        // 保存成功
                    }
                }
            });
            AVQuery<AVObject> query2 = new AVQuery<>("Book");
            query2.whereEqualTo("bookId", book_id.get(i));
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
    }
    //合并账簿
    static public void combineBookCloud(final ArrayList<Integer> book_id, final String newName){
        /*实现方法：把目标账簿中所有账目的账簿ID改成第一个账簿的账簿ID*/
        for(int i=1;i<book_id.size();i++){
            /*account账目表，book_id账目所属账簿id*/
            AVQuery<AVObject> query1=new AVQuery<>("Account");
            query1.whereEqualTo("bookid",book_id.get(i));
            query1.findInBackground(new FindCallback<AVObject>() {
                @Override
                public void done(List<AVObject> list, AVException e) {
                    if (e != null) {
                        // 出现错误
                    } else {
                        // 保存成功
                        for (AVObject todo : list) {
                            todo.put("bookid",book_id.get(0) );
                            todo.saveInBackground();
                        }
                    }
                }
            });
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
        AVQuery<AVObject> query3=new AVQuery<>("Book");
        query3.whereEqualTo("bookId",book_id.get(0));
        query3.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e != null) {
                    // 出现错误
                } else {
                    // 保存成功
                    for (AVObject todo : list) {
                        todo.put("bookName",newName );
                        todo.saveInBackground();
                    }
                }

            }
        });
    }

    //添加账目
    static public void createAccountCloud(final double bookID,final boolean inout,final double cost, final String remark,final double typeID ) {
        /*实现方法：添加新账目*/
        AVObject todo = new AVObject("Account");
        todo.put("bookid", bookID);
        todo.put("inout", inout);
        todo.put("cost", cost);
        todo.put("remark",remark);
        todo.put("typeID", typeID);
        todo.saveInBackground();// 保存到服务端
    }
    //删除账目
    static public void deleteAccountCloud(final ArrayList<Integer> account_id){
        for(int i=0;i<account_id.size();i++) {
            /*account账目表，book_id账目所属账簿id*/
            AVQuery<AVObject> query1 = new AVQuery<>("Account");
            query1.whereEqualTo("accountid", account_id.get(i));
            query1.deleteAllInBackground(new DeleteCallback() {
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
    }
    //修改账目
    static public void updateBookCloud(final double accountID ,final boolean inout,final double cost, final String remark,final double typeID) {
        /*实现方法：修改账目信息*/
        AVQuery<AVObject> query = new AVQuery<>("Book");// 构建对象
        query.whereEqualTo("accountid",accountID);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                for (AVObject todo : list) {
                    todo.put("inout", inout);
                    todo.put("cost", cost);
                    todo.put("remark",remark);
                    todo.put("typeID", typeID);
                    todo.saveInBackground();// 保存到服务端
                }
            }
        });
    }
}
