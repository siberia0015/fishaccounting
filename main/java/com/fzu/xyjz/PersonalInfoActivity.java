package com.fzu.xyjz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;

import org.w3c.dom.Text;

public class PersonalInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        AVUser user=AVUser.getCurrentUser();
        TextView Nickname=(TextView)findViewById(R.id.nickname);
        TextView sex=(TextView)findViewById(R.id.sex);
        TextView city=(TextView)findViewById(R.id.city);
        TextView birthday=(TextView)findViewById(R.id.birthday);
        Button goBack=(Button)findViewById(R.id.goBack2Second);
        Button changeInfo=(Button)findViewById(R.id.changeInfo);
        Nickname.setText(user.getString("nickname"));
        sex.setText(user.getString("sex"));
        city.setText(user.getString("city"));
        birthday.setText(user.getString("birthday"));

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
    }

}
