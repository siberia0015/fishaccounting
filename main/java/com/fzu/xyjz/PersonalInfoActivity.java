package com.fzu.xyjz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        final AVUser user=AVUser.getCurrentUser();

        TextView Nickname=(TextView)findViewById(R.id.nickname);
        TextView sex=(TextView)findViewById(R.id.sex);
        TextView city=(TextView)findViewById(R.id.city);
        final TextView birthday=(TextView)findViewById(R.id.birthday);
        TextView email=(TextView)findViewById(R.id.email);
        TextView isVIP=(TextView)findViewById(R.id.isVIP);

        Button goBack=(Button)findViewById(R.id.goBack2Second);
        Button changeInfo=(Button)findViewById(R.id.changeInfo);
        Button toBook=(Button)findViewById(R.id.toBook);
        Button becomeVIP=(Button)findViewById(R.id.becomeVIP);

        Nickname.setText(user.getString("nickname"));
        sex.setText(user.getString("sex"));
        city.setText(user.getString("city"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        if(user.getDate("birthday")!=null) {
            birthday.setText(simpleDateFormat.format(user.getDate("birthday")));
        }
        email.setText(user.getString("email"));
        Date date=new Date();
        if(user.getDate("VIP_EndDate")!=null) {
            if (user.getDate("VIP_EndDate").getTime() < date.getTime()) {
                user.put("isVIP", false);
                user.saveInBackground();
            }
        }
        if(user.getBoolean("isVIP")==true){
            isVIP.setText("是");
        }else{
            isVIP.setText("否");
        }

        changeInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PersonalInfoActivity.this.finish();
                Intent intent=new Intent(PersonalInfoActivity.this, ChangeInfoActivity.class);
                startActivity(intent);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PersonalInfoActivity.this.finish();
                Intent intent=new Intent(PersonalInfoActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        toBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PersonalInfoActivity.this.finish();
                Intent intent=new Intent(PersonalInfoActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });
        becomeVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalInfoActivity.this.finish();
                Intent intent=new Intent(PersonalInfoActivity.this, becomeVIPActivity.class);
                startActivity(intent);
            }
        });
    }

}
