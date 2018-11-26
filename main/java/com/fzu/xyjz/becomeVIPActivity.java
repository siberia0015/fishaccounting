package com.fzu.xyjz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class becomeVIPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_vip);
        final AVUser user=AVUser.getCurrentUser();
        Button buyMonth=(Button)findViewById(R.id.buyMonth);
        Button buySeason=(Button)findViewById(R.id.buySeason);
        Button buyForever=(Button)findViewById(R.id.buyForever);
        Button goBack=(Button)findViewById(R.id.goBack2PersonalInfo);
        TextView VIP_EndTime=(TextView)findViewById(R.id.VIP_EndTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        if(user.getDate("VIP_EndDate")!=null) {
            VIP_EndTime.setText(simpleDateFormat.format(user.getDate("VIP_EndDate")));
        }else{
            VIP_EndTime.setText("非会员");
        }
        Calendar calendar = Calendar.getInstance();
//未完成
        buyMonth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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
        });
        buySeason.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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
        });
        buyForever.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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
        });
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(becomeVIPActivity.this, PersonalInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
